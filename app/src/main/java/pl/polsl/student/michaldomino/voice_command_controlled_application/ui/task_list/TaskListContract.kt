package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.task_list.TaskListItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BaseView

interface TaskListContract {

    interface View : BaseView {
        fun addRow(text: CharSequence)
        fun getItems(): MutableList<TaskListItem>
        fun setNewItemName(item: TaskListItem, newName: String)
    }

    abstract class Presenter(override val view: View) : BasePresenter(view) {
        abstract fun addItems(userInput: String)
        abstract fun getItems(): MutableList<TaskListItem>
        abstract fun setNewItemName(item: TaskListItem, newName: String)
    }
}