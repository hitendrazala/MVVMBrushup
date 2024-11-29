package com.example.mvvmbrushup.ui.coin_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.mvvmbrushup.data.model.Team

@Composable
fun TeamListItem(
    teamMember : Team,
    modifier : Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        teamMember.name?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = teamMember.position,
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic
        )

    }

}