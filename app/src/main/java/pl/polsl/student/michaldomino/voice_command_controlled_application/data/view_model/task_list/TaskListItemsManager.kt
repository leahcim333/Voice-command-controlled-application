package pl.polsl.student.michaldomino.voice_command_controlled_application.data.view_model.task_list

import android.view.LayoutInflater
import android.widget.LinearLayout

class TaskListItemsManager(private val inflater: LayoutInflater, private val parentLinearLayout: LinearLayout) {

    private val container: LinkedHashSet<TaskListItem> = linkedSetOf()

    val items: MutableList<TaskListItem>
        get() {
            return container.toMutableList()
        }

    fun addRow(text: CharSequence, isChecked: Boolean = false) {
        val rowItem = TaskListItem(inflater).setText(text).setChecked(isChecked)
        container.add(rowItem)
        parentLinearLayout.addView(rowItem.getView(), parentLinearLayout.childCount)
    }
}