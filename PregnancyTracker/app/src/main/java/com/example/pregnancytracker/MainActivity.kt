package com.example.pregnancytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pregnancytracker.ui_ViewModel.Screen.TrackMyPregnancyScreen
import com.example.pregnancytracker.data.repository.VitalsRepository
import com.example.pregnancytracker.ui.theme.PregnancyTrackerTheme
import com.example.pregnancytracker.ui_ViewModel.viewmodel.PregnancyVitalsViewModel
import com.example.pregnancytracker.ui_ViewModel.viewmodel.PregnancyVitalsViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PregnancyTrackerTheme {
                val app = application as PregnancyApp

                val repository = VitalsRepository(app.database.vitalEntryDao())

                val vitalsViewModel: PregnancyVitalsViewModel = viewModel(
                    factory = PregnancyVitalsViewModelFactory(repository)
                )

                TrackMyPregnancyScreen(
                    vm = vitalsViewModel
                )
            }
        }
    }
}