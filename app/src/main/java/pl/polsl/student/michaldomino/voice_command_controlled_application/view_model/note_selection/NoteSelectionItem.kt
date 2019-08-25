package pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.note_selection

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Note

class NoteSelectionItem(inflater: LayoutInflater, val note: Note) {

    private val rowView: View = inflater.inflate(R.layout.note_selection_row, null)

    private val noteNameTextView: TextView = rowView.findViewById(R.id.note_name)

    private val noteTypeImageView: ImageView = rowView.findViewById(R.id.note_type)

    var name: String
        get() {
            return note.name
        }
        set(value) {
            note.name = value
        }

    var type: NoteType
        get() {
            return note.type
        }
        set(value) {
            note.type = value
        }

    fun getView(): View {
        return rowView
    }

    fun setText(value: CharSequence): NoteSelectionItem {
        name = value.toString()
        this.noteNameTextView.text = value
        return this
    }

    fun setType(value: NoteType): NoteSelectionItem {
        type = value
        this.noteTypeImageView.setImageResource(value.resId)
        return this
    }
}