package and5.abrar.roomdatabase

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_adapter_student.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AdapterStudent(val listDataStudent: List<Student>):RecyclerView.Adapter<AdapterStudent.ViewHolder>() {
    private var mDBnew : DbStudent? = null
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterStudent.ViewHolder {
        val viewitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_adapter_student,parent, false)
        return ViewHolder(viewitem)
    }

    override fun onBindViewHolder(holder: AdapterStudent.ViewHolder, position: Int) {
        holder.itemView.tv_id_student.text = listDataStudent[position].id.toString()
        holder.itemView.tv_nama_student.text = listDataStudent[position].nama
        holder.itemView.tv_email_student.text = listDataStudent[position].email
        holder.itemView.delete.setOnClickListener {
            mDBnew = DbStudent.getInstance(it.context)
            AlertDialog.Builder(it.context)
                .setTitle("Hapus data")
                .setMessage("yakin hapus data")
                .setPositiveButton("ya"){
                    dialogInterface : DialogInterface, i : Int ->
                    GlobalScope.async {
                        val result = mDBnew?.studentDao()?.deleteStudent(listDataStudent[position])
                        (holder.itemView.context as MainActivity).runOnUiThread{
                            if (result != 0){
                                Toast.makeText(it.context, "data ${listDataStudent[position].nama} dihapus",Toast.LENGTH_LONG).show()
                                (holder.itemView.context as MainActivity).getDataStudent()
                            }else{
                                Toast.makeText(it.context, "gagal di hapus",Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
                .setNegativeButton("Tidak"){
                        dialogInterface : DialogInterface, i : Int ->
                    dialogInterface.dismiss()
                }
                .show()
        }
        holder.itemView.edit.setOnClickListener {
         val pindah = Intent(it.context, EditStudentActvty::class.java)
            pindah.putExtra("datastudent",listDataStudent[position])
            it.context.startActivity(pindah)
        }
    }

    override fun getItemCount(): Int {
        return listDataStudent.size
    }
}