package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter

abstract class CSStaticNode(override val presenter: BasePresenter) : CSNode(presenter) {

    protected abstract val availableCommands: Array<BaseCommandState>

}