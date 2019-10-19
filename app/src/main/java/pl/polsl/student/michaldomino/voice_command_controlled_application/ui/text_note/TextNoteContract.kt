package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note

import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.TextNote
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenterInterface
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsView
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.text_note.TextNoteItem

interface TextNoteContract {

    interface View : VoiceCommandsView {
        fun addText(text: String)
        fun textNoteItem(): TextNoteItem
        fun setTextNote(textNote: TextNote)
        fun setText(text: String)
    }

    interface Presenter : VoiceCommandsPresenterInterface {
        fun addText(text: String)
    }
}