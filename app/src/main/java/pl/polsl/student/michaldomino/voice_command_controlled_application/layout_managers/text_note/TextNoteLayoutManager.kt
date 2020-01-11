package pl.polsl.student.michaldomino.voice_command_controlled_application.layout_managers.text_note

import android.view.LayoutInflater
import android.widget.LinearLayout
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.TextNote

class TextNoteLayoutManager(
    inflater: LayoutInflater,
    parentLinearLayout: LinearLayout,
    textNote: TextNote
) {

    private val container = TextNoteItem(inflater, parentLinearLayout, textNote)

    val item: TextNoteItem
        get() {
            return container
        }

    fun addText(text: String) {
        container.addText(text)
    }

    fun setText(text: String) {
        container.setText(text)
    }
}