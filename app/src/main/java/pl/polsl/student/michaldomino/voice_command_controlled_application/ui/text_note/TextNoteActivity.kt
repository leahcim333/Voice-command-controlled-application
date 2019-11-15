package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.text_note

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GestureDetectorCompat
import kotlinx.android.synthetic.main.activity_text_note.*
import kotlinx.android.synthetic.main.content_parent.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.CommandRecognizer
import pl.polsl.student.michaldomino.voice_command_controlled_application.logic.activity_actions.DoubleTapListener
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

    companion object {
        const val PERMISSIONS_REQUEST_RECORD_AUDIO = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_note)
        setSupportActionBar(toolbar)

        val noteId = intent.getStringExtra("noteId").toLong()
        val noteName = intent.getStringExtra("noteName")
        title = noteName

        presenter = TextNotePresenter(this, noteId)
        mDetector = GestureDetectorCompat(this, DoubleTapListener(this))
        parentLinearLayout = findViewById(R.id.parent_linear_layout)
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
        presenter.onDoubleTap()
    }

    override fun speakAndRunAction(message: String, function: () -> Unit) {
        speaker.speakInForeground(message)
    }

    override fun textNoteItem(): TextNoteItem {
        return textNoteManager.item
    }

    override fun addText(text: String) {
        textNoteManager.addText(text)
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
