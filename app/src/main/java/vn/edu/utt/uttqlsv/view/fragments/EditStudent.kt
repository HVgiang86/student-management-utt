package vn.edu.utt.uttqlsv.view.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_edit_student.*
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.controller.StudentListManager
import vn.edu.utt.uttqlsv.controller.utils.Validator
import vn.edu.utt.uttqlsv.model.Gender
import vn.edu.utt.uttqlsv.model.Student
import vn.edu.utt.uttqlsv.view.activities.StudentList
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_STUDENT = "student_param"

/**
 * A simple [Fragment] subclass.
 * Use the [EditStudent.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class EditStudent : Fragment() {
    // TODO: Rename and change types of parameters
    private var studentCode: String? = null
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            studentCode = it.getString(ARG_STUDENT)

            for (a in StudentListManager.studentList) {
                if (studentCode.equals(a.studentCode)) {
                    student = a
                }
            }
        }
    }

    @SuppressLint("MissingInflatedId", "SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_edit_student, container, false)

        //init spinner for gender picker dropdown menu
        val genderDropDownListItems = arrayOf("Male", "Female")
        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, genderDropDownListItems
        )

        val spinner: Spinner = v.findViewById(R.id.edit_student_fragment_gender_spinner)
        spinner.adapter = adapter

        if (student?.gender == Gender.MALE) spinner.setSelection(0)
        else spinner.setSelection(1)

        //Init view for each property of Student
        val studentCodeEdt: TextView = v.findViewById(R.id.edit_student_fragment_student_code_edt)
        val nameEdt: EditText = v.findViewById(R.id.edit_student_fragment_student_name_edt)
        val classNameEdt: EditText = v.findViewById(R.id.edit_student_fragment_class_name_edt)
        val gradeEdt: EditText = v.findViewById(R.id.edit_student_fragment_grade_edt)
        val addressEdt: EditText =
            v.findViewById(R.id.edit_student_fragment_add_student_address_edt)
        val mathEdt: EditText = v.findViewById(R.id.edit_student_fragment_math_score_edt)
        val literatureEdt: EditText =
            v.findViewById(R.id.edit_student_fragment_literature_score_edt)
        val englishEdt: EditText = v.findViewById(R.id.edit_student_fragment_english_score_edt)
        val physicEdt: EditText = v.findViewById(R.id.edit_student_fragment_physic_score_edt)
        val geographyEdt: EditText = v.findViewById(R.id.edit_student_fragment_geography_score_edt)
        val historyEdt: EditText = v.findViewById(R.id.edit_student_fragment_history_score_edt)
        val biologyEdt: EditText = v.findViewById(R.id.edit_student_fragment_biology_score_edt)
        val chemistryEdt: EditText = v.findViewById(R.id.edit_student_fragment_chemistry_score_edt)
        val saveBtn: Button = v.findViewById(R.id.save_btn)
        val dobTV: TextView = v.findViewById(R.id.edit_student_fragment_date_of_birth_tv)
        val datePickerBtn: ImageButton = v.findViewById(R.id.edit_student_fragment_show_date_picker)


        var date = SimpleDateFormat("dd/MM/yyyy").parse(student?.dateOfBirth!!)
        if (date == null) date = Date(System.currentTimeMillis())
        Log.d("Edit Student", "$date")

        var initYear = date.year + 1900
        var initMonth = date.month + 1
        var initDate = date.date

        dobTV.text = "$initDate-$initMonth-$initYear"
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            dobTV.text = "$dayOfMonth/${month + 1}/$year"
            initYear = year
            initMonth = month + 1
            initDate = dayOfMonth
        }
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            android.R.style.Theme_Material_Light_Dialog_NoActionBar,
            dateSetListener,
            initYear,
            initMonth,
            initDate
        )

        datePickerBtn.setOnClickListener { datePickerDialog.show() }

        //set initial value for each view of properties
        studentCodeEdt.text = student?.studentCode
        nameEdt.setText(student?.name)
        classNameEdt.setText(student?.className)
        gradeEdt.setText(student?.grade.toString())
        addressEdt.setText(student?.address)
        mathEdt.setText(student?.mathScore.toString())
        literatureEdt.setText(student?.literatureScore.toString())
        englishEdt.setText(student?.englishScore.toString())
        physicEdt.setText(student?.physicScore.toString())
        geographyEdt.setText(student?.geographyScore.toString())
        historyEdt.setText(student?.historyScore.toString())
        chemistryEdt.setText(student?.chemistryScore.toString())
        biologyEdt.setText(student?.biologyScore.toString())

        //Handle Save Button click event
        saveBtn.setOnClickListener {

            val studentName = nameEdt.text.toString().trim()
            val className = classNameEdt.text.toString().trim()
            val grade = gradeEdt.text.toString().trim()
            val gender: Boolean =
                if (edit_student_fragment_gender_spinner.selectedItemId == "0".toLong()) Gender.MALE
                else Gender.FEMALE

            val address = addressEdt.text.toString().trim()
            val mathScoreStr = mathEdt.text.toString().trim()
            val literatureScoreStr = literatureEdt.text.toString().trim()
            val englishScoreStr = englishEdt.text.toString().trim()
            val physicScoreStr = physicEdt.text.toString().trim()
            val geographyScoreStr = geographyEdt.text.toString().trim()
            val historyScoreStr = historyEdt.text.toString().trim()
            val chemistryScoreStr = chemistryEdt.text.toString().trim()
            val biologyScoreStr = biologyEdt.text.toString().trim()

            val newStudent = Student()
            newStudent.name = studentName
            newStudent.className = className
            if (grade.isNotEmpty())
                newStudent.grade = Integer.parseInt(grade)
            else
                newStudent.grade = 10

            newStudent.gender = gender
            newStudent.address = address

            if (mathScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(mathScoreStr)) newStudent.mathScore =
                mathScoreStr.toFloat()
            else newStudent.mathScore = 0f

            if (literatureScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(
                    literatureScoreStr
                )
            ) newStudent.literatureScore = literatureScoreStr.toFloat()
            else newStudent.literatureScore = 0f

            if (englishScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(englishScoreStr)) newStudent.englishScore =
                englishScoreStr.toFloat()
            else newStudent.englishScore = 0f

            if (physicScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(physicScoreStr)) newStudent.physicScore =
                physicScoreStr.toFloat()
            else newStudent.physicScore = 0f

            if (geographyScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(geographyScoreStr)) newStudent.geographyScore =
                geographyScoreStr.toFloat()
            else newStudent.geographyScore = 0f

            if (historyScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(historyScoreStr)) newStudent.historyScore =
                historyScoreStr.toFloat()
            else newStudent.historyScore = 0f

            if (chemistryScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(chemistryScoreStr)) newStudent.chemistryScore =
                chemistryScoreStr.toFloat()
            else newStudent.chemistryScore = 0f

            if (biologyScoreStr.isNotEmpty() && Validator.isValidFloatingNumber(biologyScoreStr)) newStudent.biologyScore =
                biologyScoreStr.toFloat()
            else newStudent.biologyScore = 0f

            val dobStr = toDateString(initDate, initMonth, initYear)
            Log.d("Add Student", "Date String $dobStr")
            if (Validator.isValidDateString(dobStr)) {
                newStudent.dateOfBirth = dobStr
                StudentListManager.updateStudentInfo(student!!, newStudent)
            }


            val studentListActivity = activity as? StudentList
            studentListActivity?.onBackPressed()
            studentListActivity?.refreshStudentList()
        }

        return v
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment EditStudent.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(studentCode: String) = EditStudent().apply {
            arguments = Bundle().apply {
                putString(ARG_STUDENT, studentCode)
            }
        }
    }

}