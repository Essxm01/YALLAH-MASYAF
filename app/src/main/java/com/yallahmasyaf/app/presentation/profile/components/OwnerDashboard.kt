package com.yallahmasyaf.app.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yallahmasyaf.app.domain.model.CoastalUnit
import com.yallahmasyaf.app.presentation.explore.components.CoastalUnitCard
import com.yallahmasyaf.app.presentation.theme.CoastalGold
import com.yallahmasyaf.app.presentation.theme.DeepOceanBlue
import com.yallahmasyaf.app.presentation.theme.SunsetCoral
import com.yallahmasyaf.app.presentation.theme.YallahMasyafTheme

@Composable
fun OwnerDashboard(
    ownerUnits: List<CoastalUnit>,
    onAddUnitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // 1. Stats Row Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatsCard(
                title = "إجمالي الأرباح",
                value = "23,500 ج.م",
                valueColor = CoastalGold,
                modifier = Modifier.weight(1.2f)
            )
            StatsCard(
                title = "حجوزات نشطة",
                value = "3 حجز",
                valueColor = Color.White,
                modifier = Modifier.weight(0.9f)
            )
            StatsCard(
                title = "المشاهدات",
                value = "142",
                valueColor = Color.White,
                modifier = Modifier.weight(0.9f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 2. Add New Unit Button Section
        Button(
            onClick = onAddUnitClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SunsetCoral,
                contentColor = Color.White
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "إضافة وحدة",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "إضافة وحدة ساحلية جديدة",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // 3. Current Owned Properties List
        Text(
            text = "وحداتك الساحلية الحالية",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = DeepOceanBlue,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        if (ownerUnits.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.03f),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "لا توجد وحدات معروضة حالياً.\nاضغط على الزر أعلاه لإضافة وحدتك الساحلية الأولى.",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    lineHeight = 22.sp
                )
            }
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ownerUnits.forEach { unit ->
                    CoastalUnitCard(
                        unit = unit,
                        onClick = {}, // Dashboard preview mode
                        onFavoriteClick = {} // Dashboard preview mode
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun StatsCard(
    title: String,
    value: String,
    valueColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = DeepOceanBlue
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontSize = 11.sp,
                color = Color.White.copy(alpha = 0.6f),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value,
                fontSize = 15.sp,
                color = valueColor,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OwnerDashboardPreview() {
    YallahMasyafTheme {
        OwnerDashboard(
            ownerUnits = emptyList(),
            onAddUnitClick = {}
        )
    }
}
