package com.example.dne.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dne.data.StudentRepository
import com.example.dne.toImageBitmap
import com.example.dne.ui.components.AppTopbar
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import qrcode.QRCode

@OptIn(ExperimentalEncodingApi::class, ExperimentalFoundationApi::class)
@Composable
fun dneScreen(onAdminClick: () -> Unit) {
    val repository = remember { StudentRepository() }
    val uriHandler = LocalUriHandler.current
    
    val name = repository.getStudentName()
    val id = repository.getStudentId()
    val institution = repository.getStudentInstitution()
    val course = repository.getStudentCourse()
    val level = repository.getStudentLevel()
    val document = repository.getStudentDocument()
    val birthdate = repository.getStudentBirthdate()
    val photoBase64 = repository.getStudentPhoto()
    
    // Expiration logic: March of next year
    // Since today is May 2026 (per context), next year is 2027
    val expiration = "31/03/2027"

    val imageBitmap: ImageBitmap? = remember(photoBase64) {
        if (photoBase64.isNotEmpty()) {
            try {
                Base64.decode(photoBase64).toImageBitmap()
            } catch (e: Exception) {
                null
            }
        } else null
    }

    val qrCodeBitmap: ImageBitmap? = remember {
        try {
            QRCode.ofSquares()
                .build("https://www.une.org.br/lp/dne-documento-do-estudante/")
                .renderToBytes()
                .toImageBitmap()
        } catch (e: Exception) {
            null
        }
    }

    Scaffold(
        topBar = { AppTopbar() },
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Photo Card
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(3f / 4f)
                                .padding(16.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFF0F0F0))
                                .combinedClickable(
                                    onClick = {},
                                    onLongClick = onAdminClick
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (imageBitmap != null) {
                                Image(
                                    bitmap = imageBitmap,
                                    contentDescription = "Student Photo",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Text("Foto", color = Color.Gray, fontSize = 12.sp)
                            }
                        }
                    }

                    // QR Code Card
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(3f / 4f)
                                .padding(16.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White)
                                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (qrCodeBitmap != null) {
                                Image(
                                    bitmap = qrCodeBitmap,
                                    contentDescription = "QR Code",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Fit
                                )
                            } else {
                                Text("QR Code", color = Color.Gray, fontSize = 12.sp)
                            }
                        }
                    }
                }

                // Student Details Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Student Information
                        StudentInfoField("NOME", name.ifEmpty { "NOME DO ESTUDANTE" })
                        StudentInfoField("INSTITUIÇÃO", institution.ifEmpty { "INSTITUIÇÃO DE ENSINO" })
                        StudentInfoField("CURSO", course.ifEmpty { "CURSO" })
                        StudentInfoField("GRAU DE ESCOLARIDADE", level.ifEmpty { "ESCOLARIDADE" })
                        
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Box(modifier = Modifier.weight(1f)) {
                                StudentInfoField("DOCUMENTO", document.ifEmpty { "000.000.000-00" })
                            }
                            Box(modifier = Modifier.weight(1f)) {
                                StudentInfoField("DATA DE NASCIMENTO", birthdate.ifEmpty { "00/00/0000" })
                            }
                        }

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Box(modifier = Modifier.weight(1f)) {
                                StudentInfoField("CÓDIGO DE USO", id.ifEmpty { "000000000" })
                            }
                            Box(modifier = Modifier.weight(1f)) {
                                StudentInfoField("VALIDADE", expiration)
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Footer logos or text could go here if needed to match the reference better
                        Text(
                            "CERTIFICAÇÃO DIGITAL",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StudentInfoField(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(
            text = label,
            fontSize = 10.sp,
            color = Color(0xFF1976D2),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value.uppercase(),
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )
    }
}