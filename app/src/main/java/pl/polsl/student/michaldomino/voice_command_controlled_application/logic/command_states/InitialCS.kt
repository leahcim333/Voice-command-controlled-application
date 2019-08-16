package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states

import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenter

class InitialCS(override val presenter: VoiceCommandsPresenter) : BaseCommandState(presenter) {
    override fun initialize() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val commandNameId: Int?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun processInput(userInput: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}