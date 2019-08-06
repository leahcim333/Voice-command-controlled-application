package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_note_selection.*
import kotlinx.android.synthetic.main.content_parent.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic.activity_actions.CommandActivatorGestureListener
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.note_selection.NoteSelectionItemsManager
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.note_selection.NoteType

class NoteSelectionActivity : AppCompatActivity(), NoteSelectionContract.View {

    private lateinit var presenter: NoteSelectionContract.Presenter

    private lateinit var mDetector: GestureDetectorCompat

    private lateinit var parentLinearLayout: LinearLayout

    private lateinit var noteSelectionItemsManager: NoteSelectionItemsManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_selection)
        setSupportActionBar(toolbar)

        parentLinearLayout = findViewById(R.id.parent_linear_layout)
        noteSelectionItemsManager = NoteSelectionItemsManager(layoutInflater, parentLinearLayout)

        noteSelectionItemsManager.addRow("first list", NoteType.SHOPPING_LIST)
        noteSelectionItemsManager.addRow("second list", NoteType.SHOPPING_LIST)
        noteSelectionItemsManager.addRow("note", NoteType.NOTE)

        presenter = NoteSelectionPresenter(this)
        mDetector = GestureDetectorCompat(this, CommandActivatorGestureListener(presenter))
        clickableScreenView.setOnTouchListener { _, event -> mDetector.onTouchEvent(event) }
        presenter.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && null != data) {
            presenter.processInput(data)
        }
    }

    override fun addNote(name: String, type: NoteType) {
        noteSelectionItemsManager.addRow(name, type)
    }

}
