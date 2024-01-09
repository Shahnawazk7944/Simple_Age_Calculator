package com.example.simple_age_calculator

import android.widget.DatePicker
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDate(
    state: DatePickerState,
    isOpen: Boolean,
    onConfirmText: String = "Select",
    onDismissText: String = "Cancel",
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
   if(isOpen){
       DatePickerDialog(
           onDismissRequest = {onDismiss()},
           confirmButton = {
               TextButton(onClick = { onConfirm() })
               {
                   Text(text = onConfirmText)
               } },
           dismissButton = {  TextButton(onClick = { onDismiss() })
           {
               Text(text = onDismissText)
           } },
           content = {
               DatePicker(
                   state = state,
                   dateValidator = { timestamp ->
                       val selectedDate = Instant
                           .ofEpochMilli(timestamp)
                           .atZone(ZoneId.systemDefault())
                           .toLocalDate()
                       val currentDate = LocalDate.now(ZoneId.systemDefault())
                       selectedDate<=currentDate
                   }
               )
           }
       )
   }
}
