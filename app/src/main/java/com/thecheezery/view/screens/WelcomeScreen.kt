package com.thecheezery.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecheezery.ui.theme.Brighter_Pink
import com.thecheezery.ui.theme.Dusty_white
import com.thecheezery.ui.theme.Pinky
import com.thecheezery.ui.theme.Purple_grey
import com.thecheezery.view.components.*

@Composable
fun WelcomeScreen(onStart: () -> Unit) {
    Column(Modifier.fillMaxSize().background(Purple_grey)) {
        Box(
            modifier = Modifier.weight(1f).fillMaxWidth()
                .background(Brush.verticalGradient(listOf(Brighter_Pink, Pinky, Purple_grey))),
            contentAlignment = Alignment.Center,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(Modifier.height(10.dp))
                BrandWordmark(color = Color.White, size = 62)
                Text("Un antojo para alegrar tu día", color = Color.White, fontSize = 14.sp)
            }
        }
        Column(Modifier.fillMaxWidth().padding(horizontal = 26.dp, vertical = 26.dp)) {
            Text("Bienvenido a The Cheezery", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(7.dp))
            Text("Postres, café y algo rico para compartir.", color = Dusty_white, fontSize = 13.sp)
            Spacer(Modifier.height(22.dp))
            PinkButton("Comenzar", onStart, Modifier.align(Alignment.CenterHorizontally))
        }
    }
}
