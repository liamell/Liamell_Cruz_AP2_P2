package edu.ucne.liamell_cruz_ap2_p2.presentation.navigation
import retrofit2.http.GET
import edu.ucne.liamell_cruz_ap2_p2.data.remote.dto.GastoDto


interface GastosApiService {
    @GET("api/Gastos")
    suspend fun getGastos(): List<GastoDto>
}
