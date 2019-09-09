package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Note
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenterInterface
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsView
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.note_selection.NoteSelectionItem

interface NoteSelectionContract {

    interface View : VoiceCommandsView {
        fun addNote(note: Note)
        fun getItems(): MutableList<NoteSelectionItem>
        fun deleteNote(noteSelectionItem: NoteSelectionItem)
    }

    interface Presenter : VoiceCommandsPresenterInterface {
        fun addTaskList(userInput: String)
        fun openNote(userInput: String)
    }
}