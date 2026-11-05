package com.thecheezery.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import com.thecheezery.model.domain.Product
import com.thecheezery.ui.theme.Pinky
import com.thecheezery.ui.theme.Purple_grey
import com.thecheezery.ui.theme.Very_purple
import com.thecheezery.view.components.*

@Composable
fun AddProductScreen(onBack: () -> Unit, onSave: (String, String, Double) -> Unit) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    val amount = price.toDoubleOrNull()
    Column(Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 12.dp)) {
        TextButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) { Text("‹  Menú", color = Very_purple) }
        Text("Un nuevo antojo", color = Pinky, fontFamily = FontFamily.Cursive, fontSize = 42.sp, modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 18.dp))
        Text("Agrega un producto a la categoría Sweets", color = Purple_grey, fontSize = 15.sp, modifier = Modifier.padding(bottom = 20.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Nombre del producto") }, singleLine = true, shape = RoundedCornerShape(12.dp))
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(value = description, onValueChange = { description = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Descripción") }, minLines = 2, maxLines = 3, shape = RoundedCornerShape(12.dp))
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(value = price, onValueChange = { price = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Precio ($)") }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), shape = RoundedCornerShape(12.dp))
        Spacer(Modifier.height(22.dp))
        PinkButton("Guardar producto", { if (name.isNotBlank() && amount != null && amount > 0) onSave(name, description, amount) }, Modifier.align(Alignment.CenterHorizontally))
    }
}
