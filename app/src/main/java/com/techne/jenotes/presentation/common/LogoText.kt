package com.techne.jenotes.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techne.jenotes.R
import com.techne.jenotes.presentation.ui.theme.bgColor
import com.techne.jenotes.presentation.ui.theme.greyText
import com.techne.jenotes.presentation.ui.theme.plusJakartaSans

@Composable
fun GradientText(text: String) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color(0xFFFF5D75), Color(0xFFFFAE5E)),
    )
    BasicText(
        text = text,
        style = TextStyle(
            brush = gradientBrush,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = plusJakartaSans
        ),
    )
}

@Composable
fun LogoText(modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = modifier.padding(0.dp, 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GradientText(text = "J&E")
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Notes.", style = TextStyle(
                    fontFamily = plusJakartaSans,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            )
        }
        Text(
            text = "Capture. Quiz. Remember.", style = TextStyle(
                fontFamily = plusJakartaSans,
                fontWeight = FontWeight(200),
                fontSize = 16.sp,
                color = Color.White,
            ), textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoginWithGoogleText(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    color: Color,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .padding(2.dp)
                    .weight(1f)
                    .height(0.5.dp)
                    .background(color = color)
            )
            Text(
                text = "or continue with",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = plusJakartaSans,
                    fontWeight = FontWeight.Light,
                    color = color,
                    textAlign = TextAlign.Center,
                )
            )
            Box(
                Modifier
                    .padding(2.dp)
                    .weight(1f)
                    .height(0.5.dp)
                    .background(color = color)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .clickable(onClick = onClick)
                .padding(10.dp)
                .size(20.dp)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(12.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.googlelogo),
                contentDescription = "Google logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun HeaderText(
    title: String,
    subtitle: String,
    titleColor: Color,
    subtitleColor: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = plusJakartaSans
            ),
            color = titleColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = subtitle,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Light,
                fontFamily = plusJakartaSans
            ),
            color = subtitleColor,
            textAlign = TextAlign.Center
        )
    }
}
