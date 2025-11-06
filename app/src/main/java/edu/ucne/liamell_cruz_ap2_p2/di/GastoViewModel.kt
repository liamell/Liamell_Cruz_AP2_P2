package edu.ucne.liamell_cruz_ap2_p2.ui.gastos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.liamell_cruz_ap2_p2.data.remote.dto.GastoDto
import edu.ucne.liamell_cruz_ap2_p2.repository.GastoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GastoViewModel @Inject constructor(
    private val repository: GastoRepository
) : ViewModel() {

    private val _gastos = MutableStateFlow<List<GastoDto>>(emptyList())
    val gastos = _gastos.asStateFlow()

    fun cargarGastos() {
        viewModelScope.launch {
            try {
                _gastos.value = repository.getGastos()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
