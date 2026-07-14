package com.yallahmasyaf.app.presentation.explore.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yallahmasyaf.app.presentation.theme.DeepOceanBlue
import com.yallahmasyaf.app.presentation.theme.YallahMasyafTheme

@Composable
fun ExploreSearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "ابحث عن شاليه، فيلا، أو معلم سياحي...",
                fontSize = 14.sp
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "بحث",
                tint = DeepOceanBlue
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(28.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = DeepOceanBlue,
            unfocusedBorderColor = OutlinedTextFieldDefaults.colors().unfocusedBorderColor,
            focusedContainerColor = OutlinedTextFieldDefaults.colors().focusedContainerColor,
            unfocusedContainerColor = OutlinedTextFieldDefaults.colors().unfocusedContainerColor
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ExploreSearchBarPreview() {
    YallahMasyafTheme {
        ExploreSearchBar(
            query = "",
            onQueryChanged = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
