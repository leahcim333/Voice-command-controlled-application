package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import android.content.Intent
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.BaseCommandState

abstract class BasePresenter(protected open val view: BaseView) {

    abstract var currentState: BaseCommandState

    abstract fun start()

    abstract fun runCommand(data: Intent, requestCode: Int)

    abstract fun onDoubleTap()

    fun getString(resId: Int): String {
        return view.getString(resId)
    }
}