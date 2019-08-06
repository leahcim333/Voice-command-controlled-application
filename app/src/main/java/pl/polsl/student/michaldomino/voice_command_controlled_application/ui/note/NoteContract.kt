package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BaseView

interface NoteContract {

    interface View : BaseView

    abstract class Presenter(override val view: View) : BasePresenter(view)
}