package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.text_note.model

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandsModel

object TextNoteCommandsModel : BaseCommandsModel() {

    val ADD_TEXT = R.string.add_text

    override val availableCommands = mutableListOf(
        ADD_TEXT
    )
}