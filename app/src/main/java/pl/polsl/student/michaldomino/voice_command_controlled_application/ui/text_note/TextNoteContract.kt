package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BaseView

interface TextNoteContract {

    interface View : BaseView {
        fun addText(text: String)
    }

    abstract class Presenter(override val view: View) : BasePresenter(view) {
        abstract fun addText(text: String)
    }
}