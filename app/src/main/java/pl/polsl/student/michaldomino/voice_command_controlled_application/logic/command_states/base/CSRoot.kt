package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.Word
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenter

class CSRoot(
    override val presenter: VoiceCommandsPresenter,
    val model: BaseCommandStateModel
) : CSStaticNode(presenter) {

    override val commandNameId: Int? = null

    override val messageToSpeakId: Int = R.string.tell_command

    override fun processInput(userInput: String) {
        val command = Word(userInput)
        val mostSimilarCommandState: BaseCommandState? = command.getMostSimilar(
            model.availableCommandStates,
            { presenter.getString(it.commandNameId!!) })
        if (mostSimilarCommandState != null) {
            presenter.currentState = mostSimilarCommandState
            mostSimilarCommandState.initialize()
        } else {
            presenter.speak(presenter.getString(R.string.command_unrecognized))
        }
    }
}