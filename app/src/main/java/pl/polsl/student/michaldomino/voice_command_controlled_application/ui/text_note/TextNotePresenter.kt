package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.text_note.model.TextNoteCommandStatesModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.dao.TextNoteDao
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.database.AppDatabase
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.TextNote
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenter

class TextNotePresenter(override val view: TextNoteContract.View, val noteId: Long) :
    VoiceCommandsPresenter(view),
    TextNoteContract.Presenter {

    override val initialState: CSRoot = CSRoot(this, TextNoteCommandStatesModel(this))

    override var currentState: BaseCommandState = initialState

    private lateinit var dao: TextNoteDao

    private val disposable = CompositeDisposable()

    private lateinit var textNoteInDatabase: TextNote

    override fun create() {
        dao = AppDatabase.getInstance(view.getApplicationContext()).textNoteDao()
        loadTextNote()
    }

    override fun stop() {
        disposable.clear()
    }

    private fun loadTextNote() {
        disposable.add(
            dao.findByNoteId(noteId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { textNote -> validateTextNote(textNote) },
                    { error -> handleError(error) })
        )
    }

    private fun handleError(error: Throwable?) {
        view.showToast(error?.localizedMessage)
    }

    private fun validateTextNote(textNote: TextNote?) {
        if (textNote != null) {
            textNoteInDatabase = textNote
            passToView(textNote)
        } else {
            val newTextNote = TextNote(noteId)
            disposable.add(
                dao.insert(newTextNote)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ id ->
                        passToView(newTextNote).also {
                            textNoteInDatabase = newTextNote
                        }
                    }, { error -> handleError(error) })
            )

        }
    }

    private fun passToView(textNote: TextNote) {
        view.setTextNote(textNote)
    }

    override fun speak(message: String) {
        view.speakInForeground(message)
    }

    override fun addText(text: String) {
        view.addText(text)
    }

    fun readText() {
        view.speakInForeground(view.textNoteItem().text)
    }

    fun saveChanges() {
        val textNote = view.textNoteItem().textNote
        if (textNote.text != textNoteInDatabase.text) {
            disposable.add(
                dao.update(textNoteInDatabase)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { textNoteInDatabase.text = textNote.text },
                        { error -> handleError(error) })
            )
        } else {
            speak(getString(R.string.no_changes))
        }
    }

    fun discardChanges() {
        val textNote = view.textNoteItem()
        if (textNote.text != textNoteInDatabase.text) {
            view.setText(textNoteInDatabase.text)
        } else {
            speak(getString(R.string.no_changes))
        }
    }

}
