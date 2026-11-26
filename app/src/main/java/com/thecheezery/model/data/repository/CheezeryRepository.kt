package com.thecheezery.model.data.repository

import androidx.room.withTransaction
import com.thecheezery.model.data.MenuData
import com.thecheezery.model.data.database.AppDatabase
import com.thecheezery.model.data.database.entity.ComboEntity
import com.thecheezery.model.data.database.entity.ProductComboEntity
import com.thecheezery.model.data.database.entity.ProductEntity
import com.thecheezery.model.data.database.relation.ComboWithProducts
import com.thecheezery.model.domain.Category
import kotlinx.coroutines.flow.Flow

class CheezeryRepository(private val database: AppDatabase) {

    private val productDao = database.productDao()
    private val comboDao = database.comboDao()
    private val productComboDao = database.productComboDao()

    suspend fun insertProduct(product: ProductEntity): Long {
        return productDao.insertProduct(product)
    }

    suspend fun updateProduct(product: ProductEntity) {
        productDao.updateProduct(product)
    }

    suspend fun deleteProduct(product: ProductEntity) {
        productDao.deleteProduct(product)
    }

    fun getAllProducts(): Flow<List<ProductEntity>> {
        return productDao.getAllProducts()
    }

    fun getProductsByCategory(category: Category): Flow<List<ProductEntity>> {
        return productDao.getProductsByCategory(category)
    }

    suspend fun getProductById(id: Int): ProductEntity? {
        return productDao.getProductById(productId = id)
    }

    fun searchProducts(name: String): Flow<List<ProductEntity>> {
        return productDao.searchProducts(name)
    }

    fun getCombosWithProducts(): Flow<List<ComboWithProducts>> {
        return comboDao.getCombosWithProducts()
    }

    suspend fun getComboWithProducts(id: Int): ComboWithProducts? {
        return comboDao.getComboWithProducts(id)
    }

    suspend fun deleteCombo(combo: ComboEntity) {
        comboDao.deleteCombo(combo)
    }

    suspend fun insertComboWithProducts(combo: ComboEntity, productIds: List<Int>): Long {
        return database.withTransaction {
            val comboId = comboDao.insertCombo(combo).toInt()
            productComboDao.insertProductCombos(
                productIds.map { ProductComboEntity(productId = it, comboId = comboId) },
            )
            comboId.toLong()
        }
    }

    suspend fun seedIfEmpty() {
        database.withTransaction {
            if (productDao.countProducts() > 0) return@withTransaction
            val ids = mutableMapOf<String, Int>()
            MenuData.products.forEach { (category, products) ->
                products.forEach { product ->
                    ids[product.id] = productDao.insertProduct(
                        ProductEntity(
                            name = product.name,
                            description = product.description,
                            price = product.price.toFloat(),
                            category = category,
                        ),
                    ).toInt()
                }
            }
            MenuData.combos.forEach { combo ->
                val comboId = comboDao.insertCombo(
                    ComboEntity(name = combo.name, price = combo.price.toFloat()),
                ).toInt()
                productComboDao.insertProductCombos(
                    combo.productIds.mapNotNull { ids[it] }
                        .map { ProductComboEntity(productId = it, comboId = comboId) },
                )
            }
        }
    }
}
