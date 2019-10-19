package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Task
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsView
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.task_list.TaskListItem

interface TaskListContract {

    interface View : VoiceCommandsView {
        fun addTask(task: Task)
        fun getItems(): List<TaskListItem>
        fun setNewItemName(item: TaskListItem, newName: String)
        fun clearList()
        fun deleteTask(taskListItem: TaskListItem)
    }

    interface Presenter : VoiceCommandsPresenter
}