package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.shopping_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListPresenter

class CommandsModel(val presenter: ShoppingListPresenter) {

    fun getAvailableCommands(presenter: ShoppingListPresenter): List<String> {
        val commandIds = mutableListOf(R.string.add_items, R.string.edit_item, R.string.list_items)
        return commandIds.map { presenter.getString(it) }
    }
}
