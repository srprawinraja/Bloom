package com.example.bloom.ui.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bloom.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepScreen(
    stepIndex: Int,
    totalSteps: Int = 4,
    onNext: () -> Unit,
    onBack: () -> Unit,
    nextButtonText: String? = null,
    nextButtonEnabled: Boolean = true,
    secondaryButton: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {
                    StepProgressBar(currentStep = stepIndex, totalSteps = totalSteps)
                },
                navigationIcon = {
                    if (stepIndex > 0) {
                        IconButton(onClick = onBack) {
                            Image(
                                painter = painterResource(id = R.drawable.img_7),
                                contentDescription = "Back",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = onNext,
                        enabled = nextButtonEnabled,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                        )
                    ) {
                        Text(
                            text = nextButtonText ?: (if (stepIndex == totalSteps - 1) "Finish" else "Continue"),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                    if (secondaryButton != null) {
                        secondaryButton()
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = content
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StepScreenPreview() {
    MaterialTheme {
        StepScreen(
            stepIndex = 1,
            onNext = {},
            onBack = {}
        ) {
            Text("Sample Content Area")
        }
    }
}
