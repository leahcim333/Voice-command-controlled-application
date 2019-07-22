package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter

abstract class BaseCommandState(open val presenter: BasePresenter) {

//    protected open val presenter : BasePresenter = _presenter

    protected abstract val responseMap: Map<Int, Unit>

    abstract fun performCommand(command: String?)

    fun setState(newState: BaseCommandState) {


    }
}