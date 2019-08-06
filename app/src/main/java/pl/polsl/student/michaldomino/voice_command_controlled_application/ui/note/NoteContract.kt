package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BaseView

interface NoteContract {

    interface View : BaseView {
        fun addText(text: String)
    }

    abstract class Presenter(override val view: View) : BasePresenter(view) {
        abstract fun addText(text: String)
    }
}