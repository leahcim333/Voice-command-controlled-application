package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.note

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note.NotePresenter

class NoteInitialCS(override val presenter: NotePresenter) : CSRoot(presenter) {

    val ADD_TEXT = AddTextCS(presenter)

    override val availableCommands: Array<BaseCommandState> = arrayOf(ADD_TEXT)
}