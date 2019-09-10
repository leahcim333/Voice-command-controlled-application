package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list.model

import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.AvailableCommandsCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandStateModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list.AddTaskCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list.EditTaskSelectCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list.ListTaskCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list.SaveChangesCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list.TaskListPresenter

class TaskListCommandStatesModel(override val presenter: TaskListPresenter) :
    BaseCommandStateModel(presenter) {

    private val ADD_TASKS = AddTaskCS(presenter)

    private val AVAILABLE_COMMANDS = AvailableCommandsCS(presenter, this)

    private val EDIT_TASK_SELECT = EditTaskSelectCS(presenter)

    private val LIST_TASKS = ListTaskCS(presenter)

    private val SAVE_CHANGES = SaveChangesCS(presenter)

    override val availableCommandStates: List<BaseCommandState> = listOf(
        ADD_TASKS,
        AVAILABLE_COMMANDS,
        EDIT_TASK_SELECT,
        LIST_TASKS,
        SAVE_CHANGES
    )
}