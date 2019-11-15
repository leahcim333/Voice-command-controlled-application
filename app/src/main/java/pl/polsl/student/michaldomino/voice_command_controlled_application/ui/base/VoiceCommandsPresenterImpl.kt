package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import android.os.Bundle
import android.speech.SpeechRecognizer
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSRoot

abstract class VoiceCommandsPresenterImpl(protected open val view: VoiceCommandsView) :
    VoiceCommandsPresenter {

    protected abstract val initialState: CSRoot

    override fun askForInput(messageId: Int) {

        if (view.isRecordAudioGranted()){
            val message: String = view.getString(messageId)
            view.speakAndRunAction(message) { view.startListening() }

        } else {
            speak(getString(R.string.record_audio_permission_request))
            view.requestPermission()
        }
    }

    override fun processInput(bundle: Bundle) {
        val possibleMatches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        if (possibleMatches != null) {
            val userInput = possibleMatches[0]
            currentState.processInput(userInput)
        }
    }

    override fun onDoubleTap() {
        view.stopActivityActions()
        currentState = initialState
        currentState.initialize()
    }

    override fun getString(resId: Int): String {
        return view.getString(resId)
    }

    override fun handleServerError() {
        speak(getString(R.string.turn_on_internet_connection))
    }

    override fun speak(message: String) {
        view.speakAndRunAction(message) {}
    }

    override fun closeApplication() {
        view.finishAffinity()
    }

    override fun onPermissionGranted() {}

    override fun onPermissionDenied() {
        speak(getString(R.string.record_audio_permission_denied))
    }
}