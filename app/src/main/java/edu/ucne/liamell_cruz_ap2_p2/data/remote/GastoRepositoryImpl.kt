package edu.ucne.liamell_cruz_ap2_p2.data.repository

import edu.ucne.liamell_cruz_ap2_p2.data.remote.GastoRemoteDataSource
import edu.ucne.liamell_cruz_ap2_p2.domain.model.Gasto
import edu.ucne.liamell_cruz_ap2_p2.domain.repository.GastoRepository
import javax.inject.Inject

class GastoRepositoryImpl @Inject constructor(
    private val remote: GastoRemoteDataSource
) : GastoRepository {

    override suspend fun getGastos(): List<Gasto> {
        val response = remote.getGastos()
        return response.body()?.map {
            Gasto(
                id = it.gastoId,
                fecha = it.fecha,
                suplidor = it.suplidor,
                ncf = it.ncf,
                itbis = it.itbis,
                monto = it.monto
            )
        } ?: emptyList()
    }

    override suspend fun saveGasto(gasto: Gasto) {
        remote.addGasto(
            edu.ucne.liamell_cruz_ap2_p2.data.remote.dto.GastoRequest(
                fecha = gasto.fecha,
                suplidor = gasto.suplidor,
                ncf = gasto.ncf,
                itbis = gasto.itbis,
                monto = gasto.monto
            )
        )
    }

    override suspend fun updateGasto(gasto: Gasto) {
        remote.updateGasto(
            gasto.id,
            edu.ucne.liamell_cruz_ap2_p2.data.remote.dto.GastoRequest(
                fecha = gasto.fecha,
                suplidor = gasto.suplidor,
                ncf = gasto.ncf,
                itbis = gasto.itbis,
                monto = gasto.monto
            )
        )
    }
}
