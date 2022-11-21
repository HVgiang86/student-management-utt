package vn.edu.utt.uttqlsv.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.model.Gender
import vn.edu.utt.uttqlsv.model.Student

class StudentListAdapter(
    private var context: Context,
    private var studentList: MutableList<Student>,
    private var selectionList: BooleanArray
) : RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {

    init {
        for (i in 0.until(studentList.size)) {
            selectionList[i] = false
        }
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentCodeTV: TextView = itemView.findViewById(R.id.student_code_tv)
        val studentNameTV: TextView = itemView.findViewById(R.id.student_name_tv)
        val genderTV: TextView = itemView.findViewById(R.id.gender_tv)
        val dateOfBirthTV: TextView = itemView.findViewById(R.id.date_of_birth_tv)
        val selectionCheckBox: CheckBox = itemView.findViewById(R.id.selection_checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.student_items, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.studentCodeTV.text = studentList[position].studentCode
        holder.studentNameTV.text = studentList[position].name
        if (studentList[position].gender == Gender.Male)
            holder.genderTV.text = "Male"
        else
            holder.genderTV.text = "Female"

        holder.dateOfBirthTV.text = "30/12/2002"

        holder.selectionCheckBox.setOnCheckedChangeListener { _, isChecked ->
            selectionList[position] = isChecked
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}