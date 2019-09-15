package pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.task_list

import android.view.LayoutInflater
import android.widget.LinearLayout
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Task

class TaskListItemsManager(private val inflater: LayoutInflater, private val parentLinearLayout: LinearLayout) {

    private val container: LinkedHashSet<TaskListItem> = linkedSetOf()

    val items: List<TaskListItem>
        get() {
            return container.toList()
        }

    fun addTask(task: Task) {
        val rowItem = TaskListItem(inflater, task)
        container.add(rowItem)
        parentLinearLayout.addView(rowItem.getView(), parentLinearLayout.childCount)
    }

    fun deleteTaskListItem(taskListItem: TaskListItem) {
        container.remove(taskListItem)
        parentLinearLayout.removeView(taskListItem.getView())
    }

    fun clear() {
        container.clear()
        parentLinearLayout.removeAllViews()
    }
}