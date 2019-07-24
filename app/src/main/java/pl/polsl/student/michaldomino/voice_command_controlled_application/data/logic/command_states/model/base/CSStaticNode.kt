package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.model.base

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter

abstract class CSStaticNode(override val presenter: BasePresenter) : BaseCommandState(presenter) {

    abstract fun processInput(userInput: String)
}