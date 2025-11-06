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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GastoScreen(
    viewModel: GastoViewModel = hiltViewModel()
) {
    val gastos by viewModel.gastos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarGastos()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Gastos") }
            )
        }

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (gastos.isEmpty()) {
                Text("Cargando gastos...")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(gastos) { gasto ->
                        GastoItem(gasto)
                    }
                }
            }
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
                text = gasto.descripcion,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Monto: ${gasto.monto}")
        }
    }
}
