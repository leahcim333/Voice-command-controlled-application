package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list.model.ShoppingListCommandsModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListPresenter

class AddItemsCS(override val presenter: ShoppingListPresenter) : CSNode(presenter) {

    override val messageToSpeakId: Int = R.string.list_items

    override val commandNameId: Int? = ShoppingListCommandsModel.ADD_ITEMS_COMMAND

    override fun processInput(userInput: String) {
        presenter.addItems(userInput)
    }
}
