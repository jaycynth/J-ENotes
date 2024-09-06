package com.techne.jenotes.presentation.home

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VerticalAlignBottom
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.techne.jenotes.presentation.common.GradientText
import com.techne.jenotes.presentation.ui.theme.bgColor
import com.techne.jenotes.presentation.ui.theme.fieldColor
import com.techne.jenotes.presentation.ui.theme.labelBgColor
import com.techne.jenotes.presentation.ui.theme.whiteColor

@Composable
fun HomeScreen(
    navController: NavController, modifier: Modifier = Modifier, onBackPress: () -> Unit,
) {

    Scaffold(
        bottomBar = { BottomNavigation(navController) }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BackHandler {
                onBackPress()
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.BarChart,
                    contentDescription = null,
                    tint = bgColor
                )
                Spacer(modifier = Modifier.width(10.dp))

                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = bgColor
                )
            }

            GradientText(text = "My Notes", fontSize = 20.sp)

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 30.dp, end = 30.dp, bottom = 5.dp)
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
fun BottomNavigation(navController: NavController) {
    var selectedTabIndex by remember { mutableIntStateOf(1) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 60.dp)
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(56.dp)
                .background(
                    color = whiteColor,
                    shape = RoundedCornerShape(30.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BottomBarTab(
                icon = Icons.Rounded.Search,
                isSelected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 }
            )
            Spacer(modifier = Modifier.width(64.dp))
            BottomBarTab(
                icon = Icons.Rounded.Settings,
                isSelected = selectedTabIndex == 2,
                onClick = { selectedTabIndex = 2 }
            )
        }
        Box(
            modifier = Modifier
                .size(52.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-16).dp) // Overlay the middle icon over the row
                .clip(CircleShape)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFA574), Color(0xFFFA6FFF))
                    )
                )
                .clickable {
                    selectedTabIndex = 1
                    navController.navigate("editor")
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Middle Tab",
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Composable
fun BottomBarTab(
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(if (isSelected) Color(0xFFDDDDDD) else Color.Transparent)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            tint = bgColor,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}


@Composable
fun NotesList() {

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
    val containerColor = if (isClicked) bgColor else labelBgColor
    val textColor = if (isClicked) Color.White else Color.Black

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 2.dp, vertical = 20.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(containerColor)
            .clickable { onClick() },
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


