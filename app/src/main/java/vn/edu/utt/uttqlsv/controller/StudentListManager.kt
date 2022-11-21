package vn.edu.utt.uttqlsv.controller

import vn.edu.utt.uttqlsv.model.Gender
import vn.edu.utt.uttqlsv.model.ScoreBoard
import vn.edu.utt.uttqlsv.model.Student
import vn.edu.utt.uttqlsv.model.database.StudentRealmDatabaseHelper

object StudentListManager {
    var studentList = mutableListOf<Student>()
    private val realmHelper = StudentRealmDatabaseHelper()

    init {
        realmHelper.open()

        val student1 = Student("00001", "Hoang Van A", "12A1", 12, Gender.Male, "this is address")
        val student2 = Student("00002", "Tran Thi B", "12A1", 12, Gender.Female, "this is address")
        val student3 = Student("00003", "Hoang Thi C", "12A1", 12, Gender.Female, "this is address")
        val student4 =
            Student("00004", "Nguyen Hoang Tuan D", "12A1", 12, Gender.Male, "this is address")

        realmHelper.insert(student1)
        realmHelper.insert(student2)
        realmHelper.insert(student3)
        realmHelper.insert(student4)

        loadStoredStudentList()
    }

    fun loadStoredStudentList() {
        studentList = realmHelper.read()
    }


    fun editStudentInfo(
        student: Student,
        studentCode: String,
        name: String,
        className: String,
        grade: String,
        gender: Gender,
        address: String,
        scoreBoard: ScoreBoard
    ) {
        val gradeNumber = Integer.parseInt(grade)
        val newDataStudent = Student(studentCode, name, className, gradeNumber, gender, address)
        student.score = scoreBoard
        realmHelper.update(studentCode, newDataStudent)
        studentList.remove(student)
        studentList.add(newDataStudent)
    }

    private fun isStudentCodeExist(studentCode: String): Boolean {
        for (a in studentList) {
            if (a.studentCode == studentCode) {
                return true
            }
        }
        return false
    }

    fun addAnStudent(
        studentCode: String,
        name: String,
        className: String,
        grade: String,
        gender: Gender,
        address: String,
        scoreBoard: ScoreBoard
    ): Boolean {
        if (isStudentCodeExist(studentCode)) {
            return false
        }
        val gradeNumber = Integer.parseInt(grade)
        val student = Student(studentCode, name, className, gradeNumber, gender, address)
        student.score = scoreBoard
        student.updateAvgScore()
        studentList.add(student)
        realmHelper.insert(student)
        return true
    }

    fun deleteStudent(student: Student) {
        if (studentList.contains(student)) {
            studentList.remove(student)
        }
        realmHelper.delete(student)
    }

    fun deleteStudentAtPosition(position: Int) {
        var student = Student()
        if (position < studentList.size) {
            student = studentList[position]
            studentList.removeAt(position)
        }

        realmHelper.delete(student)
    }
}