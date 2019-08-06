package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.note.NoteInitialCS

class NotePresenter(override val view: NoteContract.View) : NoteContract.Presenter(view) {

    private val REQUEST_CODE_SPEECH_RECOGNIZER = 0

    override val initialState: CSRoot = NoteInitialCS(this)

    override var currentState: BaseCommandState = initialState

    private val speaker: Speaker = Speaker(view.getApplicationContext())

    override fun start() {

    }

    override fun askForInput(messageId: Int) {
        val message: String = view.getString(messageId)
        speaker.speakInForeground(message)
        view.startSpeechRecognizer(REQUEST_CODE_SPEECH_RECOGNIZER, messageId)
    }

    override fun speak(message: String) {
        speaker.speakInForeground(message)
    }

    override fun addText(text: String) {
        view.addText(text)
    }

}