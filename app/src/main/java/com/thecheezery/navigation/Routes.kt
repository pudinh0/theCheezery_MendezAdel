package com.thecheezery.navigation

import kotlinx.serialization.Serializable
import com.thecheezery.model.domain.Category

@Serializable
object Welcome

@Serializable
object Login

@Serializable
object Menu

@Serializable
data class Products(val category: Category)

@Serializable
object Cart

@Serializable
object AddProduct
