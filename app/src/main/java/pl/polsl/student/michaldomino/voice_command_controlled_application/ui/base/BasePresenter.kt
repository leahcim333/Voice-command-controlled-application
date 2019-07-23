package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import android.content.Intent
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.BaseCommandState as BaseCommandState1

interface BasePresenter {

    val view: BaseView

    var currentState: BaseCommandState1

    fun start()

    fun runCommand(data: Intent, requestCode: Int)

    fun onDoubleTap()

    fun getString(resId: Int): String {
        return view.getString(resId)
    }
}