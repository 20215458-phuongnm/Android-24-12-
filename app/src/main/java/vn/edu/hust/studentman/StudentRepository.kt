package vn.edu.hust.studentman

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudentRepository(context: Context) {

    private val studentDao = AppDatabase.getDatabase(context).studentDao()

    suspend fun insertStudent(student: StudentEntity): Long {
        return withContext(Dispatchers.IO) {
            studentDao.insertStudent(student)
        }
    }

    suspend fun updateStudent(student: StudentEntity): Int {
        return withContext(Dispatchers.IO) {
            studentDao.updateStudent(student)
        }
    }

    suspend fun deleteStudent(student: StudentEntity) {
        withContext(Dispatchers.IO) {
            studentDao.deleteStudent(student)
        }
    }

    fun getAllStudents(): List<StudentEntity> {
        return studentDao.getAllStudents()
    }
}
