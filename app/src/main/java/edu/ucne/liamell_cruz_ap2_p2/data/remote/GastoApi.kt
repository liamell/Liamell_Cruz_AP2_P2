package edu.ucne.liamell_cruz_ap2_p2.data.remote

import edu.ucne.liamell_cruz_ap2_p2.data.remote.dto.GastoDto
import edu.ucne.liamell_cruz_ap2_p2.data.remote.dto.GastoRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GastoApi {

    @GET("api/Gastos")
    suspend fun getGastos(): Response<List<GastoDto>>

    @POST("api/Gastos")
    suspend fun postGasto(@Body gasto: GastoRequest): Response<GastoDto>

    @PUT("api/Gastos/{id}")
    suspend fun updateGasto(@Path("id") id: Int, @Body gasto: GastoRequest): Response<Unit>
}