package com.yallahmasyaf.app.presentation.details.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yallahmasyaf.app.presentation.theme.DeepOceanBlue
import com.yallahmasyaf.app.presentation.theme.SunsetCoral
import com.yallahmasyaf.app.presentation.theme.YallahMasyafTheme

@Composable
fun CommunicationActionBar(
    pricePerNight: Double,
    ownerPhone: String = "+201000000000",
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Surface(
        modifier = modifier.fillMaxWidth(),
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Price info
            Column {
                Text(
                    text = "%,.0f ج.م".format(pricePerNight),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = SunsetCoral
                )
                Text(
                    text = "لكل ليلة",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            
            // Communication Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Call Phone Button
                Button(
                    onClick = { launchDialIntent(context, ownerPhone) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DeepOceanBlue
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "اتصال", fontWeight = FontWeight.Bold, color = Color.White)
                }

                // WhatsApp message Button
                Button(
                    onClick = { launchWhatsAppIntent(context, ownerPhone) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF25D366) // WhatsApp brand green color
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "واتساب", fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}

private fun launchDialIntent(context: Context, phone: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phone")
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "عذراً، لا يمكن إجراء الاتصال حالياً", Toast.LENGTH_SHORT).show()
    }
}

private fun launchWhatsAppIntent(context: Context, phone: String) {
    try {
        val message = "مرحباً، أنا مهتم بحجز وحدتك الساحلية المعروضة على تطبيق يلا مصيف"
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://api.whatsapp.com/send?phone=$phone&text=" + Uri.encode(message))
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "عذراً، تطبيق واتساب غير مثبت على الجهاز", Toast.LENGTH_SHORT).show()
    }
}

@Preview(showBackground = true)
@Composable
fun CommunicationActionBarPreview() {
    YallahMasyafTheme {
        CommunicationActionBar(pricePerNight = 8000.0)
    }
}
