package com.insantech.hippocrates.ui.result

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.insantech.hippocrates.data.PersonalityType

@Composable
fun ResultScreen(navController: NavController) {
    // Contoh hasil (di versi final diisi dari hasil tes)
    val result = mapOf(
        PersonalityType.KOLERIS to 72,
        PersonalityType.SANGUINIS to 60,
        PersonalityType.MELANKOLIS to 50,
        PersonalityType.PLEGMATIS to 80
    )

    val dominant = result.maxByOrNull { it.value }?.key ?: PersonalityType.KOLERIS

    val descriptions = mapOf(
        PersonalityType.KOLERIS to Pair(
            "Koleris (Dominan, Tegas, Pemimpin)",
            "Koleris adalah tipe yang tegas, logis, dan senang mengambil keputusan. Mereka memiliki kemampuan kepemimpinan yang kuat dan berorientasi pada hasil.",
        ),
        PersonalityType.SANGUINIS to Pair(
            "Sanguinis (Ceria, Ramah, Energik)",
            "Sanguinis dikenal sebagai pribadi yang hangat, suka bersosialisasi, dan selalu membawa energi positif ke sekitarnya.",
        ),
        PersonalityType.MELANKOLIS to Pair(
            "Melankolis (Perfeksionis, Serius, Analitis)",
            "Melankolis sangat memperhatikan detail, disiplin, dan memiliki standar tinggi dalam segala hal.",
        ),
        PersonalityType.PLEGMATIS to Pair(
            "Plegmatis (Tenang, Sabar, Diplomatis)",
            "Plegmatis adalah pribadi yang damai, sabar, dan dapat menenangkan orang lain di situasi sulit.",
        ),
    )

    val improvements = mapOf(
        PersonalityType.KOLERIS to "Belajarlah mendengarkan pendapat orang lain dan mengontrol rasa ingin menguasai situasi.",
        PersonalityType.SANGUINIS to "Fokuslah menyelesaikan apa yang dimulai, dan belajarlah mendengarkan lebih dalam.",
        PersonalityType.MELANKOLIS to "Cobalah lebih fleksibel dan tidak terlalu keras pada diri sendiri maupun orang lain.",
        PersonalityType.PLEGMATIS to "Beranilah mengambil keputusan dan jangan takut keluar dari zona nyaman."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Hasil Tes Kepribadian", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)

        result.forEach { (type, score) ->
            val (title, desc) = descriptions[type] ?: return@forEach
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            LinearProgressIndicator(
                progress = score / 100f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            Text("${score}%", style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.height(4.dp))
            Text(desc, style = MaterialTheme.typography.bodyMedium)
            Text(
                "Saran pengembangan: ${improvements[type]}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
        }

        // Dominan
        Text(
            "Kepribadian Dominan: ${descriptions[dominant]?.first}",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            descriptions[dominant]?.second ?: "",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(24.dp))
        Button(
            onClick = { navController.navigate("assessment") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ulangi Tes")
        }
    }
}
