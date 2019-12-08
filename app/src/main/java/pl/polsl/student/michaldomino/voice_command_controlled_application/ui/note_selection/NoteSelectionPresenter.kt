package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import android.content.Intent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.layout_managers.note_selection.NoteSelectionItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.layout_managers.note_selection.NoteType
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.Word
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.BaseCommandState
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.base.CSRoot
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.command_states.note_selection.model.NoteSelectionCommandStatesModel
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.dao.NoteDao
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.database.AppDatabase
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Note
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.base.VoiceCommandsPresenterImpl
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.task_list.TaskListActivity
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note.TextNoteActivity

class NoteSelectionPresenter(override val view: NoteSelectionContract.View) :
    VoiceCommandsPresenterImpl(view),
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

    private fun addNote(userInput: String, noteType: NoteType) {
        val existingItems: List<String> = view.getItems().map { it.name }
        if (existingItems.contains(userInput)) {
            speak(getString(R.string.note_already_exists))
        } else {
            val newNote = Note(userInput, noteType)
            disposable.add(
                dao.insert(newNote)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { id -> assignNewIdAndAddToView(newNote, id) },
                        { error -> handleError(error) })
            )
        }
    }

    private fun assignNewIdAndAddToView(note: Note, assignedId: Long) {
        note.id = assignedId
        view.addNote(note)
    }

    private fun listNotes(items: List<NoteSelectionItem>, itemDelimiter: String = "... ") {
        val messageBuilder = StringBuilder()
        items.forEach { messageBuilder.append(it.name).append(itemDelimiter) }
        speak(messageBuilder.toString())
    }

    fun addTaskList(userInput: String) {
        addNote(userInput, NoteType.TASK_LIST)
    }

    fun addTextNote(userInput: String) {
        addNote(userInput, NoteType.TEXT_NOTE)
    }

    fun openNote(userInput: String) {
        val selectedNote = Word(userInput)
        val mostSimilarNote: NoteSelectionItem? =
            selectedNote.getMostSimilar(view.getItems(), { it.name })
        if (mostSimilarNote != null) {
            val note = mostSimilarNote.note
            val cls: Class<out Any> = when (note.type) {
                NoteType.TASK_LIST -> TaskListActivity::class.java
                NoteType.TEXT_NOTE -> TextNoteActivity::class.java
            }
            val intent = Intent(view.getApplicationContext(), cls)
            intent.putExtra("noteId", note.id.toString())
            intent.putExtra("noteName", note.name)
            view.startActivity(intent)
        } else {
            speak(getString(R.string.note_does_not_exist))
        }

    }

    fun deleteNote(userInput: String) {
        val selectedNote = Word(userInput)
        val mostSimilarNote: NoteSelectionItem? =
            selectedNote.getMostSimilar(view.getItems(), { it.name })
        if (mostSimilarNote != null) {
            val noteToDelete = mostSimilarNote.note
            disposable.add(
                dao.delete(noteToDelete)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { view.deleteNote(mostSimilarNote) },
                        { error -> handleError(error) })
            )
        } else {
            speak(getString(R.string.note_does_not_exist))
        }
    }

    fun getItems(): List<NoteSelectionItem> {
        return view.getItems()
    }

    fun renameNote(selectedNote: NoteSelectionItem, newName: String) {
        val existingNotes = view.getItems().map { it.name }
        if (newName !in existingNotes) {
            val noteToChange = selectedNote.note
            noteToChange.name = newName
            disposable.add(
                dao.update(selectedNote.note)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ selectedNote.setName(newName) }, { error -> handleError(error) })
            )
        } else {
            speak(getString(R.string.note_already_exists))
        }
    }

    fun listAllNotes(itemDelimiter: String = "... ") {
        val notes: List<NoteSelectionItem> = getItems()
        listNotes(notes, itemDelimiter)
    }

    fun listTaskLists(itemDelimiter: String = "... ") {
        val notes: List<NoteSelectionItem> = getItems().filter { it.type == NoteType.TASK_LIST }
        listNotes(notes, itemDelimiter)
    }

    fun listTextNotes(itemDelimiter: String = "... ") {
        val notes: List<NoteSelectionItem> = getItems().filter { it.type == NoteType.TEXT_NOTE }
        listNotes(notes, itemDelimiter)
    }
}