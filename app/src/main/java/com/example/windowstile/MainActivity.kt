package com.example.windowstile

import android.os.Bundle
import android.service.quicksettings.Tile
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.windowstile.ui.theme.WindowsTileTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WindowsTileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

//                    LazyVerticalGrid(
//                        cells = GridCells.Fixed(3),
//                        modifier = Modifier.padding(8.dp),
//                    ) {
//                        items(5){
//                            WindowTile()
//                        }
//                    }

                    WindowTile(images = listOf(
                        "https://demo.sirv.com/nuphar.jpg?w=400",
                        "https://demo.sirv.com/looks.jpg?w=400"
                    ))

                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WindowTile(images: List<String>) {

    var currentItemIndex by remember { mutableStateOf(0) }
    val size = images.size

    Column(modifier = Modifier) {
        Surface(
            modifier = Modifier
                .padding(2.dp)
                .size(120.dp)
        ) {
            AnimatedContent(
                targetState = currentItemIndex,
                transitionSpec = {
                    slideInVertically(animationSpec = TweenSpec(1000, easing = FastOutSlowInEasing), initialOffsetY =  { height -> height })
                        .with(slideOutVertically(animationSpec = TweenSpec(1000, easing = FastOutSlowInEasing), targetOffsetY = { height -> -height}))
                }
            ) { index ->
                AsyncImage(
                    model = images[index%size],
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "null"
                )
            }
        }

        Button(onClick = { currentItemIndex++ }) {
            Text(text = "change img")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WindowsTileTheme {
        
    }
}