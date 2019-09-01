package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSLeaf
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list.model.TaskListCommandsModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list.TaskListPresenter

class AvailableCommandsCS(override val presenter: TaskListPresenter) : CSLeaf(presenter) {

    private val ITEM_DELIMITER = ", "

    override val commandNameId: Int? = TaskListCommandsModel.AVAILABLE_COMMANDS_COMMAND

    override fun initialize() {
        val availableCommands: MutableList<Int> = TaskListCommandsModel.availableCommands
        val messageBuilder = StringBuilder().append(presenter.getString(R.string.available_commands_are))
        availableCommands.forEach { messageBuilder.append(presenter.getString(it)).append(ITEM_DELIMITER) }
        presenter.speak(messageBuilder.toString())
    }

    override fun processInput(userInput: String) {}
}