package vn.edu.utt.uttqlsv.view.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.android.synthetic.main.activity_sign_up.gender_spinner
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.controller.StudentListManager
import vn.edu.utt.uttqlsv.controller.utils.Validator
import vn.edu.utt.uttqlsv.model.Student
import java.time.LocalDate

class AddStudent : AppCompatActivity() {
    private val date = LocalDate.now()
    private var initYear: Int = date.year
    private var initMonth: Int = date.monthValue
    private var initDate: Int = date.dayOfMonth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        initGenderPickerMenu()

        //Handle add student button
        add_btn.setOnClickListener {
            requestAddStudent()
        }


        Log.d("Add Student", "current time: $initMonth/$initDate/$initYear")
        date_of_birth_tv.text = "${initMonth + 1}/$initDate/$initYear"
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            date_of_birth_tv.text = "$dayOfMonth/${month + 1}/$year"
            initYear = year
            initMonth = month + 1
            initDate = dayOfMonth
        }
        val datePickerDialog = DatePickerDialog(
            this,
            android.R.style.Theme_Material_Light_Dialog_NoActionBar,
            dateSetListener,
            initYear,
            initMonth,
            initDate
        )

        show_date_picker.setOnClickListener { datePickerDialog.show() }
    }

    private fun initGenderPickerMenu() {
        val genderDropDownListItems = arrayOf("Male", "Female")
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, genderDropDownListItems)
        gender_spinner.adapter = adapter
    }

    private fun requestAddStudent() {
        val studentCode = student_code_edt.text.toString().trim()
        val studentName = student_name_edt.text.toString().trim()
        val className = class_name_edt.text.toString().trim()
        val grade = grade_edt.text.toString().trim()
        val gender: Boolean = gender_spinner.selectedItemId == "0".toLong()
        val address = add_student_address_edt.text.toString().trim()
        val mathScoreStr = math_score_edt.text.toString().trim()
        val literatureScoreStr = literature_score_edt.text.toString().trim()
        val englishScoreStr = english_score_edt.text.toString().trim()
        val physicScoreStr = physic_score_edt.text.toString().trim()
        val geographyScoreStr = geography_score_edt.text.toString().trim()
        val historyScoreStr = history_score_edt.text.toString().trim()
        val chemistryScoreStr = chemistry_score_edt.text.toString().trim()
        val biologyScoreStr = biology_score_edt.text.toString().trim()

        val student = Student()
        student.studentCode = studentCode
        student.name = studentName
        student.className = className

        if (grade.isNotEmpty()) student.grade = Integer.parseInt(grade)
        else student.grade = 10

        student.gender = gender
        student.address = address

        if (mathScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(mathScoreStr)) student.mathScore =
            mathScoreStr.toFloat()
        else student.mathScore = 0f

        if (literatureScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(
                literatureScoreStr
            )
        ) student.literatureScore = literatureScoreStr.toFloat()
        else student.literatureScore = 0f

        if (englishScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(englishScoreStr)) student.englishScore =
            englishScoreStr.toFloat()
        else student.englishScore = 0f

        if (physicScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(physicScoreStr)) student.physicScore =
            physicScoreStr.toFloat()
        else student.physicScore = 0f

        if (geographyScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(geographyScoreStr)) student.geographyScore =
            geographyScoreStr.toFloat()
        else student.geographyScore = 0f

        if (historyScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(historyScoreStr)) student.historyScore =
            historyScoreStr.toFloat()
        else student.historyScore = 0f

        if (chemistryScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(chemistryScoreStr)) student.chemistryScore =
            chemistryScoreStr.toFloat()
        else student.chemistryScore = 0f

        if (biologyScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(biologyScoreStr)) student.biologyScore =
            biologyScoreStr.toFloat()
        else student.biologyScore = 0f


        val dobStr = toDateString(initDate, initMonth, initYear)
        Log.d("Add Student", "Date String $dobStr")
        if (Validator.isValidDateString(dobStr)) student.dateOfBirth = dobStr
        else {
            Log.d("Add Student", "Date String invalid $dobStr")
            return
        }


        if (StudentListManager.addAnStudent(student)) {
            Toast.makeText(this, "add student successfully!", Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK)
            finish()
        } else {
            Toast.makeText(this, "this student code is exist, try again!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun toDateString(day: Int, month: Int, year: Int): String {
        var s = ""
        s += if (day < 10) "0$day/"
        else "$day/"

        s += if (month < 10) "0$month/"
        else "$month/"
        s += "$year"
        return s
    }
}