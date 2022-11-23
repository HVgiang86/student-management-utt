package vn.edu.utt.uttqlsv.model.database

import android.util.Log
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import vn.edu.utt.uttqlsv.model.Student

class StudentRealmDatabaseHelper {

    private lateinit var realm: Realm

    fun open() {
        val config = RealmConfiguration.Builder(setOf(Student::class)).build()
        realm = Realm.open(config)
        Log.d("Realm Log", "Successfully opened realm: ${realm.configuration.name}")
    }

    fun insert(student: Student) {

        realm.writeBlocking {
            val studentTarget: Student? =
                this.query<Student>("studentCode == $0", student.studentCode).first().find()

            if (studentTarget == null) this.copyToRealm(student)
        }
        Log.d("Realm Log", "Inserted student $student")
    }

    fun update(studentCode: String, newDataStudent: Student) {
        realm.writeBlocking {
            val studentTarget: Student? =
                this.query<Student>("studentCode == $0", studentCode).first().find()

            studentTarget?.name = newDataStudent.name
            studentTarget?.gender = newDataStudent.gender
            studentTarget?.grade = newDataStudent.grade
            studentTarget?.className = newDataStudent.className
            studentTarget?.address = newDataStudent.address
            studentTarget?.mathScore = newDataStudent.mathScore
            studentTarget?.biologyScore = newDataStudent.biologyScore
            studentTarget?.chemistryScore = newDataStudent.chemistryScore
            studentTarget?.englishScore = newDataStudent.englishScore
            studentTarget?.geographyScore = newDataStudent.geographyScore
            studentTarget?.historyScore = newDataStudent.historyScore
            studentTarget?.literatureScore = newDataStudent.literatureScore
            studentTarget?.physicScore = newDataStudent.physicScore
            studentTarget?.dateOfBirth = newDataStudent.dateOfBirth
        }
        Log.d("Realm Log", "updated!")
    }

    fun delete(student: Student) {
        realm.writeBlocking {
            val studentTarget: Student? =
                this.query<Student>("studentCode == $0", student.studentCode).first().find()
            delete(studentTarget!!)
        }
    }

    fun read(): MutableList<Student> {
        val studentList = mutableListOf<Student>()
        val resultsList: RealmResults<Student> = realm.query<Student>().find()
        for (a in resultsList) {
            studentList.add(a)
        }

        return studentList
    }

    fun close() {
        realm.close()
    }
}