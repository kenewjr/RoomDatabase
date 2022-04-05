package and5.abrar.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class DbStudent : RoomDatabase(){

    abstract fun studentDao():StudentDao

    companion object{
        private var INSTANCE : DbStudent? = null
        fun getInstance(context : Context):DbStudent? {
            if (INSTANCE == null){
                synchronized(DbStudent::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DbStudent::class.java,"Student.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}