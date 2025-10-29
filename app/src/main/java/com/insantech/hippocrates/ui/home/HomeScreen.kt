package com.insantech.hippocrates.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.insantech.hippocrates.navigation.Screen
import com.insantech.hippocrates.R

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // --- Judul & Gambar Utama ---
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Kalau belum ada gambar, bisa dikosongkan atau tambahkan ilustrasi di res/drawable
            // Image(painter = painterResource(R.drawable.ic_brain), contentDescription = "Hippocrates", modifier = Modifier.size(120.dp))
            Text(
                "Tes Kepribadian Hippocrates",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "Kenali tipe kepribadianmu berdasarkan teori klasik empat temperamen: Koleris, Sanguinis, Melankolis, dan Plegmatis.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 12.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }

        // --- Tombol Menu ---
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate(Screen.Assessment.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Mulai Tes")
            }

            OutlinedButton(
                onClick = { navController.navigate(Screen.Result.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Lihat Hasil Terakhir")
            }

            TextButton(
                onClick = { navController.navigate(Screen.About.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Tentang Tes")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            "© 2025 Hippocrates Personality Test",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline
        )
    }
}
