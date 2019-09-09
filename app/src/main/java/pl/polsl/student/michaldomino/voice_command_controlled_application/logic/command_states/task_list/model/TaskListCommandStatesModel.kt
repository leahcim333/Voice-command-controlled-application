package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list.model

import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.AvailableCommandsCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandStateModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list.AddItemsCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list.EditItemSelectCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list.ListItemsCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list.TaskListPresenter

class TaskListCommandStatesModel(override val presenter: TaskListPresenter) :
    BaseCommandStateModel(presenter) {

    private val ADD_ITEMS = AddItemsCS(presenter)

    private val AVAILABLE_COMMANDS = AvailableCommandsCS(presenter, this)

    private val EDIT_ITEM_SELECT = EditItemSelectCS(presenter)

    private val LIST_ITEMS = ListItemsCS(presenter)

    override val availableCommandStates: List<BaseCommandState> = listOf(
        ADD_ITEMS,
        AVAILABLE_COMMANDS,
        EDIT_ITEM_SELECT,
        LIST_ITEMS
    )
}