package com.thecheezery.model.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.thecheezery.model.data.database.entity.ProductEntity
import com.thecheezery.model.domain.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Query("SELECT * FROM Products")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM Products WHERE categoryProduct = :category")
    fun getProductsByCategory(category: Category): Flow<List<ProductEntity>>

    @Query("SELECT * FROM Products WHERE idProduct = :productId")
    suspend fun getProductById(productId: Int): ProductEntity?

    @Query("SELECT * FROM Products WHERE nameProduct LIKE '%' || :name || '%'")
    fun searchProducts(name: String): Flow<List<ProductEntity>>

    @Query("SELECT COUNT(*) FROM Products")
    suspend fun countProducts(): Int
}
