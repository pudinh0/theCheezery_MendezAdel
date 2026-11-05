package com.thecheezery.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecheezery.model.domain.Product
import com.thecheezery.ui.theme.DarkGrey
import com.thecheezery.ui.theme.Less_Purple
import com.thecheezery.ui.theme.Pinky
import com.thecheezery.ui.theme.Purple_grey
import com.thecheezery.ui.theme.Very_purple
import com.thecheezery.view.components.*
import com.thecheezery.utilities.asPrice

@Composable
fun CartScreen(products: List<Product>, onRemove: (Product) -> Unit, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 12.dp)) {
        TextButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) { Text("‹  Menú", color = Very_purple) }
        Text("Mi pedido", color = Pinky, fontFamily = FontFamily.Cursive, fontSize = 48.sp, modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 10.dp))
        if (products.isEmpty()) {
            Column(Modifier.weight(1f).fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                CartGlyph(size = 54, color = Very_purple)
                Spacer(Modifier.height(12.dp))
                Text("Agrega productos a tu pedido.", color = Purple_grey, fontSize = 16.sp, textAlign = TextAlign.Center)
            }
        } else {
            LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(products.mapIndexed { index, product -> index to product }, key = { it.first }) { (_, product) ->
                    CartRow(product, onRemove = { onRemove(product) })
                }
            }
        }
        val total = products.sumOf(Product::price)
        Row(Modifier.fillMaxWidth().padding(vertical = 18.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
            Text("Total", color = DarkGrey, fontSize = 28.sp)
            Spacer(Modifier.width(14.dp))
            Text("$${total.asPrice()}", color = Less_Purple, fontSize = 38.sp, fontWeight = FontWeight.Bold)
        }
    }
}
