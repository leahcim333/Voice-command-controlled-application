package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import android.content.Intent
import android.speech.RecognizerIntent
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.InitialCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListActivity


class MainPresenter(override val view: MainContract.View) : MainContract.Presenter {

    private val REQUEST_CODE_COMMAND_RECOGNITION = 0

    override var currentState: BaseCommandState = InitialCS(this)

    private val speaker: Speaker = Speaker(view.getApplicationContext())

    override fun start() {
        view.startActivityFromClass(ShoppingListActivity::class.java)
    }

    override fun onDoubleTap() {
        speaker.speak(getString(R.string.tell_command))
        view.startCommandRecognizer(REQUEST_CODE_COMMAND_RECOGNITION, R.string.tell_command)
    }

    override fun runCommand(data: Intent, requestCode: Int) {
        val possibleMatches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        val command = possibleMatches[0]
        when (requestCode) {
            REQUEST_CODE_COMMAND_RECOGNITION -> {
                currentState.performCommand(command)
            }
            else -> {
            }
        }
//        speaker.speak(command)
    }
}