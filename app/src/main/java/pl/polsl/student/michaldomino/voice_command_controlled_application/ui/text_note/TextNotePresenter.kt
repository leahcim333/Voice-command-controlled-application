package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.text_note.model.TextNoteCommandStatesModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.dao.TextNoteDao
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.database.AppDatabase
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenter

class TextNotePresenter(override val view: TextNoteContract.View, val noteId: Long) :
    VoiceCommandsPresenter(view),
    TextNoteContract.Presenter {

    override val initialState: CSRoot = CSRoot(this, TextNoteCommandStatesModel(this))

    override var currentState: BaseCommandState = initialState

    private lateinit var dao: TextNoteDao

    private val disposable = CompositeDisposable()

    private var textInDatabase: String? = null

    override fun create() {
        dao = AppDatabase.getInstance(view.getApplicationContext()).textNoteDao()
        loadTextNote()
    }

    override fun stop() {
        disposable.clear()
    }

    private fun handleError(error: Throwable?) {
        view.showToast(error?.localizedMessage)
    }

    private fun loadTextNote() {
        disposable.add(
            dao.findByNoteId(noteId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ textNote ->
                    view.addText(textNote.text).also { textInDatabase = textNote.text }
                }, { error -> handleError(error) })
        )
    }

    override fun speak(message: String) {
        view.speakInForeground(message)
    }

    override fun addText(text: String) {
        view.addText(text)
    }

    fun readText() {
        val text: String = view.getText()
    }

}
