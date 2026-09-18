package com.example.bloom.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bloom.R
import com.example.bloom.data.UserPreferences
import com.example.bloom.ui.onboarding.components.StepScreen
import com.example.bloom.ui.theme.BloomTheme
import com.example.bloom.ui.theme.PlusJakartaSans

@Composable
fun OnBoardingScreen(
    onFinish: () -> Unit,
    onBackToStart: () -> Unit
) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
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
            onNext = {
                userPreferences.isOnboardingCompleted = true
                userPreferences.userName = firstName
                onFinish()
            },
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
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(129.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Welcome to Bloom",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "A simple daily companion for managing\n" +
                        "PCOS — track your cycle, meals,\n" +
                        "movement, water, relaxation and sleep.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
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
            verticalArrangement = Arrangement.Top // Below status bar
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "A little about you",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            androidx.compose.foundation.text.BasicTextField(
                value = firstName,
                onValueChange = onFirstNameChange,
                modifier = Modifier
                    .width(338.dp)
                    .height(49.dp)
                    .background(Color.Transparent)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFF3DEDC),
                        shape = RoundedCornerShape(14.dp)
                    ),
                textStyle = MaterialTheme.typography.headlineMedium.copy(
                    textAlign = TextAlign.Start,
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                singleLine = true,
                cursorBrush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.primary),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (firstName.isEmpty()) {
                            Text(
                                text = "Your first name",
                                style = TextStyle(
                                    fontFamily = PlusJakartaSans,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 15.sp,
                                    lineHeight = 15.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                    textAlign = TextAlign.Start
                                )
                            )
                        }
                        innerTextField()
                    }
                }
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
                modifier = Modifier.size(44.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "What would you like to track\n" +
                        "daily?",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pick the goals that matter to you — only these will\n" +
                        "show up on your dashboard. Partial progress still\n" +
                        "counts, and your flower fills in as you go.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(24.dp))

            val features = listOf(
                FeatureData(
                    iconRes = R.drawable.img_1,
                    title = "Meals · 3 balanced meals",
                    description = "Steadier blood sugar helps keep\nPCOS symptoms in check."
                ),
                FeatureData(
                    iconRes = R.drawable.img_2,
                    title = "Movement · 30 minutes",
                    description = "Supports insulin sensitivity, a key\nlever in PCOS."
                ),
                FeatureData(
                    iconRes = R.drawable.img_3,
                    title = "Water · 8 glasses",
                    description = "Helps with energy and bloating."
                ),
                FeatureData(
                    iconRes = R.drawable.img_4,
                    title = "Relaxation · 15 minutes",
                    description = "Lower stress means lower cortisol,\nwhich can worsen symptoms."
                ),
                FeatureData(
                    iconRes = R.drawable.img_5,
                    title = "Sleep · 8 hours",
                    description = "Consistent sleep helps balance\nhormones over time."
                ),
                FeatureData(
                    iconRes = R.drawable.img_6,
                    title = "Cycle · period dates",
                    description = "Spot your personal pattern and\npredict your next period."
                ),
                FeatureData(
                    iconRes = R.drawable.img_7,
                    title = "Symptom check-in · bloating,\nskin, mood, sleep quality",
                    description = "Track how you feel day to day to\nnotice patterns over time."
                )
            )

            features.forEachIndexed { index, feature ->
                FeatureComponent(
                    iconRes = feature.iconRes,
                    title = feature.title,
                    description = feature.description
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

data class FeatureData(val iconRes: Int, val title: String, val description: String)

@Composable
fun FeatureComponent(iconRes: Int, title: String, description: String) {
    var isEnabled by remember { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = isEnabled,
                onCheckedChange = { isEnabled = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                    checkedTrackColor = MaterialTheme.colorScheme.primary,
                    uncheckedThumbColor = MaterialTheme.colorScheme.outline,
                    uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                )
            )
        }
    }
}

@Composable
fun OnBoardingStepFour(onNext: () -> Unit, onBack: () -> Unit) {
    val options = listOf(
        OptionData("Morning", "7:30 AM — start the day on track"),
        OptionData("Afternoon", "1:00 PM — a midday check-in"),
        OptionData("Evening", "8:00 PM — wind down and reflect")
    )
    var selectedOption by remember { mutableStateOf(options[0]) }

    StepScreen(
        stepIndex = 3,
        onNext = onNext,
        onBack = onBack,
        nextButtonText = "Enter Bloom",
        secondaryButton = {
            TextButton(
                onClick = onNext, // Or handle differently if it skips
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "I'll set this up later",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_9),
                contentDescription = "Step 4 Image",
                modifier = Modifier.size(44.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "When should we remind you?",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "We'll send one daily nudge to log how you're doing.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Pick time of day",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.align(Alignment.Start),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .selectableGroup()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                options.forEach { option ->
                    val isSelected = (option == selectedOption)
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (isSelected) MaterialTheme.colorScheme.surface else Color.Transparent)
                            .border(
                                width = 1.dp,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else Color(0xFFF3DEDC),
                                shape = RoundedCornerShape(14.dp)
                            )
                            .selectable(
                                selected = isSelected,
                                onClick = { selectedOption = option },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colorScheme.primary
                            )
                        )
                        Column(modifier = Modifier.padding(start = 16.dp)) {
                            Text(
                                text = option.title,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = option.subtitle,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Or set a custom time",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            // Bar like component
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "8:00 am",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = "Set custom time",
                    tint = Color.Black
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

data class OptionData(val title: String, val subtitle: String)

@Preview(showBackground = true)
@Composable
fun OnBoardingStepOnePreview() {
    BloomTheme {
        OnBoardingStepOne(onNext = {}, onBack = {})
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingStepTwoPreview() {
    BloomTheme {
        OnBoardingStepTwo(firstName = "", onFirstNameChange = {}, onNext = {}, onBack = {})
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingStepThreePreview() {
    BloomTheme {
        OnBoardingStepThree(onNext = {}, onBack = {})
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingStepFourPreview() {
    BloomTheme {
        OnBoardingStepFour(onNext = {}, onBack = {})
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    BloomTheme {
        OnBoardingScreen(onFinish = {}, onBackToStart = {})
    }
}
