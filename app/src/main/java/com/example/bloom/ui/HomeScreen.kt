package com.example.bloom.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.bloom.ui.Insights.InsightsScreen
import com.example.bloom.ui.dashboard.DashBoardScreen
import com.example.bloom.ui.learn.LearnScreen
import com.example.bloom.ui.settings.SettingsScreen
import com.example.bloom.ui.theme.BloomRed
import com.example.bloom.ui.theme.BloomTheme

@Composable
fun HomeScreen() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Dashboard", "Insights", "Learn", "Settings")
    val icons = listOf(
        Icons.Default.Dashboard,
        Icons.Default.AutoGraph,
        Icons.Default.School,
        Icons.Default.Settings
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.background,
                tonalElevation = 0.dp
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = BloomRed,
                            indicatorColor = BloomRed,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedItem) {
                0 -> DashBoardScreen()
                1 -> InsightsScreen()
                2 -> LearnScreen()
                3 -> SettingsScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BloomTheme {
        HomeScreen()
    }
}
