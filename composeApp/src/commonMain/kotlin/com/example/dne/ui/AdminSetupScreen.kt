package com.example.dne.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.vinceglb.filekit.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.core.PickerMode
import io.github.vinceglb.filekit.core.PickerType
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import com.example.dne.data.StudentRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalEncodingApi::class)
@Composable
fun AdminSetupScreen(onSave: () -> Unit) {
    val repository = remember { StudentRepository() }
    val scope = rememberCoroutineScope()
    var name by remember { mutableStateOf(repository.getStudentName()) }
    var id by remember { mutableStateOf(repository.getStudentId()) }
    var course by remember { mutableStateOf(repository.getStudentCourse()) }
    var institution by remember { mutableStateOf(repository.getStudentInstitution()) }
    var level by remember { mutableStateOf(repository.getStudentLevel()) }
    var document by remember { mutableStateOf(repository.getStudentDocument()) }
    var birthdate by remember { mutableStateOf(repository.getStudentBirthdate()) }
    var photoBase64 by remember { mutableStateOf(repository.getStudentPhoto()) }

    val picker = rememberFilePickerLauncher(
        type = PickerType.Image,
        mode = PickerMode.Single,
        onResult = { file ->
            scope.launch {
                file?.let {
                    val bytes = it.readBytes()
                    photoBase64 = Base64.encode(bytes)
                }
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).padding(top = 40.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Admin Setup", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("Student ID") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = institution,
            onValueChange = { institution = it },
            label = { Text("Institution") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = course,
            onValueChange = { course = it },
            label = { Text("Course") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = level,
            onValueChange = { level = it },
            label = { Text("Level of Education") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = document,
            onValueChange = { document = it },
            label = { Text("Document (RG/CPF)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = birthdate,
            onValueChange = { birthdate = it },
            label = { Text("Birthdate (DD/MM/YYYY)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { picker.launch() }) {
            Text("Pick Student Photo")
        }
        if (photoBase64.isNotEmpty()) {
            Text("Photo selected", color = MaterialTheme.colorScheme.primary)
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                repository.saveStudent(name, id, course, photoBase64, institution, level, document, birthdate)
                onSave()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}