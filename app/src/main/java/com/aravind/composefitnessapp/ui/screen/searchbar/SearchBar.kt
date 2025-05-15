package com.aravind.composefitnessapp.ui.screen.searchbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController ) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    val items = listOf(
        "Apple", "Banana", "Cherry", "Date", "Elderberry",
        "Fig", "Grape", "Honeydew", "Kiwi", "Lemon"
    )

    val filteredItems = items.filter {
        it.contains(query, ignoreCase = true)
    }
    Column(modifier = Modifier.fillMaxSize()) {


        SearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = {
                focusManager.clearFocus()
                //active = false
                 },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text("Search...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "بحث") },

            trailingIcon = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                if (query.isNotEmpty()) {
                    IconButton(onClick = { query = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "مسح")
                    }
                }

                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "رجوع",
                            modifier = Modifier.graphicsLayer {
                                rotationY = 180f }
                        )
                    }
                }



            },
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        ) {
            LazyColumn {
                items(filteredItems) { item ->
                    ListItem(
                        headlineContent = { Text(item) },
                        modifier = Modifier
                            .clickable {
                                query = item
                                active = false
                            }
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    MaterialTheme {
       val navController = rememberNavController()
        SearchScreen(navController)

    }
}

