package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Note
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsView
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.note_selection.NoteSelectionItem

interface NoteSelectionContract {

    interface View : VoiceCommandsView {
        fun addNote(note: Note)
        fun getItems(): List<NoteSelectionItem>
        fun deleteNote(noteSelectionItem: NoteSelectionItem)
    }

    interface Presenter : VoiceCommandsPresenter
}