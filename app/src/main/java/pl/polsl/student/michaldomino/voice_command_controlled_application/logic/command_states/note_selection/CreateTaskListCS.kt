package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSNode
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection.NoteSelectionPresenter

class CreateTaskListCS(override val presenter: NoteSelectionPresenter) : CSNode(presenter) {

    override val messageToSpeakId: Int = R.string.tell_name_of_the_new_list

    override val commandNameId: Int? = R.string.create_task_list

    override fun processInput(userInput: String) {
        presenter.addTaskList(userInput)
    }
}