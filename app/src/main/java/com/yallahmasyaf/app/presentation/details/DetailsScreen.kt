package com.yallahmasyaf.app.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yallahmasyaf.app.presentation.details.components.AmenitiesGrid
import com.yallahmasyaf.app.presentation.details.components.CommunicationActionBar
import com.yallahmasyaf.app.presentation.details.components.DetailsImageCarousel
import com.yallahmasyaf.app.presentation.theme.CoastalGold
import com.yallahmasyaf.app.presentation.theme.DeepOceanBlue

@Composable
fun DetailsScreen(
    state: DetailsUiState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            state.coastalUnit?.let { unit ->
                CommunicationActionBar(
                    pricePerNight = unit.pricePerNight,
                    ownerPhone = "+201000000000" // Mockup contact phone
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                state.errorMessage != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = state.errorMessage ?: "حدث خطأ غير متوقع",
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier.background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = CircleShape
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "رجوع",
                                tint = Color.White
                            )
                        }
                    }
                }
                state.coastalUnit != null -> {
                    val unit = state.coastalUnit
                    
                    Box(modifier = Modifier.fillMaxSize()) {
                        // Scrollable main content Column
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                        ) {
                            // 1. Photo Carousel
                            DetailsImageCarousel(images = unit.images)
                            
                            // 2. Unit Details Information
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                // Title and Rating Row
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = unit.title,
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = DeepOceanBlue,
                                        modifier = Modifier.weight(1f)
                                    )
                                    
                                    Spacer(modifier = Modifier.width(8.dp))
                                    
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = "التقييم",
                                            tint = CoastalGold,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(modifier = Modifier.width(2.dp))
                                        Text(
                                            text = unit.rating.toString(),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 15.sp
                                        )
                                        Text(
                                            text = " (${unit.reviewCount})",
                                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                            fontSize = 14.sp
                                        )
                                    }
                                }
                                
                                Spacer(modifier = Modifier.height(8.dp))
                                
                                // Location pin details
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = "الموقع",
                                        tint = CoastalGold,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${unit.district} - ${unit.city}",
                                        fontSize = 14.sp,
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                    )
                                }
                                
                                Spacer(modifier = Modifier.height(16.dp))
                                
                                // Divider line
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                                )
                                
                                Spacer(modifier = Modifier.height(16.dp))
                                
                                // Description Text
                                Text(
                                    text = "الوصف الكامل",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepOceanBlue
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = unit.description,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                                    lineHeight = 22.sp
                                )
                            }
                            
                            // 3. Amenities and policies grid layout
                            AmenitiesGrid(unit = unit)
                            
                            // Extra space to prevent bottom navigation bar overlap
                            Spacer(modifier = Modifier.height(80.dp))
                        }
                        
                        // Floating premium back button on top-start corner
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(16.dp)
                                .size(40.dp)
                                .background(Color.White.copy(alpha = 0.85f), shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(onClick = onBackClick) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "رجوع",
                                    tint = DeepOceanBlue,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
