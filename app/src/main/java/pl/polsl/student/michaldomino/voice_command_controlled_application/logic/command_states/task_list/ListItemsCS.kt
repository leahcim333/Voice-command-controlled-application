package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSLeaf
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list.TaskListPresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.task_list.TaskListItem

class ListItemsCS(override val presenter: TaskListPresenter) : CSLeaf(presenter) {

    private val ITEM_DELIMITER = ", "

    override val commandNameId: Int? = R.string.list_items

    override fun initialize() {
        val itmes: MutableList<TaskListItem> = presenter.getItems()
        val messageBuilder = StringBuilder()
        itmes.forEach { messageBuilder.append(it.text).append(ITEM_DELIMITER) }
        presenter.speak(messageBuilder.toString())
    }

    override fun processInput(userInput: String) {}
}