package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.Word
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list.TaskListPresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.task_list.TaskListItem

class EditItemSelectCS(override val presenter: TaskListPresenter) : CSNode(presenter) {

    override val messageToSpeakId: Int = R.string.select_item

    override val commandNameId: Int? = R.string.edit_item

    override fun processInput(userInput: String) {
        val items: List<TaskListItem> = presenter.getItems()
        val mostSimilar = Word(userInput).getMostSimilar(items, { it.text })
        if (mostSimilar != null) {
            val nextState = EditItemChangeCS(presenter, mostSimilar)
            presenter.currentState = nextState
            nextState.initialize()
        } else {
            // TODO: unrecognized
        }
    }
}