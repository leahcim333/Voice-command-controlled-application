package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import android.content.Intent

abstract class BasePresenter(protected open val view: BaseView) {

//    abstract var currentState: BaseCommandState

    abstract fun start()

    abstract fun processInput(data: Intent)

    abstract fun onDoubleTap()

    abstract fun askForInput(message: String?)

    fun getString(resId: Int): String {
        return view.getString(resId)
    }
}