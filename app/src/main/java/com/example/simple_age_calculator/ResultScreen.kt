package com.example.simple_age_calculator

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ShapeDefaults
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.simple_age_calculator.models.SavedViewModal.SaveDataEvent
import com.example.simple_age_calculator.models.SavedViewModal.SaveDataState
import com.example.simple_age_calculator.ui.theme.AzureMist
import com.example.simple_age_calculator.ui.theme.BlueMain
import com.example.simple_age_calculator.ui.theme.BottomSheetColor
import com.example.simple_age_calculator.ui.theme.MainButton
import com.example.simple_age_calculator.ui.theme.poppins
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Oval
import com.gandiva.neumorphic.shape.RoundedCorner
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateAge(
    navController: NavController,
    dob: String,
    todayDate: String,
    ageYears: String,
    ageMonths: String,
    ageDays: String,
    bornOn: String,
    totalDays: String,
    totalWeeks: String,
    totalMonths: String,
    state: SaveDataState,
    onEvent: (SaveDataEvent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    // skipPartiallyExpanded = true for opening bottom sheet
    // state at fixed sized
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val titleColor = Color.Gray
    val headColor = BlueMain
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BottomSheetColor)
            .padding(start = 10.dp, end = 10.dp, top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        //--------------- RESULT Heading
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start

        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 20.dp)
                    .neu(
                        lightShadowColor = BottomSheetColor,
                        darkShadowColor = Color.LightGray,
                        shadowElevation = 8.dp,
                        lightSource = LightSource.LEFT_TOP,
                        shape = Flat(Oval),
                    ),
                //elevation = CardDefaults.cardElevation(10.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = AzureMist
                ),

                ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = headColor
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.weight(6f),
                text = "Results",
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Medium,
                fontFamily = poppins,
                //style = MaterialTheme.typography.titleSmall,
                color = headColor,
                fontSize = 25.sp
            )
        }


        //--------------- YOUR AGE BOX
        Spacer(modifier = Modifier.height(60.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 20.dp)
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

            )
        {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Your Age :",
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppins,
                    color = titleColor,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 10.dp, start = 10.dp)
                )

                //--------------- AGE BOX
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "$ageYears Years  $ageMonths Months  $ageDays Days",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppins,
                        color = headColor,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            }
        }
        //----AGE BOX END


        //--------------- More Details
        Spacer(modifier = Modifier.height(40.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(300.dp)
                .neu(
                    lightShadowColor = BottomSheetColor,
                    darkShadowColor = Color.LightGray,
                    shadowElevation = 15.dp,
                    lightSource = LightSource.LEFT_TOP,
                    shape = Flat(RoundedCorner(24.dp)),
                ),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(containerColor = AzureMist),
            shape = RoundedCornerShape(24.dp),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {

                //----- 1st Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    verticalAlignment = Alignment.Top,
                    //horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Date Of Birth",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins,
                        color = titleColor,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = dob,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins,
                        color = headColor,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                }

                Divider(thickness = 0.2.dp)
                //----- 2nd Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    verticalAlignment = Alignment.Top,
                    //horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Today's Date",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins,
                        color = titleColor,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = todayDate,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins,
                        color = headColor,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                }

                Divider(thickness = 0.2.dp)
                //----- 3rd Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    verticalAlignment = Alignment.Top,
                    //horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Born On",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins,
                        color = titleColor,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = bornOn,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins,
                        color = headColor,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                }

                Divider(thickness = 0.2.dp)
                //----- 4th Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    verticalAlignment = Alignment.Top,
                    //horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Total Months",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins,
                        color = titleColor,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = totalMonths,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins,
                        color = headColor,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                }

                Divider(thickness = 0.2.dp)
                // ----- 5th Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    verticalAlignment = Alignment.Top,
                    //horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Total Weeks",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins,
                        color = titleColor,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = totalWeeks,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins,
                        color = headColor,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                }

                Divider(thickness = 0.2.dp)
                // ----- 6th Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    verticalAlignment = Alignment.Top,
                    //horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Total Days",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins,
                        color = titleColor,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = totalDays,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins,
                        color = headColor,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Button(
                onClick = {
                    showBottomSheet = true
                },
                modifier = Modifier
                    // .height(60.dp)
                    //.width(220.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 30.dp)
                    .height(50.dp)
                    .neu(
                        lightShadowColor = BottomSheetColor,
                        darkShadowColor = Color.Gray,
                        shadowElevation = 10.dp,
                        lightSource = LightSource.LEFT_TOP,
                        shape = Flat(RoundedCorner(12.dp)),
                    ),
                colors = ButtonDefaults.buttonColors(MainButton),
                shape = ShapeDefaults.Medium
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Save Data",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins,
                        //style = MaterialTheme.typography.titleSmall,
                        color = AzureMist,
                        fontSize = 16.sp
                    )
                    //                Spacer(modifier = Modifier.width(5.dp))
                    //                Icon(
                    //                    imageVector = Icons.Default.ArrowForward,
                    //                    contentDescription = null,
                    //                    tint = AzureMist
                    //                )
                }
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
                    SaveDatesBottomSheet(
                        navController = navController,
                        dob = dob,
                        todayDate = todayDate,
                        ageYears = ageYears,
                        ageMonths = ageMonths,
                        ageDays = ageDays,
                        bornOn = bornOn,
                        totalDays = totalDays,
                        totalWeeks = totalWeeks,
                        totalMonths = totalMonths,
                        state = state,
                        onEvent = onEvent
                    ) {
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

}

@Preview(showBackground = true)
@Composable
fun CalculateAgePreview() {
    CalculateAge(
        navController = rememberNavController(), "", "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        state = SaveDataState(),
        onEvent = {}
    )
}