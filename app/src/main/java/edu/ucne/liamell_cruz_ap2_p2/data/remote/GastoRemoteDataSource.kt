package edu.ucne.liamell_cruz_ap2_p2.data.remote

import edu.ucne.liamell_cruz_ap2_p2.data.remote.GastoApi
import edu.ucne.liamell_cruz_ap2_p2.data.remote.dto.GastoRequest
import javax.inject.Inject

class GastoRemoteDataSource @Inject constructor(
    private val gastoApi: GastoApi
) {
    suspend fun getGastos() = gastoApi.getGastos()
    suspend fun addGasto(gasto: GastoRequest) = gastoApi.postGasto(gasto)
    suspend fun updateGasto(id: Int, gasto: GastoRequest) = gastoApi.updateGasto(id, gasto)
}
