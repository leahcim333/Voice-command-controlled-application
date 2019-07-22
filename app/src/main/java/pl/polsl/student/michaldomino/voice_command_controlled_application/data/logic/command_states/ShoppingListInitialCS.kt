package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter

class ShoppingListInitialCS(override val presenter: BasePresenter) : BaseCommandState(presenter) {

    override var responseMap: Map<Int, Unit> = mapOf(
        R.string.add_element to setState(AddShoppingListElementCS(presenter)),
        R.string.command_recognizer_message to performCommand("abc")
    )

    override fun setState(newState: BaseCommandState) {
        val a = newState
    }


    override fun performCommand(command: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}