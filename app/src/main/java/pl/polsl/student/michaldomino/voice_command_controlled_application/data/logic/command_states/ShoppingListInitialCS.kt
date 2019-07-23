package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.Command
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListPresenter

class ShoppingListInitialCS(override val presenter: ShoppingListPresenter) : BaseCommandState(presenter) {

    override fun performCommand(userInput: String) {
        val command = Command(userInput)
        when {
            isAddElement(command) -> {
                presenter.currentState = AddShoppingListElementCS(presenter)
            }
            isEditElement(command) -> {

            }
            else -> {

            }
        }
    }

    private fun isAddElement(command: Command): Boolean {
        return isSimilar(command, R.string.add_elements)
    }

    private fun isEditElement(command: Command): Boolean {
        return isSimilar(command, R.string.edit_element)
    }

    private fun isSimilar(command: Command, resId: Int): Boolean {
        val stringCommand = presenter.getString(resId)
        return command.similarTo(stringCommand)
    }
}