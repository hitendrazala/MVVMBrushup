package com.example.mvvmbrushup.ui.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import com.example.mvvmbrushup.data.model.ExchangeDataItem

@Composable
fun SearchListItem(
    item: ExchangeDataItem,
    onItemClick: (CoinData) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  }
            .padding(vertical = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = item.exchangeName,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )

//            Text(
//                text = item.type,
//                style = MaterialTheme.typography.bodyMedium
//            )

        }
    }
}