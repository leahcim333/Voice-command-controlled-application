package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.shopping_list

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_shopping_list.*
import kotlinx.android.synthetic.main.content_parent.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.CommandActivatorGestureListener
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.shopping_list.RowItemsManager

class ShoppingListActivity : AppCompatActivity(), ShoppingListContract.View {

    private lateinit var presenter: ShoppingListContract.Presenter

    private lateinit var mDetector: GestureDetectorCompat

    private lateinit var parentLinearLayout: LinearLayout

    private lateinit var rowItemsManager: RowItemsManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)
        setSupportActionBar(toolbar)

        parentLinearLayout = findViewById(R.id.parent_linear_layout)
        rowItemsManager = RowItemsManager(layoutInflater, parentLinearLayout)
        presenter = ShoppingListPresenter(this)
        mDetector = GestureDetectorCompat(this, CommandActivatorGestureListener(presenter))
        clickableScreenView.setOnTouchListener { _, event -> mDetector.onTouchEvent(event) }
        presenter.start()
    }

    override fun startSpeechRecognizer(requestCode: Int, message: String?) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, message)
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

    override fun addRow(text: CharSequence) {
        rowItemsManager.addRow(text)
//        val inflater: LayoutInflater = layoutInflater
//        val row = RowItem(inflater).setText(text).setChecked(false)
//        parentLinearLayout.addView(row.getView(), parentLinearLayout.childCount)
    }

}
