package edu.ucne.liamell_cruz_ap2_p2.domain.repository

import edu.ucne.liamell_cruz_ap2_p2.domain.model.Gasto

interface GastoRepository {
    suspend fun getGastos(): List<Gasto>
    suspend fun saveGasto(gasto: Gasto)
    suspend fun updateGasto(gasto: Gasto)
}
