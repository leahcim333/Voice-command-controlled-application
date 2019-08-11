package pl.polsl.student.michaldomino.voice_command_controlled_application.data.view_model.note_selection

import android.view.LayoutInflater
import android.widget.LinearLayout

class NoteSelectionItemsManager(private val inflater: LayoutInflater, private val parentLinearLayout: LinearLayout) {

    private val container: LinkedHashSet<NoteSelectionItem> = linkedSetOf()

    val items: MutableList<NoteSelectionItem>
        get() {
            return container.toMutableList()
        }

    fun addRow(text: CharSequence, type: NoteType) {
        val rowItem = NoteSelectionItem(inflater).setText(text).setType(type)
        container.add(rowItem)
        parentLinearLayout.addView(rowItem.getView(), parentLinearLayout.childCount)
    }
}