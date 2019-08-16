package pl.polsl.student.michaldomino.voice_command_controlled_application.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.dao.NoteDao
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.Converters
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.Note

@Database(entities = arrayOf(Note::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}