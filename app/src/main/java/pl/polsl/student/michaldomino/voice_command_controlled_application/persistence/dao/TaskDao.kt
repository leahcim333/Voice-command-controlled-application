package pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import pl.polsl.student.michaldomino.voice_command_controlled_application.persistence.model.Task

@Dao
interface TaskDao {

    @Insert
    fun insert(note: Task): Completable

    @Update
    fun update(note: Task)

    @Delete
    fun delete(note: Task)

    @Query("SELECT * FROM tasks")
    fun findAll(): Single<List<Task>>
}