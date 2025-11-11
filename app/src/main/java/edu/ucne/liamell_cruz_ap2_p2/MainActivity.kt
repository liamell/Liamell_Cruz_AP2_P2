package edu.ucne.liamell_cruz_ap2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.liamell_cruz_ap2_p2.presentation.Screens.GastoScreen
import edu.ucne.liamell_cruz_ap2_p2.ui.theme.Liamell_Cruz_AP2_P2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Liamell_Cruz_AP2_P2Theme {
                GastoScreen()
            }
        }
    }
}
