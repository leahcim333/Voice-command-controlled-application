package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenter

abstract class BaseCommandState(protected open val presenter: VoiceCommandsPresenter) {

    abstract val commandNameId: Int?

    abstract fun initialize()

    abstract fun processInput(userInput: String)
}