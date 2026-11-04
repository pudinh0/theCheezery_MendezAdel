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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecheezery.model.domain.Category
import com.thecheezery.ui.theme.DisabledGrey
import com.thecheezery.ui.theme.Less_Purple
import com.thecheezery.ui.theme.Pinky
import com.thecheezery.ui.theme.Purple_grey
import com.thecheezery.view.components.*

@Composable
fun MenuScreen(onCategory: (Category) -> Unit, onCart: () -> Unit, onAddProduct: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 18.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            CircleAction("cart", onCart, tint = Pinky, size = 48)
        }
        Spacer(Modifier.weight(.7f))
        BrandWordmark(color = Less_Purple, size = 66, modifier = Modifier.align(Alignment.CenterHorizontally))
        Text("Café, antojos y algo dulce", color = Purple_grey, fontSize = 13.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(30.dp))
        val categories = listOf(Category.HOT_DRINKS, Category.COLD_DRINKS, Category.SALTIES, Category.SWEETS, Category.COMBOS)
        val tiles = categories.map { it.title to { onCategory(it) } } + ("Agregar producto" to onAddProduct)
        tiles.chunked(2).forEach { row ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                row.forEach { (title, action) ->
                    CategoryCard(title, onClick = action, modifier = Modifier.weight(1f))
                }
                if (row.size == 1) Spacer(Modifier.weight(1f))
            }
            Spacer(Modifier.height(12.dp))
        }
        Spacer(Modifier.weight(1f))
        Text("Hecho con cariño para disfrutar.", color = DisabledGrey, fontSize = 12.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}
