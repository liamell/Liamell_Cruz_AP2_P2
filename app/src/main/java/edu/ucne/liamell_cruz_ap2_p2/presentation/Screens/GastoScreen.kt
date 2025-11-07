package edu.ucne.liamell_cruz_ap2_p2.ui.gastos

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
import edu.ucne.liamell_cruz_ap2_p2.data.remote.dto.GastoDto
import edu.ucne.liamell_cruz_ap2_p2.presentation.GastoViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GastoScreen(
    viewModel: GastoViewModel = hiltViewModel()
) {
    val gastos by viewModel.gastos.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        topBar = { TopAppBar(title = { Text("Lista de Gastos") }) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showBottomSheet = true },
                modifier = Modifier.padding(16.dp)
            ) { Text("+") }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (gastos.isEmpty()) {
                Text("No hay gastos registrados.")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(gastos) { gasto -> GastoItem(gasto) }
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
                onGuardar = { nuevoGasto ->

                    val gastoConFecha = if (nuevoGasto.fecha.isBlank()) {
                        nuevoGasto.copy(
                            fecha = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
                        )
                    } else {
                        nuevoGasto
                    }


                    viewModel.guardarGasto(gastoConFecha)


                    showBottomSheet = false
                }
            )
        }
    }
}

@Composable
fun GastoItem(gasto: GastoDto) {
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
        }
    }
}

@Composable
fun GastoForm(
    onGuardar: (GastoDto) -> Unit
) {
    var suplidor by remember { mutableStateOf("") }
    var ncf by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var itbis by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Agregar Gasto", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = suplidor,
            onValueChange = { suplidor = it },
            label = { Text("Suplidor") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = ncf,
            onValueChange = { ncf = it },
            label = { Text("NCF") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = itbis,
            onValueChange = { itbis = it },
            label = { Text("ITBIS") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = monto,
            onValueChange = { monto = it },
            label = { Text("Monto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val nuevoGasto = GastoDto(
                    gastoId = 0,
                    suplidor = suplidor,
                    ncf = ncf,
                    fecha = fecha,
                    itbis = itbis.toDoubleOrNull() ?: 0.0,
                    monto = monto.toDoubleOrNull() ?: 0.0
                )
                onGuardar(nuevoGasto)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
    }
}
