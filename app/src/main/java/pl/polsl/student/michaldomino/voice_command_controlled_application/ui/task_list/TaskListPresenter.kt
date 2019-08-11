package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list

import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.command_states.task_list.TaskListInitialCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.view_model.task_list.TaskListItem

class TaskListPresenter(override val view: TaskListContract.View) : TaskListContract.Presenter(view) {

    override val initialState: CSRoot = TaskListInitialCS(this)

    override var currentState: BaseCommandState = initialState

    override fun start() {

    }

    override fun addItems(userInput: String) {
        val resourceDelimiter: String = getString(R.string.elements_delimiter)
        val fullDelimiter = " $resourceDelimiter "
        val elements: List<String> = userInput.split(fullDelimiter)
        val existingItems: List<String> = view.getItems().map { it.text }
        elements.filter { it !in existingItems }.forEach { view.addRow(it) }
    }

    override fun getItems(): MutableList<TaskListItem> {
        return view.getItems()
    }

    override fun setNewItemName(item: TaskListItem, newName: String) {
        val existingItems: List<String> = view.getItems().map { it.text }
        if (newName !in existingItems)
            view.setNewItemName(item, newName)
        else
            speak(getString(R.string.item_already_exists))
    }

    override fun speak(message: String) {
        view.speakInForeground(message)
    }
}