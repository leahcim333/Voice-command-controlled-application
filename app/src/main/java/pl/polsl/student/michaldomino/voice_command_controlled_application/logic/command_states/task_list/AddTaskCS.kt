package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list.TaskListPresenter

class AddTaskCS(override val presenter: TaskListPresenter) : CSNode(presenter) {

    override val messageToSpeakId: Int = R.string.list_tasks

    override val commandNameId: Int? = R.string.add_tasks

    override fun processInput(userInput: String) {
        presenter.addItems(userInput)
    }
}
