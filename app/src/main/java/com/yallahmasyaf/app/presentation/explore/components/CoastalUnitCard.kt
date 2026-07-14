package com.yallahmasyaf.app.presentation.explore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yallahmasyaf.app.domain.model.CoastalUnit
import com.yallahmasyaf.app.presentation.theme.CoastalGold
import com.yallahmasyaf.app.presentation.theme.DeepOceanBlue
import com.yallahmasyaf.app.presentation.theme.SunsetCoral
import com.yallahmasyaf.app.presentation.theme.YallahMasyafTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoastalUnitCard(
    unit: CoastalUnit,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column {
            // 1. Image Section (Gradient Placeholder)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                DeepOceanBlue,
                                Color(0xFF00A896),
                                Color(0xFF028090)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                // District Overlay Badge (TopEnd)
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.85f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = unit.district.substringBefore(" ("),
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Floating Favorite Heart Button (TopStart)
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                        .background(
                            color = Color.Black.copy(alpha = 0.35f),
                            shape = CircleShape
                        )
                        .size(36.dp),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = onFavoriteClick,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = if (unit.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "المفارلة",
                            tint = if (unit.isFavorite) SunsetCoral else Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                // Placeholder text to indicate image loading capability
                Text(
                    text = "YALLAH MASYAF",
                    color = Color.White.copy(alpha = 0.3f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.5.sp
                )
            }

            // 2. Info Details Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Location row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "الموقع",
                        tint = CoastalGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${unit.district} - ${unit.city}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Title and Rating Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = unit.title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "التقييم",
                            tint = CoastalGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = unit.rating.toString(),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = " (${unit.reviewCount})",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 3. Key Amenities & Policies Pills
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    if (unit.hasPool) {
                        AmenityPill(text = "مسبح")
                    }
                    if (unit.hasWifi) {
                        AmenityPill(text = "واي فاي")
                    }
                    if (unit.hasBeachAccess) {
                        AmenityPill(text = "دخول للشاطئ")
                    }
                    if (unit.isFamiliesOnly) {
                        PolicyPill(text = "عائلات فقط")
                    } else if (unit.isBachelorAllowed) {
                        PolicyPill(text = "متاح شباب")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Divider line
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 4. Pricing & Rooms Summary Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Rooms info
                    Text(
                        text = "${unit.bedroomCount} غرف • ${unit.bathroomCount} حمام",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )

                    // Price per night
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "${unit.pricePerNight.toInt()}",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = SunsetCoral,
                                fontSize = 20.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = " ج.م / ليلة",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AmenityPill(text: String) {
    Box(
        modifier = Modifier
            .background(
                color = Color(0xFF00A896).copy(alpha = 0.1f),
                shape = RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = Color(0xFF00A896),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PolicyPill(text: String) {
    Box(
        modifier = Modifier
            .background(
                color = SunsetCoral.copy(alpha = 0.1f),
                shape = RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = SunsetCoral,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CoastalUnitCardPreview() {
    YallahMasyafTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            CoastalUnitCard(
                unit = CoastalUnit(
                    id = "1",
                    title = "شاليه فاخر صف أول على البحر - مراسي",
                    description = "شاليه فاخر مجهز بالكامل.",
                    images = emptyList(),
                    city = "الساحل الشمالي",
                    district = "مراسي (سيدي عبد الرحمن)",
                    pricePerNight = 8000.0,
                    minStayDays = 3,
                    bedroomCount = 3,
                    bathroomCount = 2,
                    bedCount = 4,
                    rating = 4.8,
                    reviewCount = 24,
                    hasKitchen = true,
                    hasAirConditioning = true,
                    hasWifi = true,
                    hasPool = true,
                    hasGarden = false,
                    hasBeachAccess = true,
                    requiresBeachCard = true,
                    hasParking = true,
                    hasElevator = false,
                    isPetsAllowed = false,
                    isFamiliesOnly = true,
                    isBachelorAllowed = false,
                    checkInTime = "02:00 PM",
                    checkOutTime = "12:00 PM"
                ),
                onClick = {},
                onFavoriteClick = {}
            )
        }
    }
}
