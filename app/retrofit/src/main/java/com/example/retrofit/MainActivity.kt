package com.example.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier, myViewModel: MyViewModel = viewModel()) {
    val hits = myViewModel.wikiUiState

    Column(modifier = modifier.padding(16.dp)) {
        Text("Finnish Presidents")
        Spacer(modifier = Modifier.height(16.dp))

        DataProvider.presidents.forEach { president ->
            Text(
                text = "${president.name} (${president.startDuty}-${president.endDuty})",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        myViewModel.getHits(president.name)
                    }
                    .padding(vertical = 8.dp),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Wikipedia Hit Count: $hits",
        )
    }
}
