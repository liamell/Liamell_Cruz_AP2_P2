package edu.ucne.liamell_cruz_ap2_p2.repository
import edu.ucne.liamell_cruz_ap2_p2.data.remote.GastoApi
import edu.ucne.liamell_cruz_ap2_p2.data.remote.dto.GastoDto
//import edu.ucne.liamell_cruz_ap2_p2.presentation.navigation.RetrofitInstance.api
import javax.inject.Inject


class GastoRepository @Inject constructor(
    private val gastoApi: GastoApi
) {
    suspend fun getGastos(): List<GastoDto> = gastoApi.getGastos()

    suspend fun getGasto(id: Int) = gastoApi.getGasto(id)

    suspend fun addGasto(gasto: GastoDto) = gastoApi.postGasto(gasto)




}