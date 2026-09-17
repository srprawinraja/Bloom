package com.example.bloom.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bloom.R
import com.example.bloom.ui.components.StepScreen

@Composable
fun OnBoardingScreen(
    onFinish: () -> Unit,
    onBackToStart: () -> Unit
) {
    var currentStep by remember { mutableIntStateOf(0) }
    var firstName by remember { mutableStateOf("") }

    when (currentStep) {
        0 -> OnBoardingStepOne(
            onNext = { currentStep = 1 },
            onBack = onBackToStart
        )
        1 -> OnBoardingStepTwo(
            firstName = firstName,
            onFirstNameChange = { firstName = it },
            onNext = { currentStep = 2 },
            onBack = { currentStep = 0 }
        )
        2 -> OnBoardingStepThree(
            onNext = { currentStep = 3 },
            onBack = { currentStep = 1 }
        )
        3 -> OnBoardingStepFour(
            onNext = onFinish,
            onBack = { currentStep = 2 }
        )
    }
}

@Composable
fun OnBoardingStepOne(onNext: () -> Unit, onBack: () -> Unit) {
    StepScreen(
        stepIndex = 0,
        onNext = onNext,
        onBack = onBack,
        nextButtonText = "Get Started"
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Welcome to Bloom", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Your journey to wellness starts here.", fontSize = 16.sp)
        }
    }
}

@Composable
fun OnBoardingStepTwo(
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    StepScreen(
        stepIndex = 1,
        onNext = onNext,
        onBack = onBack,
        nextButtonEnabled = firstName.isNotBlank()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Let's get to know you", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = firstName,
                onValueChange = onFirstNameChange,
                label = { Text("First Name") },
                placeholder = { Text("your first name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }
    }
}

@Composable
fun OnBoardingStepThree(onNext: () -> Unit, onBack: () -> Unit) {
    StepScreen(
        stepIndex = 2,
        onNext = onNext,
        onBack = onBack
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Step 3 Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Enhance Your Experience", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Choose the features you want to enable for a better journey.",
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            repeat(7) { index ->
                FeatureComponent(
                    icon = Icons.Default.Notifications,
                    title = "Feature ${index + 1}",
                    description = "This is a brief description for feature ${index + 1}."
                )
                if (index < 6) {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}

@Composable
fun FeatureComponent(icon: ImageVector, title: String, description: String) {
    var isEnabled by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = description, fontSize = 14.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = { isEnabled = !isEnabled },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isEnabled) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
            )
        ) {
            Text(if (isEnabled) "Enabled" else "Enable")
        }
    }
}

@Composable
fun OnBoardingStepFour(onNext: () -> Unit, onBack: () -> Unit) {
    val options = listOf("Morning", "Afternoon", "Evening")
    val times = mapOf(
        "Morning" to "7:30 AM - start the day",
        "Afternoon" to "1:00 PM - midday boost",
        "Evening" to "8:00 PM - wind down"
    )
    var selectedOption by remember { mutableStateOf(options[0]) }

    StepScreen(
        stepIndex = 3,
        onNext = onNext,
        onBack = onBack,
        nextButtonText = "Enter Bloom"
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Final Touches", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Set your preferred time to receive daily bloom updates.",
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Pick time of day",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column(Modifier.selectableGroup()) {
                options.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = { selectedOption = text },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = null // null recommended for accessibility with screen readers
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = times[selectedOption] ?: "",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingStepOnePreview() {
    MaterialTheme {
        OnBoardingStepOne(onNext = {}, onBack = {})
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingStepTwoPreview() {
    MaterialTheme {
        OnBoardingStepTwo(firstName = "", onFirstNameChange = {}, onNext = {}, onBack = {})
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingStepThreePreview() {
    MaterialTheme {
        OnBoardingStepThree(onNext = {}, onBack = {})
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingStepFourPreview() {
    MaterialTheme {
        OnBoardingStepFour(onNext = {}, onBack = {})
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    MaterialTheme {
        OnBoardingScreen(onFinish = {}, onBackToStart = {})
    }
}
