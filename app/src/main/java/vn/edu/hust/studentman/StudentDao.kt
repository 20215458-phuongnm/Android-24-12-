package vn.edu.hust.studentman

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {

    @Insert
    suspend fun insertStudent(student: StudentEntity): Long

    @Update
    suspend fun updateStudent(student: StudentEntity): Int

    @Delete
    suspend fun deleteStudent(student: StudentEntity)

    @Query("SELECT * FROM students")
    fun getAllStudents(): List<StudentEntity>
}
