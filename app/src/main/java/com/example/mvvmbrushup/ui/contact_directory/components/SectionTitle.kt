package com.example.mvvmbrushup.ui.contact_directory.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SectionTitle(
    title: String,
    showSortButton: Boolean = false,
    buttonText: String = "",
    onSortButtonClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color(0xFFEEEEEE))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )
        if (showSortButton) {
            TextButton(onClick = { onSortButtonClick }) {
                Text(
                    text = buttonText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF007AFF) // Blue color
                )
            }
        }
    }
}