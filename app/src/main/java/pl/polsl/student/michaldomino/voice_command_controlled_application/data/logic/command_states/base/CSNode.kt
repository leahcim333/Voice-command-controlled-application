package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter

abstract class CSNode(override val presenter: BasePresenter) : BaseCommandState(presenter) {

    protected abstract val messageToSpeak: String

    abstract fun processInput(userInput: String)

    override fun initialize() {
        presenter.askForInput(messageToSpeak)
    }
}