package pl.polsl.student.michaldomino.voice_command_controlled_application.data.dao

import androidx.room.*
import pl.polsl.student.michaldomino.voice_command_controlled_application.data.model.Note

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM notes")
    fun findAll(): List<Note>
}