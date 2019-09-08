package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenter

class AvailableCommandsCS(
    override val presenter: VoiceCommandsPresenter,
    val commandsModel: BaseCommandStateModel
) : CSLeaf(presenter) {

    private val ITEMS_DELIMITER = ", "

    override val commandNameId: Int? = R.string.available_commands

    override fun initialize() {
        val availableCommands: MutableList<Int?> = commandsModel.availableCommandStates
            .map { it.commandNameId }
            .filter { it != commandNameId }
            .toMutableList()
        val messageBuilder =
            StringBuilder().append(presenter.getString(R.string.available_commands_are))
        availableCommands.forEach {
            messageBuilder.append(it?.let { it1 -> presenter.getString(it1) })
                .append(ITEMS_DELIMITER)
        }
        presenter.speak(messageBuilder.toString())
    }

    override fun processInput(userInput: String) {}
}