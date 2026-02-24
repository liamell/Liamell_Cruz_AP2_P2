package edu.ucne.liamell_cruz_ap2_p2.presentation.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.liamell_cruz_ap2_p2.domain.model.Gasto
import edu.ucne.liamell_cruz_ap2_p2.presentation.viewmodel.GastoViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GastoScreen(
    viewModel: GastoViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState

    var showBottomSheet by remember { mutableStateOf(false) }
    var gastoSeleccionado by remember { mutableStateOf<Gasto?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Gastos") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    gastoSeleccionado = null
                    showBottomSheet = true
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("+")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoading -> CircularProgressIndicator()
                uiState.error != null -> Text("Error: ${uiState.error}")
                uiState.gastos.isEmpty() -> Text("No hay gastos registrados.")
                else -> LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.gastos) { gasto ->
                        GastoItem(
                            gasto = gasto,
                            onEditar = {
                                gastoSeleccionado = it
                                showBottomSheet = true
                            }
                        )
                    }
                }
            }
        }


        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                GastoForm(
                    gastoExistente = gastoSeleccionado,
                    onGuardar = { nuevoGasto ->
                        val gastoConFecha = if (nuevoGasto.fecha.isBlank()) {
                            nuevoGasto.copy(
                                fecha = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
                            )
                        } else nuevoGasto

                        if (gastoSeleccionado == null) {
                            viewModel.guardarGasto(gastoConFecha)
                        } else {
                            viewModel.actualizarGasto(gastoConFecha)
                        }


                        viewModel.cargarGastos()
                        showBottomSheet = false
                    }
                )
            }
        }
    }
}

@Composable
fun GastoItem(
    gasto: Gasto,
    onEditar: (Gasto) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = gasto.suplidor,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "NCF: ${gasto.ncf}")
            Text(text = "Fecha: ${gasto.fecha}")
            Text(text = "ITBIS: ${gasto.itbis}")
            Text(text = "Monto: ${gasto.monto}")

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { onEditar(gasto) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Editar")
            }
        }
    }
}

@Composable
fun GastoForm(
    gastoExistente: Gasto? = null,
    onGuardar: (Gasto) -> Unit
) {
    var suplidor by remember { mutableStateOf(gastoExistente?.suplidor ?: "") }
    var ncf by remember { mutableStateOf(gastoExistente?.ncf ?: "") }
    var fecha by remember { mutableStateOf(gastoExistente?.fecha ?: "") }
    var itbis by remember { mutableStateOf(gastoExistente?.itbis?.toString() ?: "") }
    var monto by remember { mutableStateOf(gastoExistente?.monto?.toString() ?: "") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = if (gastoExistente == null) "Agregar Gasto" else "Editar Gasto",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = suplidor, onValueChange = { suplidor = it }, label = { Text("Suplidor") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = ncf, onValueChange = { ncf = it }, label = { Text("NCF") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = fecha, onValueChange = { fecha = it }, label = { Text("Fecha (opcional)") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = itbis, onValueChange = { itbis = it }, label = { Text("ITBIS") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = monto, onValueChange = { monto = it }, label = { Text("Monto") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val gasto = Gasto(
                    id = gastoExistente?.id ?: 0,
                    suplidor = suplidor,
                    ncf = ncf,
                    fecha = fecha,
                    itbis = itbis.toDoubleOrNull() ?: 0.0,
                    monto = monto.toDoubleOrNull() ?: 0.0
                )
                onGuardar(gasto)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (gastoExistente == null) "Guardar" else "Actualizar")
        }
    }
}
