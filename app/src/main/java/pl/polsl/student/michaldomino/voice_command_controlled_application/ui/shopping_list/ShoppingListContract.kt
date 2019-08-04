package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list.RowItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BaseView

interface ShoppingListContract {

    interface View : BaseView {
        fun addRow(text: CharSequence)
        fun getItems(): MutableList<RowItem>
        fun setNewItemName(item: RowItem, newName: String)
        fun startSpeechRecognizer(requestCode: Int, messageId: Int)
    }

    abstract class Presenter(override val view: View) : BasePresenter(view) {
        abstract fun addItems(userInput: String)
        abstract fun getItems(): MutableList<RowItem>
        abstract fun setNewItemName(item: RowItem, newName: String)
        abstract fun speak(message: String)
    }
}