package pl.polsl.student.michaldomino.voice_command_controlled_application.layout_managers.note_selection

import android.view.LayoutInflater
import android.widget.LinearLayout
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Note

class NoteSelectionLayoutManager(
    private val inflater: LayoutInflater,
    private val parentLinearLayout: LinearLayout
) {

    private val container: LinkedHashSet<NoteSelectionItem> = linkedSetOf()

    val items: List<NoteSelectionItem>
        get() {
            return container.toList()
        }

    fun addNote(note: Note) {
        val rowItem = NoteSelectionItem(inflater, note)
        container.add(rowItem)
        parentLinearLayout.addView(rowItem.getView(), parentLinearLayout.childCount)
    }

    fun deleteNote(noteSelectionItem: NoteSelectionItem) {
        parentLinearLayout.removeView(noteSelectionItem.getView())
        container.remove(noteSelectionItem)
    }
}