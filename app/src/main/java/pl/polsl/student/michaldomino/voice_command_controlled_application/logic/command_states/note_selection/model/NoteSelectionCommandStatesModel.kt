package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection.model

import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandStateModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.shared.AvailableCommandsCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection.NoteSelectionPresenter

class NoteSelectionCommandStatesModel(override val presenter: NoteSelectionPresenter) :
    BaseCommandStateModel(presenter) {

    private val AVAILABLE_COMMANDS =
        AvailableCommandsCS(
            presenter,
            this
        )

    private val LIST_ALL_NOTES = ListAllNotesCS(presenter)

    private val LIST_TASK_LISTS = ListTaskListsCS(presenter)

    private val LIST_TEXT_NOTES = ListTextNotesCS(presenter)

    private val CREATE_TASK_LIST = CreateTaskListCS(presenter)

    private val CREATE_TEXT_NOTE = CreateTextNoteCS(presenter)

    private val OPEN_NOTE = OpenNoteCS(presenter)

    private val DELETE_NOTE = DeleteNoteCS(presenter)

    private val RENAME_NOTE = RenameNoteSelectCS(presenter)

    override val availableCommandStates: List<BaseCommandState> = listOf(
        AVAILABLE_COMMANDS,
        LIST_ALL_NOTES,
        LIST_TASK_LISTS,
        LIST_TEXT_NOTES,
        CREATE_TASK_LIST,
        CREATE_TEXT_NOTE,
        OPEN_NOTE,
        DELETE_NOTE,
        RENAME_NOTE
    )

}


