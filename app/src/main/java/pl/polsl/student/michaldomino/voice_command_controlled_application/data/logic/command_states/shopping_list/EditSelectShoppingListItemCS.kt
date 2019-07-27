package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.Word
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSDynamicNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list.RowItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListPresenter

class EditSelectShoppingListItemCS(override val presenter: ShoppingListPresenter) : CSDynamicNode(presenter) {
    override val messageToSpeak: String = presenter.getString(R.string.select_item)
    override val commandName: String? = presenter.getString(R.string.edit_item)

    override fun processInput(userInput: String) {
        val items: MutableList<RowItem> = presenter.getItems()
        val selectedItem = Word(userInput)
        val mostSimilar = items.maxBy { selectedItem.similarityWith(it.text) }
        if (mostSimilar != null && selectedItem.similarTo(mostSimilar.text)) {
            val nextState = EditChangeShoppingListItemCS(presenter, mostSimilar)
            presenter.currentState = nextState
            nextState.initialize()
        } else {
            // unrecognized
        }
    }
}