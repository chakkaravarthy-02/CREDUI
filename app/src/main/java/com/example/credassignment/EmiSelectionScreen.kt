package com.example.credassignment

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmiSelection(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    toExpand: () -> Unit,
    toCollapse: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1927),
            contentColor = Color(0xFF6E7985)
        ),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        modifier = Modifier
            .background(Color(0xFF14191D))
            .padding(top = 2.dp, start = 2.dp, end = 2.dp)
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn(initialAlpha = 0.1f, animationSpec = tween(600))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)

            ) {
                Column(
                    modifier = Modifier.padding(start = 16.dp, end = 80.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        text = "how do you wish to pay?"
                    )
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.labelSmall,
                        text = "choose one of our recommended plans or make your own"
                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                    LazyRow {
                        item {
                            Card(modifier = Modifier.size(150.dp)) {

                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(24.dp))
                    Button(
                        border = BorderStroke(2.dp, Color(0xFF6E7985)),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color(0xFF6E7985)
                        ), onClick = {}) {
                        Text(text = "Create your own")
                    }
                }
                Button(
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF37469B)
                    ),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .align(Alignment.BottomCenter),
                    onClick = {
                        toCollapse()
                    }
                ) {
                    Text(text = "Select your bank account")
                }
            }
        }
        AnimatedVisibility(
            visible = !expanded,
            enter = fadeIn(initialAlpha = 0.1f, animationSpec = tween(durationMillis = 600)),
            exit = fadeOut(targetAlpha = 0.1f, animationSpec = tween(600))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1A1927))
                    .padding(16.dp)
            ) {
                Row {
                    Column(verticalArrangement = Arrangement.SpaceEvenly) {
                        Text(text = "EMI")
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(fontWeight = FontWeight.Bold, text = "4,247/ mo")
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Column(verticalArrangement = Arrangement.SpaceEvenly) {
                        Text(text = "duration")
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(fontWeight = FontWeight.Bold, text = "12 months")
                    }
                }
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.align(Alignment.TopEnd),
                    onClick = {
                        toExpand()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "dropDown"
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun EmiPreview() {
    EmiSelection(expanded = true, toExpand = {
        val mainExpanded = true
    }, toCollapse = {
        val emiExpanded = false
    })
}