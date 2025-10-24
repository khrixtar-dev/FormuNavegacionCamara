package com.example.formunavegacion.viewmodel

import androidx.lifecycle.ViewModel
import com.example.formunavegacion.model.UsuarioErrores
import com.example.formunavegacion.model.Usuarios
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UsuarioViewModel: ViewModel() {

    // permite manipulacion en esta clase
    private val _usuario = MutableStateFlow(Usuarios())

    // permite manipulacion desde Screen
    val usuario: StateFlow<Usuarios> = _usuario

    // metodos de cambio
    fun onChangeNombre(nombre: String){
        _usuario.update {
            it.copy(
                nombre=nombre,
                errores = it.errores.copy(nombre = null)
            )
        }
    }
    fun onChangeCorreo(correo: String){
        _usuario.update {
            it.copy(
                correo = correo,
                errores = it.errores.copy(correo = null)
            )
        }
    }
    fun onChangePassword(pass: String){
        _usuario.update {
            it.copy(
                password = pass,
                errores = it.errores.copy(password = null)
            )
        }
    }
    fun onChangeAceptarTerminos(valor: Boolean){
        _usuario.update { it.copy(aceptarTerminos = valor) }
    }
    fun validar(): Boolean{
        val f = _usuario.value
        val errores = UsuarioErrores(
            nombre = if (f.nombre.isBlank()) "nombre esta vacio" else null,
            correo = if (f.correo.isBlank() || !f.correo.contains("@")) "error correo" else null,
            password = if (f.password.isBlank()) "pass vacia" else null,
            aceptarTerminos = if (f.aceptarTerminos==false)"debe aceptar los terminos" else null
        )
        _usuario.update {
            it.copy(errores = errores)
        }
        if (errores.nombre ==null && errores.correo ==null && errores.password==null && errores.aceptarTerminos==null){
            return  true
        } else{
            return  false
        }
        //val hayError = listOfNotNull(errores.nombre,errores.password,errores.correo).isNotEmpty()
        //return !hayError
    }
}