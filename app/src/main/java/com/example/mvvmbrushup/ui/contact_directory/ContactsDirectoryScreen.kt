package com.example.mvvmbrushup.ui.contact_directory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mvvmbrushup.ui.contact_directory.components.ContactItem
import com.example.mvvmbrushup.ui.contact_directory.components.SectionTitle
import com.example.mvvmbrushup.ui.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsDirectoryScreen(
    navController: NavController,
    viewModel: ContactsDirectoryViewModel = hiltViewModel()
) {
    val mainContacts by remember { mutableStateOf(viewModel.getMainContacts()) }
    val allContacts by remember { mutableStateOf(viewModel.getAllContacts()) }
    val isAscending by viewModel.isAscending.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Contacts Directory",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold) // Bold title
                    )
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFC107) // Yellow
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            item { SectionTitle(title = "Main Contacts") }
            itemsIndexed(mainContacts) { index, contact ->
                ContactItem(
                    title = contact.name,
                    phone = contact.number,
                    isLastItem = (index == mainContacts.lastIndex)
                )
            }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { SectionTitle(title = "All Contacts", showSortButton = true,buttonText = if (isAscending) "Sort Z to A" else "Sort A to Z",
                onSortButtonClick = { viewModel.toggleSortOrder() }) }

            itemsIndexed(allContacts) { index, contact ->
                ContactItem(
                    title = contact.name,
                    phone = contact.number,
                    isLastItem = (index == allContacts.lastIndex)
                )
            }
        }
    }
}