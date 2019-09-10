package pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.task_list

import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Task

class TaskListItem(inflater: LayoutInflater, val task: Task) {

    private val rowView: View = inflater.inflate(R.layout.task_list_row, null)

    private var textView: TextView = rowView.findViewById(R.id.task_text)

    private var checkBox: CheckBox = rowView.findViewById(R.id.task_checkbox)

    val text: String
        get() {
            return task.name
        }

    val checked: Boolean
        get() {
            return task.isChecked
        }

    fun getView(): View {
        return rowView
    }

    fun setName(value: CharSequence): TaskListItem {
        task.name = value.toString()
        this.textView.text = value
        return this
    }

    fun setChecked(value: Boolean): TaskListItem {
        task.isChecked = value
        this.checkBox.isChecked = value
        return this
    }
}