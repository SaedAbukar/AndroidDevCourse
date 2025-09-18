import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasics.R
import com.example.firstandroidapp.ui.theme.FirstAndroidAppTheme

val FONTSIZE = 60.sp
val LINEHEIGHT = 80.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstAndroidAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Transparent) {
                        Greeting(
                            name = "Saed",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Log.d("Tester123", "Greeting()")
    val image = painterResource(R.drawable.androidparty)
    Box(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        Column (verticalArrangement = Arrangement.Center){
            GreetingRow(name, modifier.padding(50.dp))
            ButtonPart(modifier
                .padding(50.dp)
                .align(alignment = Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun GreetingRow(name: String, modifier: Modifier) {
    Log.d("Tester123", "GreetingRow()")
    Row {
        Text(
            text = stringResource(R.string.hello, name),
            modifier = modifier,
            color = Color.Black,
            fontSize = FONTSIZE,
            lineHeight = LINEHEIGHT,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ButtonPart(modifier: Modifier) {
    Log.d("Tester123", "ButtonPart()")
    var c by rememberSaveable { mutableIntStateOf(0) }
    if (c < -5) c = -5
    if (c > 10) c = 10
    Row (modifier) {
        Button({
            c++
            Log.d("Tester123", "onClick() c = $c")
        }) { Text("+", fontSize = FONTSIZE, lineHeight = LINEHEIGHT)}
        Text("$c", modifier, color = Color.Black, fontSize = FONTSIZE, lineHeight = LINEHEIGHT)
        Button({
            c--
            Log.d("Tester123", "onClick() c = $c")
        }) { Text("-", fontSize = FONTSIZE, lineHeight = LINEHEIGHT)}
    }
    }

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstAndroidAppTheme {
        Greeting("Android")
    }
}