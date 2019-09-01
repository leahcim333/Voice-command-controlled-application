package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list.model

import pl.polsl.student.michaldomino.voice_command_controlled_application.R

object TaskListCommandsModel {

    val ADD_ITEMS_COMMAND = R.string.add_items

    val AVAILABLE_COMMANDS_COMMAND = R.string.available_commands

    val EDIT_ITEM_COMMAND = R.string.edit_item

    val LIST_ITEMS_COMMAND = R.string.list_items

    val availableCommands = mutableListOf(
        ADD_ITEMS_COMMAND,
        EDIT_ITEM_COMMAND,
        LIST_ITEMS_COMMAND
    )
}
