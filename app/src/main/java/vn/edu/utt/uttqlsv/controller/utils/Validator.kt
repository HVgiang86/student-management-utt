package vn.edu.utt.uttqlsv.controller.utils

import java.util.regex.Pattern

object Validator {
    //ReGex Pattern for password, floating number, date checking
    private const val PASSWORD_PATTERN =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"

    private const val FLOATING_NUMBER_PATTERN = "[+-]?[0-9]+(\\.[0-9]+)?([Ee][+-]?[0-9]+)?"

    private const val DATE_PATTERN = "^\\d{2}/\\d{2}/\\d{4}"

    fun isValidPassword(password: String): Boolean {
        val pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

    /**
     * This function will validate floating number passed as string is valid or not
     * @param float floating number as string
     * @return true or false
     */
    fun isValidFloatingNumber(float: String): Boolean {
        val pattern = Pattern.compile(FLOATING_NUMBER_PATTERN)
        val matcher = pattern.matcher(float)
        return matcher.matches()
    }

    /**
     * this function will check date string is inform dd/MM/yyyy
     * @param date date string
     * @return true or false
     */
    fun isValidDateString(date: String): Boolean {
        val pattern = Pattern.compile(DATE_PATTERN)
        val matcher = pattern.matcher(date)
        return matcher.matches()
    }
}