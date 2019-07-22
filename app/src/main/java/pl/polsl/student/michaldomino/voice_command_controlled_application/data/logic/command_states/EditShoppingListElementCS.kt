package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter

class EditShoppingListElementCS(override val presenter: BasePresenter) : BaseCommandState(presenter) {
    override val responseMap: Map<Int, Unit>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun performCommand(command: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setState(newState: BaseCommandState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}