package com.example.dne

import com.example.dne.data.StudentRepository
import com.russhwolf.settings.MapSettings
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ComposeAppCommonTest {

    @Test
    fun testStudentRepositoryHasStudentData() {
        val settings = MapSettings()
        val repository = StudentRepository(settings)
        
        // Assert initial state is false (no data saved yet)
        assertFalse(repository.hasStudentData())
        
        repository.saveStudent(
            name = "Test Student",
            id = "12345",
            course = "Computer Science",
            photoBase64 = "base64String",
            institution = "Test University",
            level = "Undergraduate",
            document = "123.456.789-00",
            birthdate = "01/01/2000"
        )
        
        // Assert state is true after saving student data
        assertTrue(repository.hasStudentData())
    }
}