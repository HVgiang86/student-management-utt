package vn.edu.utt.uttqlsv.model

data class ScoreBoard(
    var mathScore: Float = 0f,
    var literatureScore: Float = 0f,
    var englishScore: Float = 0f,
    var physicScore: Float = 0f,
    var chemistryScore: Float = 0f,
    var historyScore: Float = 0f,
    var geographyScore: Float = 0f,
    var biologyScore: Float = 0f
) {
    fun getAvgScore(): Float {
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

        return sum/(count*1.0f)
    }
}
