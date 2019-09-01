package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenter

abstract class CSStaticNode(override val presenter: VoiceCommandsPresenter) : CSNode(presenter) {

    protected abstract val availableCommands: Array<BaseCommandState>

}