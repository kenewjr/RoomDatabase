package and5.abrar.roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var mDBnew : DbStudent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDBnew=DbStudent.getInstance(this)
        getDataStudent()

        fab_add.setOnClickListener{
            startActivity(Intent(this,AddStudentActvty::class.java))
        }
    }
    fun getDataStudent(){
    rv_student.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    GlobalScope.launch {
        val listdata  =  mDBnew?.studentDao()?.getAllStudent()
        runOnUiThread {
            listdata.let {
                val adapt = AdapterStudent(it!!)
                rv_student.adapter = adapt
            }
        }
    }
    }

    override fun onResume() {
        super.onResume()
        getDataStudent()
    }

    override fun onDestroy() {
        super.onDestroy()
        DbStudent.destroyInstance()
    }
}