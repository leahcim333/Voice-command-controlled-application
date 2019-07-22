package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.Command
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter

class ShoppingListInitialCS(override val presenter: BasePresenter) : BaseCommandState(presenter) {

    override fun performCommand(userInput: String) {
        val command = Command(userInput)
        when {
            command.similarTo("abc") -> {

            }
            command.similarTo("bcd") -> {

            }
            command.similarTo("xyz") -> {

            }
            else -> {

            }
        }
    }
}