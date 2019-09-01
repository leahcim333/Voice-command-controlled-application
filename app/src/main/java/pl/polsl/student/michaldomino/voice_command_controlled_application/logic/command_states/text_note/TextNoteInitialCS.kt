package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.text_note

import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note.TextNotePresenter

class TextNoteInitialCS(override val presenter: TextNotePresenter) : CSRoot(presenter) {

    val ADD_TEXT = AddTextCS(presenter)

    override val availableCommands: Array<BaseCommandState> = arrayOf(ADD_TEXT)
}