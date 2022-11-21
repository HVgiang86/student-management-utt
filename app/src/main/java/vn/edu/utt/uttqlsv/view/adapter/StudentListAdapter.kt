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
import vn.edu.utt.uttqlsv.view.activities.StudentList
import vn.edu.utt.uttqlsv.view.fragments.StudentDetail

@Suppress("DEPRECATION")
class StudentListAdapter(
    private var activity: StudentList,
    private var studentList: MutableList<Student>,
    private var selectionList: BooleanArray
) : RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {

    init {
        for (i in 0.until(studentList.size)) {
            selectionList[i] = false
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener,View.OnLongClickListener {
        val studentCodeTV: TextView = itemView.findViewById(R.id.student_code_tv)
        val studentNameTV: TextView = itemView.findViewById(R.id.student_name_tv)
        val genderTV: TextView = itemView.findViewById(R.id.gender_tv)
        val dateOfBirthTV: TextView = itemView.findViewById(R.id.date_of_birth_tv)
        val selectionCheckBox: CheckBox = itemView.findViewById(R.id.selection_checkbox)
        private lateinit var clickListener: ItemClickListener

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            clickListener.onClick(v!!,adapterPosition,false)
        }

        override fun onLongClick(v: View?): Boolean {
            clickListener.onClick(v!!,adapterPosition,true)
            return true
        }

        fun setOnClickListener(listener: ItemClickListener){
            clickListener = listener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
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

        holder.setOnClickListener(object : ItemClickListener{
            override fun onClick(view: View, position: Int, isLongClick: Boolean) {
                when(isLongClick) {
                    //long click
                    true -> {

                    }

                    //short click
                    false -> {
                        val studentDetailFragment = StudentDetail.newInstance(studentList[position].studentCode)
                        val manager = activity.supportFragmentManager
                        val transaction = manager.beginTransaction()
                        transaction.add(R.id.student_detail_fragment_container,studentDetailFragment)
                        transaction.addToBackStack("On Top Of Backstack").commit()

                    }
                }
            }
        })

    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    interface ItemClickListener{
        fun onClick(view: View, position: Int, isLongClick: Boolean)
    }


}