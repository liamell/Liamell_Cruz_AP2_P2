package edu.ucne.liamell_cruz_ap2_p2.domain.usecase


import edu.ucne.liamell_cruz_ap2_p2.domain.repository.GastoRepository
import javax.inject.Inject

class GetGastosUseCase @Inject constructor(
    private val repository: GastoRepository
) {
    suspend operator fun invoke() = repository.getGastos()
}
