package com.example.simple_age_calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.simple_age_calculator.data.local.SaveDataDatabase
import com.example.simple_age_calculator.models.SavedViewModal.SaveDataViewModal
import com.example.simple_age_calculator.models.SetupNavGraph
import com.example.simple_age_calculator.ui.theme.BottomSheetColor
import com.example.simple_age_calculator.ui.theme.Simple_Age_CalculatorTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            SaveDataDatabase::class.java,
            name = "savedata.db"
        ).build()
    }

    private val viewModal by viewModels<SaveDataViewModal>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SaveDataViewModal(database.saveDataDao) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Simple_Age_CalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BottomSheetColor
                    //color = MaterialTheme.colorScheme.background
                ) {
                    val state by viewModal.state.collectAsState()
                    navController = rememberNavController()
                    SetupNavGraph(
                        navController = navController,
                        state = state,
                        onEvent = viewModal::onEvent
                    )
                    //HomeScreen(onNavigateToResult = {})
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Simple_Age_CalculatorTheme {
        HomeScreen(navController = rememberNavController())
    }
}