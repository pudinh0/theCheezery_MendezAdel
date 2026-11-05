package com.thecheezery.viewmodel

import androidx.lifecycle.ViewModel
import com.thecheezery.model.domain.Category
import com.thecheezery.model.data.MenuData
import com.thecheezery.model.domain.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ShopState(
    val cart: List<Product> = emptyList(),
    val customProducts: List<Product> = emptyList(),
)

class ShopViewModel : ViewModel() {
    private val _state = MutableStateFlow(ShopState())
    val state: StateFlow<ShopState> = _state.asStateFlow()

    fun addToCart(product: Product) = _state.update { it.copy(cart = it.cart + product) }
    fun removeFromCart(product: Product) = _state.update { state ->
        val index = state.cart.indexOfFirst { it.id == product.id }
        if (index < 0) state else state.copy(cart = state.cart.toMutableList().also { it.removeAt(index) })
    }

    fun saveProduct(name: String, description: String, price: Double) = _state.update { state ->
        val product = Product("custom-${System.currentTimeMillis()}", name.trim(), description.trim(), price)
        state.copy(customProducts = state.customProducts + product)
    }

    fun products(category: Category): List<Product> = MenuData.products[category].orEmpty() +
        if (category == Category.SWEETS) _state.value.customProducts else emptyList()
}
