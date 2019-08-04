package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.list_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot

class ListSelectionPresenter(override val view: ListSelectionContract.View) : ListSelectionContract.Presenter(view) {

    override val initialState: CSRoot
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override var currentState: BaseCommandState
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun askForInput(messageId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}