package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list

import io.reactivex.Completable
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

    private fun handleError(error: Throwable?) {
        view.showToast(error?.localizedMessage)
    }

    override fun create() {
        dao = AppDatabase.getInstance(view.getApplicationContext()).taskDao()
        disposable.add(
            dao.findAllByNoteId(noteId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ tasks -> addTasksToView(tasks) }, { error -> handleError(error) })
        )
    }

    private fun addTasksToView(tasks: List<Task>?) {
        tasks?.forEach { view.addTask(it) }
    }

    override fun stop() {
        disposable.clear()
    }

    override fun addItems(userInput: String) {
        val resourceDelimiter: String = getString(R.string.elements_delimiter)
        val fullDelimiter = " $resourceDelimiter "
        val elements: List<String> = userInput.split(fullDelimiter)
        val existingItems: List<String> = view.getItems().map { it.text }
        elements.filter { it !in existingItems }.forEach { view.addTask(Task(it, noteId)) }
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

    fun saveChanges() {
        val tasks = view.getItems().map { it.task }
        disposable.add(
            Completable.fromAction { dao.upsert(tasks) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ speak("ok") }, { error -> handleError(error) })
        )
    }
}