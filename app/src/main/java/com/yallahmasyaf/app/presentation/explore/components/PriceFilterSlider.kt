package com.yallahmasyaf.app.presentation.explore.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yallahmasyaf.app.presentation.theme.CoastalGold
import com.yallahmasyaf.app.presentation.theme.DeepOceanBlue
import com.yallahmasyaf.app.presentation.theme.SunsetCoral
import com.yallahmasyaf.app.presentation.theme.YallahMasyafTheme

@Composable
fun PriceFilterSlider(
    maxPrice: Float,
    onPriceChanged: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Price Label Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "الحد الأقصى للسعر: ",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )
            
            Text(
                text = if (maxPrice >= 30000f) "مفتوح" else "%,d ج.م".format(maxPrice.toInt()),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                color = SunsetCoral,
                fontSize = 15.sp
            )
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        // Slider Control
        Slider(
            value = maxPrice,
            onValueChange = onPriceChanged,
            valueRange = 0f..30000f,
            colors = SliderDefaults.colors(
                thumbColor = CoastalGold,
                activeTrackColor = DeepOceanBlue,
                inactiveTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PriceFilterSliderPreview() {
    YallahMasyafTheme {
        PriceFilterSlider(
            maxPrice = 15000f,
            onPriceChanged = {}
        )
    }
}
