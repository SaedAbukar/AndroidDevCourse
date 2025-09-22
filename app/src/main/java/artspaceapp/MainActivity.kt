package artspaceapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import artspaceapp.ui.theme.ComposeBasicsTheme
import com.example.composebasics.R
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(name: String, modifier: Modifier = Modifier) {
    var currentPhase by rememberSaveable { mutableIntStateOf(1) }

    val currentImage = when (currentPhase) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    val currentText = when (currentPhase) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.lemonade_glass
        4 -> R.string.empty_glass
        else -> R.string.lemon_tree
    }

    val currentArtist = when (currentPhase) {
        1 -> R.string.cr1
        2 -> R.string.cr2
        3 -> R.string.cr3
        4 -> R.string.cr4
        else -> R.string.cr1
    }

    val currentYear = when (currentPhase) {
        1 -> R.string.yr1
        2 -> R.string.yr2
        3 -> R.string.yr3
        4 -> R.string.yr4
        else -> R.string.yr1
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Box(modifier = Modifier.background(Color.White).shadow(3.dp, RectangleShape).padding(16.dp)) {
            Image(
                modifier = Modifier.padding(16.dp).padding(32.dp),
                painter = painterResource(currentImage),
                contentDescription = stringResource(currentArtist)
            )
        }
        Spacer(
            modifier = Modifier.height(46.dp)
        )
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp).background(Color.hsl(230.0f, 0.53f, 0.79f, 0.3f))) {
            Text(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                text = stringResource(currentText),
                textAlign = TextAlign.Left,
                fontSize = 32.sp,
                fontWeight = FontWeight.Light
            )
            Row {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp, start = 16.dp),
                    text = stringResource(currentArtist),
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(bottom = 16.dp, start = 4.dp, end = 16.dp),
                    text = "(" + stringResource(currentYear) + ")",
                    textAlign = TextAlign.Left,
                )
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (currentPhase <= 1) currentPhase = 4 else currentPhase--;Log.d("123456", "currentphase: $currentPhase")
                },
                modifier = Modifier.width(120.dp)
            ) {
                Text(
                    text = stringResource(R.string.prev)
                )
            }
            Button(
                onClick = {
                    if (currentPhase >= 4) currentPhase = 1 else currentPhase++;Log.d("123456", "currentphase: $currentPhase")
                },
                modifier = Modifier.width(120.dp)
            ) {
                Text(
                    text = stringResource(R.string.next),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ComposeBasicsTheme {
        ArtSpaceApp("Android")
    }
}