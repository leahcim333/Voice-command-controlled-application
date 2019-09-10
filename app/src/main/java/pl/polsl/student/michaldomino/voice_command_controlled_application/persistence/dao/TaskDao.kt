package pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.dao

import androidx.room.*
import io.reactivex.Single
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Task

@Dao
interface TaskDao {

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insert(task: Task): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(task: List<Task>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM tasks")
    fun findAll(): Single<List<Task>>

    @Query("SELECT * FROM tasks WHERE note_id = :noteId")
    fun findAllByNoteId(noteId: Long): Single<List<Task>>

    @Transaction
    fun upsert(tasks: List<Task>) {
        val rowIds = insert(tasks)
        val tasksToUpdate =
            rowIds.mapIndexedNotNull { index, rowId -> if (rowId == -1L) null else tasks[index] }
        tasksToUpdate.forEach { update(it) }
    }

//    fun upsertAsync(tasks: List<Task>): Completable {
//        return Completable.fromAction { upsert(tasks) }
//    }
}