package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSLeaf
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection.NoteSelectionPresenter

class ListTextNotesCS(override val presenter: NoteSelectionPresenter) : CSLeaf(presenter) {

    override val commandNameId: Int? = R.string.list_text_notes

    override fun initialize() {
        presenter.listTextNotes()
    }
}