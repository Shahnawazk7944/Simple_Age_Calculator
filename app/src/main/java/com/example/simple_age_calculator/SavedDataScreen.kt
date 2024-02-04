package com.example.simple_age_calculator

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.simple_age_calculator.models.SavedViewModal.SaveDataEvent
import com.example.simple_age_calculator.models.SavedViewModal.SaveDataState
import com.example.simple_age_calculator.ui.theme.AzureMist
import com.example.simple_age_calculator.ui.theme.BlueMain
import com.example.simple_age_calculator.ui.theme.BottomSheetColor
import com.example.simple_age_calculator.ui.theme.ChocoMain
import com.example.simple_age_calculator.ui.theme.ChocolateBrown
import com.example.simple_age_calculator.ui.theme.MainButton
import com.example.simple_age_calculator.ui.theme.PinkDark
import com.example.simple_age_calculator.ui.theme.poppins
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.RoundedCorner


@Composable
fun SavedDataScreen(
    navController: NavController,
    state: SaveDataState,
    onEvent: (SaveDataEvent) -> Unit
) {

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    //.fillMaxSize()
                    .height(55.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    //.background(MainButton)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your Saved Age Records",
                    modifier = Modifier.weight(1f),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                IconButton(onClick = { onEvent(SaveDataEvent.SortSavedData) }) {
                    Icon(
                        imageVector = Icons.Rounded.Sort, contentDescription = "Sort Icon",
                        modifier = Modifier.size(35.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }

            }
        },

        )
    { paddingValues ->

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
                .background(BottomSheetColor)
                .padding(vertical = 10.dp)
        ) {
            LazyColumn(

                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp,),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(state.savedData.size) { index ->
                    SaveDataItems(state = state, index = index, onEvent = onEvent)
                }

            }
        }

    }
}

@Composable
fun SaveDataItems(
    state: SaveDataState,
    index: Int,
    onEvent: (SaveDataEvent) -> Unit
) {
    val titleColor = Color.Gray
    val headColor = BlueMain
    var cardState by remember {
        mutableStateOf(false)
    }
    val rotateState by animateFloatAsState(targetValue = if (cardState) 180f else 0f)
    //Spacer(modifier = Modifier.height(60.dp))
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(100.dp)
//            .padding(horizontal = 20.dp)
//            .neu(
//                lightShadowColor = BottomSheetColor,
//                darkShadowColor = Color.LightGray,
//                shadowElevation = 10.dp,
//                lightSource = LightSource.LEFT_TOP,
//                shape = Flat(RoundedCorner(20.dp)),
//            ),
//        elevation = CardDefaults.cardElevation(10.dp),
//        colors = CardDefaults.cardColors(containerColor = AzureMist),
//        shape = RoundedCornerShape(20.dp),
//
//        )
//    {
//
//    }
//    //----AGE BOX END


    //--------------- More Details
    //Spacer(modifier = Modifier.height(40.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300, easing = LinearOutSlowInEasing
                )
            )
            .padding(horizontal = 10.dp).padding(vertical = 10.dp)
            //.height(400.dp)
            .neu(
                lightShadowColor = BottomSheetColor,
                darkShadowColor = Color.LightGray,
                shadowElevation = 8.dp,
                lightSource = LightSource.LEFT_TOP,
                shape = Flat(RoundedCorner(20.dp)),
            ),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = AzureMist),
        shape = RoundedCornerShape(24.dp),

        ) {
        Column(
            //modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            //----Name
            //                Text(
            //                    text = "Name :",
            //                    fontStyle = FontStyle.Normal,
            //                    fontWeight = FontWeight.Medium,
            //                    fontFamily = poppins,
            //                    color = titleColor,
            //                    fontSize = 16.sp,
            //                    modifier = Modifier.padding(top = 10.dp, start = 10.dp)
            //                )

            //---------------Name Box

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier
                    .fillMaxWidth().padding(start = 10.dp, top = 10.dp)
                    .weight(2f)) {
                    Text(
                        text = state.savedData[index].name,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppins,
                        color = ChocoMain,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                }

                IconButton(
                    onClick = { cardState = !cardState },
                    modifier = Modifier
                        .alpha(5f)
                        .rotate(rotateState)
//                        .weight(1f),
                ) {

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "dropDownIcon",
                    )
                }
            }




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
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${state.savedData[index].ageYears} Years ${state.savedData[index].ageMonths} Months ${state.savedData[index].ageDays} Days",
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppins,
                    color = headColor,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            if (cardState) {


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
                        text = state.savedData[index].dob,
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
                        text = state.savedData[index].todayDate,
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
                        text = state.savedData[index].bornOn,
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
                        text = state.savedData[index].totalMonths,
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
                        text = state.savedData[index].totalWeeks,
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
                        text = state.savedData[index].totalDays,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppins,
                        color = headColor,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(.6f)
                    )
                    IconButton(onClick = { onEvent(SaveDataEvent.DeleteSavedData(state.savedData[index])) }) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "Delete Icon",
                            modifier = Modifier
                                .size(25.dp)
                                .weight(1f),
                            tint = PinkDark
                        )
                    }
                }
            }

        }


    }
}


@Composable
@Preview(showBackground = true)
fun ItemsPreview() {
    SaveDataItems(
        state = SaveDataState(),
        index = 0,
        onEvent = {},
    )
}
