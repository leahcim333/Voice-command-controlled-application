package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.Word
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.task_list.model.TaskListCommandStatesModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.dao.TaskDao
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.database.AppDatabase
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Task
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.task_list.TaskListItem

class TaskListPresenter(override val view: TaskListContract.View, val noteId: Long) :
    VoiceCommandsPresenter(view),
    TaskListContract.Presenter {

    override val initialState: CSRoot = CSRoot(this, TaskListCommandStatesModel(this))

    override var currentState: BaseCommandState = initialState

    private val disposable = CompositeDisposable()

    private lateinit var dao: TaskDao

    override fun create() {
        dao = AppDatabase.getInstance(view.getApplicationContext()).taskDao()
        loadTasksFromDatabase()
    }

    override fun stop() {
        disposable.clear()
    }

    private fun handleError(error: Throwable?) {
        view.showToast(error?.localizedMessage)
    }

    private fun addTasksToView(tasks: List<Task>?) {
        tasks?.forEach { view.addTask(it) }
    }

    private fun loadTasksFromDatabase() {
        disposable.add(
            dao.findAllByNoteId(noteId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ tasks -> addTasksToView(tasks) }, { error -> handleError(error) })
        )
    }

    private fun updateIds(ids: List<Long>) {
        view.getItems().map { it.task }.forEachIndexed { index, task -> task.id = ids[index] }
    }

    private fun splitInput(userInput: String): List<String> {
        val resourceDelimiter: String = getString(R.string.elements_delimiter)
        val fullDelimiter = " $resourceDelimiter "
        return userInput.split(fullDelimiter)
    }

    private fun changeChecked(userInput: String, isChecked: Boolean) {
        val items: List<String> = splitInput(userInput)
        val existingItems: MutableList<TaskListItem> = view.getItems().toMutableList()
        val itemsToCheck: MutableList<TaskListItem> = mutableListOf()
        for (item in items) {
            val mostSimilarItem = Word(item).getMostSimilar(existingItems, { it.text })
            if (mostSimilarItem != null && !itemsToCheck.contains(mostSimilarItem)) {
                existingItems.remove(mostSimilarItem)
                itemsToCheck.add(mostSimilarItem)
                mostSimilarItem.setChecked(isChecked)
            }
        }
    }

    fun addItems(userInput: String) {
        val items: List<String> = splitInput(userInput)
        val existingItems: List<String> = view.getItems().map { it.text }
        items.filter { it !in existingItems }.forEach { view.addTask(Task(it, noteId)) }
    }

    fun getItems(): List<TaskListItem> {
        return view.getItems()
    }

    fun setNewItemName(item: TaskListItem, newName: String) {
        val existingItems: List<String> = view.getItems().map { it.text }
        if (newName !in existingItems)
            view.setNewItemName(item, newName)
        else
            speak(getString(R.string.item_already_exists))
    }

    fun saveChanges() {
        val items = view.getItems().map { it.task }
        disposable.add(
            Single.fromCallable { dao.saveChangesByNoteId(items, noteId) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ ids -> updateIds(ids) }, { error -> handleError(error) })
        )
    }

    fun clear() {
        view.clear()
    }

    fun discardChanges() {
        view.clear()
        loadTasksFromDatabase()
    }

    override fun speak(message: String) {
        view.speakInForeground(message)
    }

    fun deleteItem(userInput: String) {
        val selectedItem = Word(userInput)
        val items: List<TaskListItem> = view.getItems()
        val mostSimilarItem: TaskListItem? = selectedItem.getMostSimilar(items, { it.text })
        if (mostSimilarItem != null) {
            view.deleteTask(mostSimilarItem)
        }
    }

    fun checkItems(userInput: String) {
        changeChecked(userInput, true)
    }

    fun uncheckItems(userInput: String) {
        changeChecked(userInput, false)
    }
}