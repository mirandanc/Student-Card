package com.example.studentcard.data

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get

class StudentRepository {
    private val settings: Settings = Settings()

    fun saveStudent(name: String, id: String, course: String, photoBase64: String) {
        settings.putString("student_name", name)
        settings.putString("student_id", id)
        settings.putString("student_course", course)
        settings.putString("student_photo", photoBase64)
    }

    fun getStudentName(): String = settings.getString("student_name", "")
    fun getStudentId(): String = settings.getString("student_id", "")
    fun getStudentCourse(): String = settings.getString("student_course", "")
    fun getStudentPhoto(): String = settings.getString("student_photo", "")
}