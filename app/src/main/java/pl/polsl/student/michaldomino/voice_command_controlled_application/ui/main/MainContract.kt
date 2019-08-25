package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.Note
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BaseView

interface MainContract {

    interface View : BaseView {
        fun finish()
    }

    interface Presenter : BasePresenter {
        fun doSth(notes: List<Note>?)
        fun doElse(error: Throwable?)
    }
}