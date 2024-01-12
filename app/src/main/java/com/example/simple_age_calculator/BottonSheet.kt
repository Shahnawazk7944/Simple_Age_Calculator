package com.example.simple_age_calculator

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.simple_age_calculator.models.Screen
import com.example.simple_age_calculator.ui.theme.AzureMist
import com.example.simple_age_calculator.ui.theme.BlueMain
import com.example.simple_age_calculator.ui.theme.BottomSheetColor
import com.example.simple_age_calculator.ui.theme.ChocoMain
import com.example.simple_age_calculator.ui.theme.MainButton
import com.example.simple_age_calculator.ui.theme.ResetButton
import com.example.simple_age_calculator.ui.theme.TextFieldColor
import com.example.simple_age_calculator.ui.theme.playFairFamily
import com.example.simple_age_calculator.ui.theme.poppins
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(navController: NavController, hideSheet: () -> Unit) {
    val scope = rememberCoroutineScope()
    val snackBarState = remember {
        SnackbarHostState()
    }
    SnackbarHost(hostState = snackBarState)
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
            text = "Age Calculator",
            fontFamily = playFairFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            color = BlueMain,
        )


        //----------Birth Date
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Date Of Birth",
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
                .clickable {
                    isBirthDatePickerOpen = true
                },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp, end = 7.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = birthDatePlaceHolder,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color.Gray,
                )
                IconButton(onClick = { isBirthDatePickerOpen = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = "cal",
                        Modifier.size(25.dp)
                    )

                }

            }
        }


        //----------Today's Date
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Today's Date",
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
                .clickable { isTodayDatePickerOpen = true },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp, end = 7.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = todayDatePlaceHolder,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color.Gray,
                )
                IconButton(onClick = { isTodayDatePickerOpen = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = "cal",
                        Modifier.size(25.dp)
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

                    dob == null -> scope.launch {
                        snackBarState.showSnackbar(
                            message = "Please enter Date of birth",
                            actionLabel = "Retry",
                            duration = SnackbarDuration.Short
                        )
                    }

                    tDate == null -> scope.launch {
                        snackBarState.showSnackbar(
                            message = "Please enter Today's Date",
                            actionLabel = "Retry",
                            duration = SnackbarDuration.Short
                        )
                    }
                    dob == null && tDate == null -> scope.launch {
                        snackBarState.showSnackbar(
                            message = "Both dates are required",
                            actionLabel = "Retry",
                            duration = SnackbarDuration.Short
                        )
                    }
                    else -> {
                        val dBirth = LocalDate.ofEpochDay(dob / 86400000)
                        val tdate = LocalDate.ofEpochDay(tDate / 86400000)
                        val period = Period.between(dBirth, tdate)
                        val ageYears = period.years // Number of years elapsed
                        val ageMonths = period.months // Number of months elapsed (excluding years)
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
                birthDatePlaceHolder = "DD - MM - YYYY"
                todayDatePlaceHolder = "DD - MM - YYYY"
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
    BottomSheet(navController = rememberNavController(), hideSheet = {})
}