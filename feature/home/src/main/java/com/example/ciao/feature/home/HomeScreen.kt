package com.example.ciao.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ciao.common.result.Result
import com.example.ciao.core.domain.usecase.auth.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.ciao.feature.home.R

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    init {
        loadChats()
    }
    
    private fun loadChats() {
        // Mock data - in real app, this would come from repository
        val mockChats = listOf(
            ChatItem(
                id = "1",
                name = "Maria Garcia",
                message = "Sounds good, see you then!",
                time = "10:42 AM",
                unreadCount = 2,
                avatarRes = R.drawable.img15,
                isOnline = true
            ),
            ChatItem(
                id = "2",
                name = "Lunch Group",
                message = "Alex: I'll bring the drinks.",
                time = "10:35 AM",
                unreadCount = 0,
                avatarRes = R.drawable.img16,
                isOnline = false
            ),
            ChatItem(
                id = "3",
                name = "David Smith",
                message = "Can you send me the file?",
                time = "Yesterday",
                unreadCount = 0,
                avatarRes = R.drawable.img17,
                isOnline = true
            ),
            ChatItem(
                id = "4",
                name = "Project Phoenix",
                message = "You were mentioned in the chat",
                time = "Yesterday",
                unreadCount = 1,
                avatarRes = R.drawable.img18,
                isOnline = false
            ),
            ChatItem(
                id = "5",
                name = "Emily Johnson",
                message = "Perfect, thank you!",
                time = "Mon",
                unreadCount = 0,
                avatarRes = R.drawable.img8,
                isOnline = false
            ),
            ChatItem(
                id = "6",
                name = "Michael Chen",
                message = "Just saw your message, I'll reply soon.",
                time = "Mon",
                unreadCount = 0,
                avatarRes = R.drawable.img9,
                isOnline = true
            )
        )
        _uiState.value = _uiState.value.copy(chats = mockChats)
    }
    
    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SearchQueryChanged -> {
                _uiState.value = _uiState.value.copy(searchQuery = event.query)
            }
            HomeEvent.LogoutClicked -> {
                logout()
            }
            is HomeEvent.TabSelected -> {
                _uiState.value = _uiState.value.copy(selectedTab = event.tab)
            }
        }
    }
    
    private fun logout() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoggingOut = true)
            val result = signOutUseCase(Unit)
            when (result) {
                is Result.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoggingOut = false,
                        isLoggedOut = true
                    )
                }
                is Result.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoggingOut = false,
                        error = result.message
                    )
                }
                Result.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoggingOut = true)
                }
            }
        }
    }
}

data class HomeUiState(
    val chats: List<ChatItem> = emptyList(),
    val searchQuery: String = "",
    val selectedTab: Int = 0,
    val isLoggingOut: Boolean = false,
    val isLoggedOut: Boolean = false,
    val error: String? = null
)

data class ChatItem(
    val id: String,
    val name: String,
    val message: String,
    val time: String,
    val unreadCount: Int,
    val avatarRes: Int,
    val isOnline: Boolean
)

sealed interface HomeEvent {
    data class SearchQueryChanged(val query: String) : HomeEvent
    data object LogoutClicked : HomeEvent
    data class TabSelected(val tab: Int) : HomeEvent
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showLogoutDialog by remember { mutableStateOf(false) }
    
    LaunchedEffect(uiState.isLoggedOut) {
        if (uiState.isLoggedOut) {
            onLogout()
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Chats",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = { /* Search action */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = uiState.selectedTab == 0,
                    onClick = { viewModel.onEvent(HomeEvent.TabSelected(0)) },
                    icon = {
                        Icon(
                            imageVector = if (uiState.selectedTab == 0) Icons.Filled.Chat else Icons.Outlined.ChatBubbleOutline,
                            contentDescription = "Chats"
                        )
                    },
                    label = { Text("Chats") }
                )
                NavigationBarItem(
                    selected = uiState.selectedTab == 1,
                    onClick = { viewModel.onEvent(HomeEvent.TabSelected(1)) },
                    icon = {
                        Icon(
                            imageVector = if (uiState.selectedTab == 1) Icons.Filled.Call else Icons.Outlined.Call,
                            contentDescription = "Calls"
                        )
                    },
                    label = { Text("Calls") }
                )
                NavigationBarItem(
                    selected = uiState.selectedTab == 2,
                    onClick = { viewModel.onEvent(HomeEvent.TabSelected(2)) },
                    icon = {
                        Icon(
                            imageVector = if (uiState.selectedTab == 2) Icons.Filled.Person else Icons.Outlined.Person,
                            contentDescription = "Contacts"
                        )
                    },
                    label = { Text("Contacts") }
                )
                NavigationBarItem(
                    selected = uiState.selectedTab == 3,
                    onClick = { showLogoutDialog = true },
                    icon = {
                        Icon(
                            imageVector = if (uiState.selectedTab == 3) Icons.Filled.Settings else Icons.Outlined.Settings,
                            contentDescription = "Settings"
                        )
                    },
                    label = { Text("Settings") }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* New chat action */ },
                containerColor = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "New Chat",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.chats) { chat ->
                    ChatListItem(
                        chat = chat,
                        onClick = { /* Navigate to chat */ }
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 88.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                }
            }
            
            // Logout confirmation dialog
            if (showLogoutDialog) {
                AlertDialog(
                    onDismissRequest = { showLogoutDialog = false },
                    title = { Text("Logout") },
                    text = { Text("Are you sure you want to logout?") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showLogoutDialog = false
                                viewModel.onEvent(HomeEvent.LogoutClicked)
                            }
                        ) {
                            Text("Logout")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showLogoutDialog = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }
            
            // Loading overlay
            if (uiState.isLoggingOut) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun ChatListItem(
    chat: ChatItem,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar with online indicator
        Box {
            Image(
                painter = painterResource(id = chat.avatarRes),
                contentDescription = chat.name,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            if (chat.isOnline) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.BottomEnd)
                        .background(MaterialTheme.colorScheme.primary, CircleShape)
                        .padding(2.dp)
                        .background(MaterialTheme.colorScheme.surface, CircleShape)
                )
            }
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        // Chat info
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = chat.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = chat.message,
                fontSize = 14.sp,
                color = if (chat.unreadCount > 0) MaterialTheme.colorScheme.primary 
                       else MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        
        Spacer(modifier = Modifier.width(8.dp))
        
        // Time and unread badge
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = chat.time,
                fontSize = 12.sp,
                color = if (chat.unreadCount > 0) MaterialTheme.colorScheme.primary 
                       else MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (chat.unreadCount > 0) {
                Spacer(modifier = Modifier.height(4.dp))
                Badge(
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Text(
                        text = chat.unreadCount.toString(),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
