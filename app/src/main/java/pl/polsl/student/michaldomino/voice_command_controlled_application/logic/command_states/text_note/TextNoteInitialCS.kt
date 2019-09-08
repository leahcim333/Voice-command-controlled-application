package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.text_note

import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandStateModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note.TextNotePresenter

class TextNoteInitialCS(override val presenter: TextNotePresenter) : CSRoot(presenter) {
    override val model: BaseCommandStateModel
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    val ADD_TEXT = AddTextCS(presenter)
//    override val availableCommands: Array<BaseCommandState> = arrayOf(ADD_TEXT)
}