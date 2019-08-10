package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection.NoteSelectionPresenter

class NoteSelectionInitialCS(override val presenter: NoteSelectionPresenter) : CSRoot(presenter) {

    private val CREATE_TASK_LIST = CreateTaskListCS(presenter)

    private val OPEN_NOTE = OpenNoteCS(presenter)

    override val availableCommands: Array<BaseCommandState> = arrayOf(CREATE_TASK_LIST, OPEN_NOTE)
}