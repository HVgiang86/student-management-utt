package vn.edu.utt.uttqlsv.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class Student() : RealmObject {

    @PrimaryKey
    var studentCode: String = "0001"
    var name: String = ""
    var className: String = ""
    var grade: Int = 10
    var gender: Gender = Gender.Male
    var address: String = ""
    var score: ScoreBoard = ScoreBoard()
    var avgScore: Float = 0f

    constructor(
        studentCode: String,
        name: String,
        className: String,
        grade: Int,
        gender: Gender,
        address: String
    ) : this() {
        this.studentCode = studentCode
        this.name = name
        this.className = className
        this.grade = grade
        this.gender = gender
        this.address = address
    }

    fun updateAvgScore() {
        avgScore = score.getAvgScore()
    }


}

