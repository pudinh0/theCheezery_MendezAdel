package com.thecheezery.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecheezery.ui.theme.Less_Purple
import com.thecheezery.ui.theme.Purple_grey
import com.thecheezery.ui.theme.Very_purple
import com.thecheezery.view.components.*

@Composable
fun LoginScreen(onBack: () -> Unit, onSignIn: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }
    val validEmail = remember(email) { android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() }
    val emailError = submitted && email.isBlank() || (email.isNotBlank() && !validEmail)
    val passwordError = submitted && password.isBlank()

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextButton(onClick = onBack, modifier = Modifier.align(Alignment.Start).padding(top = 12.dp)) {
            Text("‹  Regresar", color = Very_purple, fontSize = 16.sp)
        }
        Spacer(Modifier.weight(1f))
        BrandWordmark(color = Less_Purple, size = 64)
        Text("Tu lugar favorito para darte un gusto", color = Purple_grey, fontSize = 14.sp)
        Spacer(Modifier.height(42.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Correo electrónico") },
            singleLine = true,
            isError = emailError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = RoundedCornerShape(12.dp),
        )
        if (emailError) Text(if (email.isBlank()) "Ingresa tu correo electrónico" else "Revisa el formato del correo", color = MaterialTheme.colorScheme.error, fontSize = 12.sp, modifier = Modifier.align(Alignment.Start).padding(start = 12.dp, top = 3.dp))
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Contraseña") },
            singleLine = true,
            isError = passwordError,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(12.dp),
        )
        if (passwordError) Text("Ingresa tu contraseña", color = MaterialTheme.colorScheme.error, fontSize = 12.sp, modifier = Modifier.align(Alignment.Start).padding(start = 12.dp, top = 3.dp))
        Spacer(Modifier.height(24.dp))
        PinkButton("Entrar", {
            submitted = true
            if (email.isNotBlank() && validEmail && password.isNotBlank()) onSignIn()
        })
        Spacer(Modifier.weight(1.3f))
    }
}
