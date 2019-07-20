package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import android.content.Intent
import android.speech.RecognizerIntent
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main.MainContract.Presenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main.MainContract.View

class MainPresenter(_view: View) : Presenter {

    private var view: View = _view

    override fun onDoubleTap() {
        view.startCommandRecognizer()
    }

    override fun runCommand(data: Intent) {
        val possibleMatches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        val command = possibleMatches[0]
        view.speak(command)
    }
}