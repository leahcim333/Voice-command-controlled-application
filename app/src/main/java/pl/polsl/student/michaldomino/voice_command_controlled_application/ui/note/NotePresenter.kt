package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.note.NoteInitialCS

class NotePresenter(override val view: NoteContract.View) : NoteContract.Presenter(view) {

    override val initialState: CSRoot = NoteInitialCS(this)

    override var currentState: BaseCommandState = initialState

    override fun start() {

    }

    override fun speak(message: String) {
        view.speakInForeground(message)
    }

    override fun addText(text: String) {
        view.addText(text)
    }

}