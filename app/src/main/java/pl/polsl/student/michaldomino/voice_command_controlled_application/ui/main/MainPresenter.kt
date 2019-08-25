package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection.NoteSelectionActivity


class MainPresenter(val view: MainContract.View) : MainContract.Presenter {

    override fun start() {
        view.startActivityFromClass(NoteSelectionActivity::class.java)
        view.finish()
    }
}