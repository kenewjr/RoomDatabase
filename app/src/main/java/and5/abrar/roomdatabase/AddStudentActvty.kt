package and5.abrar.roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_student_actvty.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddStudentActvty : AppCompatActivity() {
    var dbstudent : DbStudent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student_actvty)
        dbstudent = DbStudent.getInstance(this)
        btnSave.setOnClickListener {
        GlobalScope.async {
            val nama = in_nama.text.toString()
            val email = in_email.text.toString()
            val hasil = dbstudent?.studentDao()?.insertStudent(Student(null,nama,email))

            runOnUiThread{
                if (hasil != 0.toLong()){
                    Toast.makeText(this@AddStudentActvty,"Success", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this@AddStudentActvty,"Failed", Toast.LENGTH_LONG).show()
                }

            }
        }
        }
    }
}