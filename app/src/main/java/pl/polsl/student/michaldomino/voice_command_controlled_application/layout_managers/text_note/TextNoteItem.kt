package pl.polsl.student.michaldomino.voice_command_controlled_application.layout_managers.text_note

import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.TextNote

class TextNoteItem(
    inflater: LayoutInflater,
    parentLinearLayout: LinearLayout,
    val textNote: TextNote
) {

    private val view: View = inflater.inflate(R.layout.text_note_view, parentLinearLayout)

    private val textView: TextView = view.findViewById(R.id.text_view)

    init {
        setText(textNote.text)
    }

    val text: String
        get() {
            return textNote.text
        }

    fun addText(value: String) {
        val textBuilder = StringBuilder()
        textBuilder.append(textNote.text).append(" ").append(value)
        val newText = textBuilder.toString()
        textNote.text = newText
        textView.text = newText
    }

    fun setText(value: String) {
        textNote.text = value
        textView.text = value
    }
}