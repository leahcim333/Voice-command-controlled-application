package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection.model.NoteSelectionCommandStatesModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.dao.NoteDao
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.database.AppDatabase
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Note
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.note_selection.NoteType

class NoteSelectionPresenter(override val view: NoteSelectionContract.View) :
    VoiceCommandsPresenter(view),
    NoteSelectionContract.Presenter {

    override val initialState: CSRoot = CSRoot(this, NoteSelectionCommandStatesModel(this))

    override var currentState: BaseCommandState = initialState

    private val disposable = CompositeDisposable()

    private lateinit var dao: NoteDao

    override fun create() {
        dao = AppDatabase.getInstance(view.getApplicationContext()).noteDao()
        disposable.add(
            dao.findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ notes -> addNotesToView(notes) }, { error -> handleError(error) })
        )
    }

    override fun stop() {
        disposable.clear()
    }

    private fun addNotesToView(notes: List<Note>?) {
        notes?.forEach { view.addNote(it) }
    }

    private fun handleError(error: Throwable?) {
        view.showToast(error?.localizedMessage)
    }

    override fun addTaskList(userInput: String) {
        val newNote = Note(userInput, NoteType.TASK_LIST)
        view.addNote(newNote)
    }

    fun addTextNote(userInput: String) {
        val existingItems: List<String> = view.getItems().map { it.name }
        if (existingItems.contains(userInput)) {
            view.speakInForeground(getString(R.string.item_already_exists))
        } else {
            val newNote = Note(userInput, NoteType.TEXT_NOTE)
            disposable.add(
                dao.insert(newNote)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ view.addNote(newNote) }, { error -> handleError(error) })
            )
        }
    }

    override fun openNote(userInput: String) {

    }

    override fun speak(message: String) {
        view.speakInForeground(message)
    }
}