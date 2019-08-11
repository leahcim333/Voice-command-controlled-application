package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.task_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.Word
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.task_list.model.TaskListCommandsModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.view_model.task_list.TaskListItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list.TaskListPresenter

class EditItemSelectCS(override val presenter: TaskListPresenter) : CSNode(presenter) {

    override val messageToSpeakId: Int = R.string.select_item

    override val commandNameId: Int? = TaskListCommandsModel.EDIT_ITEM_COMMAND

    override fun processInput(userInput: String) {
        val items: MutableList<TaskListItem> = presenter.getItems()
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