package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.note_selection.NoteType
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BaseView

interface NoteSelectionContract {

    interface View : BaseView {
        fun addNote(name: String, type: NoteType)
    }

    abstract class Presenter(override val view: View) : BasePresenter(view) {
        abstract fun addTaskList(userInput: String)
        abstract fun openNote(userInput: String)
    }
}