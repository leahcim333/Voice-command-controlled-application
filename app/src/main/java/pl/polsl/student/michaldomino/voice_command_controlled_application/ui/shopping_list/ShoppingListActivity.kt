package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.support.v4.content.ContextCompat
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_shopping_list.*
import kotlinx.android.synthetic.main.content_parent.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.CommandActivatorGestureListener
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list.ShoppingListItem
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list.ShoppingListItemsManager
import java.util.*


class ShoppingListActivity : AppCompatActivity(), ShoppingListContract.View {

    private lateinit var presenter: ShoppingListContract.Presenter

    private lateinit var mDetector: GestureDetectorCompat

    private lateinit var parentLinearLayout: LinearLayout

    private lateinit var shoppingListItemsManager: ShoppingListItemsManager

    private lateinit var mSpeechRecognizer: SpeechRecognizer

    private lateinit var mSpeechRecognizerIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)
        setSupportActionBar(toolbar)

        checkPermission()

        parentLinearLayout = findViewById(R.id.parent_linear_layout)
        shoppingListItemsManager = ShoppingListItemsManager(layoutInflater, parentLinearLayout)

        shoppingListItemsManager.addRow("one")
        shoppingListItemsManager.addRow("two")
        shoppingListItemsManager.addRow("elephant")
        shoppingListItemsManager.addRow("dog")

        presenter = ShoppingListPresenter(this)
        mDetector = GestureDetectorCompat(this, CommandActivatorGestureListener(presenter))
        clickableScreenView.setOnTouchListener { _, event -> mDetector.onTouchEvent(event) }
//        presenter.start()

        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)

        mSpeechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mSpeechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        mSpeechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            Locale.getDefault()
        )

        mSpeechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(bundle: Bundle) {

            }

            override fun onBeginningOfSpeech() {

            }

            override fun onRmsChanged(v: Float) {

            }

            override fun onBufferReceived(bytes: ByteArray) {

            }

            override fun onEndOfSpeech() {

            }

            override fun onError(i: Int) {

            }

            override fun onResults(bundle: Bundle) {
                //getting all the matches
                val matches = bundle
                    .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)

                //displaying the first match
                if (matches != null)
                    presenter.speak(matches[0])
            }

            override fun onPartialResults(bundle: Bundle) {

            }

            override fun onEvent(i: Int, bundle: Bundle) {

            }
        })
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:$packageName")
                )
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onDoubleTap() {
        mSpeechRecognizer.startListening(mSpeechRecognizerIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && null != data) {
            presenter.processInput(data)
        }
    }

    override fun addRow(text: CharSequence) {
        shoppingListItemsManager.addRow(text)
    }

    override fun getItems(): MutableList<ShoppingListItem> {
        return shoppingListItemsManager.items
    }

    override fun setNewItemName(item: ShoppingListItem, newName: String) {
        item.setText(newName)
    }
}
