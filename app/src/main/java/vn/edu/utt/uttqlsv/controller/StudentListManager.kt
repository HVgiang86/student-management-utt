package vn.edu.utt.uttqlsv.controller

import vn.edu.utt.uttqlsv.model.Student
import vn.edu.utt.uttqlsv.model.database.StudentRealmDatabaseHelper

object StudentListManager {
    var studentList = mutableListOf<Student>()
    private val realmHelper = StudentRealmDatabaseHelper()

    init {
        realmHelper.open()
        loadStoredStudentList()
    }

    private fun loadStoredStudentList() {
        studentList = realmHelper.read()
    }


    fun updateStudentInfo(
        student: Student, newStudent: Student
    ) {
        realmHelper.update(student.studentCode, newStudent)
        studentList.remove(student)
        studentList.add(newStudent)
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
        student: Student
    ): Boolean {
        if (student.studentCode.isEmpty()) return false
        if (isStudentCodeExist(student.studentCode)) {
            return false
        }
        studentList.add(student)
        realmHelper.insert(student)
        return true
    }

    fun deleteStudent(student: Student) {
        for (a in studentList) {
            if (a.studentCode == student.studentCode) {
                studentList.remove(a)
            }
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

    fun close() {
        realmHelper.close()
    }
}