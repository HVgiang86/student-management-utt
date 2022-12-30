package vn.edu.utt.uttqlsv.controller;

import android.content.Context;

import java.util.ArrayList;

import vn.edu.utt.uttqlsv.model.Student;
import vn.edu.utt.uttqlsv.model.database.StudentRealmDatabaseHelper;

public class StudentListManager {

    //singleton installation
    private static final StudentListManager INSTANCE = new StudentListManager();
    private final StudentRealmDatabaseHelper realmHelper = StudentRealmDatabaseHelper.getInstance();
    private ArrayList<Student> studentList = new ArrayList<>();

    private StudentListManager() {
    }

    public static StudentListManager getInstance() {
        return INSTANCE;
    }
    //end of singleton installation

    public void init(Context context) {
        realmHelper.open(context);
        loadStoredStudentList();
    }

    private void loadStoredStudentList() {
        ArrayList<Student> list = realmHelper.read();
        if (list != null) studentList = list;
    }


    public void updateStudentInfo(Student student, Student newStudent) {
        realmHelper.update(student.getStudentCode(), newStudent);
        studentList.remove(student);
        studentList.add(newStudent);
    }

    private boolean isStudentCodeExist(String studentCode) {
        for (Student a : studentList) {
            if (a.getStudentCode().equals(studentCode)) {
                return true;
            }
        }
        return false;
    }

    public boolean addStudent(Student student) {
        if (student.getStudentCode().isEmpty()) return false;
        if (isStudentCodeExist(student.getStudentCode())) return false;
        studentList.add(student);
        realmHelper.insert(student);
        return true;
    }

    public void deleteStudent(Student student) {
        studentList.removeIf(s -> s.getStudentCode().equals(student.getStudentCode()));
        realmHelper.delete(student);
    }

    public void deleteStudentAtPosition(int position) {
        Student st = new Student();
        if (position < studentList.size()) {
            st = studentList.get(position);
            studentList.remove(position);
        }

        realmHelper.delete(st);
    }

    public void close() {
        realmHelper.close();
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }
}
