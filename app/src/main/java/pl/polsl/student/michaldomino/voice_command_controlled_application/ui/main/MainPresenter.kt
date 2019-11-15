package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection.NoteSelectionActivity


class MainPresenter(val view: MainContract.View) : MainContract.Presenter {

    override fun create() {
        if (!view.isRecordAudioGranted())
            view.requestPermission()
    }

    override fun stop() {}

    override fun onPermissionGranted() {
        startNoteSelection()
    }

    override fun onPermissionDenied() {
        view.speakInForeground(view.getString(R.string.record_audio_permission_denied))
        startNoteSelection()
    }

    private fun startNoteSelection() {
        view.startActivityFromClass(NoteSelectionActivity::class.java)
        view.finish()
    }
}