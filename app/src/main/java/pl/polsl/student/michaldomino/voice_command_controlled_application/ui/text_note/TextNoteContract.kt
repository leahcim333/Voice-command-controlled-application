package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenterInterface
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsView

interface TextNoteContract {

    interface View : VoiceCommandsView {
        fun addText(text: String)
        fun getText(): String
    }

    interface Presenter : VoiceCommandsPresenterInterface {
        fun addText(text: String)
    }
}