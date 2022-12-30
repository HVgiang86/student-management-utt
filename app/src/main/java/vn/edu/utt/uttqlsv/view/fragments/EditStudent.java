package vn.edu.utt.uttqlsv.view.fragments;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import vn.edu.utt.uttqlsv.R;
import vn.edu.utt.uttqlsv.controller.StudentListManager;
import vn.edu.utt.uttqlsv.controller.utils.Validator;
import vn.edu.utt.uttqlsv.model.Gender;
import vn.edu.utt.uttqlsv.model.Student;
import vn.edu.utt.uttqlsv.view.activities.StudentList;

public class EditStudent extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_STUDENT = "student_param";
    int initYear;
    int initMonth;
    int initDate;
    private Student student;

    public static EditStudent newInstance(String studentCode) {
        EditStudent fragment = new EditStudent();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_STUDENT, studentCode);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            String studentCode = getArguments().getString(ARG_STUDENT);

            for (Student s : StudentListManager.getInstance().getStudentList()) {
                if (studentCode.equals(s.getStudentCode())) {
                    student = s;
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_student, container, false);

        Spinner spinner = v.findViewById(R.id.edit_student_fragment_gender_spinner);
        String[] items = {"Male", "Female"};
        ArrayAdapter adapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

        if (student.isGender() == Gender.MALE) spinner.setSelection(0);
        else spinner.setSelection(1);

        //Init view for each property of student
        TextView studentCodeEdt = v.findViewById(R.id.edit_student_fragment_student_code_edt);
        EditText nameEdt = v.findViewById(R.id.edit_student_fragment_student_name_edt);
        EditText classNameEdt = v.findViewById(R.id.edit_student_fragment_class_name_edt);
        EditText gradeEdt = v.findViewById(R.id.edit_student_fragment_grade_edt);
        EditText addressEdt = v.findViewById(R.id.edit_student_fragment_add_student_address_edt);
        EditText mathEdt = v.findViewById(R.id.edit_student_fragment_math_score_edt);
        EditText literatureEdt = v.findViewById(R.id.edit_student_fragment_literature_score_edt);
        EditText englishEdt = v.findViewById(R.id.edit_student_fragment_english_score_edt);
        EditText physicEdt = v.findViewById(R.id.edit_student_fragment_physic_score_edt);
        EditText geographyEdt = v.findViewById(R.id.edit_student_fragment_geography_score_edt);
        EditText historyEdt = v.findViewById(R.id.edit_student_fragment_history_score_edt);
        EditText biologyEdt = v.findViewById(R.id.edit_student_fragment_biology_score_edt);
        EditText chemistryEdt = v.findViewById(R.id.edit_student_fragment_chemistry_score_edt);
        Button saveBtn = v.findViewById(R.id.save_btn);
        TextView dobTV = v.findViewById(R.id.edit_student_fragment_date_of_birth_tv);
        ImageButton datePickerBtn = v.findViewById(R.id.edit_student_fragment_show_date_picker);


        try {
            @SuppressLint("SimpleDateFormat") Date date = new SimpleDateFormat("dd/MM/yyyy").parse(student.getDateOfBirth());
            if (date == null) date = new Date(System.currentTimeMillis());

            initYear = date.getYear() + 1900;
            initMonth = date.getMonth() + 1;
            initDate = date.getDate();

            dobTV.setText(initDate + "/" + initMonth + "/" + initYear);
            DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
                dobTV.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                initYear = year;
                initMonth = month + 1;
                initDate = dayOfMonth;
            };

            DatePickerDialog dialog = new DatePickerDialog(requireContext(), android.R.style.Theme_Material_Light_Dialog_NoActionBar, dateSetListener, initYear, initMonth, initYear);
            datePickerBtn.setOnClickListener(v1 -> dialog.show());

            //set initial value for each view of properties
            studentCodeEdt.setText(student.getStudentCode());
            nameEdt.setText(student.getName());
            classNameEdt.setText(student.getClassName());
            gradeEdt.setText(student.getGrade());
            addressEdt.setText(student.getAddress());
            mathEdt.setText(String.valueOf(student.getMathScore()));
            literatureEdt.setText(String.valueOf(student.getLiteratureScore()));
            englishEdt.setText(String.valueOf(student.getEnglishScore()));
            physicEdt.setText(String.valueOf(student.getPhysicScore()));
            geographyEdt.setText(String.valueOf(student.getGeographyScore()));
            historyEdt.setText(String.valueOf(student.getHistoryScore()));
            chemistryEdt.setText(String.valueOf(student.getChemistryScore()));
            biologyEdt.setText(String.valueOf(student.getBiologyScore()));

            //Handle save button click event
            saveBtn.setOnClickListener(v1 -> {
                String studentName = nameEdt.getText().toString().trim();
                String className = classNameEdt.getText().toString().trim();
                String grade = gradeEdt.getText().toString().trim();
                boolean gender = spinner.getSelectedItemId() == 0 ? Gender.MALE : Gender.FEMALE;
                String address = addressEdt.getText().toString().trim();
                String mathStr = mathEdt.getText().toString().trim();
                String literatureStr = literatureEdt.getText().toString().trim();
                String englishStr = englishEdt.getText().toString().trim();
                String physicStr = physicEdt.getText().toString().trim();
                String geographyStr = geographyEdt.getText().toString().trim();
                String historyStr = historyEdt.getText().toString().trim();
                String chemistryStr = chemistryEdt.getText().toString().trim();
                String biologyStr = biologyEdt.getText().toString().trim();

                Student newStudent = edit(studentName, className, grade, gender, address, mathStr, literatureStr, englishStr, physicStr, geographyStr, historyStr, chemistryStr, biologyStr);
                if (newStudent != null) {
                    StudentListManager.getInstance().updateStudentInfo(student, newStudent);
                }

                StudentList studentListActivity = (StudentList) getActivity();
                studentListActivity.onBackPressed();
                studentListActivity.refreshStudentList();

            });

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return v;
    }

    private Student edit(String studentName, String className, String grade, boolean gender, String address, String mathStr, String literatureStr, String englishStr, String physicStr, String geographyStr, String historyStr, String chemistryStr, String biologyStr) {
        Student student = new Student();
        student.setName(studentName);
        student.setClassName(className);
        if (!grade.isEmpty()) student.setGrade(Integer.parseInt(grade));
        else student.setGrade(10);

        student.setGender(gender);
        student.setAddress(address);

        if (!mathStr.isEmpty() && Validator.isValidFloatingNumber(mathStr))
            student.setMathScore(Float.parseFloat(mathStr));
        else student.setMathScore(0f);

        if (!literatureStr.isEmpty() && Validator.isValidFloatingNumber(literatureStr))
            student.setLiteratureScore(Float.parseFloat(literatureStr));
        else student.setLiteratureScore(0f);

        if (!englishStr.isEmpty() && Validator.isValidFloatingNumber(englishStr))
            student.setEnglishScore(Float.parseFloat(englishStr));
        else student.setEnglishScore(0f);

        if (!physicStr.isEmpty() && Validator.isValidFloatingNumber(physicStr))
            student.setPhysicScore(Float.parseFloat(physicStr));
        else student.setPhysicScore(0f);

        if (!geographyStr.isEmpty() && Validator.isValidFloatingNumber(geographyStr))
            student.setGeographyScore(Float.parseFloat(geographyStr));
        else student.setGeographyScore(0f);

        if (!historyStr.isEmpty() && Validator.isValidFloatingNumber(historyStr))
            student.setHistoryScore(Float.parseFloat(historyStr));
        else student.setHistoryScore(0f);

        if (!chemistryStr.isEmpty() && Validator.isValidFloatingNumber(chemistryStr))
            student.setChemistryScore(Float.parseFloat(chemistryStr));
        else student.setChemistryScore(0f);

        if (!biologyStr.isEmpty() && Validator.isValidFloatingNumber(biologyStr))
            student.setBiologyScore(Float.parseFloat(biologyStr));
        else student.setBiologyScore(0f);

        String dobStr = toDateString(initDate, initMonth, initYear);

        if (Validator.isValidDateString(dobStr)) {
            student.setDateOfBirth(dobStr);
            return student;
        }

        return null;
    }

    private String toDateString(int day, int month, int year) {
        String s = "";
        s += (day < 10) ? ("0" + day + "/") : (day + "/");
        s += (month < 10) ? ("0" + month + "/") : (month + "/");
        s += year;
        return s;
    }
}
