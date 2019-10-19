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

    private var tasksInDatabase: List<Task>? = null

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
                .subscribe({ tasks ->
                    addTasksToView(tasks).also {
                        tasksInDatabase = tasks.map { it.copy() }
                    }
                }, { error -> handleError(error) })
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

    private fun performActionOnItems(userInput: String, action: (TaskListItem) -> Any) {
        val items: List<String> = splitInput(userInput)
        val existingItems: MutableList<TaskListItem> = view.getItems().toMutableList()
        val itemsToCheck: MutableList<TaskListItem> = mutableListOf()
        for (item in items) {
            val mostSimilarItem = Word(item).getMostSimilar(existingItems, { it.text })
            if (mostSimilarItem != null && !itemsToCheck.contains(mostSimilarItem)) {
                existingItems.remove(mostSimilarItem)
                itemsToCheck.add(mostSimilarItem)
                action(mostSimilarItem)
            }
        }
    }

    private fun listItems(items: List<TaskListItem>, itemDelimiter: String = "... ") {
        val messageBuilder = StringBuilder()
        items.forEach { messageBuilder.append(it.text).append(itemDelimiter) }
        speak(messageBuilder.toString())
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
        if (items != tasksInDatabase) {
            disposable.add(
                Single.fromCallable { dao.saveChangesByNoteId(items, noteId) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ ids ->
                        updateIds(ids).also { tasksInDatabase = view.getItems().map { it.task } }
                    }, { error -> handleError(error) })
            )
        } else {
            speak(getString(R.string.no_changes))
        }
    }

    fun clear() {
        view.clear()
    }

    fun discardChanges() {
        val items = view.getItems().map { it.task }
        if (items != tasksInDatabase) {
            view.clear()
            addTasksToView(tasksInDatabase)
        } else {
            speak(getString(R.string.no_changes))
        }
    }

    override fun speak(message: String) {
        view.speakInForeground(message)
    }

    fun deleteItems(userInput: String) {
        performActionOnItems(userInput) { view.deleteTask(it) }
    }

    fun checkItems(userInput: String) {
        performActionOnItems(userInput) { it.setChecked(true) }
    }

    fun uncheckItems(userInput: String) {
        performActionOnItems(userInput) { it.setChecked(false) }
    }

    fun closeNote() {
        view.finish()
    }


    fun listAllItems(itemDelimiter: String = "... ") {
        val items: List<TaskListItem> = getItems()
        listItems(items, itemDelimiter)
    }

    fun listCheckedItems(itemDelimiter: String = "... ") {
        val items: List<TaskListItem> = getItems().filter { it.checked }
        listItems(items, itemDelimiter)
    }

    fun listUncheckedItems(itemDelimiter: String = "... ") {
        val items: List<TaskListItem> = getItems().filter { !it.checked }
        listItems(items, itemDelimiter)
    }
}