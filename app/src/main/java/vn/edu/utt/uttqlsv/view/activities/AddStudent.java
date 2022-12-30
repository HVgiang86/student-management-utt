package vn.edu.utt.uttqlsv.view.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;

import java.time.LocalDate;

import vn.edu.utt.uttqlsv.R;
import vn.edu.utt.uttqlsv.controller.StudentListManager;
import vn.edu.utt.uttqlsv.controller.utils.Validator;
import vn.edu.utt.uttqlsv.model.Gender;
import vn.edu.utt.uttqlsv.model.Student;

public class AddStudent extends AppCompatActivity {
    private final LocalDate date = LocalDate.now();
    private int initYear = date.getMonthValue();
    private int initMonth = date.getMonthValue();
    private int initDate = date.getDayOfMonth();

    private EditText studentCodeEdt;
    private EditText studentNameEdt;
    private EditText classNameEdt;
    private EditText gradeEdt;
    private Spinner genderSpinner;
    private EditText addressEdt;
    private EditText mathEdt;
    private EditText literatureEdt;
    private EditText englishEdt;
    private EditText physicEdt;
    private EditText geographyEdt;
    private EditText historyEdt;
    private EditText chemistryEdt;
    private EditText biologyEdt;

    @Override
    public void onCreateSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onCreateSupportNavigateUpTaskStack(builder);
        setContentView(R.layout.activity_add_student);

        studentCodeEdt = findViewById(R.id.student_code_edt);
        studentNameEdt = findViewById(R.id.student_name_edt);
        classNameEdt = findViewById(R.id.class_name_edt);
        gradeEdt = findViewById(R.id.grade_edt);
        genderSpinner = findViewById(R.id.gender_spinner);
        addressEdt = findViewById(R.id.add_student_address_edt);
        mathEdt = findViewById(R.id.math_score_edt);
        literatureEdt = findViewById(R.id.literature_score_edt);
        englishEdt = findViewById(R.id.english_score_edt);
        physicEdt = findViewById(R.id.physic_score_edt);
        geographyEdt = findViewById(R.id.geography_score_edt);
        historyEdt = findViewById(R.id.history_score_edt);
        chemistryEdt = findViewById(R.id.chemistry_score_edt);
        biologyEdt = findViewById(R.id.biology_score_edt);

        ImageButton showDatePickerBtn = findViewById(R.id.show_date_picker);
        TextView dobTV = findViewById(R.id.date_of_birth_tv);
        Button addBtn = findViewById(R.id.add_btn);

        initGenderPickerMenu();

        @SuppressLint("SetTextI18n") DatePickerDialog.OnDateSetListener listener = (view, year, month, dayOfMonth) -> {
            dobTV.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            initYear = year;
            initMonth = month + 1;
            initDate = dayOfMonth;
        };

        DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_Material_Light_Dialog_NoActionBar, listener, initYear, initMonth, initDate);

        showDatePickerBtn.setOnClickListener(v -> dialog.show());

        //Handle add student button
        addBtn.setOnClickListener(b -> requestAddStudent());
    }

    private void requestAddStudent() {
        String studentCode = studentCodeEdt.getText().toString().trim();
        String studentName = studentNameEdt.getText().toString().trim();
        String className = classNameEdt.getText().toString().trim();
        String grade = gradeEdt.getText().toString().trim();
        boolean gender = (genderSpinner.getSelectedItemId() == 0) ? Gender.MALE : Gender.FEMALE;
        String address = addressEdt.getText().toString().trim();
        String mathStr = mathEdt.getText().toString().trim();
        String literatureStr = literatureEdt.getText().toString().trim();
        String englishStr = englishEdt.getText().toString().trim();
        String physicStr = physicEdt.getText().toString().trim();
        String geographyStr = geographyEdt.getText().toString().trim();
        String historyStr = historyEdt.getText().toString().trim();
        String chemistryStr = chemistryEdt.getText().toString().trim();
        String biologyStr = biologyEdt.getText().toString().trim();

        Student student = new Student();
        student.setStudentCode(studentCode);
        student.setName(studentName);
        student.setClassName(className);
        student.setAddress(address);
        student.setGender(gender);

        if (!grade.isEmpty()) student.setGrade(Integer.parseInt(grade));
        else student.setGrade(10);

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
        if (Validator.isValidDateString(dobStr)) student.setDateOfBirth(dobStr);
        else return;

        if (StudentListManager.getInstance().addStudent(student)) {
            Toast.makeText(this, "add student successfully!", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "this student code is exist, try again!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initGenderPickerMenu() {
        String[] items = {"Male", "Female"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items);
        genderSpinner.setAdapter(adapter);
    }

    private String toDateString(int day, int month, int year) {
        String s = "";
        s += (day < 10) ? ("0" + day + "/") : (day + "/");
        s += (month < 10) ? ("0" + month + "/") : (month + "/");
        s += year;
        return s;
    }
}
