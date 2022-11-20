package vn.edu.utt.uttqlsv.model

data class Account(
    var username: String,
    var hashPassword: String,
    var email: String,
    var phoneNumber: String,
    var gender: Gender,
    var address: String
)
