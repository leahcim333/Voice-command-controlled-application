package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import kotlinx.android.synthetic.main.activity_note_selection.*
import kotlinx.android.synthetic.main.content_parent.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.CommandActivatorGestureListener
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.CommandRecognizer
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.note_selection.NoteSelectionItemsManager
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.note_selection.NoteType

class NoteSelectionActivity : AppCompatActivity(), NoteSelectionContract.View {

    private lateinit var presenter: NoteSelectionContract.Presenter

    private lateinit var mDetector: GestureDetectorCompat

    private lateinit var parentLinearLayout: LinearLayout

    private lateinit var noteSelectionItemsManager: NoteSelectionItemsManager

    private lateinit var speaker: Speaker

    private lateinit var commandRecognizer: CommandRecognizer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_selection)
        setSupportActionBar(toolbar)

        presenter = NoteSelectionPresenter(this)
        mDetector = GestureDetectorCompat(this, CommandActivatorGestureListener(this))
        parentLinearLayout = findViewById(R.id.parent_linear_layout)
        noteSelectionItemsManager = NoteSelectionItemsManager(layoutInflater, parentLinearLayout)
        speaker = Speaker(applicationContext)
        commandRecognizer = CommandRecognizer(this)

        noteSelectionItemsManager.addRow("first list", NoteType.TASK_LIST)
        noteSelectionItemsManager.addRow("second list", NoteType.TASK_LIST)
        noteSelectionItemsManager.addRow("note", NoteType.TEXT_NOTE)

        clickableScreenView.setOnTouchListener { _, event -> mDetector.onTouchEvent(event) }
        presenter.start()
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

    override fun addNote(name: String, type: NoteType) {
        noteSelectionItemsManager.addRow(name, type)
    }

}
