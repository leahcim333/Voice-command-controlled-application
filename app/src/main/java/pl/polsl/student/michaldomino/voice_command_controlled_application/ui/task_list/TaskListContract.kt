package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Task
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenterInterface
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsView
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.task_list.TaskListItem

interface TaskListContract {

    interface View : VoiceCommandsView {
        fun addTask(task: Task)
        fun getItems(): MutableList<TaskListItem>
        fun setNewItemName(item: TaskListItem, newName: String)
    }

    interface Presenter : VoiceCommandsPresenterInterface {
        fun addItems(userInput: String)
        fun getItems(): MutableList<TaskListItem>
        fun setNewItemName(item: TaskListItem, newName: String)
    }
}