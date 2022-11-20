package vn.edu.utt.uttqlsv.model

data class Student(
    var studentCode: String,
    var name: String,
    var className: String,
    var grade: String,
    var gender: Gender,
    var address: String,
    var email: String
)
