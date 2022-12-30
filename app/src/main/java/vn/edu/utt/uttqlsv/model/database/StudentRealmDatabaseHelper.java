package vn.edu.utt.uttqlsv.model.database;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import vn.edu.utt.uttqlsv.model.Student;


public class StudentRealmDatabaseHelper {

    private static final String REALM_NAME = "student_db";
    private static final String TAG = "REALM TAG";
    private Realm realm;
    private boolean isRead = false;
    private RealmResults<Student> realmResults;
    private boolean isOpened = false;


    //single ton installation
    private static final StudentRealmDatabaseHelper INSTANCE = new StudentRealmDatabaseHelper();
    private StudentRealmDatabaseHelper() {
    }

    public static StudentRealmDatabaseHelper getInstance() {
        return INSTANCE;
    }
    //end of single ton installation

    public void open(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name(REALM_NAME).allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(config);
        isOpened = true;
    }

    public void insert(Student student) {
        Student check = realm.copyToRealm(student);
        if (!isRead) read();

        if (isExist(check.getStudentCode())) return;

        realm.executeTransaction(transaction -> {
            realm.insert(check);
            Log.d(TAG, "Inserted student: " + check.getStudentCode() + " into db");

        });
    }

    public ArrayList<Student> read() {
        if (!isRead) {
            if (!isOpened) {
                Log.d(TAG, "Realm database not init yet");
                return null;
            }

            realm.executeTransaction(transaction -> {
                realmResults = realm.where(Student.class).findAll();
                Log.d(TAG, "Loaded " + realmResults.size() + " students(s) from db!");
            });

            isRead = true;
        }

        if (realmResults.isEmpty()) return null;
        return convertResultsToArrayList();
    }

    public void delete(Student student) {
        Student target = null;
        for (Student s: realmResults) {
            if (s.getStudentCode().equals(student.getStudentCode())) {
                target = s;
            }
        }

        Student finalTarget = target;
        realm.executeTransaction(transaction -> {
            if (finalTarget != null)
                finalTarget.deleteFromRealm();
        });
    }

    public void update(String studentCode, Student newStudent) {
        realm.executeTransaction(transaction -> {
            for (Student student: realmResults) {
                if (student.getStudentCode().equals(studentCode)) {
                    student.setName(newStudent.getName());
                    student.setStudentCode(newStudent.getStudentCode());
                    student.setGender(newStudent.isGender());
                    student.setGrade(newStudent.getGrade());
                    student.setClassName(newStudent.getClassName());
                    student.setAddress(newStudent.getAddress());
                    student.setMathScore(newStudent.getMathScore());
                    student.setLiteratureScore(newStudent.getLiteratureScore());
                    student.setBiologyScore(newStudent.getBiologyScore());
                    student.setChemistryScore(newStudent.getChemistryScore());
                    student.setEnglishScore(newStudent.getEnglishScore());
                    student.setGeographyScore(newStudent.getGeographyScore());
                    student.setHistoryScore(newStudent.getHistoryScore());
                    student.setPhysicScore(newStudent.getPhysicScore());
                    student.setDateOfBirth(newStudent.getDateOfBirth());
                    transaction.insertOrUpdate(student);
                    Log.d(TAG, "updated!");
                }
            }
        });
    }


    private ArrayList<Student> convertResultsToArrayList() {
        if (isRead) {
            return new ArrayList<>(realmResults);
        }
        return null;
    }

    public void close() {
        realm.close();
    }

    private boolean isExist(String studentCode) {
        for (Student s : realmResults) {
            if (s.getStudentCode().equals(studentCode)) return true;
        }
        return false;
    }

}
