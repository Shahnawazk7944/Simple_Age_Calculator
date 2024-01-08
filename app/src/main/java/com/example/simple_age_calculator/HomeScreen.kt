package com.example.simple_age_calculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simple_age_calculator.ui.theme.BlueMain
import com.example.simple_age_calculator.ui.theme.PinkDark
import com.example.simple_age_calculator.ui.theme.playFairFamily

@Composable
fun HomeScreen() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.think1),
            contentDescription = "thinking Age",
            Modifier.size(300.dp)
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 150.dp, start = 25.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Age",
                    fontFamily = playFairFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 60.sp,
                    color = BlueMain,
                )
                Text(
                    text = "Calculator",
                    fontFamily = playFairFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 30.sp,
                    color = PinkDark,
                )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 70.dp)
        ) {
                Image(painter = painterResource(id = R.drawable.next2),
                    contentDescription = "next",
                    Modifier.size(60.dp).clickable {  }
                )
        }




    }
}