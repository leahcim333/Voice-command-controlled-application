package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.shared

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSLeaf
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.NotePresenter

class DiscardChangesCS(override val presenter: NotePresenter) : CSLeaf(presenter) {

    override val commandNameId: Int? = R.string.discard_changes

    override fun initialize() {
        presenter.discardChanges()
    }
}