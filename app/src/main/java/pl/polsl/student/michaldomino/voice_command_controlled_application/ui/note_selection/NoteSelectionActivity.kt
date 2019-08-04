package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_note_selection.*
import kotlinx.android.synthetic.main.content_parent.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.CommandActivatorGestureListener

class NoteSelectionActivity : AppCompatActivity(), NoteSelectionContract.View {

    private lateinit var presenter: NoteSelectionContract.Presenter

    private lateinit var mDetector: GestureDetectorCompat

    private lateinit var parentLinearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_selection)
        setSupportActionBar(toolbar)

        parentLinearLayout = findViewById(R.id.parent_linear_layout)
        presenter = NoteSelectionPresenter(this)
        mDetector = GestureDetectorCompat(this, CommandActivatorGestureListener(presenter))
        clickableScreenView.setOnTouchListener { _, event -> mDetector.onTouchEvent(event) }
        presenter.start()
    }

    override fun startSpeechRecognizer(requestCode: Int, messageId: Int) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, resources.getString(messageId))
        try {
            startActivityForResult(intent, requestCode)
        } catch (e: ActivityNotFoundException) {
            showToast(e.message)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && null != data) {
            presenter.processInput(data)
        }
    }

}
