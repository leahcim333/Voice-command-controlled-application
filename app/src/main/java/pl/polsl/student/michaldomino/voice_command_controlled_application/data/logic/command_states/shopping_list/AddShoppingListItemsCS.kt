package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSLeaf
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListPresenter

class AddShoppingListItemsCS(override val presenter: ShoppingListPresenter) : CSLeaf(presenter) {

    override val messageToSpeak: String = presenter.getString(R.string.list_items)

    override val commandName: String? = presenter.getString(R.string.add_items)

    override fun processInput(userInput: String) {
        presenter.addItems(userInput)
    }
}
