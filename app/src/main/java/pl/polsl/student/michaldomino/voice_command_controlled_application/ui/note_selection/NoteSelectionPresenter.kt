package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.note_selection.NoteSelectionInitialCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.note_selection.NoteType

class NoteSelectionPresenter(override val view: NoteSelectionContract.View) : NoteSelectionContract.Presenter(view) {

    private val REQUEST_CODE_SPEECH_RECOGNIZRER = 0

    override val initialState: CSRoot = NoteSelectionInitialCS(this)

    override var currentState: BaseCommandState = initialState

    private val speaker: Speaker = Speaker(view.getApplicationContext())

    override fun start() {

    }

    override fun askForInput(messageId: Int) {
        val message: String = view.getString(messageId)
        speaker.speakInForeground(message)
        view.startSpeechRecognizer(REQUEST_CODE_SPEECH_RECOGNIZRER, messageId)
    }

    override fun addShoppingList(userInput: String) {
        view.addNote(userInput, NoteType.SHOPPING_LIST.type)
    }

    override fun openNote(userInput: String) {

    }

    override fun speak(message: String) {
        speaker.speakInForeground(message)
    }
}