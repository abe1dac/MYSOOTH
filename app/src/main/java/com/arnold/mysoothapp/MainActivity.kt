package com.arnold.mysoothapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arnold.mysoothapp.ui.theme.MySoothAppTheme
import androidx.compose.ui.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySoothAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                   // SearchBar(modifier = Modifier.padding(innerPadding))
                   // MyApp()
                    MyLevel(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MyLevel(modifier: Modifier = Modifier) {
    Column {
        SearchBar()
        Spacer(modifier = Modifier.height(20.dp))
        MyApp()
        Spacer(modifier = Modifier.height(20.dp))
        AlignYourBodyElement(drawable = R.drawable.mms, text = R.string.Inversion,
            modifier = Modifier.padding(8.dp))
        Spacer(modifier = Modifier.height(20.dp))
        AlignYourBodyRow()
        Spacer(modifier = Modifier.height(20.dp))
        FavoriteCollectionCard(drawable = R.drawable.mms1,
            text = R.string.nature_meditation,
            modifier = Modifier.padding(8.dp))
        Spacer(modifier = Modifier.height(20.dp))
        FavoriteCollectionsGrid()

    }


}


@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(item.drawable, item.text)
        }
    }
}
// Define data class
data class AlignYourBodyItem(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

// Sample data
val alignYourBodyData = listOf(
    AlignYourBodyItem(R.drawable.mms, R.string.Inversion),
    AlignYourBodyItem(R.drawable.mm2, R.string.Inversion),
    AlignYourBodyItem(R.drawable.mm4, R.string.nature_meditation),
    AlignYourBodyItem(R.drawable.mms, R.string.Inversion),
    AlignYourBodyItem(R.drawable.mm2, R.string.Inversion),
    AlignYourBodyItem(R.drawable.mm4, R.string.nature_meditation)
    // Add more items as needed
)

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Row {
                Text(stringResource(R.string.PlaceHOlder_search))
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)

    )
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("World", "Compose")
) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}
@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Column {
                Text(
                    text = stringResource(text),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Text(text = "Welcome to meditation")
            }
            
        }
    }
}
@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        /*items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(item.drawable, item.text, Modifier.height(80.dp))*/
        items(favoriteCollectionsData){ item ->
            FavoriteCollectionCard(item.drawable, item.text, Modifier.height(80.dp))
        }
    }
}
// Data class definition
data class FavoriteCollectionItem(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

// Sample data
val favoriteCollectionsData = listOf(
    FavoriteCollectionItem(R.drawable.mms1, R.string.nature_meditation),
    FavoriteCollectionItem(R.drawable.mn3, R.string.nature_meditation2),
    FavoriteCollectionItem(R.drawable.mns2, R.string.nature_meditation3),
    FavoriteCollectionItem(R.drawable.mms1, R.string.nature_meditation),
    FavoriteCollectionItem(R.drawable.mn3, R.string.nature_meditation2),
    FavoriteCollectionItem(R.drawable.mns2, R.string.nature_meditation3),
    FavoriteCollectionItem(R.drawable.mms1, R.string.nature_meditation)

)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello ")
                Text(text = name)
            }
            ElevatedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}