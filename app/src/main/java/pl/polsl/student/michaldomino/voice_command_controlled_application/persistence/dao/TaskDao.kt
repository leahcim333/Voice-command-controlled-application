package pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.dao

import androidx.room.*
import io.reactivex.Single
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: List<Task>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM tasks")
    fun findAll(): Single<List<Task>>

    @Query("SELECT * FROM tasks WHERE note_id = :noteId")
    fun findAllByNoteId(noteId: Long): Single<List<Task>>

    @Query("DELETE FROM tasks WHERE note_id = :noteId")
    fun deleteAllByNoteId(noteId: Long)

    @Transaction
    fun saveChangesByNoteId(tasks: List<Task>, noteId: Long): List<Long> {
        deleteAllByNoteId(noteId)
        return insert(tasks)
    }
}