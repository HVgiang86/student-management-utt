package vn.edu.utt.uttqlsv.controller

import vn.edu.utt.uttqlsv.model.Gender
import vn.edu.utt.uttqlsv.model.Student
import vn.edu.utt.uttqlsv.model.database.StudentRealmDatabaseHelper

object StudentListManager {
    var studentList = mutableListOf<Student>()
    private val realmHelper = StudentRealmDatabaseHelper()

    init {
        realmHelper.open()

        val student1 = Student("00001","Hoang Van A","12A1",12,Gender.Male,"this is address")
        val student2 = Student("00002","Tran Thi B","12A1",12,Gender.Female,"this is address")
        val student3 = Student("00003","Hoang Thi C","12A1",12,Gender.Female,"this is address")
        val student4 = Student("00004","Nguyen Hoang Tuan D","12A1",12,Gender.Male,"this is address")

        realmHelper.insert(student1)
        realmHelper.insert(student2)
        realmHelper.insert(student3)
        realmHelper.insert(student4)

        loadStoredStudentList()
    }

    fun loadStoredStudentList() {
        studentList = realmHelper.read()
    }

    fun editStudentInfo(student: Student) {

    }
}