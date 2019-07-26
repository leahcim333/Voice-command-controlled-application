package pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list

import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import pl.polsl.student.michaldomino.voice_command_controlled_application.R

class RowItem(inflater: LayoutInflater) {

    private val rowView: View = inflater.inflate(R.layout.check_list_row, null)

    private var textView = rowView.findViewById<TextView>(R.id.row_text)

    private var checkBox = rowView.findViewById<CheckBox>(R.id.row_checkbox)

    fun getView(): View {
        return rowView
    }

    fun setText(value: CharSequence): RowItem {
        textView.text = value
        return this
    }

    fun setChecked(value: Boolean): RowItem {
        checkBox.isChecked = value
        return this
    }
}