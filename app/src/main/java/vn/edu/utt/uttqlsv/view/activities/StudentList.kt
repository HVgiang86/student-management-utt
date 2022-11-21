package vn.edu.utt.uttqlsv.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_student_list.*
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.controller.StudentListManager
import vn.edu.utt.uttqlsv.model.Gender
import vn.edu.utt.uttqlsv.model.Student
import vn.edu.utt.uttqlsv.view.adapter.StudentListAdapter

class StudentList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        val studentList = StudentListManager.studentList
        var selection = BooleanArray(20)

        val adapter = StudentListAdapter(this,studentList, selectionList = selection)
        recycler.adapter = adapter

        fab.setOnClickListener{
            val intent = Intent(this,AddStudent::class.java)
            startActivity(intent)
        }
    }
}