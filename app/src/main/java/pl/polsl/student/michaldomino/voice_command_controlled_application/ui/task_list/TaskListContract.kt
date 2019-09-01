package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list

import android.os.Bundle
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsView
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.task_list.TaskListItem

interface TaskListContract {

    interface View : VoiceCommandsView {
        fun addRow(text: CharSequence)
        fun getItems(): MutableList<TaskListItem>
        fun setNewItemName(item: TaskListItem, newName: String)
    }

    interface Presenter : BasePresenter {
        fun addItems(userInput: String)
        fun getItems(): MutableList<TaskListItem>
        fun setNewItemName(item: TaskListItem, newName: String)
        fun processInput(bundle: Bundle)
        fun onDoubleTap()
    }
}