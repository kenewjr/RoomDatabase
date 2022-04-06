package and5.abrar.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_student_actvty.*
import kotlinx.android.synthetic.main.activity_edit_student_actvty.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditStudentActvty : AppCompatActivity() {
    private var dbnew : DbStudent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student_actvty)
        dbnew = DbStudent.getInstance(this)
        val getDataStd = intent.getParcelableExtra<Student>("datastudent")as Student
        ed_nama.setText(getDataStd.nama)
        ed_email.setText(getDataStd.email)
        btnEdit.setOnClickListener {
            getDataStd.nama = ed_nama.text.toString()
            getDataStd.email = ed_email.text.toString()
            GlobalScope.async {
                val perintah = dbnew?.studentDao()?.updateStudent(getDataStd)

                runOnUiThread {
                    if (perintah != 0){
                        Toast.makeText(this@EditStudentActvty,"data ${getDataStd.nama} berhasil di edit",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@EditStudentActvty,"data gagal edit",Toast.LENGTH_LONG).show()
                    }
                    finish()
                }
            }
        }
    }
}