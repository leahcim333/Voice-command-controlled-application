package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.note

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.note.model.NoteCommandsModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note.NotePresenter

class AddTextCS(override val presenter: NotePresenter) : CSNode(presenter) {

    override val messageToSpeakId: Int = R.string.list_items // temp

    override val commandNameId: Int? = NoteCommandsModel.ADD_TEXT

    override fun processInput(userInput: String) {
        presenter.addText(userInput)
    }
}