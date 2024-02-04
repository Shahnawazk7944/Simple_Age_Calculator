package com.example.simple_age_calculator

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.simple_age_calculator.ui.theme.MainButton
import com.example.simple_age_calculator.ui.theme.PinkDark
import com.example.simple_age_calculator.ui.theme.poppins
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.RoundedCorner
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone


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
            modifier = Modifier
                .fillMaxSize()
                .background(BottomSheetColor)
            //.padding(vertical = 0.dp)
        ) {
            LazyColumn(

                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
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
    val context = LocalContext.current
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
            .padding(horizontal = 10.dp)
            .padding(vertical = 10.dp)
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
            Text(
                text = "Name :",
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Medium,
                fontFamily = poppins,
                color = titleColor,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 10.dp, start = 10.dp)
            )

            //---------------Name Box

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 0.dp)
                        .weight(2f)
                ) {
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
                        modifier = Modifier.size(30.dp)
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
                modifier = Modifier.padding(top = 0.dp, start = 10.dp)
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
            Spacer(modifier = Modifier.height(10.dp))
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
                        modifier = Modifier.weight(1f)
                    )

                }



                Divider(thickness = 0.2.dp)
                // ----- 7th Row For Action Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    verticalAlignment = Alignment.Top,
                    //horizontalArrangement = Arrangement.Start
                ) {

                    Button(
                        onClick = {

                            fun stringToMillis(
                                dateString: String,
                                timeZone: TimeZone = TimeZone.getTimeZone("Asia/Kolkata")
                            ): Long {
                                val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                                formatter.timeZone = timeZone // Set the time zone to IST

                                val date = try {
                                    formatter.parse(dateString)
                                        ?: return 0 // Return 0 if parsing fails
                                } catch (e: ParseException) {
                                    return 0 // Handle parsing errors gracefully
                                }

                                return date.time // Return milliseconds since the epoch
                            }

                            val dobMilli = stringToMillis(state.savedData[index].dob)
                            val dobInstant =
                                Instant.ofEpochMilli(dobMilli) // Instant in original time zone
                            val dobInIST =
                                dobInstant.atZone(ZoneId.of("Asia/Kolkata")) // Convert to IST
                            val dob = dobInIST.toLocalDate()
                            //val dob = LocalDate.ofEpochDay(dobMilli / 86400000)
                            val tdate = LocalDate.ofEpochDay(System.currentTimeMillis() / 86400000)
                            val period = Period.between(dob, tdate)

                            val ageYears = period.years // Number of years elapsed
                            val ageMonths =
                                period.months // Number of months elapsed (excluding years)
                            val ageDays =
                                period.days // Number of days elapsed (excluding months and years)
                            // Calculate the total days, weeks, months since birth and Born On
                            val bornOn = dob.dayOfWeek.name
                            val totalDays =
                                period.toTotalMonths() * 30.4375.toInt() // Approximate average month length
                            val totalWeeks = totalDays / 7
                            val totalMonths = period.toTotalMonths()

//                            state.savedData[index].dob.value = dob,
//                            state.savedData[index].todayDate.value = todayDate,
//                            state.savedData[index].ageYears.value = ageYears,
//                            state.savedData[index].ageMonths.value = ageMonths,
//                            state.savedData[index].ageDays.value = ageDays,
//                            state.savedData[index].bornOn.value = bornOn,
//                            state.savedData[index].totalDays.value = totalDays,
//                            state.savedData[index].totalMonths.value = totalMonths,
//                            state.savedData[index].totalWeeks.value = totalWeeks,
                            state.name.value = state.savedData[index].name
                            state.dob.value = dobMilli.changeMillisToDateStrings()
                            state.todayDate.value =
                                System.currentTimeMillis().changeMillisToDateStrings()
                            state.ageYears.value = ageYears.toString()
                            state.ageMonths.value = ageMonths.toString()
                            state.ageDays.value = ageDays.toString()
                            state.bornOn.value = bornOn
                            state.totalDays.value = totalDays.toString()
                            state.totalMonths.value = totalMonths.toString()
                            state.totalWeeks.value = totalWeeks.toString()

                            onEvent(
                                SaveDataEvent.SaveData(
                                    name = state.name.value,
                                    dob = state.dob.value,
                                    todayDate = state.todayDate.value,
                                    ageYears = state.ageYears.value,
                                    ageMonths = state.ageMonths.value,
                                    ageDays = state.ageDays.value,
                                    bornOn = state.bornOn.value,
                                    totalDays = state.totalDays.value,
                                    totalWeeks = state.totalWeeks.value,
                                    totalMonths = state.totalMonths.value,
                                )
                            )
                            onEvent(SaveDataEvent.DeleteSavedData(state.savedData[index]))
                            Toast.makeText(
                                context,
                                "Data Updated successfully",
                                Toast.LENGTH_LONG
                            ).show()


                        },
                        modifier = Modifier
                            // .height(60.dp)
                            //.width(220.dp)
                            //.fillMaxWidth()
                            .height(50.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(MainButton),
                        shape = ShapeDefaults.Medium
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Update To Today",
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Medium,
                                fontFamily = poppins,
                                //style = MaterialTheme.typography.titleSmall,
                                color = AzureMist,
                                fontSize = 12.sp
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = null,
                                tint = AzureMist,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    }

                    //------- RESET BUTTON
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = {
                            onEvent(SaveDataEvent.DeleteSavedData(state.savedData[index]))
                            Toast.makeText(
                                context,
                                "Data Deleted successfully",
                                Toast.LENGTH_LONG
                            ).show()
                        },
                        modifier = Modifier
                            // .height(60.dp)
                            //.width(220.dp)
                            //.fillMaxWidth()
                            .height(50.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(PinkDark),
                        shape = ShapeDefaults.Medium
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Delete",
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Medium,
                                fontFamily = poppins,
                                //style = MaterialTheme.typography.titleSmall,
                                color = AzureMist,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = AzureMist,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    }

                }


            }


        }


    }
}

fun Long?.changeMillisToDateStrings(): String {
    val date: LocalDate = this?.let {
        Instant
            .ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    } ?: LocalDate.now()
    return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
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
