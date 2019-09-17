package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.Word
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection.NoteSelectionPresenter

class RenameNoteSelectCS(override val presenter: NoteSelectionPresenter) : CSNode(presenter) {

    override val messageToSpeakId: Int = R.string.select_note

    override val commandNameId: Int? = R.string.rename_note

    override fun processInput(userInput: String) {
        val existingNotes = presenter.getItems()
        val mostSimilarNote = Word(userInput).getMostSimilar(existingNotes, { it.name })
        if (mostSimilarNote != null) {
            val nextState = RenameNoteChangeCS(presenter, mostSimilarNote)
            presenter.currentState = nextState
            nextState.initialize()
        } else {
            presenter.speak(presenter.getString(R.string.note_does_not_exist))
        }
    }
}