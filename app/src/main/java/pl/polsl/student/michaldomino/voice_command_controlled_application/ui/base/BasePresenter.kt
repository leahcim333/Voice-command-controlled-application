package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import android.content.Intent
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.BaseCommandState

interface BasePresenter {

    var currentState: BaseCommandState

    val view: BaseView

//    protected abstract val view : BaseView

    fun start()

    fun getString(resId: Int): String {
        return view.getString(resId)
    }

    fun onDoubleTap() {
        view.startCommandRecognizer()
    }

    fun runCommand(data: Intent)
}