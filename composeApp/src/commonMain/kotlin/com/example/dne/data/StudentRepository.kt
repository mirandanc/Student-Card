package com.example.dne.data

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get

class StudentRepository(private val settings: Settings = Settings()) {

    fun saveStudent(
        name: String,
        id: String,
        course: String,
        photoBase64: String,
        institution: String,
        level: String,
        document: String,
        birthdate: String
    ) {
        settings.putString("student_name", name)
        settings.putString("student_id", id)
        settings.putString("student_course", course)
        settings.putString("student_photo", photoBase64)
        settings.putString("student_institution", institution)
        settings.putString("student_level", level)
        settings.putString("student_document", document)
        settings.putString("student_birthdate", birthdate)
    }

    fun getStudentName(): String = settings.getString("student_name", "")
    fun getStudentId(): String = settings.getString("student_id", "")
    fun getStudentCourse(): String = settings.getString("student_course", "")
    fun getStudentPhoto(): String = settings.getString("student_photo", "")
    fun getStudentInstitution(): String = settings.getString("student_institution", "")
    fun getStudentLevel(): String = settings.getString("student_level", "")
    fun getStudentDocument(): String = settings.getString("student_document", "")
    fun getStudentBirthdate(): String = settings.getString("student_birthdate", "")

    fun hasStudentData(): Boolean = getStudentName().isNotEmpty()
}