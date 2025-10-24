package com.example.formunavegacion.ui.screen

import android.R
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.formunavegacion.viewmodel.UsuarioViewModel

@Composable
fun LoginScreen(
    viewModel: UsuarioViewModel,
    navController: NavController
) {
    val usuario by viewModel.usuario.collectAsState();


    Column (
        modifier = Modifier.fillMaxSize().padding(25.dp),
        verticalArrangement = Arrangement.Center
    ){
        OutlinedTextField(
            value = usuario.nombre,
            onValueChange = viewModel::onChangeNombre,
            label = { Text("nombre") },
            isError = usuario.errores.nombre!=null,
            supportingText = {
                usuario.errores.nombre?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            }
        )
        Spacer(modifier = Modifier.height(18.dp))
        OutlinedTextField(
            value = usuario.correo,
            onValueChange = viewModel::onChangeCorreo,
            label = { Text("correo") },
            isError = usuario.errores.correo!=null,
            supportingText = {
                usuario.errores.correo?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            }
        )
        Spacer(modifier = Modifier.height(18.dp))
        OutlinedTextField(
            value = usuario.password,
            onValueChange = viewModel::onChangePassword,
            label = { Text("password") },
            isError = usuario.errores.password!=null,
            visualTransformation = PasswordVisualTransformation(),
            supportingText = {
                usuario.errores.password?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = usuario.aceptarTerminos,
                onCheckedChange = viewModel::onChangeAceptarTerminos)
            Text("Acepta los terminos del acceso al sitio para continuar")
        }

        var contexto = LocalContext.current
        Button(
            onClick = {
                if ( viewModel.validar()){
                    Toast.makeText(contexto, "Ingresaste", Toast.LENGTH_SHORT).show()
                    navController.navigate(route = "bienvenida")
                } else {
                    Toast.makeText(contexto, "No ingresaste", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Aceptar")
        }
    }
}