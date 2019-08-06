package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note

import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.android.synthetic.main.content_parent.*
import kotlinx.android.synthetic.main.note_view.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.CommandActivatorGestureListener
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.CommandRecognizer
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.Speaker

class NoteActivity : AppCompatActivity(), NoteContract.View {

    private val presenter: NoteContract.Presenter = NotePresenter(this)

    private val mDetector: GestureDetectorCompat =
        GestureDetectorCompat(this, CommandActivatorGestureListener(presenter))

    private val parentLinearLayout: LinearLayout = findViewById(R.id.parent_linear_layout)

    private val speaker: Speaker = Speaker(applicationContext)

    private val commandRecognizer: CommandRecognizer = CommandRecognizer(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(toolbar)

        clickableScreenView.setOnTouchListener { _, event -> mDetector.onTouchEvent(event) }
        presenter.start()

        val inflater = layoutInflater
        inflater.inflate(R.layout.note_view, parentLinearLayout)
    }

    override fun startListening() {
        commandRecognizer.startListening()
    }

    override fun onCommandRecognizerResults(bundle: Bundle) {
        presenter.processInput(bundle)
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

}
