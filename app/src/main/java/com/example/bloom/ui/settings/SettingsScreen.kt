package com.example.bloom.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bloom.data.UserPreferences
import com.example.bloom.ui.theme.BloomTheme
import com.example.bloom.ui.theme.Fraunces
import com.example.bloom.ui.theme.PlusJakartaSans

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val userName = userPreferences.userName.ifBlank { "Prawin" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Title: Settings
        Text(
            text = "settings",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontFamily = Fraunces,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        // Subtitle
        Text(
            text = "your profile and daily goal targets",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = PlusJakartaSans,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Profile Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = userName, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                    Text(text = "pcos journey Not set", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
        
        TextButton(
            onClick = { /* TODO */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "edit profile", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // What you're tracking Section
        Text(
            text = "what you're tracking",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(12.dp))
        
        TrackingToggleItem(Icons.Default.Restaurant, "meals")
        TrackingToggleItem(Icons.AutoMirrored.Filled.DirectionsRun, "movement")
        TrackingToggleItem(Icons.Default.WaterDrop, "water")

        Spacer(modifier = Modifier.height(24.dp))

        // Daily goal targets Section
        Text(
            text = "Daily goal targets",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(12.dp))
        
        GoalTargetItem(Icons.Default.Restaurant, "meals", 3)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Notifications
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Notifications, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "notifications", style = MaterialTheme.typography.bodyLarge)
            }
            var notificationsEnabled by remember { mutableStateOf(true) }
            Switch(checked = notificationsEnabled, onCheckedChange = { notificationsEnabled = it })
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Remainder time
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.AccessTime, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "remainder time", style = MaterialTheme.typography.bodyLarge)
            }
            Text(text = "8:00 am", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))
        
        // Live notification preview placeholder
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "live notification preview", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Time for your morning check-in!", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "app version 1.0.0",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer, contentColor = MaterialTheme.colorScheme.error),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "signout", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(80.dp)) // Space for bottom nav
    }
}

@Composable
fun TrackingToggleItem(icon: ImageVector, title: String) {
    var isEnabled by remember { mutableStateOf(true) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
        }
        Switch(checked = isEnabled, onCheckedChange = { isEnabled = it })
    }
}

@Composable
fun GoalTargetItem(icon: ImageVector, title: String, initialValue: Int) {
    var value by remember { mutableIntStateOf(initialValue) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { if (value > 0) value-- }) {
                Icon(Icons.Default.Remove, contentDescription = "Decrease")
            }
            Text(text = value.toString(), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            IconButton(onClick = { value++ }) {
                Icon(Icons.Default.Add, contentDescription = "Increase")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    BloomTheme {
        SettingsScreen()
    }
}
