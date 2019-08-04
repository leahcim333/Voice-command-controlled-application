package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.note_selection.model.CommandsModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection.NoteSelectionPresenter

class CreateShoppingListCS(override val presenter: NoteSelectionPresenter) : CSNode(presenter) {

    override val messageToSpeakId: Int = R.string.tell_name_of_new_list

    override val commandNameId: Int? = CommandsModel.CREATE_SHOPPING_LIST_COMMAND

    override fun processInput(userInput: String) {
        presenter.addShoppingList(userInput)
    }
}