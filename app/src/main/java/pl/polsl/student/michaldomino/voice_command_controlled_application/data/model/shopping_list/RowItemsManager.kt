package pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list

import android.view.LayoutInflater
import android.widget.LinearLayout

class RowItemsManager(private val inflater: LayoutInflater, private val parentLinearLayout: LinearLayout) {

    private val container: LinkedHashSet<RowItem> = linkedSetOf()

    val items: MutableList<RowItem>
        get() {
            return container.toMutableList()
        }

    fun addRow(text: CharSequence, isChecked: Boolean = false) {
        val rowItem = RowItem(inflater).setText(text).setChecked(isChecked)
        container.add(rowItem)
        parentLinearLayout.addView(rowItem.getView(), parentLinearLayout.childCount)
    }

    fun edit(oldValue: CharSequence, newValue: CharSequence) {
        val editedRow = container.first { it.text == oldValue }
        editedRow.setText(newValue)
    }
}