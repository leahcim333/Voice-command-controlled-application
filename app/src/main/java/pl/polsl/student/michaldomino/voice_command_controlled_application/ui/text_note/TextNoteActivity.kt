package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import kotlinx.android.synthetic.main.activity_text_note.*
import kotlinx.android.synthetic.main.content_parent.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.CommandActivatorGestureListener
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.CommandRecognizer
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.TextNote
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.text_note.TextNoteItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.text_note.TextNoteManager

class TextNoteActivity : AppCompatActivity(), TextNoteContract.View {

    private lateinit var presenter: TextNoteContract.Presenter

    private lateinit var mDetector: GestureDetectorCompat

    private lateinit var parentLinearLayout: LinearLayout

    private lateinit var textNoteManager: TextNoteManager

    private lateinit var speaker: Speaker

    private lateinit var commandRecognizer: CommandRecognizer

    private lateinit var text: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_note)
        setSupportActionBar(toolbar)

        val noteId = intent.getStringExtra("noteId").toLong()
        val noteName = intent.getStringExtra("noteName")
        title = noteName
        presenter = TextNotePresenter(this, noteId)
        mDetector = GestureDetectorCompat(this, CommandActivatorGestureListener(this))
        parentLinearLayout = findViewById(R.id.parent_linear_layout)
        speaker = Speaker(applicationContext)
        commandRecognizer = CommandRecognizer(this)

//        val inflater = layoutInflater
//        inflater.inflate(R.layout.text_note_view, parentLinearLayout)
        clickableScreenView.setOnTouchListener { _, event -> mDetector.onTouchEvent(event) }
        presenter.create()
    }

    override fun startListening() {
        commandRecognizer.startListening()
    }

    override fun onCommandRecognizerResults(bundle: Bundle) {
        presenter.processInput(bundle)
    }

    override fun onDoubleTap() {
        presenter.onDoubleTap()
    }

    override fun speakInForeground(message: String) {
        speaker.speakInForeground(message)
    }

    override fun textNoteItem(): TextNoteItem {
        return textNoteManager.item
    }

    override fun addText(text: String) {
        textNoteManager.addText(text)
//        val currentText = text_view.text
//        val textBuilder = StringBuilder()
//        textBuilder.append(currentText).append(text)
//        text_view.text = textBuilder.toString()
    }

    override fun setTextNote(textNote: TextNote) {
        textNoteManager = TextNoteManager(layoutInflater, parentLinearLayout, textNote)
    }

    override fun setText(text: String) {
        textNoteManager.setText(text)
    }

    override fun onSpeechRecognizerServerError() {
        presenter.handleServerError()
    }
}
