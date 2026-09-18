package com.example.bloom.ui.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bloom.ui.theme.BloomTheme
import com.example.bloom.ui.theme.Fraunces
import com.example.bloom.ui.theme.PlusJakartaSans

@Composable
fun LearnScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Title: Learn
        Text(
            text = "Learn",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontFamily = Fraunces,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        // Subtitle
        Text(
            text = "Understand PCOS, at your own page",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = PlusJakartaSans,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Diagnosis & Basics Section
        Text(
            text = "Diagnosis & Basics",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontFamily = PlusJakartaSans
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        
        val questions = listOf(
            "What exactly is PCOS?",
            "How is PCOS diagnosed?",
            "Common symptoms of PCOS",
            "Causes and risk factors",
            "Types of PCOS",
            "Living with PCOS daily"
        )

        questions.forEach { question ->
            QuestionComponent(question)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(80.dp)) // Space for bottom nav
    }
}

@Composable
fun QuestionComponent(question: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = question,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LearnScreenPreview() {
    BloomTheme {
        LearnScreen()
    }
}
