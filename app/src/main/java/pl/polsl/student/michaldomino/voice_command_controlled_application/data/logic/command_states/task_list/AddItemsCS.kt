package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.task_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.task_list.model.TaskListCommandsModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list.TaskListPresenter

class AddItemsCS(override val presenter: TaskListPresenter) : CSNode(presenter) {

    override val messageToSpeakId: Int = R.string.list_items

    override val commandNameId: Int? = TaskListCommandsModel.ADD_ITEMS_COMMAND

    override fun processInput(userInput: String) {
        presenter.addItems(userInput)
    }
}
