package clickbehaviour

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.example.composebasics.R
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import clickbehaviour.ui.theme.ComposeBasicsTheme
import java.lang.Math.random
import kotlin.math.log
import kotlin.math.round
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeClicker(
                        modifier = Modifier.padding(innerPadding).fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun LemonadeClicker(modifier: Modifier = Modifier) {
    var currentPhase by remember { mutableIntStateOf(1) }
    var phaseTwoClicker by remember { mutableIntStateOf(0) }
    var requiredClicks by remember { mutableIntStateOf(0) }

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

    val currentImageDesc = when (currentPhase) {
        1 -> R.string.lemon_tree_desc
        2 -> R.string.lemon_desc
        3 -> R.string.lemonade_glass_desc
        4 -> R.string.empty_glass_desc
        else -> R.string.lemon_tree_desc
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.lemonade),
            color = Color.Black,
            modifier = Modifier.background(Color.Yellow).fillMaxWidth().padding(16.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(200.dp))
        Image(
            painter = painterResource(currentImage),
            contentDescription = stringResource(currentImageDesc),
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .clickable {
                    when (currentPhase) {
                        1 -> {
                            currentPhase = 2
                            requiredClicks = Random.nextInt(2, 5)
                            phaseTwoClicker = 0
                            Log.d("Image123", "Required clicks: $requiredClicks")
                        }
                        2 -> {
                            phaseTwoClicker++
                            Log.d("Image123", "Phase two clicker: $phaseTwoClicker")
                            if (phaseTwoClicker >= requiredClicks) {
                                currentPhase = 3
                            }
                        }
                        3 -> currentPhase = 4
                        4 -> currentPhase = 1
                    }
                    Log.d("Image123", "current phase: $currentPhase")
                    Log.d("Image123", "current image: $currentImage")
                }
                .background(Color(0f, 210f, 0f, 0.21f))
                .padding(25.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = stringResource(currentText))
    }
}


@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    ComposeBasicsTheme {
        LemonadeClicker(modifier = Modifier)
    }
}