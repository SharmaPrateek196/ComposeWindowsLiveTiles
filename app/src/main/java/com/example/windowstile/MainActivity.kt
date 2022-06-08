package com.example.windowstile

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import com.example.windowstile.ui.theme.WindowsTileTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WindowsTileTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    LazyVerticalGrid(
                        cells = GridCells.Fixed(3),
                        modifier = Modifier.padding(8.dp),
                    ) {
                        getImagesLists().let { list ->
                            list.forEach {
                                item {
                                    WindowTile(images = it.list, it.delay)
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WindowTile(images: List<String>, delay: Long) {

    var currentItemIndex by remember { mutableStateOf(0) }
    val size = images.size

    val handler = Handler(Looper.getMainLooper())

    handler.post(
        object : Runnable {
            override fun run() {
                currentItemIndex++
                if(delay>0) handler.postDelayed(this, delay)
            }
        }
    )

    Surface(
        modifier = Modifier
            .padding(2.dp)
            .size(120.dp)
    ) {
        AnimatedContent(
            targetState = currentItemIndex,
            transitionSpec = {
                slideInVertically(animationSpec = TweenSpec(1800, easing = FastOutSlowInEasing), initialOffsetY =  { height -> height })
                    .with(slideOutVertically(animationSpec = TweenSpec(1800, easing = FastOutSlowInEasing), targetOffsetY = { height -> -height}))
            }
        ) { index ->
            AsyncImage(
                model = images[index%size],
                contentScale = ContentScale.FillBounds,
                contentDescription = "null"
            )
        }
    }
}

fun getImagesLists(): List<TileItem> {
    return listOf(
        TileItem(
            listOf(
                "https://demo.sirv.com/nuphar.jpg?w=400",
                "https://images.pexels.com/photos/7475822/pexels-photo-7475822.jpeg?cs=srgb&dl=pexels-printexstar-11625335.jpg&fm=jpg&w=640&h=427"
            ),
            3500
        ),
        TileItem(
            listOf(
                "https://images.pexels.com/photos/8378108/pexels-photo-8378108.jpeg?cs=srgb&dl=pexels-raquel-8378108.jpg&fm=jpg&w=640&h=960",
                "https://images.pexels.com/photos/11625335/pexels-photo-11625335.jpeg?cs=srgb&dl=pexels-printexstar-11625335.jpg&fm=jpg&w=640&h=427"
            ),
            0
        ),
        TileItem(
            listOf(
                "https://images.pexels.com/photos/4792719/pexels-photo-4792719.jpeg?cs=srgb&dl=pexels-anete-lusina-4792719.jpg&fm=jpg&w=640&h=427",
                "https://demo.sirv.com/nuphar.jpg?w=400",
            ),
            4500
        ),
        TileItem(
            listOf(
                "https://images.pexels.com/photos/4631086/pexels-photo-4631086.jpeg?cs=srgb&dl=pexels-printexstar-11625335.jpg&fm=jpg&w=640&h=427",
                "https://images.pexels.com/photos/11625335/pexels-photo-11625335.jpeg?cs=srgb&dl=pexels-printexstar-11625335.jpg&fm=jpg&w=640&h=427"
            ),
            0
        ),TileItem(
            listOf(
                "https://images.pexels.com/photos/7475822/pexels-photo-7475822.jpeg?cs=srgb&dl=pexels-printexstar-11625335.jpg&fm=jpg&w=640&h=427",
                "https://images.pexels.com/photos/11625335/pexels-photo-11625335.jpeg?cs=srgb&dl=pexels-printexstar-11625335.jpg&fm=jpg&w=640&h=427"
            ),
            0
        ),
        TileItem(
            listOf(
                "https://images.pexels.com/photos/10584840/pexels-photo-10584840.jpeg?cs=srgb&dl=pexels-adam-cole-10584840.jpg&fm=jpg&w=640&h=427",
                "https://images.pexels.com/photos/11625335/pexels-photo-11625335.jpeg?cs=srgb&dl=pexels-printexstar-11625335.jpg&fm=jpg&w=640&h=427"
            ),
            9000
        ),TileItem(
            listOf(
                "https://demo.sirv.com/nuphar.jpg?w=400",
                "https://images.pexels.com/photos/8378108/pexels-photo-8378108.jpeg?cs=srgb&dl=pexels-raquel-8378108.jpg&fm=jpg&w=640&h=960"
            ),
            0
        ),
        TileItem(
            listOf(
                "https://images.pexels.com/photos/4631086/pexels-photo-4631086.jpeg?cs=srgb&dl=pexels-printexstar-11625335.jpg&fm=jpg&w=640&h=427",
                "https://images.pexels.com/photos/8378108/pexels-photo-8378108.jpeg?cs=srgb&dl=pexels-raquel-8378108.jpg&fm=jpg&w=640&h=960"
            ),
            5500
        ),
        TileItem(
            listOf(
                "https://images.pexels.com/photos/4631086/pexels-photo-4631086.jpeg?cs=srgb&dl=pexels-printexstar-11625335.jpg&fm=jpg&w=640&h=427",
                "https://images.pexels.com/photos/11625335/pexels-photo-11625335.jpeg?cs=srgb&dl=pexels-printexstar-11625335.jpg&fm=jpg&w=640&h=427"
            ),
            0
        ),
        TileItem(
            listOf(
                "https://demo.sirv.com/nuphar.jpg?w=400",
                "https://images.pexels.com/photos/4631086/pexels-photo-4631086.jpeg?cs=srgb&dl=pexels-printexstar-11625335.jpg&fm=jpg&w=640&h=427"
            ),
            3500
        ),
        TileItem(
            listOf(
                "https://demo.sirv.com/nuphar.jpg?w=400",
                "https://images.pexels.com/photos/10584840/pexels-photo-10584840.jpeg?cs=srgb&dl=pexels-adam-cole-10584840.jpg&fm=jpg&w=640&h=427"
            ),
            0
        ),
        TileItem(
            listOf(
                "https://images.pexels.com/photos/4631086/pexels-photo-4631086.jpeg?cs=srgb&dl=pexels-printexstar-11625335.jpg&fm=jpg&w=640&h=427",
                "https://images.pexels.com/photos/11625335/pexels-photo-11625335.jpeg?cs=srgb&dl=pexels-printexstar-11625335.jpg&fm=jpg&w=640&h=427"
            ),
            6500
        ),TileItem(
            listOf(
                "https://demo.sirv.com/nuphar.jpg?w=400",
                "https://images.pexels.com/photos/8378108/pexels-photo-8378108.jpeg?cs=srgb&dl=pexels-raquel-8378108.jpg&fm=jpg&w=640&h=960"
            ),
            0
        ),TileItem(
            listOf(
                "https://images.pexels.com/photos/4631086/pexels-photo-4631086.jpeg?cs=srgb&dl=pexels-printexstar-11625335.jpg&fm=jpg&w=640&h=427",
                "https://images.pexels.com/photos/11625335/pexels-photo-11625335.jpeg?cs=srgb&dl=pexels-printexstar-11625335.jpg&fm=jpg&w=640&h=427"
            ),
            0
        ),
        TileItem(
            listOf(
                "https://demo.sirv.com/nuphar.jpg?w=400",
                "https://images.pexels.com/photos/10584840/pexels-photo-10584840.jpeg?cs=srgb&dl=pexels-adam-cole-10584840.jpg&fm=jpg&w=640&h=427"
            ),
            7200
        ),
        TileItem(
            listOf(
                "https://demo.sirv.com/nuphar.jpg?w=400",
                "https://images.pexels.com/photos/8378108/pexels-photo-8378108.jpeg?cs=srgb&dl=pexels-raquel-8378108.jpg&fm=jpg&w=640&h=960"
            ),
            0
        ),
        TileItem(
            listOf(
                "https://demo.sirv.com/nuphar.jpg?w=400",
                "https://images.pexels.com/photos/8378108/pexels-photo-8378108.jpeg?cs=srgb&dl=pexels-raquel-8378108.jpg&fm=jpg&w=640&h=960"
            ),
            3300
        ),
        TileItem(
            listOf(
                "https://demo.sirv.com/nuphar.jpg?w=400",
                "https://images.pexels.com/photos/8378108/pexels-photo-8378108.jpeg?cs=srgb&dl=pexels-raquel-8378108.jpg&fm=jpg&w=640&h=960"
            ),
            0
        ),
        TileItem(
            listOf(
                "https://demo.sirv.com/nuphar.jpg?w=400",
                "https://images.pexels.com/photos/8378108/pexels-photo-8378108.jpeg?cs=srgb&dl=pexels-raquel-8378108.jpg&fm=jpg&w=640&h=960"
            ),
            7000
        ),
    )
}

data class TileItem(
    val list: List<String>,
    val delay: Long
)