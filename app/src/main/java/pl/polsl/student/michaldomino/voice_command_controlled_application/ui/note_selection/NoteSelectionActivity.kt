package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GestureDetectorCompat
import kotlinx.android.synthetic.main.activity_note_selection.*
import kotlinx.android.synthetic.main.content_parent.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.CommandRecognizer
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.DoubleTapListener
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.Speaker
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Note
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.note_selection.NoteSelectionItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.note_selection.NoteSelectionItemsManager

class NoteSelectionActivity : AppCompatActivity(), NoteSelectionContract.View {

    private lateinit var presenter: NoteSelectionContract.Presenter

    private lateinit var mDetector: GestureDetectorCompat

    private lateinit var parentLinearLayout: LinearLayout

    private lateinit var noteSelectionItemsManager: NoteSelectionItemsManager

    private lateinit var speaker: Speaker

    private lateinit var commandRecognizer: CommandRecognizer

    companion object {
        const val PERMISSIONS_REQUEST_RECORD_AUDIO = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_selection)
        setSupportActionBar(toolbar)

        presenter = NoteSelectionPresenter(this)
        mDetector = GestureDetectorCompat(this, DoubleTapListener(this))
        parentLinearLayout = findViewById(R.id.parent_linear_layout)
        noteSelectionItemsManager = NoteSelectionItemsManager(layoutInflater, parentLinearLayout)
        commandRecognizer = CommandRecognizer(this)
        clickableScreenView.setOnTouchListener { _, event -> mDetector.onTouchEvent(event) }
        speaker = Speaker(this)
    }

    override fun onSpeakerReady() {
        presenter.create()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            PERMISSIONS_REQUEST_RECORD_AUDIO
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSIONS_REQUEST_RECORD_AUDIO -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    presenter.onPermissionGranted()
                } else {
                    presenter.onPermissionDenied()
                }
                return
            }
        }
    }

    override fun startListening() {
        commandRecognizer.startListening()
    }

    override fun onCommandRecognizerResults(bundle: Bundle) {
        presenter.processInput(bundle)
    }

    override fun onDoubleTap() {
        speaker.stopSpeaking()
        commandRecognizer.cancelListening()
        presenter.onDoubleTap()
    }

    override fun speakAndRunAction(message: String, function: () -> Unit) {
        speaker.speakAndRunAction(message, function)
//        speaker.speakAndRunAction(message)
    }

    override fun addNote(note: Note) {
        noteSelectionItemsManager.addNote(note)
    }

    override fun getItems(): List<NoteSelectionItem> {
        return noteSelectionItemsManager.items
    }

    override fun deleteNote(noteSelectionItem: NoteSelectionItem) {
        noteSelectionItemsManager.deleteNote(noteSelectionItem)
    }

    override fun onSpeechRecognizerServerError() {
        presenter.handleServerError()
    }
}
