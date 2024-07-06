package com.wira_fkom.jetpack_composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wira_fkom.jetpack_composebasic.ui.navigation.NavigationItem
import com.wira_fkom.jetpack_composebasic.ui.navigation.Screen
import com.wira_fkom.jetpack_composebasic.ui.theme.Jetpack_ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_ComposeBasicTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JetRewardApp()
                }
            }
        }
    }
}

@Composable
fun JetRewardApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    // Scaffold provides the basic visual structure, including a BottomBar
    // Scaffold menyediakan struktur visual dasar, termasuk BottomBar
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        // NavHost handles navigation between different screens
        // NavHost menangani navigasi antara layar yang berbeda
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Cart.route) {
                CartScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
        // CenterContent displays content in the center of the screen
        // CenterContent menampilkan konten di tengah layar
        CenterContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // NavigationBar is used to display the BottomBar with navigation items
    // NavigationBar digunakan untuk menampilkan BottomBar dengan item navigasi
    NavigationBar(
        modifier = modifier,
    ) {
        // List of navigation items
        // Daftar item navigasi
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_cart),
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Cart
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        // Adding each navigation item to the BottomBar
        // Menambahkan setiap item navigasi ke BottomBar
        navigationItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    // Handle navigation item click
                    // Menangani klik item navigasi
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun CenterContent(modifier: Modifier = Modifier) {
    // Box is used to center the content within the available space
    // Box digunakan untuk memusatkan konten dalam ruang yang tersedia
    Box(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter) // Pusatkan di bagian atas
            .padding(top = 132.dp) // Margin top 32dp
    ) {
        Text(
            text = "Hello, Wira Sukma Saputra",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun HomeScreen() {
    // HomeScreen displays a simple text in the center
    // HomeScreen menampilkan teks sederhana di tengah
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(text = "Home Screen")
    }
}

@Composable
fun CartScreen() {
    // CartScreen displays a simple text in the center
    // CartScreen menampilkan teks sederhana di tengah
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(text = "Cart Screen")
    }
}

@Composable
fun ProfileScreen() {
    // ProfileScreen displays a simple text in the center
    // ProfileScreen menampilkan teks sederhana di tengah
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(text = "Profile Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun JetRewardAppPreview() {
    Jetpack_ComposeBasicTheme {
        JetRewardApp()
    }
}
