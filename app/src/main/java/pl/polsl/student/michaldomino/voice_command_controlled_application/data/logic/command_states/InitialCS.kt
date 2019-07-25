package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter

class InitialCS(override val presenter: BasePresenter) : BaseCommandState(presenter) {
    override val messageToSpeak: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val commandName: String?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun processInput(userInput: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}