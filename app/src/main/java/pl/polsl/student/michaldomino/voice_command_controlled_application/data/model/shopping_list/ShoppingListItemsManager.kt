package pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list

import android.view.LayoutInflater
import android.widget.LinearLayout

class ShoppingListItemsManager(private val inflater: LayoutInflater, private val parentLinearLayout: LinearLayout) {

    private val container: LinkedHashSet<ShoppingListItem> = linkedSetOf()

    val items: MutableList<ShoppingListItem>
        get() {
            return container.toMutableList()
        }

    fun addRow(text: CharSequence, isChecked: Boolean = false) {
        val rowItem = ShoppingListItem(inflater).setText(text).setChecked(isChecked)
        container.add(rowItem)
        parentLinearLayout.addView(rowItem.getView(), parentLinearLayout.childCount)
    }
}