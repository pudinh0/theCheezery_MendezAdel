package com.thecheezery.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecheezery.model.domain.Category
import com.thecheezery.model.domain.Product
import com.thecheezery.ui.theme.Pinky
import com.thecheezery.ui.theme.Very_purple
import com.thecheezery.view.components.*

@Composable
fun ProductScreen(
    category: Category,
    products: List<Product>,
    onAdd: (Product) -> Unit,
    onBack: () -> Unit,
    onCart: () -> Unit,
) {
    Column(Modifier.fillMaxSize().padding(horizontal = 18.dp)) {
        Row(Modifier.fillMaxWidth().padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = onBack) { Text("‹  Menú", color = Very_purple) }
            CircleAction("cart", onCart, tint = Pinky, size = 44)
        }
        Text(
            text = category.title,
            color = Pinky,
            fontFamily = FontFamily.Cursive,
            fontSize = 48.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 12.dp),
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxSize()) {
            items(products, key = { it.id }) { product -> ProductRow(product, onAdd = { onAdd(product) }) }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
