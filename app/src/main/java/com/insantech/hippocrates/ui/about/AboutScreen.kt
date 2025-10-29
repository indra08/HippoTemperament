package com.insantech.hippocrates.ui.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AboutScreen(navController: NavHostController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Tentang Tes Kepribadian", style = MaterialTheme.typography.titleLarge)

        Text(
            "Tes ini didasarkan pada teori klasik empat “temperamen” yang pertama kali diperkenalkan oleh " +
                    "Hippocrates (±460–370 SM) dan dikembangkan oleh Galen. Teori ini membagi kepribadian menjadi empat tipe utama: " +
                    "Koleris, Sanguinis, Melankolis, dan Plegmatis."
        )

        Text(
            "Model ini berfokus pada sifat dasar manusia dan cara seseorang bereaksi terhadap dunia sekitarnya. " +
                    "Meskipun teori ini bersifat klasik, banyak digunakan untuk pengembangan diri dan refleksi karakter pribadi."
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("Referensi Teori & Sumber Online:", style = MaterialTheme.typography.titleMedium)

        // Fungsi pembuat teks link
        @Composable
        fun makeLink(label: String, url: String): AnnotatedString = buildAnnotatedString {
            append("• $label")
            addStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Medium
                ),
                start = 0,
                end = length
            )
            addStringAnnotation(
                tag = "URL",
                annotation = url,
                start = 0,
                end = length
            )
        }

        val links = listOf(
            "Wikipedia – Four Temperaments" to "https://en.wikipedia.org/wiki/Four_temperaments",
            "Hippocrates 4 Temperaments (PDF)" to "https://www.theaiam.com.au/wp-content/uploads/2016/09/Hippocrates-4-Temperaments-Digital-Copy.pdf",
            "Personality Psychology – Four Temperaments & Humors" to "https://personality-psychology.com/four-temperaments-and-humors/",
            "ResearchGate – The Hippocratic View on Human Temperament" to "https://www.researchgate.net/publication/283119853_The_Hippocratic_View_on_Humors_and_Human_Temperament",
            "Verywell Mind – Temperament and Personality Types" to "https://www.verywellmind.com/four-temperaments-2795420",
            "PositivePsychology – Ancient Personality Theory" to "https://positivepsychology.com/four-temperaments/"
        )

        // Loop semua link
        links.forEach { (label, url) ->
            val annotatedText = makeLink(label, url)
            ClickableText(
                text = annotatedText,
                onClick = { offset ->
                    annotatedText.getStringAnnotations("URL", offset, offset)
                        .firstOrNull()?.let {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.item))
                            context.startActivity(intent)
                        }
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Catatan penting: Teori keempat temperamen ini adalah sistem kuno yang tidak sepenuhnya didukung oleh " +
                    "penelitian psikologi modern sebagai model tunggal kepribadian, namun tetap populer untuk refleksi diri dan pengembangan pribadi."
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("Tentang Aplikasi:", style = MaterialTheme.typography.titleMedium)

        Text(
            "Aplikasi ini dikembangkan oleh Insantech sebagai sarana pembelajaran dan refleksi diri, " +
                    "membantu pengguna memahami kecenderungan kepribadian mereka secara sederhana dan menyenangkan."
        )

        Spacer(modifier = Modifier.height(16.dp))
        val insantechLink = buildAnnotatedString {
            append("© 2025 Insantech – https://insantech-id.web.app/")
            addStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                ),
                start = 10,
                end = length
            )
            addStringAnnotation(
                tag = "URL",
                annotation = "https://insantech-id.web.app/",
                start = 10,
                end = length
            )
        }

        ClickableText(
            text = insantechLink,
            onClick = { offset ->
                insantechLink.getStringAnnotations("URL", offset, offset)
                    .firstOrNull()?.let {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.item))
                        context.startActivity(intent)
                    }
            }
        )
    }
}
