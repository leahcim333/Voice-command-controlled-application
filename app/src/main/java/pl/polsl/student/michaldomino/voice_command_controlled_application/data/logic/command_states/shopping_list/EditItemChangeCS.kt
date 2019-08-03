package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSDynamicNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list.RowItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListPresenter

class EditItemChangeCS(override val presenter: ShoppingListPresenter, private val selectedItem: RowItem) :
    CSDynamicNode(presenter) {

    override val messageToSpeakId: Int = R.string.tell_new_name

    override val commandName: String? = null

    override fun processInput(userInput: String) {
        presenter.setNewItemName(selectedItem, userInput)
    }
}