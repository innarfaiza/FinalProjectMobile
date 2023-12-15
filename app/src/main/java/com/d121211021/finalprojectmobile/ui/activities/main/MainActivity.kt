package com.d121211021.finalprojectmobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211021.finalprojectmobile.data.models.Place
import com.d121211021.finalprojectmobile.ui.activities.detail.DetailActivity
import com.d121211021.finalprojectmobile.ui.activities.main.MainUiState
import com.d121211021.finalprojectmobile.ui.activities.main.MainViewModel
import com.d121211021.finalprojectmobile.ui.theme.FinalProjectMobileTheme
import com.d121211021.finalprojectmobile.ui.theme.backcol
import com.d121211021.finalprojectmobile.ui.theme.fullwhite

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalProjectMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backcol
                ) {
                    Column {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "Hogwarts Library",
                                    fontWeight = FontWeight.Medium,
                                )
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                            )
                        )
                        val mainViewModel: MainViewModel =
                            viewModel(factory = MainViewModel.Factory)
                        ListPlaceScreen(mainViewModel.mainUiState)
                    }
                }
            }
        }
    }

    @Composable
    fun ListPlaceScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (mainUiState) {
                is MainUiState.Loading -> Text(
                    text = "Loading ...",
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.Center)
                )

                is MainUiState.Error -> Text(
                    text = "Error Found",
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.Center)
                )

                is MainUiState.Success -> PlaceList(
                    place = mainUiState.place,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

    @Composable
    fun PlaceList(place: List<Place>, modifier: Modifier = Modifier) {
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(2),
        ) {
            items(place) { place ->
                PlaceItem(place = place)
            }
        }
    }

    @Composable
    fun PlaceItem(place: Place) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp),)
                .clickable {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("PLACE", place)
                    startActivity(intent)
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(245.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(place.webformatURL.toString())
                        .crossfade(true)
                        .build(), contentDescription = place.tags,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.extraSmall),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${place.user.toString()}'s",
                    color= fullwhite,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            //like, comment, collections

            //like, comment, collections end
        }

    }
}