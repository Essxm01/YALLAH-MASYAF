package com.yallahmasyaf.app.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yallahmasyaf.app.presentation.theme.DeepOceanBlue
import com.yallahmasyaf.app.presentation.theme.YallahMasyafTheme

@Composable
fun DetailsImageCarousel(
    images: List<String>,
    modifier: Modifier = Modifier
) {
    val displayCount = if (images.isEmpty()) 3 else images.size
    val pagerState = rememberPagerState(pageCount = { displayCount })

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            // Summer themed color gradients reflecting sea/sun/sand
            val gradientColors = when (page % 3) {
                0 -> listOf(DeepOceanBlue, Color(0xFF00A896))
                1 -> listOf(Color(0xFF028090), Color(0xFF00A896))
                else -> listOf(DeepOceanBlue, Color(0xFFE2B659))
            }
            
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = Brush.verticalGradient(gradientColors)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "صورة المعرض ${page + 1}",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Slide counter indicator
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .background(
                    color = Color.Black.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = "${pagerState.currentPage + 1} / $displayCount",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsImageCarouselPreview() {
    YallahMasyafTheme {
        DetailsImageCarousel(images = emptyList())
    }
}
