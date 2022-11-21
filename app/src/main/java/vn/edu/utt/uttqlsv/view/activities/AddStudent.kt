package vn.edu.utt.uttqlsv.view.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.android.synthetic.main.activity_sign_up.gender_spinner
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.controller.StudentListManager
import vn.edu.utt.uttqlsv.model.Gender
import vn.edu.utt.uttqlsv.model.ScoreBoard

class AddStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val genderDropDownListItems = arrayOf("Male", "Female")
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, genderDropDownListItems)
        gender_spinner.adapter = adapter

        add_btn.setOnClickListener {
            val studentCode = student_code_edt.text.toString().trim()
            val studentName = student_name_edt.text.toString().trim()
            val className = class_name_edt.text.toString().trim()
            val grade = grade_edt.text.toString().trim()
            val gender: Gender
            val i: Long = 0
            gender = if (gender_spinner.selectedItemId == i) Gender.Male
            else Gender.Female

            var address = add_student_address_edt.text.toString().trim()
            val mathScoreStr = math_score_edt.text.toString().trim()
            val literatureScoreStr = literature_score_edt.text.toString().trim()
            val englishScoreStr = english_score_edt.text.toString().trim()
            val physicScoreStr = physic_score_edt.text.toString().trim()
            val geographyScoreStr = geography_score_edt.text.toString().trim()
            val historyScoreStr = history_score_edt.text.toString().trim()
            val chemistryScoreStr = chemistry_score_edt.text.toString().trim()

            val scoreBoard = ScoreBoard()
            if (mathScoreStr.isNotEmpty()) scoreBoard.mathScore = mathScoreStr.toFloat()
            if (literatureScoreStr.isNotEmpty()) scoreBoard.literatureScore =
                literatureScoreStr.toFloat()
            if (englishScoreStr.isNotEmpty()) scoreBoard.englishScore = englishScoreStr.toFloat()
            if (physicScoreStr.isNotEmpty()) scoreBoard.physicScore = physicScoreStr.toFloat()
            if (geographyScoreStr.isNotEmpty()) scoreBoard.geographyScore =
                geographyScoreStr.toFloat()
            if (historyScoreStr.isNotEmpty()) scoreBoard.historyScore = historyScoreStr.toFloat()
            if (chemistryScoreStr.isNotEmpty()) scoreBoard.chemistryScore =
                chemistryScoreStr.toFloat()

            if (StudentListManager.addAnStudent(
                    studentCode, studentName, className, grade, gender, address, scoreBoard
                )
            ) {
                Toast.makeText(this, "add student successfully!", Toast.LENGTH_SHORT).show()
                finish()
            }
            else {
                Toast.makeText(this, "this student code is exist, try again!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}