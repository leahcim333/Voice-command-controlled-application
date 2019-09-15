package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list.model

import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.AvailableCommandsCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandStateModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list.TaskListPresenter

class TaskListCommandStatesModel(override val presenter: TaskListPresenter) :
    BaseCommandStateModel(presenter) {

    private val AVAILABLE_COMMANDS = AvailableCommandsCS(presenter, this)

    private val ADD_ITEMS = AddItemCS(presenter)

    private val DELETE_ITEM = DeleteItemCS(presenter)

    private val EDIT_ITEM = EditItemSelectCS(presenter)

    private val LIST_ITEMS = ListTaskCS(presenter)

    private val CHECK_ITEMS = CheckItemsCS(presenter)

    private val SAVE_CHANGES = SaveChangesCS(presenter)

    private val DISCARD_CHANGES = DiscardChangesCS(presenter)

    private val CLEAR = ClearCS(presenter)

    override val availableCommandStates: List<BaseCommandState> = listOf(
        AVAILABLE_COMMANDS,
        ADD_ITEMS,
        EDIT_ITEM,
        DELETE_ITEM,
        LIST_ITEMS,
        CHECK_ITEMS,
        SAVE_CHANGES,
        DISCARD_CHANGES,
        CLEAR
    )
}