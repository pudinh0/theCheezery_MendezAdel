package com.thecheezery.model.domain

import kotlinx.serialization.Serializable

@Serializable
enum class Category(val title: String) {
    HOT_DRINKS("Hot drinks"),
    COLD_DRINKS("Cold drinks"),
    SALTIES("Salties"),
    SWEETS("Sweets"),
    COMBOS("Combos"),
}
