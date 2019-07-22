package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base

import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.BaseCommandState

interface BasePresenter {

    var currentState: BaseCommandState

    fun start()

    fun getString(resId: Int): String
}