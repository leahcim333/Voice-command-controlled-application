package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_note_selection.*
import kotlinx.android.synthetic.main.content_parent.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.CommandActivatorGestureListener
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.CommandRecognizer
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.note_selection.NoteSelectionItemsManager
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.note_selection.NoteType

class NoteSelectionActivity : AppCompatActivity(), NoteSelectionContract.View {

    private val presenter: NoteSelectionContract.Presenter = NoteSelectionPresenter(this)

    private val mDetector: GestureDetectorCompat =
        GestureDetectorCompat(this, CommandActivatorGestureListener(presenter))

    private val parentLinearLayout: LinearLayout = findViewById(R.id.parent_linear_layout)

    private val noteSelectionItemsManager = NoteSelectionItemsManager(layoutInflater, parentLinearLayout)

    private val speaker: Speaker = Speaker(applicationContext)

    private val commandRecognizer: CommandRecognizer = CommandRecognizer(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_selection)
        setSupportActionBar(toolbar)

        noteSelectionItemsManager.addRow("first list", NoteType.SHOPPING_LIST)
        noteSelectionItemsManager.addRow("second list", NoteType.SHOPPING_LIST)
        noteSelectionItemsManager.addRow("note", NoteType.NOTE)

        clickableScreenView.setOnTouchListener { _, event -> mDetector.onTouchEvent(event) }
        presenter.start()
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

    override fun addNote(name: String, type: NoteType) {
        noteSelectionItemsManager.addRow(name, type)
    }

}
