package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import android.os.Bundle
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.view_model.note_selection.NoteType
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BaseView

interface NoteSelectionContract {

    interface View : BaseView {
        fun addNote(name: String, type: NoteType)
    }

    interface Presenter : BasePresenter {
        fun addTaskList(userInput: String)
        fun openNote(userInput: String)
        fun processInput(bundle: Bundle)
    }
}