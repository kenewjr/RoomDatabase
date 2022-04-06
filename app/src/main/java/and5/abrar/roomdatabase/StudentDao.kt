package and5.abrar.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {
    @Insert
    fun insertStudent(student: Student) : Long

    @Query("SELECT * FROM Student")
    fun getAllStudent(): List<Student>
}