package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection.model

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandsModel

object NoteSelectionCommandsModel : BaseCommandsModel() {

    val CREATE_TASK_LIST_COMMAND: Int = R.string.create_task_list

    val CREATE_TEXT_NOTE_COMMAND: Int = R.string.create_text_note

    val OPEN_NOTE_COMMAND: Int = R.string.open_note


    override val availableCommands = mutableListOf(
        CREATE_TASK_LIST_COMMAND,
        CREATE_TEXT_NOTE_COMMAND,
        OPEN_NOTE_COMMAND
    )
}