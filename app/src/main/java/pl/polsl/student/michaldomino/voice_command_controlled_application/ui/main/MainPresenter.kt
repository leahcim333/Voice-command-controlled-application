package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import android.content.Intent
import android.speech.RecognizerIntent
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.InitialCS


//private val BaseView.applicationContext: Context
//    get() {}

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    override var currentState: BaseCommandState = InitialCS(this)

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDoubleTap() {
        view.addRow("abc")
//        view.startCommandRecognizer()
    }

    override fun runCommand(data: Intent) {
        val possibleMatches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        val command = possibleMatches[0]
        view.speak(command)
    }

    override fun getString(resId: Int): String {
        return view.getString(resId)
    }
}