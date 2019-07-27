package pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list

import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import pl.polsl.student.michaldomino.voice_command_controlled_application.R

class RowItem(inflater: LayoutInflater) {

    private val rowView: View = inflater.inflate(R.layout.check_list_row, null)

    private var textView: TextView = rowView.findViewById(R.id.row_text)

    private var checkBox: CheckBox = rowView.findViewById(R.id.row_checkbox)

    val text: String
        get() {
            return textView.text.toString()
        }

    val checked: Boolean
        get() {
            return checkBox.isChecked
        }

    fun getView(): View {
        return rowView
    }

    fun setText(value: CharSequence): RowItem {
        this.textView.text = value
        return this
    }

    fun setChecked(value: Boolean): RowItem {
        this.checkBox.isChecked = value
        return this
    }
}