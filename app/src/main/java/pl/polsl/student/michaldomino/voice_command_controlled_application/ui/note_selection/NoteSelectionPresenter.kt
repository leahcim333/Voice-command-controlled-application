package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection.NoteSelectionInitialCS
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.database.AppDatabase
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Note
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.note_selection.NoteType

class NoteSelectionPresenter(override val view: NoteSelectionContract.View) :
    VoiceCommandsPresenter(view),
    NoteSelectionContract.Presenter {

    override val initialState: CSRoot = NoteSelectionInitialCS(this)

    override var currentState: BaseCommandState = initialState

    private val speaker: Speaker = Speaker(view.getApplicationContext())

    private val disposable = CompositeDisposable()

    override fun start() {
        val db = AppDatabase.getInstance(view.getApplicationContext())
        disposable.add(
            db.noteDao().findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ notes -> addNotes(notes) }, { error -> handleError(error) })
        )
    }

    override fun stop() {
        disposable.clear()
    }

    private fun addNotes(notes: List<Note>?) {
        notes?.forEach { view.addNote(it) }
    }

    private fun handleError(error: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addTaskList(userInput: String) {
        val newNote = Note(userInput, NoteType.TASK_LIST)
        view.addNote(newNote)
    }

    override fun openNote(userInput: String) {

    }

    override fun speak(message: String) {
        speaker.speakInForeground(message)
    }
}