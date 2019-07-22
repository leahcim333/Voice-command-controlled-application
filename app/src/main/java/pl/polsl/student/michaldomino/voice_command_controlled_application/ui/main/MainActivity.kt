package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.support.design.widget.Snackbar
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_parent.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.gesture_listener.CommandActivatorGestureListener
import java.util.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    private lateinit var mDetector: GestureDetectorCompat

    private lateinit var parentLinearLayout: LinearLayout

    private lateinit var mTTS: TextToSpeech

    private val RESULT_CODE_SPEECH_RECOGNIZER = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        mDetector = GestureDetectorCompat(this, CommandActivatorGestureListener(presenter))

        parentLinearLayout = findViewById(R.id.parent_linear_layout)

        clickableScreenView.setOnTouchListener { _, event -> mDetector.onTouchEvent(event) }

        mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                //if there is no error then set language
                mTTS.language = Locale.getDefault()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun startCommandRecognizer() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, resources.getString(R.string.command_recognizer_message))
        try {
            startActivityForResult(intent, RESULT_CODE_SPEECH_RECOGNIZER)
        } catch (e: ActivityNotFoundException) {
            showToast(e.message)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && null != data) {
            when (requestCode) {
                RESULT_CODE_SPEECH_RECOGNIZER -> presenter.runCommand(data)
                else -> {
                }
            }
        }
    }

    override fun speak(command: String?) {
        mTTS.speak(command, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun addRow(text: CharSequence) {
        val inflater: LayoutInflater = layoutInflater
        val rowView: View = inflater.inflate(R.layout.check_list_row, null)
        rowView.findViewById<TextView>(R.id.row_text).text = text
        parentLinearLayout.addView(rowView, parentLinearLayout.childCount)
    }

}
