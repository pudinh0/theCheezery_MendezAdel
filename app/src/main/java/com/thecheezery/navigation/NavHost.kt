package com.thecheezery.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.thecheezery.view.screens.AddComboScreen
import com.thecheezery.view.screens.AddProductScreen
import com.thecheezery.view.screens.CartScreen
import com.thecheezery.view.screens.LoginScreen
import com.thecheezery.view.screens.MenuScreen
import com.thecheezery.view.screens.ProductScreen
import com.thecheezery.view.screens.WelcomeScreen
import com.thecheezery.viewmodel.ShopViewModel

@Composable
fun CheezeryNavHost(viewModel: ShopViewModel) {
    val state by viewModel.state.collectAsState()
    val navController = rememberNavController()

    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = Welcome) {
            composable<Welcome> {
                WelcomeScreen(onStart = { navController.navigate(Login) })
            }
            composable<Login> {
                LoginScreen(
                    onBack = { navController.popBackStack() },
                    onSignIn = {
                        navController.navigate(Menu) {
                            popUpTo<Welcome> { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                )
            }
            composable<Menu> {
                MenuScreen(
                    onCategory = { navController.navigate(Products(it)) },
                    onCart = { navController.navigate(Cart) { launchSingleTop = true } },
                    onAddProduct = { navController.navigate(AddProduct) },
                    onAddCombo = { navController.navigate(AddCombo) },
                )
            }
            composable<Products> { entry ->
                val category = entry.toRoute<Products>().category
                val products by remember(category) { viewModel.products(category) }.collectAsState(initial = emptyList())
                ProductScreen(
                    category = category,
                    products = products,
                    onAdd = viewModel::addToCart,
                    onBack = { navController.popBackStack() },
                    onCart = { navController.navigate(Cart) { launchSingleTop = true } },
                )
            }
            composable<Cart> {
                CartScreen(
                    products = state.cart,
                    onRemove = viewModel::removeFromCart,
                    onBack = { navController.popBackStack() },
                )
            }
            composable<AddProduct> {
                AddProductScreen(
                    onBack = { navController.popBackStack() },
                    onSave = { name, description, price, category ->
                        viewModel.saveProduct(name, description, price, category)
                        navController.popBackStack()
                    },
                )
            }
            composable<AddCombo> {
                val products by viewModel.allProducts.collectAsState()
                AddComboScreen(
                    products = products,
                    onBack = { navController.popBackStack() },
                    onSave = { name, price, productIds ->
                        viewModel.saveCombo(name, price, productIds)
                        navController.popBackStack()
                    },
                )
            }
        }
    }
}
