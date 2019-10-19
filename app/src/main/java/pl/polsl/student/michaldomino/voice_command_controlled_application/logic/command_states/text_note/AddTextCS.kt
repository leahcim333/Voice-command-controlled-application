package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.text_note

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note.TextNotePresenter

class AddTextCS(override val presenter: TextNotePresenter) : CSNode(presenter) {

    override val messageToSpeakId: Int = R.string.tell_text

    override val commandNameId: Int? = R.string.add_text

    override fun processInput(userInput: String) {
        presenter.addText(userInput)
    }
}