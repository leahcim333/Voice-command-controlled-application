package pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.note_selection

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import pl.polsl.student.michaldomino.voice_command_controlled_application.R

class NoteSelectionItem(inflater: LayoutInflater) {

    private val rowView: View = inflater.inflate(R.layout.note_selection_row, null)

    private val noteNameTextView: TextView = rowView.findViewById(R.id.note_name)

    private val noteTypeTextView: TextView = rowView.findViewById(R.id.note_type)

    val name: String
        get() {
            return noteNameTextView.text.toString()
        }

    val type: String
        get() {
            return noteTypeTextView.text.toString()
        }

    fun getView(): View {
        return rowView
    }

    fun setText(value: CharSequence): NoteSelectionItem {
        this.noteNameTextView.text = value
        return this
    }

    fun setType(value: CharSequence): NoteSelectionItem {
        this.noteTypeTextView.text = value
        return this
    }
}