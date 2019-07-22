package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BaseView

interface MainContract {

    interface View : BaseView {
        fun addRow(text: CharSequence)
        fun startCommandRecognizer()
        fun speak(command: String?)
    }

    interface Presenter : BasePresenter
}