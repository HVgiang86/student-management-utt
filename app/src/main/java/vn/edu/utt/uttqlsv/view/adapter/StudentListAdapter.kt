package vn.edu.utt.uttqlsv.view.adapter

import android.content.Context
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.model.Gender
import vn.edu.utt.uttqlsv.model.Student
import vn.edu.utt.uttqlsv.view.activities.StudentList
import vn.edu.utt.uttqlsv.view.fragments.StudentDetail

@Suppress("DEPRECATION")
class StudentListAdapter(
    private var activity: StudentList, private var studentList: MutableList<Student>
) : RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {

    var clickedPosition: Int = 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener,
        View.OnCreateContextMenuListener {
        val studentCodeTV: TextView = itemView.findViewById(R.id.student_code_tv)
        val studentNameTV: TextView = itemView.findViewById(R.id.student_name_tv)
        val genderTV: TextView = itemView.findViewById(R.id.gender_tv)
        val dateOfBirthTV: TextView = itemView.findViewById(R.id.date_of_birth_tv)
        val classNameTV: TextView = itemView.findViewById(R.id.class_name_tv)
        private lateinit var clickListener: ItemClickListener

        init {
            itemView.setOnClickListener(this)
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onClick(v: View?) {
            clickListener.onClick(v!!, adapterPosition)
        }

        fun setOnClickListener(listener: ItemClickListener) {
            clickListener = listener
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            menu?.setHeaderTitle("Action Menu")
            menu?.add(Menu.NONE, R.id.context_menu_action_edit, Menu.NONE, "Edit")
            menu?.add(Menu.NONE, R.id.context_menu_action_delete, Menu.NONE, "Delete")
            menu?.getItem(0)?.setIcon(R.drawable.ic_edit)
            menu?.getItem(1)?.setIcon(R.drawable.ic_delete)
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
        if (studentList[position].gender == Gender.MALE) holder.genderTV.text =
            activity.getString(R.string.male)
        else holder.genderTV.text = activity.getString(R.string.female)

        holder.dateOfBirthTV.text = studentList[position].dateOfBirth
        holder.classNameTV.text = studentList[position].className

        holder.setOnClickListener(object : ItemClickListener {
            override fun onClick(view: View, position: Int) {
                openStudentDetailFragment(studentList[position].studentCode)
            }
        })

        holder.itemView.setOnLongClickListener {
            clickedPosition = holder.adapterPosition
            false
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private fun openStudentDetailFragment(studentCode: String) {
        val studentDetailFragment = StudentDetail.newInstance(studentCode)
        val manager = activity.supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.student_detail_fragment_container, studentDetailFragment)
        transaction.addToBackStack("On Top Of Backstack").commit()
    }

}