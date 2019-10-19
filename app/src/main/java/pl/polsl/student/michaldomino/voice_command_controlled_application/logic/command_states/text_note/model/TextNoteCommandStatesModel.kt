package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.text_note.model

import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.AvailableCommandsCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandStateModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.text_note.AddTextCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.text_note.DiscardChangesCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.text_note.ReadTextCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.text_note.SaveChangesCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note.TextNotePresenter

class TextNoteCommandStatesModel(override val presenter: TextNotePresenter) :
    BaseCommandStateModel(presenter) {

    private val AVAILABLE_COMMANDS = AvailableCommandsCS(presenter, this)

    private val ADD_TEXT = AddTextCS(presenter)

    private val READ_TEXT = ReadTextCS(presenter)

    private val SAVE_CHANGES = SaveChangesCS(presenter)

    private val DISCARD_CHANGES = DiscardChangesCS(presenter)

    override val availableCommandStates: List<BaseCommandState> = listOf(
        AVAILABLE_COMMANDS,
        ADD_TEXT,
        READ_TEXT,
        SAVE_CHANGES,
        DISCARD_CHANGES
    )
}