package com.aistudio.connectio.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ConnectioMainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectioMainScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = when(selectedTab) {
                            0 -> "Connectio Feed"
                            1 -> "Messages"
                            else -> "Profile"
                        },
                        fontWeight = FontWeight.Bold
                    ) 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Feed") },
                    label = { Text("Feed") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.Email, contentDescription = "Chats") },
                    label = { Text("Chats") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                0 -> FeedScreen()
                1 -> ChatListScreen()
                2 -> ProfileScreen()
            }
        }
    }
}

@Composable
fun FeedScreen() {
    val posts = remember {
        listOf(
            "Dipu" to "Welcome everyone to the new Connectio App! 🚀 Let's connect.",
            "Rahim" to "Excited to try out this new social platform.",
            "Tanvir" to "Jetpack Compose makes UI development so smooth!"
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(posts) { (user, content) ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(user.first().toString(), color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(user, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(content, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        IconButton(onClick = {}) { Icon(Icons.Default.FavoriteBorder, contentDescription = "Like") }
                        IconButton(onClick = {}) { Icon(Icons.Default.Share, contentDescription = "Share") }
                    }
                }
            }
        }
    }
}

@Composable
fun ChatListScreen() {
    val chats = remember {
        listOf(
            "Dipu" to "Hey, are you free now?",
            "Karim" to "Let's catch up tomorrow!",
            "Support Team" to "Welcome to Connectio support."
        )
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(chats) { (name, lastMsg) ->
            ListItem(
                headlineContent = { Text(name, fontWeight = FontWeight.SemiBold) },
                supportingContent = { Text(lastMsg, maxLines = 1) },
                leadingContent = {
                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(name.first().toString(), color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            )
            HorizontalDivider()
        }
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text("Dipu", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("connectio_user@example.com", color = Color.Gray, fontSize = 14.sp)
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Edit Profile")
        }
    }
}
