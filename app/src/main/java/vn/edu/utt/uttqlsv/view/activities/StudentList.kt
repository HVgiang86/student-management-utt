package vn.edu.utt.uttqlsv.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_student_list.*
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.model.Gender
import vn.edu.utt.uttqlsv.model.Student
import vn.edu.utt.uttqlsv.view.adapter.StudentListAdapter

class StudentList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        val studentList = mutableListOf<Student>()
        var selection = BooleanArray(20)
        studentList.add(Student("0001","Hoang Van A","12A1",12,Gender.Male,""))
        studentList.add(Student("0002","Hoang Van B","12A1",12,Gender.Male,""))
        studentList.add(Student("0003","Hoang Van C","12A1",12,Gender.Male,""))

        val adapter = StudentListAdapter(this,studentList, selectionList = selection)

        recycler.adapter = adapter
    }
}