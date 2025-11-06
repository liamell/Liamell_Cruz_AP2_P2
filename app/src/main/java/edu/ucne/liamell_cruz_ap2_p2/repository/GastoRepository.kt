package edu.ucne.liamell_cruz_ap2_p2.repository
import edu.ucne.liamell_cruz_ap2_p2.data.remote.dto.GastoApi
import javax.inject.Inject

class GastoRepository @Inject constructor(
    private val gastoApi: GastoApi
) {
    suspend fun getGastos() = gastoApi.getGastos()

    suspend fun getGasto(id: Int) = gastoApi.getGasto(id)
}
