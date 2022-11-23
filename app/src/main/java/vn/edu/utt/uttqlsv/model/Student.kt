package vn.edu.utt.uttqlsv.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class Student() : RealmObject {

    @PrimaryKey
    var studentCode: String = "0001"
    var name: String = ""
    var className: String = ""
    var grade: Int = 10
    var gender: Boolean = Gender.MALE
    var address: String = ""
    var mathScore: Float = 0f
    var literatureScore: Float = 0f
    var englishScore: Float = 0f
    var physicScore: Float = 0f
    var chemistryScore: Float = 0f
    var historyScore: Float = 0f
    var geographyScore: Float = 0f
    var biologyScore: Float = 0f
    var dateOfBirth: String = ""

    constructor(
        studentCode: String,
        name: String,
        className: String,
        grade: Int,
        gender: Boolean,
        address: String,
        mathScore: Float,
        literatureScore: Float,
        englishScore: Float,
        physicScore: Float,
        chemistryScore: Float,
        historyScore: Float,
        geographyScore: Float,
        biologyScore: Float,
        dateOfBirth: String
    ) : this() {
        this.studentCode = studentCode
        this.name = name
        this.className = className
        this.grade = grade
        this.gender = gender
        this.address = address
        this.mathScore = mathScore
        this.literatureScore = literatureScore
        this.englishScore = englishScore
        this.physicScore = physicScore
        this.chemistryScore = chemistryScore
        this.historyScore = historyScore
        this.geographyScore = geographyScore
        this.biologyScore = biologyScore
        this.dateOfBirth = dateOfBirth
    }


    fun getAverageScore(): Float {
        return calculateAverageScore()
    }

    private fun calculateAverageScore(): Float {
        var count = 0
        var sum = 0f
        if (mathScore != 0f) {
            count++
            sum += mathScore
        }
        if (literatureScore != 0f) {
            count++
            sum += literatureScore
        }
        if (englishScore != 0f) {
            count++
            sum += englishScore
        }
        if (physicScore != 0f) {
            count++
            sum += physicScore
        }
        if (chemistryScore != 0f) {
            count++
            sum += chemistryScore
        }
        if (historyScore != 0f) {
            count++
            sum += historyScore
        }
        if (geographyScore != 0f) {
            count++
            sum += geographyScore
        }
        if (biologyScore != 0f) {
            count++
            sum += biologyScore
        }

        if (count == 0)
            return 0f
        return sum / (count * 1.0f)
    }

}

