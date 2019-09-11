package pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.dao.NoteDao
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.dao.TaskDao
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.dao.TextNoteDao
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Converters
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Note
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Task
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.TextNote


@Database(entities = arrayOf(Note::class, Task::class, TextNote::class), version = 4)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    abstract fun taskDao(): TaskDao

    abstract fun textNoteDao(): TextNoteDao

    companion object {

        private const val DATABASE_NAME = "voice_commands.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, DATABASE_NAME
            ).fallbackToDestructiveMigration() // TODO: Remove when finished.
                .build()
    }
}