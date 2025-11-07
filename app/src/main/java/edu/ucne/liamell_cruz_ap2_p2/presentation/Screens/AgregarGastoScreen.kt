package edu.ucne.liamell_cruz_ap2_p2.presentation.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.liamell_cruz_ap2_p2.data.remote.dto.GastoDto
import edu.ucne.liamell_cruz_ap2_p2.presentation.GastoViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarGastoScreen(
    viewModel: GastoViewModel = hiltViewModel(),
    onVolver: () -> Unit
) {
    var suplidor by remember { mutableStateOf("") }
    var ncf by remember { mutableStateOf("") }
    var itbis by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Agregar Gasto") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {

                    val fechaActual = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
                    val gasto = GastoDto(
                        gastoId = 0,
                        fecha = fechaActual,
                        suplidor = suplidor,
                        ncf = ncf,
                        itbis = itbis.toDoubleOrNull() ?: 0.0,
                        monto = monto.toDoubleOrNull() ?: 0.0
                    )
                    viewModel.guardarGasto(gasto)
                    onVolver()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Gasto")
            }
        }
    }
}
