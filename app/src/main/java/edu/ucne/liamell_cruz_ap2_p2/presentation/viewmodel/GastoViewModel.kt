package edu.ucne.liamell_cruz_ap2_p2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.liamell_cruz_ap2_p2.domain.model.Gasto
import edu.ucne.liamell_cruz_ap2_p2.domain.usecase.GetGastosUseCase
import edu.ucne.liamell_cruz_ap2_p2.domain.usecase.SaveGastosUseCase
import edu.ucne.liamell_cruz_ap2_p2.domain.usecase.UpdateGastosUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@HiltViewModel
class GastoViewModel @Inject constructor(
    private val getGastosUseCase: GetGastosUseCase,
    private val saveGastoUseCase: SaveGastosUseCase,
    private val updateGastoUseCase: UpdateGastosUseCase
) : ViewModel() {

    var uiState by androidx.compose.runtime.mutableStateOf(GastoUiState())
        private set

    init {
        cargarGastos()
    }

    fun cargarGastos() = viewModelScope.launch {
        uiState = uiState.copy(isLoading = true)
        uiState = try {
            val lista = getGastosUseCase()
            uiState.copy(isLoading = false, gastos = lista)
        } catch (e: Exception) {
            uiState.copy(isLoading = false, error = e.message)
        }
    }

    fun guardarGasto(gasto: Gasto) = viewModelScope.launch {
        saveGastoUseCase(gasto)
        cargarGastos()
    }

    fun actualizarGasto(gasto: Gasto) = viewModelScope.launch {
        try {
            updateGastoUseCase(gasto)
            cargarGastos()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}

data class GastoUiState(
    val gastos: List<Gasto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
