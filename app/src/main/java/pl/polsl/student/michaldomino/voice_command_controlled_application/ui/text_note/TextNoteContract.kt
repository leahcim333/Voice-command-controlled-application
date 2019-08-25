package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note

import android.os.Bundle
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsView

interface TextNoteContract {

    interface View : VoiceCommandsView {
        fun addText(text: String)
    }

    interface Presenter : BasePresenter {
        fun addText(text: String)
        fun processInput(bundle: Bundle)
        fun onDoubleTap()
    }
}