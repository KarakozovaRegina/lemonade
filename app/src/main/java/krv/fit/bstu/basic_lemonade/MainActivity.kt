package krv.fit.bstu.basic_lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import krv.fit.bstu.basic_lemonade.ui.theme.BasiclemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasiclemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    LemonadeLayout()
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LemonadeLayout(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
) {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFF5E568)
                )


            )
        }

        ){ innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
           when(currentStep){
               1-> LemonadeImageAndText(
                   textLabelResourceId = R.string.lemon_tree,
                   drawableResourceId = R.drawable.lemon_tree,
                   contentDescriptionResourceId = R.string.lemon_tree,
                   onImageClick = {
                       currentStep = 2
                       squeezeCount = (2..4).random()

                   }
               )
               2-> LemonadeImageAndText(
                   textLabelResourceId = R.string.lemon,
                   drawableResourceId = R.drawable.lemon_squeeze,
                   contentDescriptionResourceId = R.string.lemon,
                   onImageClick = {
                       squeezeCount--

                       if(squeezeCount==0){
                           currentStep = 3
                       }

                   }
               )
               3-> LemonadeImageAndText(
                   textLabelResourceId = R.string.glass_of_lemonade,
                   drawableResourceId = R.drawable.lemon_drink,
                   contentDescriptionResourceId = R.string.glass_of_lemonade,
                   onImageClick = {
                           currentStep = 4

                   }
               )
               4-> LemonadeImageAndText(
                   textLabelResourceId = R.string.empty_glass,
                   drawableResourceId = R.drawable.lemon_restart,
                   contentDescriptionResourceId = R.string.empty_glass,
                   onImageClick = {
                       currentStep = 1

                   }
               )
           }

        }

    }


}

@Composable
fun LemonadeImageAndText(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier

){
    Box(modifier = modifier){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            )
        {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCBEBD4))
            ) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(contentDescriptionResourceId),
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                )
            }

            Spacer(Modifier.height(32.dp))


            Text(
                stringResource(textLabelResourceId),
                style = TextStyle(
                    fontSize = 18.sp
                )
            )

        }
    }
}