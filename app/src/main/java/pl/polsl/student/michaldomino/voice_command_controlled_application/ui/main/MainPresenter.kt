package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import android.content.Intent
import android.speech.RecognizerIntent

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDoubleTap() {
        view.startCommandRecognizer()
    }

    override fun runCommand(data: Intent) {
        val possibleMatches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        val command = possibleMatches[0]
        view.speak(command)
    }
}