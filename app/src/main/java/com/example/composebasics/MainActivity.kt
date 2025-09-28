package com.example.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasics.lottogame.ui.LottoScreen
import com.example.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Transparent) {
                        //ComposeArticle(modifier = Modifier.padding(innerPadding))
                        //TaskManager(modifier = Modifier.padding(innerPadding))
                        //ComposeDisplay(Modifier.padding(innerPadding))
                        //NumberGuesser(modifier = Modifier.padding(innerPadding))
                        LottoScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun ComposeArticle(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.bg_compose_background)
    Column (modifier, verticalArrangement = Arrangement.Center) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = stringResource(R.string.header),
            modifier = modifier.padding(16.dp),
            fontSize = 24.sp
        )
        Text(
            text = stringResource(R.string.first_chapter),
            modifier = modifier.padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = stringResource(R.string.second_chapter),
            modifier = modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun TaskManager(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.ic_task_completed)
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = image,
            contentDescription = "Blue checkmark surrounded by green ball for task completion message",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape).align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.completed),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
        )
        Text(
            text = stringResource(R.string.nice_work),
            fontSize = 16.sp
        )
    }
}

@Composable
fun ComposeDisplay(modifier: Modifier = Modifier){
    val text_comp_header = stringResource(R.string.text_comp_header)
    val text_comp_desc = stringResource(R.string.text_comp_desc)
    val img_comp_header = stringResource(R.string.image_comp_header)
    val img_comp_desc = stringResource(R.string.image_comp_desc)
    val row_comp_header = stringResource(R.string.row_comp_header)
    val row_comp_desc = stringResource(R.string.row_comp_desc)
    val column_comp_header = stringResource(R.string.column_comp_header)
    val column_comp_desc = stringResource(R.string.column_comp_desc)

    val color1 = Color(0xFFEADDFF)
    val color2 = Color(0xFFD0BCFF)
    val color3 = Color(0xFFB69DF8)
    val color4 = Color(0xFFF6EDFF)

    val textHeaders = listOf<String>( text_comp_header,
                                    img_comp_header,
                                    row_comp_header,
                                    column_comp_header)

    val textDesc = listOf<String>( text_comp_desc,
                                    img_comp_desc,
                                    row_comp_desc,
                                    column_comp_desc)

    val colorResources = listOf<Color>( color1, color2, color3, color4)



    Column(Modifier.fillMaxWidth()) {
        for (i in 0 until textDesc.size step 2) {
            Row(Modifier.weight(1f)) {
                for (j in 0..1) {
                    val idx = i + j
                    ComposeDescription(
                        textHeaders[idx],
                        textDesc[idx],
                        colorResources[idx],
                        Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun ComposeDescription(header: String, desc: String, color: Color, modifier: Modifier) {
    Column(modifier = modifier
        .fillMaxSize()
        .background(color = color)
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = header,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = desc,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeBasicsTheme {
        //ComposeArticle()
        //TaskManager()
        //ComposeDisplay()
        LottoScreen()
    }
}