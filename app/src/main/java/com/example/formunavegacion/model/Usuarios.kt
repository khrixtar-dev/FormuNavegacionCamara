package com.example.formunavegacion.model

// clase con los atributos del usuario
data class Usuarios(
    val nombre: String="",
    val correo: String="",
    val password: String="",
    val aceptarTerminos: Boolean = false,
    val errores: UsuarioErrores = UsuarioErrores()
)

// almacena los errores de cada atributo
data class UsuarioErrores(
    val nombre: String? = null,
    val correo: String? = null,
    val password: String? = null,
    val aceptarTerminos: String? = null
)