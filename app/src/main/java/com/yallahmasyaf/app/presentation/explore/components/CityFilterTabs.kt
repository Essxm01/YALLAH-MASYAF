package com.yallahmasyaf.app.presentation.explore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yallahmasyaf.app.presentation.theme.DeepOceanBlue
import com.yallahmasyaf.app.presentation.theme.YallahMasyafTheme

@Composable
fun CityFilterTabs(
    selectedCity: String,
    onCitySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val cities = listOf("الكل", "الساحل الشمالي", "الإسكندرية", "العين السخنة", "مرسى مطروح", "رأس البر", "الجونة")

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(cities) { city ->
            val isSelected = city == selectedCity
            CityChip(
                cityName = city,
                isSelected = isSelected,
                onSelected = { onCitySelected(city) }
            )
        }
    }
}

@Composable
private fun CityChip(
    cityName: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    val isDark = MaterialTheme.colorScheme.background.red < 0.2f // Check dark mode background
    val backgroundColor = when {
        isSelected -> DeepOceanBlue
        isDark -> Color(0xFF0F2B48)
        else -> Color(0xFFEDF2F7)
    }
    val textColor = when {
        isSelected -> Color.White
        isDark -> Color(0xFFA0AEC0)
        else -> Color(0xFF4A5568)
    }
    
    Box(
        modifier = Modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(20.dp))
            .clickable { onSelected() }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = cityName,
            color = textColor,
            fontSize = 13.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityFilterTabsPreview() {
    YallahMasyafTheme {
        CityFilterTabs(
            selectedCity = "الكل",
            onCitySelected = {}
        )
    }
}
