package com.example.mvvmbrushup.ui.coin_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mvvmbrushup.data.model.CoinData

@Composable
fun CoinListItem(
    item: CoinData,
    onItemClick: (CoinData) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = item.type,
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }
}