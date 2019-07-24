package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.model.base

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter

abstract class BaseCommandState(protected open val presenter: BasePresenter) {

//    protected open val presenter : BasePresenter = _presenter

//    protected abstract val responseMap: Map<Int, Unit>

    abstract fun processInput(userInput: String)


}