package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSLeaf
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list.RowItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListPresenter

class EditChangeShoppingListItemCS(override val presenter: ShoppingListPresenter, private val selectedItem: RowItem) :
    CSLeaf(presenter) {

    override val messageToSpeak: String = presenter.getString(R.string.tell_new_name)

    override val commandName: String? = null

    override fun processInput(userInput: String) {
        presenter.setNewItemName(selectedItem, userInput)
    }
}