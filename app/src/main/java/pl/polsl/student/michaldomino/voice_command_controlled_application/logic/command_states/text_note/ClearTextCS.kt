package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.text_note

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSLeaf
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note.TextNotePresenter

class ClearTextCS(override val presenter: TextNotePresenter) : CSLeaf(presenter) {

    override val commandNameId: Int? = R.string.clear_text

    override fun initialize() {
        presenter.clearText()
    }
}