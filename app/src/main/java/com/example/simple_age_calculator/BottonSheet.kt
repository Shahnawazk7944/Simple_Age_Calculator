package com.example.simple_age_calculator

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simple_age_calculator.ui.theme.AzureMist
import com.example.simple_age_calculator.ui.theme.BlueMain
import com.example.simple_age_calculator.ui.theme.ChocoMain
import com.example.simple_age_calculator.ui.theme.MainButton
import com.example.simple_age_calculator.ui.theme.ResetButton
import com.example.simple_age_calculator.ui.theme.TextFieldColor
import com.example.simple_age_calculator.ui.theme.playFairFamily

@Composable
fun BottomSheet() {
    Column(
        Modifier
            .fillMaxSize()
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
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Date Of Birth",
            fontFamily = playFairFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = ChocoMain,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(TextFieldColor)
                .height(20.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "23 - 08 - 2000",
                    fontFamily = playFairFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = "cal",
                        Modifier.size(15.dp)
                    )

                }

            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Today's Date",
            fontFamily = playFairFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = ChocoMain,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(TextFieldColor)
                .height(20.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "DD - MM - YYYY",
                    fontFamily = playFairFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = "cal",
                        Modifier.size(15.dp)
                    )

                }

            }
        }


        //-------- CALCULATE BUTTON
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {  },
            modifier = Modifier
                // .height(60.dp)
                //.width(220.dp)
                .fillMaxWidth().height(20.dp),
            colors = ButtonDefaults.buttonColors(MainButton),
            shape = ShapeDefaults.Small
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Calculate",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontFamily = playFairFamily,
                    //style = MaterialTheme.typography.titleSmall,
                    color = AzureMist,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = AzureMist
                )
            }
        }

        //------- RESET BUTTON
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {  },
            modifier = Modifier
                // .height(60.dp)
                //.width(220.dp)
                .fillMaxWidth().height(20.dp),
            colors = ButtonDefaults.buttonColors(ResetButton),
            shape = ShapeDefaults.Small
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "RESET",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontFamily = playFairFamily,
                    //style = MaterialTheme.typography.titleSmall,
                    color = AzureMist,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = AzureMist
                )
            }
        }


    }
}