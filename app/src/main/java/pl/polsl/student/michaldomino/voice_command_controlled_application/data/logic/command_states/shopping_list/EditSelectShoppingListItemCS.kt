package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSDynamicNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListPresenter

class EditSelectShoppingListItemCS(override val presenter: ShoppingListPresenter) : CSDynamicNode(presenter) {
    override val messageToSpeak: String = presenter.getString(R.string.edit_item) // temp
    override val commandName: String? = presenter.getString(R.string.edit_item)

    override fun processInput(userInput: String) {
//        val a: ArrayList<String> = presenter.getItems()
    }
}