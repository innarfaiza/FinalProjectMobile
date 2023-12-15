package com.d121211021.finalprojectmobile.ui.activities.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211021.finalprojectmobile.data.models.Place
import com.d121211021.finalprojectmobile.ui.theme.FinalProjectMobileTheme

class DetailActivity : ComponentActivity() {
    private var selectedPlace: Place? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedPlace = intent.getParcelableExtra("PLACE")
        setContent {
            FinalProjectMobileTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    DetailScreen()
                }
            }
        }
    }

    @Composable
    fun DetailScreen() {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ){
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(selectedPlace?.webformatURL.toString())
                    .crossfade(true)
                    .build(), contentDescription = selectedPlace?.tags.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            //card user start
            Surface(
                shape = MaterialTheme.shapes.small,
//                color = MaterialTheme.colorScheme.surfaceVariant,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.width(250.dp),
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(selectedPlace?.userImageURL.toString())
                            .crossfade(true)
                            .build(), contentDescription = selectedPlace?.tags.toString(),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(100.dp).clip(shape)
                    )
                    Text(
                        text = selectedPlace?.user.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }//card user end
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Liked by: ${selectedPlace?.likes.toString()} people",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${selectedPlace?.comments.toString()} comments",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Related tags: ${selectedPlace?.tags.toString()}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Has been downloaded: ${selectedPlace?.downloads.toString()} times",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}