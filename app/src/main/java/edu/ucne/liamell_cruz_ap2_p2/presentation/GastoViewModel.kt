package edu.ucne.liamell_cruz_ap2_p2.presentation

import android.util.Log
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

    init {
        cargarGastos()
    }

    fun cargarGastos() {
        viewModelScope.launch {
            try {
                _gastos.value = repository.getGastos()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun guardarGasto(gasto: GastoDto) {
        viewModelScope.launch {
            try {
                repository.addGasto(gasto)
                cargarGastos()
            } catch (e: Exception) {
                Log.e("GastoViewModel", "Error al guardar gasto", e)
            }
        }
    }




}