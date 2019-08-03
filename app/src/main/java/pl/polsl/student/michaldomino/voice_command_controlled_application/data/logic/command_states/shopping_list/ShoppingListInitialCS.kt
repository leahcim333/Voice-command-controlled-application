package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.Word
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListPresenter

class ShoppingListInitialCS(override val presenter: ShoppingListPresenter) : CSRoot(presenter) {

    private val commandsModel = CommandsModel(presenter)

    private val ADD_ITEMS = AddItemsCS(presenter)

    private val EDIT_ITEM_SELECT = EditItemSelectCS(presenter)

    private val LIST_ITEMS = ListItemsCS(presenter)

    override val messageToSpeak: String = presenter.getString(R.string.tell_command)

    override val availableCommands: Array<BaseCommandState> = arrayOf(ADD_ITEMS, EDIT_ITEM_SELECT, LIST_ITEMS)

    override fun processInput(userInput: String) {
        val command = Word(userInput)
        val matchingCommandState: BaseCommandState? =
            availableCommands.firstOrNull { command.similarTo(it.commandName!!) }
        if (matchingCommandState != null) {
            presenter.currentState = matchingCommandState
            matchingCommandState.initialize()
        } else {
            // command unrecognized
        }
    }
}