package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.Word
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection.NoteSelectionPresenter

class NoteSelectionInititalCS(override val presenter: NoteSelectionPresenter) : CSRoot(presenter) {

    private val ADD_NOTES = CreateShoppingListCS(presenter)

    override val availableCommands: Array<BaseCommandState> = arrayOf(ADD_NOTES)

    override val messageToSpeakId: Int = R.string.tell_command

    override fun processInput(userInput: String) {
        val command = Word(userInput)
        val matchingCommandState: BaseCommandState? =
            availableCommands.firstOrNull { command.similarTo(presenter.getString(it.commandNameId!!)) }
        if (matchingCommandState != null) {
            presenter.currentState = matchingCommandState
            matchingCommandState.initialize()
        } else {
            presenter.speak(presenter.getString(R.string.command_unrecognized))
        }
    }
}