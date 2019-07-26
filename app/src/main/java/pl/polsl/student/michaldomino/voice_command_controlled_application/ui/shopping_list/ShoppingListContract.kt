package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BaseView
import java.util.*

interface ShoppingListContract {

    interface View : BaseView {
        fun addRow(text: CharSequence)
    }

    abstract class Presenter(view: View) : BasePresenter(view) {
        abstract fun addItems(userInput: String)
        abstract fun getItems(): ArrayList<String>
    }
}