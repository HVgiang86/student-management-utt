package vn.edu.utt.uttqlsv.view.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.utt.uttqlsv.R;
import vn.edu.utt.uttqlsv.controller.StudentListManager;
import vn.edu.utt.uttqlsv.model.Student;
import vn.edu.utt.uttqlsv.view.adapter.StudentListAdapter;
import vn.edu.utt.uttqlsv.view.fragments.EditStudent;

public class StudentList extends AppCompatActivity {
    public static final int STUDENT_LIST_CHANGE_CODE = 12345;
    private final boolean[] selection = new boolean[20];
    RecyclerView recycler;
    private StudentListAdapter adapter;
    private ArrayList<Student> studentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        recycler = findViewById(R.id.recycler);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_file);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //add action menu

        //init student list and selection list
        StudentListManager.getInstance().init(getApplicationContext());
        studentList = StudentListManager.getInstance().getStudentList();
        for (int i = 0; i < studentList.size(); ++i) {
            selection[i] = false;
        }

        //display student as recycler view
        adapter = new StudentListAdapter(this, studentList);
        recycler.setAdapter(adapter);

        //Handle floating action button, this button will open add student activity
        findViewById(R.id.fab).setOnClickListener(v -> {
            Intent intent = new Intent(this, AddStudent.class);
            startActivityForResult(intent, STUDENT_LIST_CHANGE_CODE);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_menu:
                StudentListManager.getInstance().close();
                Intent intent = new Intent(this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                ActivityCompat.finishAffinity(this);
                break;
            case R.id.profile_menu:
                Toast.makeText(this, "this feature has not been implement yet!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getClickedPosition();

        switch (item.getItemId()) {
            case R.id.context_menu_action_delete:
                deleteStudentAtPosition(position);
                break;
            case R.id.context_menu_action_edit:
                openStudentEditFragment(position);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void deleteStudentAtPosition(int position) {
        StudentListManager.getInstance().deleteStudentAtPosition(position);
        Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
        refreshStudentList();
    }

    private void openStudentEditFragment(int position) {
        Toast.makeText(this, "Editor selected!", Toast.LENGTH_SHORT).show();

        EditStudent editStudent = EditStudent.newInstance(studentList.get(position).getStudentCode());
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.student_detail_fragment_container, editStudent);
        transaction.addToBackStack("edit").commit();
    }

    public void openStudentEditFragment(Student student) {
        Toast.makeText(this, "Editor selected!", Toast.LENGTH_SHORT).show();

        EditStudent editStudent = EditStudent.newInstance(student.getStudentCode());
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.student_detail_fragment_container, editStudent);
        transaction.addToBackStack("edit").commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STUDENT_LIST_CHANGE_CODE && resultCode == RESULT_OK) {
            refreshStudentList();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshStudentList();
    }

    public void requestDeleteStudent(Student student) {
        StudentListManager.getInstance().deleteStudent(student);
        refreshStudentList();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshStudentList() {
        adapter.notifyDataSetChanged();
    }
}
