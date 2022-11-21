package vn.edu.utt.uttqlsv.model.database

import android.util.Log
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import vn.edu.utt.uttqlsv.model.Student

class StudentRealmDatabaseHelper {

    private lateinit var realm: Realm

    fun open() {
        val config = RealmConfiguration.Builder(setOf(Student::class))
            .build()
        realm = Realm.open(config)
        Log.d("Realm Log","Successfully opened realm: ${realm.configuration.name}")
    }

    fun insert(student: Student) {
        realm.writeBlocking {
            this.copyToRealm(student)
        }
        Log.d("Realm Log","Inserted student $student")
    }

    fun update(student: Student) {
        realm.writeBlocking {
            val studentTarget: Student? =
                this.query<Student>("studentCode == $0",student.studentCode).first().find()

            studentTarget?.name = student.name
            studentTarget?.gender = student.gender
            studentTarget?.studentCode = student.studentCode
            studentTarget?.grade = student.grade
            studentTarget?.className = student.className
            studentTarget?.score = student.score
            studentTarget?.avgScore = student.avgScore
            studentTarget?.address = student.address
        }
    }

    fun delete(student: Student) {
        realm.writeBlocking {
            val studentTarget: Student? =
                this.query<Student>("studentCode == $0",student.studentCode).first().find()
            delete(studentTarget!!)
        }
    }

    fun close() {
        realm.close()
    }
}