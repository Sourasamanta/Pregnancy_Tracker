import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddVitalsDialog(
    purple: Color,
    titleColor: Color,
    onDismiss: () -> Unit,
    onSubmit: (heartRate: Int, sys: Int, dia: Int, weight: Int, kicks: Int) -> Unit
) {
    var heartRate by remember { mutableStateOf("") }
    var sysBp by remember { mutableStateOf("") }
    var diaBp by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var kicks by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    val shape = RoundedCornerShape(16.dp)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Add Vitals",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = titleColor
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                // Heart Rate
                OutlinedTextField(
                    value = heartRate,
                    onValueChange = { heartRate = it.onlyDigits(maxLen = 3) },
                    placeholder = { Text("Heart Rate (BPM)", fontSize = 14.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = purple,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(8.dp)
                )

                // Blood Pressure
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = sysBp,
                        onValueChange = { sysBp = it.onlyDigits(maxLen = 3) },
                        placeholder = { Text("Sys BP", fontSize = 14.sp) },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = purple,
                            unfocusedBorderColor = Color.LightGray
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    OutlinedTextField(
                        value = diaBp,
                        onValueChange = { diaBp = it.onlyDigits(maxLen = 3) },
                        placeholder = { Text("Dia BP", fontSize = 14.sp) },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = purple,
                            unfocusedBorderColor = Color.LightGray
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                }

                // Weight
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it.onlyDigits(maxLen = 3) },
                    placeholder = { Text("Weight (in kg)", fontSize = 14.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = purple,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(8.dp)
                )

                // Baby Kicks
                OutlinedTextField(
                    value = kicks,
                    onValueChange = { kicks = it.onlyDigits(maxLen = 3) },
                    placeholder = { Text("Baby Kicks", fontSize = 14.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = purple,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(8.dp)
                )

                if (error != null) {
                    Text(
                        text = error!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val hrV = heartRate.toIntOrNull()
                    val sysV = sysBp.toIntOrNull()
                    val diaV = diaBp.toIntOrNull()
                    val wV = weight.toIntOrNull()
                    val kV = kicks.toIntOrNull()

                    when {
                        hrV == null || hrV !in 40..200 -> error = "Enter valid heart rate (40-200 BPM)"
                        sysV == null || sysV !in 70..220 -> error = "Enter valid systolic BP (70-220)"
                        diaV == null || diaV !in 40..140 -> error = "Enter valid diastolic BP (40-140)"
                        diaV >= sysV -> error = "Diastolic must be less than systolic"
                        wV == null || wV !in 20..200 -> error = "Enter valid weight (20-200 kg)"
                        kV == null || kV !in 0..200 -> error = "Enter valid kicks count (0-200)"
                        else -> {
                            error = null
                            onSubmit(hrV, sysV, diaV, wV, kV)
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = purple),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Submit", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = purple)
            }
        },
        shape = shape,
        containerColor = Color.White
    )
}

private fun String.onlyDigits(maxLen: Int): String =
    filter { it.isDigit() }.take(maxLen)