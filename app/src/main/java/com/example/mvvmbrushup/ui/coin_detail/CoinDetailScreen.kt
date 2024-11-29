package com.example.mvvmbrushup.ui.coin_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvmbrushup.ui.coin_detail.components.CoinTag
import com.example.mvvmbrushup.ui.coin_detail.components.TeamListItem
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel(),
    onSearchClick: (String) -> Unit
) {

    val state = viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        state.value.coins?.let { coins ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = CenterVertically
                    ) {

                        Text(
                            text = "${coins.rank}. ${coins.name} (${coins.symbol})",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(7f)
                        )

                        Text(
                            text = "Active",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(2f)
                        )

                        IconButton(
                            onClick = { onSearchClick(coins.id) },
                            modifier = Modifier
                                .weight(1f)
                                .align(CenterVertically)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )

                        }

                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    if (coins.description.isNotBlank()) {
                        Text(
                            text = "${coins.description}",
                            style = MaterialTheme.typography.bodyMedium
                        )

                    } else {
                        Text(
                            text = "No Description",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Tag",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        state.value.coins?.tags?.forEach { tag ->
                            tag.name?.let { CoinTag(tag = it) }

                        }

                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    if (coins.team.isNotEmpty()) {
                        Text(
                            text = "Team Member",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                items(coins.team) { team ->
                    TeamListItem(
                        teamMember = team, modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }

            }
        }

        if (state.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }

}