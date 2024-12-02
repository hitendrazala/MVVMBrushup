package com.example.mvvmbrushup.ui.contact_directory.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ContactItem(title: String, phone: String, isLastItem: Boolean) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (phone.isNotEmpty()) {
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:$phone")
                    }
                    context.startActivity(intent)
                }
            }
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyLarge)
        if (phone.isNotEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = phone,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF757575)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Call",
                    modifier = Modifier.size(16.dp)
                )
            }
        }

    }
    if (!isLastItem) {
        Divider(
            color = Color(0xFFEEEEEE),
            thickness = 1.dp,
        )
    }
}