package pl.polsl.student.michaldomino.voice_command_controlled_application.data.dao

import androidx.room.*
import io.reactivex.Completable
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.Note

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note): Completable

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM notes")
    fun findAll(): List<Note>
}