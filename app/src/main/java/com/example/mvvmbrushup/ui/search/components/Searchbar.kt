package com.example.mvvmbrushup.ui.search.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    placeholder: String = "Search...",
    onSearchTextChanged: (String) -> Unit
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        color = Color.White,
        shape = RoundedCornerShape(40.dp),
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(35.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            BasicTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    onSearchTextChanged(it.text)
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.NumberPassword
                ),
                visualTransformation = VisualTransformation.None,

                        modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
                    ,  // Take up remaining space in the row
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                decorationBox = { innerTextField ->
                    if (searchText.text.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                        )
                    }
                    innerTextField()
                }
            )

            if (searchText.text.isNotEmpty()) {
                IconButton(onClick = { searchText = TextFieldValue("") }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear search text"
                    )
                }
            }
        }
    }

}