package com.wira_fkom.jetpack_composebasic

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.wira_fkom.jetpack_composebasic.ui.theme.Jetpack_ComposeBasicTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_ComposeBasicTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreen()
                }
            }
        }

        // Navigate to MainActivity after a delay
        // Navigasi ke MainActivity setelah penundaan
        lifecycleScope.launch {
            delay(3000) // 3 seconds delay
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}

@Composable
fun SplashScreen() {
    // Centered Box for splash screen content
    // Box terpusat untuk konten splash screen
    val scale = remember { Animatable(1f) }

    LaunchedEffect(true) {
        scale.animateTo(
            targetValue = 1.2f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), // Set background color
        contentAlignment = Alignment.Center // Center content
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display the image with animation
            // Menampilkan gambar dengan animasi
            Image(
                painter = painterResource(id = R.drawable.logo_compose),
                contentDescription = null, // Gambar untuk splash screen, tidak memerlukan konten deskripsi
                modifier = Modifier
                    .size(200.dp)
                    .scale(scale.value) // Animasi scale
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Welcome to JetReward App",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    Jetpack_ComposeBasicTheme {
        SplashScreen()
    }
}
