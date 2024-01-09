package com.example.simple_age_calculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simple_age_calculator.ui.theme.AzureMist
import com.example.simple_age_calculator.ui.theme.BlueMain
import com.example.simple_age_calculator.ui.theme.PinkDark
import com.example.simple_age_calculator.ui.theme.YellowMist
import com.example.simple_age_calculator.ui.theme.playFairFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

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
                Modifier
                    .size(60.dp)
                    .clickable { showBottomSheet = true }
            )
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                Modifier.heightIn(min = 500.dp, max = 500.dp),
                containerColor = YellowMist,

                sheetState = sheetState
            ) {
                // Sheet content
                BottomSheet()

            }
        }


    }
}