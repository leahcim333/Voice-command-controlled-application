package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import android.os.Bundle
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState

interface VoiceCommandsPresenterInterface : BasePresenter {

    var currentState: BaseCommandState

    fun speak(message: String)

    fun askForInput(messageId: Int)

    fun processInput(bundle: Bundle)

    fun onDoubleTap()

    fun getString(resId: Int): String

    fun handleServerError()
}