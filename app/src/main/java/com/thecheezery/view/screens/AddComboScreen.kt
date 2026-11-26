package com.thecheezery.view.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecheezery.model.data.database.entity.ProductEntity
import com.thecheezery.ui.theme.DarkGrey
import com.thecheezery.ui.theme.Pinky
import com.thecheezery.ui.theme.Purple_grey
import com.thecheezery.ui.theme.Very_purple
import com.thecheezery.utilities.asPrice
import com.thecheezery.view.components.PinkButton

@Composable
fun AddComboScreen(
    products: List<ProductEntity>,
    onBack: () -> Unit,
    onSave: (name: String, price: Double, productIds: List<Int>) -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf(setOf<Int>()) }
    var submitted by remember { mutableStateOf(false) }
    val amount = price.toDoubleOrNull()
    val valid = name.isNotBlank() && amount != null && amount > 0 && selected.size >= 2

    Column(Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 12.dp)) {
        TextButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) { Text("‹  Menú", color = Very_purple) }
        Text("Un nuevo combo", color = Pinky, fontFamily = FontFamily.Cursive, fontSize = 42.sp, modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 12.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Nombre del combo") }, singleLine = true, shape = RoundedCornerShape(12.dp))
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(value = price, onValueChange = { price = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Precio ($)") }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), shape = RoundedCornerShape(12.dp))
        Text("Elige al menos 2 productos (${selected.size} seleccionados)", color = Purple_grey, fontSize = 14.sp, modifier = Modifier.padding(top = 16.dp, bottom = 4.dp))
        LazyColumn(Modifier.weight(1f)) {
            items(products, key = { it.id }) { product ->
                val checked = product.id in selected
                Row(
                    Modifier.fillMaxWidth().clickable { selected = if (checked) selected - product.id else selected + product.id },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(checked = checked, onCheckedChange = { selected = if (it) selected + product.id else selected - product.id })
                    Column(Modifier.weight(1f)) {
                        Text(product.name, color = DarkGrey, fontSize = 16.sp)
                        Text(product.category.title, color = Purple_grey, fontSize = 12.sp)
                    }
                    Text("$${product.price.toDouble().asPrice()}", color = Very_purple, fontSize = 14.sp)
                }
            }
        }
        if (submitted && !valid) {
            Text("Completa nombre, precio y elige al menos 2 productos", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }
        Spacer(Modifier.height(10.dp))
        PinkButton("Guardar combo", {
            submitted = true
            if (valid) onSave(name, amount!!, selected.toList())
        }, Modifier.align(Alignment.CenterHorizontally))
    }
}
