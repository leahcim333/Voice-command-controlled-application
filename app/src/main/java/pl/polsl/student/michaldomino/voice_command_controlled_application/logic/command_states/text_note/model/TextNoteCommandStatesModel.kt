package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.text_note.model

import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandStateModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.text_note.AddTextCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note.TextNotePresenter

class TextNoteCommandStatesModel(override val presenter: TextNotePresenter) :
    BaseCommandStateModel(presenter) {

    private val ADD_TEXT = AddTextCS(presenter)

    override val availableCommandStates: List<BaseCommandState> = listOf(
        ADD_TEXT
    )
}