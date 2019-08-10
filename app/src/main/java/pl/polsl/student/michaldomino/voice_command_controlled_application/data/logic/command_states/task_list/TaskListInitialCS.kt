package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.task_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list.TaskListPresenter

class TaskListInitialCS(override val presenter: TaskListPresenter) : CSRoot(presenter) {

    private val ADD_ITEMS = AddItemsCS(presenter)

    private val AVAILABLE_COMMANDS = AvailableCommandsCS(presenter)

    private val EDIT_ITEM_SELECT = EditItemSelectCS(presenter)

    private val LIST_ITEMS = ListItemsCS(presenter)

    override val availableCommands: Array<BaseCommandState> = arrayOf(ADD_ITEMS, AVAILABLE_COMMANDS, EDIT_ITEM_SELECT, LIST_ITEMS)
}