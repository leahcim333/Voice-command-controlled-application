package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import android.os.Bundle
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Note
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsView

interface NoteSelectionContract {

    interface View : VoiceCommandsView {
        fun addNote(note: Note)
    }

    interface Presenter : BasePresenter {
        fun addTaskList(userInput: String)
        fun openNote(userInput: String)
        fun processInput(bundle: Bundle)
        fun onDoubleTap()
    }
}