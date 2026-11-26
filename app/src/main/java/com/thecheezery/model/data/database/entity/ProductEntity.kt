package com.thecheezery.model.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thecheezery.model.domain.Category

@Entity(tableName = "Products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idProduct")
    val id: Int = 0,

    @ColumnInfo(name = "nameProduct")
    val name: String,

    @ColumnInfo(name = "descriptionProduct")
    val description: String,

    @ColumnInfo(name = "priceProduct")
    val price: Float,

    @ColumnInfo(name = "categoryProduct")
    val category: Category,

    @ColumnInfo(name = "imageProduct")
    val image: String? = null,
)
