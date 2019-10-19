package pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.TextNote

@Dao
interface TextNoteDao {

    @Insert
    fun insert(note: TextNote): Single<Long>

    @Update
    fun update(note: TextNote): Completable

    @Delete
    fun delete(note: TextNote)

    @Query("SELECT * FROM text_notes")
    fun findAll(): Single<List<TextNote>>

    @Query("SELECT * FROM text_notes WHERE note_id = :noteId")
    fun findByNoteId(noteId: Long): Single<TextNote?>
}