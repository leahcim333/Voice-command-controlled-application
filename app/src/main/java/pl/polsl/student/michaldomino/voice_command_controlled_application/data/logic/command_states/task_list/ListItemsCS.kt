package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.task_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSLeaf
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.task_list.model.TaskListCommandsModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.view_model.task_list.TaskListItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list.TaskListPresenter

class ListItemsCS(override val presenter: TaskListPresenter) : CSLeaf(presenter) {

    private val ITEM_DELIMITER = ", "

    override val commandNameId: Int? = TaskListCommandsModel.LIST_ITEMS_COMMAND

    override fun initialize() {
        val itmes: MutableList<TaskListItem> = presenter.getItems()
        val messageBuilder = StringBuilder()
        itmes.forEach { messageBuilder.append(it.text).append(ITEM_DELIMITER) }
        presenter.speak(messageBuilder.toString())
    }

    override fun processInput(userInput: String) {}
}