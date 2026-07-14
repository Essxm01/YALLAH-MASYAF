package com.yallahmasyaf.app.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yallahmasyaf.app.presentation.profile.components.OwnerDashboard
import com.yallahmasyaf.app.presentation.theme.CoastalGold
import com.yallahmasyaf.app.presentation.theme.DeepOceanBlue
import com.yallahmasyaf.app.presentation.theme.SunsetCoral
import com.yallahmasyaf.app.presentation.theme.YallahMasyafTheme

@Composable
fun ProfileScreen(
    state: ProfileUiState,
    onToggleRoleClick: () -> Unit,
    onAddUnitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val titleText = if (state.currentRole == UserRole.OWNER) "لوحة التحكم" else "الملف الشخصي"

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = titleText,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = DeepOceanBlue
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                when (state.currentRole) {
                    UserRole.RENTER -> {
                        // Renter Mode UI Layout
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                // User Info Header Card
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = DeepOceanBlue
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(20.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(60.dp)
                                                .background(Color.White.copy(alpha = 0.2f), shape = CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = state.name.firstOrNull()?.toString() ?: "أ",
                                                color = Color.White,
                                                fontSize = 26.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        
                                        Spacer(modifier = Modifier.width(16.dp))
                                        
                                        Column {
                                            Text(
                                                text = state.name,
                                                color = Color.White,
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = state.phone,
                                                color = Color.White.copy(alpha = 0.7f),
                                                fontSize = 13.sp
                                            )
                                            Text(
                                                text = state.email,
                                                color = Color.White.copy(alpha = 0.7f),
                                                fontSize = 12.sp
                                            )
                                        }
                                    }
                                }
                                
                                Spacer(modifier = Modifier.height(24.dp))
                                
                                Text(
                                    text = "إعدادات الحساب",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepOceanBlue,
                                    modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
                                )
                                
                                ProfileOptionItem(
                                    title = "تعديل الحساب والمعلومات الشخصية",
                                    icon = Icons.Default.Edit,
                                    onClick = {}
                                )
                                
                                ProfileOptionItem(
                                    title = "الدعم الفني ومساعدة العملاء",
                                    icon = Icons.Default.Info,
                                    onClick = {}
                                )
                                
                                ProfileOptionItem(
                                    title = "سياسة الخصوصية والشروط",
                                    icon = Icons.Default.Settings,
                                    onClick = {}
                                )
                            }

                            // Switch Mode Button
                            Button(
                                onClick = onToggleRoleClick,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(54.dp)
                                    .padding(bottom = 8.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = CoastalGold,
                                    contentColor = DeepOceanBlue
                                )
                            ) {
                                Text(
                                    text = "التبديل إلى وضع المالك / أصحاب العقارات",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    UserRole.OWNER -> {
                        // Owner Mode Dashboard Layout
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Scrollable Owner Dashboard Component
                            Box(modifier = Modifier.weight(1f)) {
                                OwnerDashboard(
                                    ownerUnits = state.ownerUnits,
                                    onAddUnitClick = onAddUnitClick
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Return to Renter Button
                            Button(
                                onClick = onToggleRoleClick,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(54.dp)
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = SunsetCoral,
                                    contentColor = Color.White
                                )
                            ) {
                                Text(
                                    text = "العودة لوضع المستأجر",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileOptionItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.03f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = DeepOceanBlue,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            
            Icon(
                imageVector = Icons.Default.ArrowBack, // Pointing left in RTL indicating navigation flow
                contentDescription = "التفاصيل",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    YallahMasyafTheme {
        ProfileScreen(
            state = ProfileUiState(
                name = "أحمد محمد علي",
                phone = "+20 100 123 4567",
                email = "ahmed.mohamed@example.com",
                currentRole = UserRole.RENTER
            ),
            onToggleRoleClick = {},
            onAddUnitClick = {}
        )
    }
}
