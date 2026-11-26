package com.thecheezery.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.thecheezery.model.data.database.AppDatabase
import com.thecheezery.model.data.database.entity.ComboEntity
import com.thecheezery.model.data.database.entity.ProductEntity
import com.thecheezery.model.data.database.relation.ComboWithProducts
import com.thecheezery.model.data.repository.CheezeryRepository
import com.thecheezery.model.domain.Category
import com.thecheezery.model.domain.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ShopState(
    val cart: List<Product> = emptyList(),
)

class ShopViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CheezeryRepository(AppDatabase.getInstance(application))

    private val _state = MutableStateFlow(ShopState())
    val state: StateFlow<ShopState> = _state.asStateFlow()

    val allProducts: StateFlow<List<ProductEntity>> = repository.getAllProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    init {
        viewModelScope.launch { repository.seedIfEmpty() }
    }

    fun addToCart(product: Product) = _state.update { it.copy(cart = it.cart + product) }

    fun removeFromCart(product: Product) = _state.update { state ->
        val index = state.cart.indexOfFirst { it.id == product.id }
        if (index < 0) state else state.copy(cart = state.cart.toMutableList().also { it.removeAt(index) })
    }

    fun products(category: Category): Flow<List<Product>> =
        if (category == Category.COMBOS) {
            repository.getCombosWithProducts().map { combos -> combos.map { it.toDomain() } }
        } else {
            repository.getProductsByCategory(category).map { products -> products.map { it.toDomain() } }
        }

    fun saveProduct(name: String, description: String, price: Double, category: Category) {
        viewModelScope.launch {
            repository.insertProduct(
                ProductEntity(
                    name = name.trim(),
                    description = description.trim(),
                    price = price.toFloat(),
                    category = category,
                ),
            )
        }
    }

    fun saveCombo(name: String, price: Double, productIds: List<Int>) {
        viewModelScope.launch {
            repository.insertComboWithProducts(ComboEntity(name = name.trim(), price = price.toFloat()), productIds)
        }
    }
}

private fun ProductEntity.toDomain() = Product(
    id = "product-$id",
    name = name,
    description = description,
    price = price.toDouble(),
)

private fun ComboWithProducts.toDomain() = Product(
    id = "combo-${combo.id}",
    name = combo.name,
    description = products.joinToString(" + ") { it.name },
    price = combo.price.toDouble(),
)
