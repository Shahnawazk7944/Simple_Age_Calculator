package com.example.simple_age_calculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.simple_age_calculator.ui.theme.AzureMist
import com.example.simple_age_calculator.ui.theme.BlueMain
import com.example.simple_age_calculator.ui.theme.BottomSheetColor
import com.example.simple_age_calculator.ui.theme.PinkDark
import com.example.simple_age_calculator.ui.theme.playFairFamily
import com.example.simple_age_calculator.ui.theme.poppins
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.RoundedCorner
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    // skipPartiallyExpanded = true for opening bottom sheet
    // state at fixed sized
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
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



        Image(
            painter = painterResource(id = R.drawable.think1),
            contentDescription = "thinking Age",
            Modifier
                .size(300.dp)
                .padding(bottom = 50.dp)
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 200.dp, start = 25.dp)
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
                Modifier.heightIn(min = 520.dp, max = 520.dp),
                containerColor = BottomSheetColor,
                sheetState = sheetState,
            ) {
                // Sheet content
                BottomSheet(navController = navController) {
                    scope.launch { sheetState.hide() }
                        .invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                }

            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}