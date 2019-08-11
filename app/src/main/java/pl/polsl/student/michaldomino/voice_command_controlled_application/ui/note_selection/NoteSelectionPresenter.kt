package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.note_selection.NoteSelectionInitialCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.view_model.note_selection.NoteType

class NoteSelectionPresenter(override val view: NoteSelectionContract.View) : NoteSelectionContract.Presenter(view) {

    override val initialState: CSRoot = NoteSelectionInitialCS(this)

    override var currentState: BaseCommandState = initialState

    private val speaker: Speaker = Speaker(view.getApplicationContext())

    override fun start() {

    }

    override fun addTaskList(userInput: String) {
        view.addNote(userInput, NoteType.TASK_LIST)
    }

    override fun openNote(userInput: String) {

    }

    override fun speak(message: String) {
        speaker.speakInForeground(message)
    }
}