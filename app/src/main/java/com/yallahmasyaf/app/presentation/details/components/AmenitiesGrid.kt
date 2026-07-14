package com.yallahmasyaf.app.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yallahmasyaf.app.domain.model.CoastalUnit
import com.yallahmasyaf.app.presentation.theme.DeepOceanBlue
import com.yallahmasyaf.app.presentation.theme.SunsetCoral
import com.yallahmasyaf.app.presentation.theme.YallahMasyafTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AmenitiesGrid(
    unit: CoastalUnit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // 1. Rooms Summary Section
        Text(
            text = "المساحة والمواصفات",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = DeepOceanBlue
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.04f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RoomSummaryItem(count = unit.bedroomCount, label = "غرف نوم")
            RoomSummaryItem(count = unit.bedCount, label = "أسرّة")
            RoomSummaryItem(count = unit.bathroomCount, label = "حمامات")
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // 2. Amenities Section
        Text(
            text = "وسائل الراحة والمميزات",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = DeepOceanBlue
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (unit.hasPool) AmenityGridItem(text = "حمام سباحة", icon = Icons.Default.Check)
            if (unit.hasWifi) AmenityGridItem(text = "واي فاي مجاني", icon = Icons.Default.Wifi)
            if (unit.hasAirConditioning) AmenityGridItem(text = "تكييف هواء", icon = Icons.Default.Check)
            if (unit.hasKitchen) AmenityGridItem(text = "مطبخ متكامل", icon = Icons.Default.Home)
            if (unit.hasGarden) AmenityGridItem(text = "حديقة خاصة", icon = Icons.Default.Check)
            if (unit.hasBeachAccess) AmenityGridItem(text = "دخول للشاطئ", icon = Icons.Default.Check)
            if (unit.hasParking) AmenityGridItem(text = "موقف سيارات", icon = Icons.Default.Check)
            if (unit.hasElevator) AmenityGridItem(text = "مصعد كهربائي", icon = Icons.Default.Check)
            if (unit.requiresBeachCard) AmenityGridItem(text = "كارت بحر إلزامي", icon = Icons.Default.Info)
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // 3. Policies Section
        Text(
            text = "سياسات الحجز",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = DeepOceanBlue
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (unit.isFamiliesOnly) PolicyGridItem(text = "عائلات فقط", icon = Icons.Default.Group)
            if (unit.isBachelorAllowed) PolicyGridItem(text = "متاح للشباب", icon = Icons.Default.Person)
            if (unit.isPetsAllowed) PolicyGridItem(text = "مسموح بالحيوانات الأليفة", icon = Icons.Default.Pets)
            if (!unit.isPetsAllowed) PolicyGridItem(text = "ممنوع الحيوانات الأليفة", icon = Icons.Default.Block)
        }
    }
}

@Composable
private fun RoomSummaryItem(count: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = count.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DeepOceanBlue
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun AmenityGridItem(text: String, icon: ImageVector) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.04f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Color(0xFF00A896),
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = text,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun PolicyGridItem(text: String, icon: ImageVector) {
    Box(
        modifier = Modifier
            .background(
                color = SunsetCoral.copy(alpha = 0.08f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = SunsetCoral,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = text,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = SunsetCoral
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AmenitiesGridPreview() {
    YallahMasyafTheme {
        AmenitiesGrid(
            unit = CoastalUnit(
                id = "1",
                title = "شاليه فاخر",
                description = "شاليه فاخر مجهز بالكامل.",
                images = emptyList(),
                city = "الساحل الشمالي",
                district = "مراسي",
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
                hasGarden = true,
                hasBeachAccess = true,
                requiresBeachCard = true,
                hasParking = true,
                hasElevator = false,
                isPetsAllowed = false,
                isFamiliesOnly = true,
                isBachelorAllowed = false,
                checkInTime = "02:00 PM",
                checkOutTime = "12:00 PM"
            )
        )
    }
}
