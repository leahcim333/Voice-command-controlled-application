package pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.note_selection

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Note

class NoteSelectionItem(inflater: LayoutInflater, private val note: Note) {

    private val rowView: View = inflater.inflate(R.layout.note_selection_row, null)

    private val noteNameTextView: TextView = rowView.findViewById(R.id.note_name)

    private val noteTypeImageView: ImageView = rowView.findViewById(R.id.note_type)

    init {
        setName(note.name).setType(note.type)
    }

    val name: String
        get() {
            return note.name
        }

    val type: NoteType
        get() {
            return note.type
        }

    fun getView(): View {
        return rowView
    }

    fun setName(value: CharSequence): NoteSelectionItem {
        note.name = value.toString()
        this.noteNameTextView.text = value
        return this
    }

    fun setType(value: NoteType): NoteSelectionItem {
        note.type = value
        val resId = when (value) {
            NoteType.TASK_LIST -> android.R.drawable.checkbox_on_background
            NoteType.TEXT_NOTE -> android.R.drawable.ic_menu_edit
        }
        this.noteTypeImageView.setImageResource(resId)
        return this
    }
}