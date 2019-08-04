package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BaseView

interface NoteSelectionContract {

    interface View : BaseView {
        fun startSpeechRecognizer(requestCode: Int, messageId: Int)
    }

    abstract class Presenter(override val view: View) : BasePresenter(view) {
        abstract fun speak(message: String)
        abstract fun addShoppingList(userInput: String)
    }
}