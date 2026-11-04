package com.thecheezery.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecheezery.model.domain.Product
import com.thecheezery.ui.theme.Brighter_Pink
import com.thecheezery.ui.theme.DarkGrey
import com.thecheezery.ui.theme.Dusty_white
import com.thecheezery.ui.theme.Less_Purple
import com.thecheezery.ui.theme.Pinky
import com.thecheezery.ui.theme.Very_purple
import com.thecheezery.utilities.asPrice

@Composable
fun ProductRow(product: Product, onAdd: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp)).background(Color.White)
            .border(1.dp, Color(0xFFF1E6EF), RoundedCornerShape(18.dp)).padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.size(62.dp).clip(CircleShape).background(Dusty_white), contentAlignment = Alignment.Center) {
            Text(product.name.firstOrNull()?.uppercase() ?: "•", color = Very_purple, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        }
        Column(Modifier.weight(1f).padding(horizontal = 11.dp)) {
            Text(product.name, color = Less_Purple, fontSize = 18.sp, fontWeight = FontWeight.Medium)
            Text(product.description, color = DarkGrey, fontSize = 11.sp, lineHeight = 15.sp, maxLines = 3)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("$${product.price.asPrice()}", color = Very_purple, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(5.dp))
            CircleAction("＋", onAdd, tint = Very_purple, size = 38)
        }
    }
}

@Composable
fun CartRow(product: Product, onRemove: () -> Unit) {
    Row(Modifier.fillMaxWidth().padding(vertical = 9.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(product.name, color = DarkGrey, fontSize = 17.sp, modifier = Modifier.weight(1f))
        Text("$${product.price.asPrice()}", color = Very_purple, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Spacer(Modifier.width(12.dp))
        CircleAction("×", onRemove, tint = Brighter_Pink, size = 32)
    }
}

@Composable
fun CategoryCard(title: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.height(94.dp).clip(RoundedCornerShape(16.dp))
            .background(Brush.verticalGradient(listOf(Brighter_Pink, Pinky, Very_purple)))
            .clickable(onClick = onClick).padding(10.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(title, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.Center)
    }
}

@Composable
fun BrandWordmark(color: Color, size: Int, modifier: Modifier = Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("the", fontFamily = FontFamily.Cursive, color = color, fontSize = (size * .48).sp, lineHeight = (size * .45).sp, fontWeight = FontWeight.Bold)
        Text("Cheezery", fontFamily = FontFamily.Cursive, color = color, fontSize = size.sp, lineHeight = (size * .92).sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun PinkButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier.shadow(7.dp, CircleShape),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Brighter_Pink, contentColor = Color.White),
    ) {
        Text(label, modifier = Modifier.padding(horizontal = 12.dp, vertical = 3.dp), fontWeight = FontWeight.Medium)
    }
}

@Composable
fun CircleAction(label: String, onClick: () -> Unit, tint: Color, size: Int) {
    Box(
        modifier = Modifier.size(size.dp).clip(CircleShape).background(tint).clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        if (label == "cart") {
            CartGlyph(size = size / 2, color = Color.White)
        } else {
            Text(label, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun CartGlyph(size: Int, color: Color) {
    Canvas(Modifier.size(size.dp)) {
        val stroke = this.size.width * 0.085f
        drawLine(color, Offset(this.size.width * 0.08f, this.size.height * 0.16f), Offset(this.size.width * 0.27f, this.size.height * 0.16f), stroke)
        drawLine(color, Offset(this.size.width * 0.27f, this.size.height * 0.16f), Offset(this.size.width * 0.39f, this.size.height * 0.68f), stroke)
        drawLine(color, Offset(this.size.width * 0.39f, this.size.height * 0.68f), Offset(this.size.width * 0.84f, this.size.height * 0.68f), stroke)
        drawLine(color, Offset(this.size.width * 0.34f, this.size.height * 0.42f), Offset(this.size.width * 0.9f, this.size.height * 0.42f), stroke)
        drawLine(color, Offset(this.size.width * 0.9f, this.size.height * 0.42f), Offset(this.size.width * 0.81f, this.size.height * 0.68f), stroke)
        drawCircle(color, radius = stroke * 1.15f, center = Offset(this.size.width * 0.47f, this.size.height * 0.86f))
        drawCircle(color, radius = stroke * 1.15f, center = Offset(this.size.width * 0.77f, this.size.height * 0.86f))
    }
}
