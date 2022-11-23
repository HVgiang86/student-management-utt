package vn.edu.utt.uttqlsv.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_student_list.*
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.controller.StudentListManager
import vn.edu.utt.uttqlsv.model.Student
import vn.edu.utt.uttqlsv.view.adapter.StudentListAdapter
import vn.edu.utt.uttqlsv.view.fragments.EditStudent


@Suppress("DEPRECATION")
class StudentList : AppCompatActivity() {

    companion object {
        const val STUDENT_LIST_CHANGE_CODE = 12345
    }

    private lateinit var adapter: StudentListAdapter
    private lateinit var studentList: MutableList<Student>
    private val selection = BooleanArray(20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val toolbar = findViewById<View>(R.id.toolbar) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setLogo(R.drawable.ic_file)
        supportActionBar?.setDisplayUseLogoEnabled(true)

        //add action menu

        //init student list and selection list
        studentList = StudentListManager.studentList
        for (i in 0.until(studentList.size)) selection[i] = false

        //display student as recycler view
        adapter = StudentListAdapter(this, studentList)
        recycler.adapter = adapter


        //Handle floating action button, this button will open add student activity
        fab.setOnClickListener {
            val intent = Intent(this, AddStudent::class.java)
            startActivityForResult(intent, STUDENT_LIST_CHANGE_CODE)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.logout_menu -> {
                StudentListManager.close()
                val intent = Intent(this, Login::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                ActivityCompat.finishAffinity(this)
            }

            R.id.profile_menu -> {
                Toast.makeText(this, "this feature has not been implement yet!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = adapter.clickedPosition
        Log.d("Student List", "Context menu created, position: $position")

        when (item.itemId) {
            R.id.context_menu_action_delete -> {
                deleteStudentAtPosition(position)
            }
            R.id.context_menu_action_edit -> {
                openStudentEditFragment(position)
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun deleteStudentAtPosition(position: Int) {
        StudentListManager.deleteStudentAtPosition(position)
        Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show()
        refreshStudentList()
    }

    private fun openStudentEditFragment(position: Int) {
        Toast.makeText(this, "Editor selected!", Toast.LENGTH_SHORT).show()

        val editStudent = EditStudent.newInstance(studentList[position].studentCode)
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.student_detail_fragment_container, editStudent)
        transaction.addToBackStack("edit").commit()
    }

    fun openStudentEditFragment(student: Student) {
        Toast.makeText(this, "Editor selected!", Toast.LENGTH_SHORT).show()

        val editStudent = EditStudent.newInstance(student.studentCode)
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.student_detail_fragment_container, editStudent)
        transaction.addToBackStack("edit").commit()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == STUDENT_LIST_CHANGE_CODE && resultCode == RESULT_OK) {
            refreshStudentList()
        }
    }

    override fun onResume() {
        super.onResume()
        refreshStudentList()
    }

    fun requestDeleteStudent(student: Student) {
        StudentListManager.deleteStudent(student)
        refreshStudentList()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshStudentList() {
        adapter.notifyDataSetChanged()
    }

}