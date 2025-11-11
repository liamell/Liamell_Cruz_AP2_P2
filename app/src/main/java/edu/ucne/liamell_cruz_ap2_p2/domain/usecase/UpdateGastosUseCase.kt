package edu.ucne.liamell_cruz_ap2_p2.domain.usecase

import edu.ucne.liamell_cruz_ap2_p2.domain.model.Gasto
import edu.ucne.liamell_cruz_ap2_p2.domain.repository.GastoRepository
import javax.inject.Inject

class UpdateGastosUseCase @Inject constructor(
    private val repository: GastoRepository
) {
    suspend operator fun invoke(gasto: Gasto) = repository.updateGasto(gasto)
}
