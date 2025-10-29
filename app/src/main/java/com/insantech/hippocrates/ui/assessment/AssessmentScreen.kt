package com.insantech.hippocrates.ui.assessment

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.insantech.hippocrates.data.PersonalityType
import com.insantech.hippocrates.data.sampleQuestions
import com.insantech.hippocrates.navigation.Screen

@Composable
fun AssessmentScreen(navController: NavController) {
    var currentPage by remember { mutableStateOf(0) }
    val pageSize = 5
    val totalPages = (sampleQuestions.size + pageSize - 1) / pageSize

    val answers = remember { mutableStateMapOf<Int, Int>() }

    val currentQuestions = sampleQuestions.drop(currentPage * pageSize).take(pageSize)

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Halaman ${currentPage + 1} dari $totalPages", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))

            currentQuestions.forEach { question ->
                Text(question.text)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    (1..5).forEach { value ->
                        Button(
                            onClick = { answers[question.id] = value },
                            colors = ButtonDefaults.buttonColors(
                                if (answers[question.id] == value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Text(value.toString())
                        }
                    }
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (currentPage > 0) {
                Button(onClick = { currentPage-- }) {
                    Text("Sebelumnya")
                }
            }

            if (currentPage < totalPages - 1) {
                Button(onClick = { currentPage++ }) {
                    Text("Berikutnya")
                }
            } else {
                Button(onClick = {
                    // hitung hasil
                    val grouped = sampleQuestions.groupBy { it.type }
                    val result = grouped.mapValues { (type, qs) ->
                        qs.sumOf { answers[it.id] ?: 0 }
                    }
                    val args = result.entries.joinToString(",") { "${it.key.name}:${it.value}" }
                    navController.navigate(Screen.Result.route + "?scores=$args")
                }) {
                    Text("Lihat Hasil")
                }
            }
        }
    }
}
