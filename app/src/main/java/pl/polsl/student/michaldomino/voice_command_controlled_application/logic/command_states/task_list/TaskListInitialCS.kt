package pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list.model.TaskListCommandStatesModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list.TaskListPresenter

class TaskListInitialCS(override val presenter: TaskListPresenter) :
    CSRoot(presenter, TaskListCommandStatesModel(presenter)) {

//    override val model = TaskListCommandStatesModel(presenter)
}