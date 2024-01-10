package com.example.simple_age_calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simple_age_calculator.ui.theme.AzureMist
import com.example.simple_age_calculator.ui.theme.ChocolateBrown
import com.example.simple_age_calculator.ui.theme.MainButton
import com.example.simple_age_calculator.ui.theme.poppins

@Composable
fun CalculateAge() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AzureMist)
            .padding(start = 10.dp, end = 10.dp, top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        //--------------- RESULT Heading
        Text(
            text = "Results",
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Medium,
            fontFamily = poppins,
            //style = MaterialTheme.typography.titleSmall,
            color = ChocolateBrown,
            fontSize = 25.sp
        )


        //--------------- YOUR AGE BOX
        Spacer(modifier = Modifier.height(60.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(AzureMist)
                .height(100.dp)
                .padding(horizontal = 20.dp),

            ) {
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
                    color = MainButton,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 5.dp)
                )

                //--------------- AGE BOX
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "24 Years  08 Months  15 Days",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppins,
                        color = Color.Gray,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            }
        }
        //----AGE BOX END


        //--------------- More Details
        Spacer(modifier = Modifier.height(40.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(AzureMist)

        ){
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ){

            }
        }


    }


}

@Preview(showBackground = true)
@Composable
fun CalculateAgePreview() {
    CalculateAge()
}