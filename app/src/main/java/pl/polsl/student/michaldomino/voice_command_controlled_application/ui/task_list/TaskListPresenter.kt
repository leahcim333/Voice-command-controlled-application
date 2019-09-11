package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
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

    override fun addItems(userInput: String) {
        val resourceDelimiter: String = getString(R.string.elements_delimiter)
        val fullDelimiter = " $resourceDelimiter "
        val tasks: List<String> = userInput.split(fullDelimiter)
        val existingTasks: List<String> = view.getItems().map { it.text }
        tasks.filter { it !in existingTasks }.forEach { view.addTask(Task(it, noteId)) }
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

    fun saveChanges() {
        val tasks = view.getItems().map { it.task }
        disposable.add(
            Single.fromCallable { dao.saveChangesByNoteId(tasks, noteId) }
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
}