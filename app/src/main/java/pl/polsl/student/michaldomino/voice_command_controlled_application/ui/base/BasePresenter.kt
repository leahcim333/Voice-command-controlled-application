package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import java.util.*

abstract class BasePresenter(protected open val view: BaseView) {

    protected abstract val initialState: CSRoot

    abstract var currentState: BaseCommandState

    abstract fun start()

    abstract fun askForInput(messageId: Int)

    abstract fun speak(message: String)

    fun processInput(possibleMatches: ArrayList<String>) {
        val userInput = possibleMatches[0]
        currentState.processInput(userInput)
    }

    open fun onDoubleTap() {
        currentState = initialState
        currentState.initialize()
    }

    fun getString(resId: Int): String {
        return view.getString(resId)
    }
}