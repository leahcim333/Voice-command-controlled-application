package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandStateModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection.NoteSelectionPresenter

class NoteSelectionInitialCS(override val presenter: NoteSelectionPresenter) : CSRoot(presenter) {
    override val model: BaseCommandStateModel
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    private val CREATE_TASK_LIST = CreateTaskListCS(presenter)

    private val CREATE_TEXT_NOTE = CreateTextNoteCS(presenter)

    private val OPEN_NOTE = OpenNoteCS(presenter)

//    override val availableCommands: Array<BaseCommandState> =
//        arrayOf(CREATE_TASK_LIST, CREATE_TEXT_NOTE, OPEN_NOTE)
}