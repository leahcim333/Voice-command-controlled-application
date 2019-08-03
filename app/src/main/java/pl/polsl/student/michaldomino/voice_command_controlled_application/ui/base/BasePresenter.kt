package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import android.content.Intent
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot

abstract class BasePresenter(protected open val view: BaseView) {

    protected abstract val initialState: CSRoot

    abstract var currentState: BaseCommandState

    abstract fun start()

    abstract fun processInput(data: Intent)

    abstract fun onDoubleTap()

    abstract fun askForInput(messageId: Int)

    fun getString(resId: Int): String {
        return view.getString(resId)
    }
}