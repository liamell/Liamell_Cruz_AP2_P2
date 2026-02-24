package edu.ucne.liamell_cruz_ap2_p2.domain.model

data class Gasto(
    val id: Int,
    val fecha: String,
    val suplidor: String,
    val ncf: String,
    val itbis: Double,
    val monto: Double
)