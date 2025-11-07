package edu.ucne.liamell_cruz_ap2_p2.data.remote

import edu.ucne.liamell_cruz_ap2_p2.data.remote.dto.GastoDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GastoApi {
    @GET("api/Gastos")
    suspend fun getGastos(): List<GastoDto>

    @GET("api/Gastos/{id}")
    suspend fun getGasto(@Path("id") id: Int): GastoDto

    @POST("api/Gastos")
    suspend fun postGasto(@Body gasto: GastoDto): GastoDto





}