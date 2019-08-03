package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSLeaf
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list.RowItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListPresenter

class ListItemsCS(override val presenter: ShoppingListPresenter) : CSLeaf(presenter) {

    private val ITEM_DELIMITER = ','

    override val commandName: String? = presenter.getString(R.string.list_items)

    override fun initialize() {
        val itmes: MutableList<RowItem> = presenter.getItems()
        val messageBuilder = StringBuilder()
        itmes.forEach { messageBuilder.append(it.text).append(ITEM_DELIMITER).append(" ") }
        presenter.speak(messageBuilder.toString())
    }

    override fun processInput(userInput: String) {}
}