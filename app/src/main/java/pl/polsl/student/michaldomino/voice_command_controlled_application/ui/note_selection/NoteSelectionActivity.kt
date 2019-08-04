package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.note_selection

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_note_selection.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R

class NoteSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_selection)
        setSupportActionBar(toolbar)
    }
}
