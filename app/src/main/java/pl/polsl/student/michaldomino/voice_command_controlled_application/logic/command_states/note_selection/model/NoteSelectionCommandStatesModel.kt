package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection.model

import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandStateModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection.CreateTaskListCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection.CreateTextNoteCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection.OpenNoteCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection.NoteSelectionPresenter

class NoteSelectionCommandStatesModel(override val presenter: NoteSelectionPresenter) :
    BaseCommandStateModel(presenter) {

    private val CREATE_TASK_LIST = CreateTaskListCS(presenter)

    private val CREATE_TEXT_NOTE = CreateTextNoteCS(presenter)

    private val OPEN_NOTE = OpenNoteCS(presenter)

    override val availableCommandStates: Array<BaseCommandState> = arrayOf(
        CREATE_TASK_LIST,
        CREATE_TEXT_NOTE,
        OPEN_NOTE
    )

}


