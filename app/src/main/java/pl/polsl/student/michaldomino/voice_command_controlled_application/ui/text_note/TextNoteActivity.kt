package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import kotlinx.android.synthetic.main.activity_text_note.*
import kotlinx.android.synthetic.main.content_parent.*
import kotlinx.android.synthetic.main.text_note_view.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.CommandActivatorGestureListener
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.CommandRecognizer
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.Speaker

class TextNoteActivity : AppCompatActivity(), TextNoteContract.View {

    private lateinit var presenter: TextNoteContract.Presenter

    private lateinit var mDetector: GestureDetectorCompat

    private lateinit var parentLinearLayout: LinearLayout

    private lateinit var speaker: Speaker

    private lateinit var commandRecognizer: CommandRecognizer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_note)
        setSupportActionBar(toolbar)

        presenter = TextNotePresenter(this)
        mDetector = GestureDetectorCompat(this, CommandActivatorGestureListener(this))
        parentLinearLayout = findViewById(R.id.parent_linear_layout)
        speaker = Speaker(applicationContext)
        commandRecognizer = CommandRecognizer(this)

        clickableScreenView.setOnTouchListener { _, event -> mDetector.onTouchEvent(event) }
        presenter.create()

        val inflater = layoutInflater
        inflater.inflate(R.layout.text_note_view, parentLinearLayout)
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

    override fun addText(text: String) {
        val currentText = text_field.text
        val textBuilder = StringBuilder()
        textBuilder.append(currentText).append(text)
        text_field.text = textBuilder.toString()
    }

    override fun onSpeechRecognizerServerError() {
        presenter.handleServerError()
    }
}
