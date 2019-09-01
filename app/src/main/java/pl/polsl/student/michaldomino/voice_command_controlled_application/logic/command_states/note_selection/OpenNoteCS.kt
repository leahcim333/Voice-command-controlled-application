package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection.model.NoteSelectionCommandsModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection.NoteSelectionPresenter

class OpenNoteCS(override val presenter: NoteSelectionPresenter) : CSNode(presenter) {

    override val commandNameId: Int? = NoteSelectionCommandsModel.OPEN_NOTE

    override val messageToSpeakId: Int = R.string.tell_note_name

    override fun initialize() {
        presenter.askForInput(messageToSpeakId)
    }

    override fun processInput(userInput: String) {
        presenter.openNote(userInput)
    }
}