package vn.edu.utt.uttqlsv.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_student_list.*
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.controller.StudentListManager
import vn.edu.utt.uttqlsv.controller.StudentListManager.studentList
import vn.edu.utt.uttqlsv.view.adapter.StudentListAdapter
import vn.edu.utt.uttqlsv.view.fragments.EditStudent
import vn.edu.utt.uttqlsv.view.fragments.StudentDetail

class StudentList : AppCompatActivity() {
    private lateinit var adapter: StudentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        val studentList = StudentListManager.studentList
        var selection = BooleanArray(20)

        adapter = StudentListAdapter(this, studentList, selectionList = selection)
        recycler.adapter = adapter

        fab.setOnClickListener {
            val intent = Intent(this, AddStudent::class.java)
            startActivity(intent)
        }

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = adapter.clickedPosition
        when (item.itemId) {
            R.id.context_menu_action_delete -> {
                StudentListManager.deleteStudentAtPosition(position)
                Toast.makeText(this,"Deleted!",Toast.LENGTH_SHORT).show()
                adapter.notifyDataSetChanged()
            }
            R.id.context_menu_action_edit -> {
                Toast.makeText(this,"Editor selected!",Toast.LENGTH_SHORT).show()
                val editStudent =
                    EditStudent.newInstance(studentList[position].studentCode)
                val manager = supportFragmentManager
                val transaction = manager.beginTransaction()
                transaction.add(R.id.student_detail_fragment_container, editStudent)
                transaction.addToBackStack("On Top Of Backstack").commit()
            }

        }
        return super.onContextItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}