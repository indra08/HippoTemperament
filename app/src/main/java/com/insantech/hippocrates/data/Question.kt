package com.insantech.hippocrates.data

data class Question(
    val id: Int,
    val text: String,
    val type: PersonalityType
)

enum class PersonalityType {
    KOLERIS, SANGUINIS, MELANKOLIS, PLEGMATIS
}
