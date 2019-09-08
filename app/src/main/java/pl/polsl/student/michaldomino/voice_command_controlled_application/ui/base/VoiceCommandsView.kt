package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import android.os.Bundle

interface VoiceCommandsView : BaseView {

    fun startListening()

    fun onCommandRecognizerResults(bundle: Bundle)

    fun speakInForeground(message: String)

    fun onDoubleTap()

    fun onSpeechRecognizerServerError()
}