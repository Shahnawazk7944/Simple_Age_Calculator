package com.example.simple_age_calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.simple_age_calculator.ui.theme.AzureMist
import com.example.simple_age_calculator.ui.theme.BlueMain
import com.example.simple_age_calculator.ui.theme.BottomSheetColor
import com.example.simple_age_calculator.ui.theme.poppins
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.RoundedCorner


@Composable
fun SavedDataScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 30.dp)

    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)

                .neu(
                    lightShadowColor = BottomSheetColor,
                    darkShadowColor = Color.LightGray,
                    shadowElevation = 10.dp,
                    lightSource = LightSource.LEFT_TOP,
                    shape = Flat(RoundedCorner(20.dp)),
                ),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(containerColor = AzureMist),
            shape = RoundedCornerShape(20.dp),

            ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Your Saved Age Records",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppins,
                        color = BlueMain,
                        fontSize = 20.sp,

                        )
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = BlueMain,
                        modifier = Modifier.size(35.dp)
                    )
                }

            }
        }
    }
}