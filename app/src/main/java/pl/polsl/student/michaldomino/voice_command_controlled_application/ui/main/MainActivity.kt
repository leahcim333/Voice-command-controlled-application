package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.view.GestureDetector
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main.MainContract.Presenter
import pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main.MainContract.View

class MainActivity : AppCompatActivity(), View {


    private lateinit var presenter: Presenter

    private lateinit var mDetector: GestureDetectorCompat

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private class MyGestureListener(_presenter: Presenter) : GestureDetector.SimpleOnGestureListener() {

        private var presenter: Presenter = _presenter

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            presenter.a()
            return true
        }
    }

}
