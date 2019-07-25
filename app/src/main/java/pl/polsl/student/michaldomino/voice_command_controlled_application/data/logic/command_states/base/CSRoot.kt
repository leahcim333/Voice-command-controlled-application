package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base

import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.BasePresenter

abstract class CSRoot(override val presenter: BasePresenter) : CSStaticNode(presenter) {

    fun initialize() {
        presenter.askForInput(messageToSpeak)
    }
}