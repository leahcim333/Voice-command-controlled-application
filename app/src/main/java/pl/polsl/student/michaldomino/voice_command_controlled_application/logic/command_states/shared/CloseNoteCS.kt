package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.shared

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSLeaf
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.NotePresenter

class CloseNoteCS(override val presenter: NotePresenter) : CSLeaf(presenter) {

    override val commandNameId: Int? = R.string.close_note

    override fun initialize() {
        presenter.closeNote()
    }
}