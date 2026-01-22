@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pregnancytracker.ui_ViewModel.Screen

import AddVitalsDialog
import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pregnancytracker.ui_ViewModel.viewmodel.PregnancyVitalsViewModel
import com.example.pregnancytracker.R
import com.example.pregnancytracker.notification.ReminderScheduler
import com.example.pregnancytracker.data.local.VitalEntry

@Composable
fun TrackMyPregnancyScreen(
    modifier: Modifier = Modifier,
    vm: PregnancyVitalsViewModel
) {
    val context = LocalContext.current

    val purple = colorResource(id = R.color.preg_purple)
    val cardBackground = colorResource(id = R.color.preg_card_bg)
    val surfaceGray = colorResource(id = R.color.preg_surface_gray)
    val titleColor = colorResource(id = R.color.preg_title)

    val vitals by vm.vitals.collectAsState()
    val showAddDialog by vm.showAddDialog.collectAsState()

    // 🔔 Permission launcher
    val notificationPermissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                ReminderScheduler.scheduleReminder(context)
            }
        }

    // 🔁 Run once when screen opens
    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notificationPermissionLauncher.launch(
                Manifest.permission.POST_NOTIFICATIONS
            )
        } else {
            ReminderScheduler.scheduleReminder(context)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = surfaceGray,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Track My Pregnancy",
                        fontWeight = FontWeight.SemiBold,
                        color = titleColor,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = cardBackground
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { vm.openAddDialog() },
                containerColor = purple,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    ) { padding ->

        if (vitals.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "No vitals logged yet",
                        style = MaterialTheme.typography.bodyLarge,
                        color = titleColor.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "Tap + to add your first entry",
                        style = MaterialTheme.typography.bodyMedium,
                        color = titleColor.copy(alpha = 0.4f)
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(vitals) { entry ->
                    VitalCard(
                        entry = entry,
                        purple = purple,
                        cardBackground = cardBackground,
                        titleColor = titleColor
                    )
                }
                item { Spacer(Modifier.height(80.dp)) }
            }
        }

        if (showAddDialog) {
            AddVitalsDialog(
                purple = purple,
                titleColor = titleColor,
                onDismiss = { vm.closeAddDialog() },
                onSubmit = { heartRate, sys, dia, weight, kicks ->
                    vm.addVitals(heartRate, sys, dia, weight, kicks)
                }
            )
        }
    }
}


@Composable
private fun VitalCard(
    entry: VitalEntry,
    purple: Color,
    cardBackground: Color,
    titleColor: Color
) {
    val cardShape = RoundedCornerShape(12.dp)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(cardShape)
            .background(cardBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MetricItem(icon = "❤️", value = "${entry.heartRate} bpm", titleColor = titleColor)
                MetricItem(icon = "🩺", value = "${entry.sys}/${entry.dia} mmHg", titleColor = titleColor)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MetricItem(icon = "⚖️", value = "${entry.weightKg} kg", titleColor = titleColor)
                MetricItem(icon = "👶", value = "${entry.kicks} kicks", titleColor = titleColor)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(purple)
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Text(
                text = entry.dateTimeLabel,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}

@Composable
private fun MetricItem(
    icon: String,
    value: String,
    titleColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.width(140.dp)
    ) {
        Text(
            text = icon,
            fontSize = 20.sp,
            modifier = Modifier.size(24.dp)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = titleColor,
            fontSize = 14.sp
        )
    }
}
