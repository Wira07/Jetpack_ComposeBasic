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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wira_fkom.jetpack_composebasic.ui.navigation.NavigationItem
import com.wira_fkom.jetpack_composebasic.ui.navigation.Screen
import com.wira_fkom.jetpack_composebasic.ui.theme.Jetpack_ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_ComposeBasicTheme {
//                MyApp(modifier = Modifier.fillMaxSize())
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
) {
    Scaffold(
        bottomBar = {
            BottomBar()
        },
        modifier = modifier
    ) { innerPadding ->
        Content(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
private fun BottomBar(
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
    ) {
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
        navigationItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = false,
                onClick = {
                    // Handle navigation item click
                }
            )
        }
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    Text(
        text = "Hello, Wira Sukma Saputra",
        modifier = modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun JetRewardAppPreview() {
    Jetpack_ComposeBasicTheme {
        JetRewardApp()
    }
}
