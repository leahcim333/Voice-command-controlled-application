package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import android.os.Bundle
import android.speech.SpeechRecognizer
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot

abstract class BasePresenter(protected open val view: BaseView) {

    protected abstract val initialState: CSRoot

    abstract var currentState: BaseCommandState

    abstract fun start()

    abstract fun speak(message: String)

    fun askForInput(messageId: Int) {
        val message: String = view.getString(messageId)
        speak(message)
        view.startListening()
    }

    fun processInput(bundle: Bundle) {
        val possibleMatches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        if (possibleMatches != null) {
            val userInput = possibleMatches[0]
            currentState.processInput(userInput)
        }
    }

    fun onDoubleTap() {
        currentState = initialState
        currentState.initialize()
    }

    fun getString(resId: Int): String {
        return view.getString(resId)
    }
}