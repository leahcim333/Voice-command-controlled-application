package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.layout_managers.note_selection.NoteSelectionItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection.NoteSelectionPresenter

class RenameNoteChangeCS(
    override val presenter: NoteSelectionPresenter,
    private val selectedNote: NoteSelectionItem
) : CSNode(presenter) {

    override val messageToSpeakId: Int = R.string.tell_new_name

    override val commandNameId: Int? = null

    override fun processInput(userInput: String) {
        presenter.renameNote(selectedNote, userInput)
    }
}