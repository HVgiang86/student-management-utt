package vn.edu.utt.uttqlsv.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_edit_student.*
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.controller.StudentListManager
import vn.edu.utt.uttqlsv.model.Gender
import vn.edu.utt.uttqlsv.model.ScoreBoard
import vn.edu.utt.uttqlsv.model.Student

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_STUDENT = "student_param"

/**
 * A simple [Fragment] subclass.
 * Use the [EditStudent.newInstance] factory method to
 * create an instance of this fragment.
 */
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

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_edit_student, container, false)

        val genderDropDownListItems = arrayOf("Male", "Female")
        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, genderDropDownListItems
        )

        val spinner: Spinner = v.findViewById(R.id.edit_student_fragment_gender_spinner)
        spinner.adapter = adapter

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
        val chemistryEdt: EditText = v.findViewById(R.id.edit_student_fragment_chemistry_score_edt)
        val saveBtn:Button = v.findViewById(R.id.save_btn)

        studentCodeEdt.text = student?.studentCode
        nameEdt.setText(student?.name)
        classNameEdt.setText(student?.className)
        gradeEdt.setText(student?.grade.toString())

        var i: Long = 0
        i = if (student?.gender == Gender.Male) 0
        else 1
        spinner.setSelection(i.toInt())

        addressEdt.setText(student?.address)
        mathEdt.setText(student?.score?.mathScore.toString())
        literatureEdt.setText(student?.score?.literatureScore.toString())
        englishEdt.setText(student?.score?.englishScore.toString())
        physicEdt.setText(student?.score?.physicScore.toString())
        geographyEdt.setText(student?.score?.geographyScore.toString())
        historyEdt.setText(student?.score?.historyScore.toString())
        chemistryEdt.setText(student?.score?.chemistryScore.toString())

        saveBtn.setOnClickListener {
            val studentName = nameEdt.text.toString().trim()
            val className = classNameEdt.text.toString().trim()
            val grade = gradeEdt.text.toString().trim()
            val gender: Gender
            val i: Long = 0
            gender = if (edit_student_fragment_gender_spinner.selectedItemId == i) Gender.Male
            else Gender.Female

            var address = addressEdt.text.toString().trim()
            val mathScoreStr = mathEdt.text.toString().trim()
            val literatureScoreStr = literatureEdt.text.toString().trim()
            val englishScoreStr = englishEdt.text.toString().trim()
            val physicScoreStr = physicEdt.text.toString().trim()
            val geographyScoreStr = geographyEdt.text.toString().trim()
            val historyScoreStr = historyEdt.text.toString().trim()
            val chemistryScoreStr = chemistryEdt.text.toString().trim()

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

            StudentListManager.editStudentInfo(student!!,
                studentCode.toString(), studentName,className,grade, gender, address, scoreBoard)

        }

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
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