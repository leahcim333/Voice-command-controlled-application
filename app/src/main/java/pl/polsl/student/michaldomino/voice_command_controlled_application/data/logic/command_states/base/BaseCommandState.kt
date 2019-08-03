package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter

abstract class BaseCommandState(protected open val presenter: BasePresenter) {

    abstract val commandName: String?

    abstract fun initialize()
}