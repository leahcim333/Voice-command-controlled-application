package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenter

abstract class CSNode(override val presenter: VoiceCommandsPresenter) : BaseCommandState(presenter) {

    protected abstract val messageToSpeakId: Int

    override fun initialize() {
        presenter.askForInput(messageToSpeakId)
    }
}