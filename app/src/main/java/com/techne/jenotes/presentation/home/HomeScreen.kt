package com.techne.jenotes.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerticalAlignBottom
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.techne.jenotes.presentation.common.GradientText
import com.techne.jenotes.presentation.ui.theme.bgColor
import com.techne.jenotes.presentation.ui.theme.fieldColor
import com.techne.jenotes.presentation.ui.theme.greyText

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            GradientText(text = "My Notes", fontSize = 25.sp)

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 30.dp, end = 30.dp, bottom = 5.dp)
            ) {
                Text(
                    text = "Sort By",
                    color = bgColor,
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Date",
                        color = bgColor,
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = Icons.Default.VerticalAlignBottom,
                        contentDescription = null,
                        tint = bgColor
                    )
                }
            }
            Box(
                Modifier
                    .padding(horizontal = 30.dp)
                    .height(0.5.dp)
                    .fillMaxWidth()
                    .background(color = bgColor)
            )

            NotesFolderList(items = mutableListOf("All", "Faith", "Cooking", "Psychology"))
        }
    }
}


@Composable
fun NotesFolderList(items: List<String>) {
    var clickedItem by remember { mutableStateOf<String?>(null) }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(items) { item ->
            FolderItem(
                folder = item,
                isClicked = item == clickedItem,
                onClick = { clickedItem = if (clickedItem == item) null else item }
            )
        }
    }
}

@Composable
fun FolderItem(folder: String, isClicked: Boolean, onClick: () -> Unit) {
    val containerColor = if (isClicked) bgColor else fieldColor
    val textColor = if (isClicked) Color.White else Color.Black


    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 2.dp, vertical = 20.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(containerColor)
            .clickable { onClick()},
        contentAlignment = Alignment.Center,

    ) {
        Text(
            text = folder,
            color = textColor,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)
        )
    }
}