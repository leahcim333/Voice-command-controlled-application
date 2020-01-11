package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenter

abstract class BaseCommandStateModel(open val presenter: VoiceCommandsPresenter) {

    abstract val availableCommandStates: List<BaseCommandState>
}