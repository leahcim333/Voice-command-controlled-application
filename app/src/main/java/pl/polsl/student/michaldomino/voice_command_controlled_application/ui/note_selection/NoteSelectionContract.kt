package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.layout_managers.note_selection.NoteSelectionItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Note
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsView

interface NoteSelectionContract {

    interface View : VoiceCommandsView {
        fun addNote(note: Note)
        fun getItems(): List<NoteSelectionItem>
        fun deleteNote(noteSelectionItem: NoteSelectionItem)
    }

    interface Presenter : VoiceCommandsPresenter
}