package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import android.content.Intent
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.InitialCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list.ShoppingListActivity


class MainPresenter(override val view: MainContract.View) : MainContract.Presenter(view) {
    override val initialState: CSRoot
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun processInput(data: Intent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun askForInput(messageId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val REQUEST_CODE_COMMAND_RECOGNITION = 0

    override var currentState: BaseCommandState = InitialCS(this)

    private val speaker: Speaker = Speaker(view.getApplicationContext())

    override fun start() {
        view.startActivityFromClass(ShoppingListActivity::class.java)
    }

    override fun onDoubleTap() {
//        speaker.speakInForeground(getString(R.string.tell_command))
//        view.startSpeechRecognizer(REQUEST_CODE_COMMAND_RECOGNITION, R.string.tell_command)
    }
}