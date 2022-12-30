package vn.edu.utt.uttqlsv.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.edu.utt.uttqlsv.R;
import vn.edu.utt.uttqlsv.controller.StudentListManager;
import vn.edu.utt.uttqlsv.model.Gender;
import vn.edu.utt.uttqlsv.model.Student;
import vn.edu.utt.uttqlsv.view.activities.StudentList;

public class StudentDetail extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_STUDENT = "student_param";
    private Student student;

    public static StudentDetail newInstance(String studentCode) {
        StudentDetail studentDetail = new StudentDetail();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_STUDENT, studentCode);
        studentDetail.setArguments(bundle);
        return studentDetail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            String studentCode = getArguments().getString(ARG_STUDENT);
            for (Student s : StudentListManager.getInstance().getStudentList()) {
                if (s.getStudentCode().equals(studentCode)) {
                    student = s;
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_student_detail, container, false);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        TextView studentCodeTV = v.findViewById(R.id.student_code_tv);
        TextView nameTV = v.findViewById(R.id.student_name_tv);
        TextView gradeTV = v.findViewById(R.id.grade_tv);
        TextView classNameTV = v.findViewById(R.id.class_name_tv);
        TextView genderTV = v.findViewById(R.id.gender_tv);
        TextView addressTV = v.findViewById(R.id.address_tv);
        TextView scoreTV = v.findViewById(R.id.average_score_tv);
        Button confirmBtn = v.findViewById(R.id.student_detail_close_btn);
        TextView dobTV = v.findViewById(R.id.date_of_birth_tv);
        ImageButton backBtn = v.findViewById(R.id.back_btn);
        ImageButton deleteBtn = v.findViewById(R.id.delete_btn);
        ImageButton editBtn = v.findViewById(R.id.edit_btn);
        TextView mathTV = v.findViewById(R.id.math_score_tv);
        TextView literatureTV = v.findViewById(R.id.literature_score_tv);
        TextView englishTV = v.findViewById(R.id.english_score_tv);
        TextView physicTV = v.findViewById(R.id.physic_score_tv);
        TextView biologyTV = v.findViewById(R.id.biology_score_tv);
        TextView historyTV = v.findViewById(R.id.history_score_tv);
        TextView chemistryTV = v.findViewById(R.id.chemistry_score_tv);
        TextView geographyTV = v.findViewById(R.id.geography_score_tv);

        studentCodeTV.setText("ID: " + student.getStudentCode());
        dobTV.setText(student.getDateOfBirth());
        nameTV.setText(student.getName());
        gradeTV.setText(student.getGrade());
        classNameTV.setText(student.getClassName());

        if (student.isGender() == Gender.MALE) genderTV.setText(getString(R.string.male));
        else genderTV.setText(getString(R.string.female));

        addressTV.setText(student.getAddress());
        scoreTV.setText("" + student.getAverageScore());

        mathTV.setText(student.getMathScore() + "");
        literatureTV.setText(student.getLiteratureScore() + "");
        englishTV.setText(student.getEnglishScore() + "");
        physicTV.setText(student.getPhysicScore() + "");
        biologyTV.setText(student.getBiologyScore() + "");
        historyTV.setText(student.getHistoryScore() + "");
        chemistryTV.setText(student.getChemistryScore() + "");
        geographyTV.setText(student.getGeographyScore() + "");

        StudentList activity = (StudentList) getActivity();
        confirmBtn.setOnClickListener(v1 -> activity.onBackPressed());

        backBtn.setOnClickListener(v1 -> activity.onBackPressed());

        deleteBtn.setOnClickListener(v1 -> {
            activity.onBackPressed();
            activity.requestDeleteStudent(student);
        });

        editBtn.setOnClickListener(v1 -> {
            activity.onBackPressed();
            activity.openStudentEditFragment(student);
        });

        return v;
    }
}
