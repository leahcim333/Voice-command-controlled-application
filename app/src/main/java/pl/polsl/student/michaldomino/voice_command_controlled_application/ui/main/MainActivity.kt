package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    private lateinit var mDetector: GestureDetectorCompat

    private lateinit var parentLinearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        mDetector = GestureDetectorCompat(this, MyGestureListener(presenter))

        parentLinearLayout = findViewById(R.id.parent_linear_layout)

        clickableScreenView.setOnTouchListener { _, event -> mDetector.onTouchEvent(event) }
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

    override fun showMessage(message: CharSequence) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun addRow(text: CharSequence) {
        val inflater: LayoutInflater = layoutInflater
        val rowView: View = inflater.inflate(R.layout.check_list_row, null)
        rowView.findViewById<TextView>(R.id.row_text).text = text
        parentLinearLayout.addView(rowView, parentLinearLayout.childCount)
    }

    private class MyGestureListener(val presenter: MainContract.Presenter) : GestureDetector.SimpleOnGestureListener() {

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            presenter.onDoubleTap()
            return true
        }
    }

}
