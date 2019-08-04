package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.Word
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list.model.CommandsModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list.ShoppingListItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListPresenter

class EditItemSelectCS(override val presenter: ShoppingListPresenter) : CSNode(presenter) {

    override val messageToSpeakId: Int = R.string.select_item

    override val commandNameId: Int? = CommandsModel.EDIT_ITEM_COMMAND

    override fun processInput(userInput: String) {
        val items: MutableList<ShoppingListItem> = presenter.getItems()
        val selectedItem = Word(userInput)
        val mostSimilar = items.maxBy { selectedItem.similarityWith(it.text) }
        if (mostSimilar != null && selectedItem.similarTo(mostSimilar.text)) {
            val nextState = EditItemChangeCS(presenter, mostSimilar)
            presenter.currentState = nextState
            nextState.initialize()
        } else {
            // unrecognized
        }
    }
}