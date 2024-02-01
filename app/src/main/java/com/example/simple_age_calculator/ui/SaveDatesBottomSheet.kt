package com.example.simple_age_calculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.simple_age_calculator.R
import com.example.simple_age_calculator.SelectDate
import com.example.simple_age_calculator.models.Screen
import com.example.simple_age_calculator.ui.theme.AzureMist
import com.example.simple_age_calculator.ui.theme.BlueMain
import com.example.simple_age_calculator.ui.theme.BottomSheetColor
import com.example.simple_age_calculator.ui.theme.ChocoMain
import com.example.simple_age_calculator.ui.theme.MainButton
import com.example.simple_age_calculator.ui.theme.PinkDark
import com.example.simple_age_calculator.ui.theme.ResetButton
import com.example.simple_age_calculator.ui.theme.TextFieldColor
import com.example.simple_age_calculator.ui.theme.playFairFamily
import com.example.simple_age_calculator.ui.theme.poppins
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.RoundedCorner
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveDatesBottomSheet(
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
    hideSheet: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val snackBarState = remember {
        SnackbarHostState()
    }
    var nameState by remember {
        mutableStateOf("")
    }
    SnackbarHost(hostState = snackBarState) {
        Snackbar(
            snackbarData = it,
            containerColor = BlueMain,
            contentColor = PinkDark,
        )
    }
    var showBottomSheet by remember { mutableStateOf(false) }
    var isBirthDatePickerOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var isTodayDatePickerOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var birthDatePickerState = rememberDatePickerState(
    )
    var todayDatePickerState = rememberDatePickerState(
        //initialSelectedDateMillis = Instant.now().toEpochMilli()
    )

    var birthDatePlaceHolder by remember {
        mutableStateOf("DD-MM-YYYY")
    }
    var todayDatePlaceHolder by remember {
        mutableStateOf("DD-MM-YYYY")
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(BottomSheetColor)
            .padding(start = 25.dp, end = 25.dp, top = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Save Data",
            fontFamily = playFairFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            color = BlueMain,
        )


        //----------Birth Date
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Name",
            fontFamily = poppins,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = ChocoMain,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(TextFieldColor)
                .height(50.dp)
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(12.dp))
        ) {


            OutlinedTextField(
                value = nameState,
                onValueChange = {
                    nameState = it
                },
                modifier = Modifier
                    //.background(TextFieldColor)
                    .fillMaxSize(),
                placeholder = {
                    Text(
                        "Enter your name",
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = TextFieldColor,
                    unfocusedContainerColor = TextFieldColor,
                    //cursorColor = Color.Green,
                )


            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
//                .padding(horizontal = 20.dp)
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
                    color = Color.Gray,
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
                        color = BlueMain,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            }
        }


        //-------- CALCULATE BUTTON
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                val dob = birthDatePickerState.selectedDateMillis
                val tDate = todayDatePickerState.selectedDateMillis
                when {

                    dob == null && tDate != null -> scope.launch {
                        snackBarState.showSnackbar(
                            message = "Please enter Date of birth.",
                            actionLabel = "Retry",
                            duration = SnackbarDuration.Short
                        )
                    }

                    tDate == null && dob != null -> scope.launch {
                        snackBarState.showSnackbar(
                            message = "Please enter Today's Date.",
                            actionLabel = "Retry",
                            duration = SnackbarDuration.Short
                        )
                    }

                    dob == null && tDate == null -> scope.launch {
                        snackBarState.showSnackbar(
                            message = "Both dates are required.",
                            actionLabel = "Retry",
                            duration = SnackbarDuration.Short
                        )
                    }

                    dob != null && tDate != null -> {
                        val dBirth = LocalDate.ofEpochDay(dob / 86400000)
                        val tdate = LocalDate.ofEpochDay(tDate / 86400000)
                        if (tDate > dob) {
                            val period = Period.between(dBirth, tdate)
                            val ageYears = period.years // Number of years elapsed
                            val ageMonths =
                                period.months // Number of months elapsed (excluding years)
                            val ageDays =
                                period.days // Number of days elapsed (excluding months and years)
                            // Calculate the total days, weeks, months since birth and Born On
                            val bornOn = dBirth.dayOfWeek.name
                            val totalDays =
                                period.toTotalMonths() * 30.4375.toInt() // Approximate average month length
                            val totalWeeks = totalDays / 7
                            val totalMonths = period.toTotalMonths()
                            //Log.d("hope working","$ageYears $ageMonths $ageDays $bornOn $totalDays $totalWeeks $totalMonths")
                            navController.navigate(
                                route = Screen.Result.passAgeData(
                                    dob = birthDatePickerState.selectedDateMillis.changeMillisToDateString(),
                                    todayDate = todayDatePickerState.selectedDateMillis.changeMillisToDateString(),
                                    ageYears = ageYears,
                                    ageMonths = ageMonths,
                                    ageDays = ageDays,
                                    bornOn = bornOn,
                                    totalDays = totalDays,
                                    totalWeeks = totalWeeks,
                                    totalMonths = totalMonths
                                )
                            )
                            hideSheet()
                        } else {
                            scope.launch {
                                snackBarState.showSnackbar(
                                    message = "Today's date can't be before birth date.",
                                    actionLabel = "Retry",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    }
                }

            },
            modifier = Modifier
                // .height(60.dp)
                //.width(220.dp)
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(MainButton),
            shape = ShapeDefaults.Medium
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Calculate",
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

        //------- RESET BUTTON
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                todayDatePickerState.setSelection(null)
                birthDatePickerState.setSelection(null)
                birthDatePlaceHolder = "DD-MM-YYYY"
                todayDatePlaceHolder = "DD-MM-YYYY"
            },
            modifier = Modifier
                // .height(60.dp)
                //.width(220.dp)
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(ResetButton),
            shape = ShapeDefaults.Medium
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Reset",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppins,
                    //style = MaterialTheme.typography.titleSmall,
                    color = MainButton,
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


//          Spacer(modifier = Modifier.height(60.dp))
//            Divider(thickness = 5.dp)
    }
    // Call to Material Date Picker
    SelectDate(
        state = birthDatePickerState,
        isOpen = isBirthDatePickerOpen,
        onDismiss = { isBirthDatePickerOpen = false },
        onConfirm = {
            isBirthDatePickerOpen = false
            birthDatePlaceHolder =
                birthDatePickerState.selectedDateMillis.changeMillisToDateString()

        }
    )
    SelectDate(
        state = todayDatePickerState,
        isOpen = isTodayDatePickerOpen,
        onDismiss = { isTodayDatePickerOpen = false },
        onConfirm = {
            isTodayDatePickerOpen = false
            todayDatePlaceHolder =
                todayDatePickerState.selectedDateMillis.changeMillisToDateString()
        }
    )
}

//Extension function for to convert milli to String
fun Long?.changeMillisToDateString(): String {
    val date: LocalDate = this?.let {
        Instant
            .ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    } ?: LocalDate.now()
    return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
}


@Preview(showBackground = true)
@Composable
fun PreviewBotoomSheet() {
    SaveDatesBottomSheet(
        navController = rememberNavController(),
        hideSheet = {},
        ageDays = "",
        ageMonths = "",
        ageYears = "",
        totalMonths = "",
        totalWeeks = "",
        todayDate = "",
        totalDays = "",
        dob = "",
        bornOn = ""
    )
}