package vn.edu.utt.uttqlsv.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Student extends RealmObject {
    @PrimaryKey
    private String studentCode = "0001";

    private String name = "";
    private String className = "";
    private int grade = 10;
    private boolean gender = Gender.MALE;
    private String address = "";
    private float mathScore = 0f;
    private float literatureScore = 0f;
    private float englishScore = 0f;
    private float physicScore = 0f;
    private float chemistryScore = 0f;
    private float historyScore = 0f;
    private float geographyScore = 0f;
    private float biologyScore = 0f;
    private String dateOfBirth = "";

    public Student(String studentCode, String name, String className, int grade, boolean gender, String address, float mathScore, float literatureScore, float englishScore, float physicScore, float chemistryScore, float historyScore, float geographyScore, float biologyScore, String dateOfBirth) {
        this.studentCode = studentCode;
        this.name = name;
        this.className = className;
        this.grade = grade;
        this.gender = gender;
        this.address = address;
        this.mathScore = mathScore;
        this.literatureScore = literatureScore;
        this.englishScore = englishScore;
        this.physicScore = physicScore;
        this.chemistryScore = chemistryScore;
        this.historyScore = historyScore;
        this.geographyScore = geographyScore;
        this.biologyScore = biologyScore;
        this.dateOfBirth = dateOfBirth;
    }

    public Student() {

    }

    public float getAverageScore() {
        return calculateAverageScore();
    }

    private float calculateAverageScore() {
        int count = 0;
        float sum = 0f;
        if (mathScore != 0f) {
            count++;
            sum += mathScore;
        }
        if (literatureScore != 0f) {
            count++;
            sum += literatureScore;
        }
        if (englishScore != 0f) {
            count++;
            sum += englishScore;
        }
        if (physicScore != 0f) {
            count++;
            sum += physicScore;
        }
        if (chemistryScore != 0f) {
            count++;
            sum += chemistryScore;
        }
        if (historyScore != 0f) {
            count++;
            sum += historyScore;
        }
        if (geographyScore != 0f) {
            count++;
            sum += geographyScore;
        }
        if (biologyScore != 0f) {
            count++;
            sum += biologyScore;
        }

        if (count == 0) return 0f;
        return sum / (count * 1.0f);
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getMathScore() {
        return mathScore;
    }

    public void setMathScore(float mathScore) {
        this.mathScore = mathScore;
    }

    public float getLiteratureScore() {
        return literatureScore;
    }

    public void setLiteratureScore(float literatureScore) {
        this.literatureScore = literatureScore;
    }

    public float getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(float englishScore) {
        this.englishScore = englishScore;
    }

    public float getPhysicScore() {
        return physicScore;
    }

    public void setPhysicScore(float physicScore) {
        this.physicScore = physicScore;
    }

    public float getChemistryScore() {
        return chemistryScore;
    }

    public void setChemistryScore(float chemistryScore) {
        this.chemistryScore = chemistryScore;
    }

    public float getHistoryScore() {
        return historyScore;
    }

    public void setHistoryScore(float historyScore) {
        this.historyScore = historyScore;
    }

    public float getGeographyScore() {
        return geographyScore;
    }

    public void setGeographyScore(float geographyScore) {
        this.geographyScore = geographyScore;
    }

    public float getBiologyScore() {
        return biologyScore;
    }

    public void setBiologyScore(float biologyScore) {
        this.biologyScore = biologyScore;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
