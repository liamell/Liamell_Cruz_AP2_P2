package edu.ucne.liamell_cruz_ap2_p2.data.remote.dto
import retrofit2.http.GET
import retrofit2.http.Path

data class GastoDto(
    val gastoId: Int,
    val descripcion: String,
    val monto: Double
)

interface GastoApi {

    @GET("Gastos")
    suspend fun getGastos(): List<GastoDto>

    @GET("Gastos/{id}")
    suspend fun getGasto(@Path("id") id: Int): GastoDto
}
