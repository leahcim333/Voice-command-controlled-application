package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main.MainContract.Presenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main.MainContract.View

class MainPresenter(_view: View) : Presenter {

    private var view: View = _view

    override fun a() {
        view.showMessage("abc")
    }

    override fun onDoubleTap() {
        view.addRow("abc")
    }
}