package pl.polsl.student.michaldomino.voice_command_controlled_application.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.R
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.database.AppDatabase
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.Note
import pl.polsl.student.michaldomino.voice_command_controlled_application.view_model.note_selection.NoteType

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val disposable = CompositeDisposable()

        val db = AppDatabase.getInstance(this)

        val dao = db.noteDao()
        val note = Note("a", NoteType.TASK_LIST)
        presenter = MainPresenter(this)
        try {
            disposable.add(
                db.noteDao().insert(note)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )

            disposable.add(
                db.noteDao().findAll().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ presenter.doSth(it) }, { error -> presenter.doElse(error) })
            )
            val f = 0
        } catch (e: Exception) {
            val f = 0
        }
        checkPermission()


        presenter.start()
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

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName")
                )
                startActivity(intent)
                finish()
            }
        }
    }

    override fun startListening() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCommandRecognizerResults(bundle: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun speakInForeground(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDoubleTap() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
