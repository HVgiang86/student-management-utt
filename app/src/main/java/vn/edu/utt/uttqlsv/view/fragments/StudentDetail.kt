package vn.edu.utt.uttqlsv.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.controller.StudentListManager
import vn.edu.utt.uttqlsv.model.Gender
import vn.edu.utt.uttqlsv.model.Student
import vn.edu.utt.uttqlsv.view.adapter.ScoreBoardAdapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_STUDENT = "student_param"

/**
 * A simple [Fragment] subclass.
 * Use the [StudentDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentDetail : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_student_detail, container, false)
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        Log.d("Student Detail", "Student: $studentCode")
        val studentCodeTV:TextView = v.findViewById(R.id.student_code_tv)
        val studentNameTV: TextView = v.findViewById(R.id.student_name_tv)
        val gradeTV: TextView = v.findViewById(R.id.grade_tv)
        val classNameTV: TextView = v.findViewById(R.id.class_name_tv)
        val genderTV: TextView = v.findViewById(R.id.gender_tv)
        val addressTV: TextView = v.findViewById(R.id.address_tv)
        val scoreTV: TextView = v.findViewById(R.id.average_score_tv)
        val scoreBoardRecycler: RecyclerView = v.findViewById(R.id.score_board_recycler_view)

        studentCodeTV.text = "ID: ${student?.studentCode}"
        studentNameTV.text = student?.name
        gradeTV.text = student?.grade.toString()
        classNameTV.text = student?.className
        if (student?.gender == Gender.Male)
            genderTV.text = "Male"
        else
            genderTV.text = "Female"

        addressTV.text = student?.address
        scoreTV.text = student?.avgScore.toString()

        val adapter = ScoreBoardAdapter(student!!.score,context)
        scoreBoardRecycler.adapter = adapter


        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param studentCode Student Code for finding needed student.
         * @return A new instance of fragment StudentDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(studentCode: String) =
            StudentDetail().apply {
                arguments = Bundle().apply {
                    putString(ARG_STUDENT, studentCode)
                }
            }
    }

    override fun onDetach() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onDetach()
    }
}