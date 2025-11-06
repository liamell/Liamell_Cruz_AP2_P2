package edu.ucne.liamell_cruz_ap2_p2.presentation.navigation
import retrofit2.http.GET

interface GastosApiService {
    @GET("api/Gastos")
    suspend fun getGastos(): List<Gasto>
}
