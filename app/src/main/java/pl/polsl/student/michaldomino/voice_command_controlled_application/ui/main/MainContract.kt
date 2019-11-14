package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BaseView
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsView

interface MainContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter {
        fun onPermissionGranted()
        fun onPermissionDenied()
    }
}