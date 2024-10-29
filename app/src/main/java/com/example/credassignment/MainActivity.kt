package com.example.credassignment

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var mainExpanded by rememberSaveable {
        mutableStateOf(true)
    }

    var emiExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    var bankExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    val activity = LocalContext.current as? Activity

    BackHandler {
        if(bankExpanded){
            mainExpanded = false
            emiExpanded = true
            bankExpanded = false
        }
        else if(emiExpanded){
            mainExpanded = true
            emiExpanded = false
            bankExpanded = false
        }else{
            activity?.finish()
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF13131E))
    ) {
        Column {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0f1418)
                ),
                title = {},
                navigationIcon = {
                    IconButton(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(25.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.DarkGray
                        ), onClick = {}) {
                        Icon(
                            tint = Color.LightGray,
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "close"
                        )
                    }
                },
                actions = {
                    IconButton(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(25.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.DarkGray
                        ), onClick = {}) {
                        Icon(
                            tint = Color.LightGray,
                            painter = painterResource(id = R.drawable.baseline_question_mark_24),
                            contentDescription = "help"
                        )
                    }
                }
            )
            MainScreen(
                expanded = mainExpanded,
                toExpand = {
                    mainExpanded = true
                    emiExpanded = false
                    bankExpanded = false
                }, toCollapse = {
                    mainExpanded = false
                    emiExpanded = true
                    bankExpanded = false
                })
            EmiSelection(
                expanded = emiExpanded,
                toExpand = {
                    emiExpanded = true
                    mainExpanded = false
                    bankExpanded = false
                }, toCollapse = {
                    emiExpanded = false
                    mainExpanded = false
                    bankExpanded = true
                })

            BankScreen(expanded = bankExpanded,
                toExpand = {
                    mainExpanded = false
                    emiExpanded = false
                    bankExpanded = true
                }, toCollapse = {
                    emiExpanded = true
                    mainExpanded = false
                    bankExpanded = false
                })
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    toExpand: () -> Unit,
    toCollapse: () -> Unit
) {
    var amountText by rememberSaveable {
        mutableStateOf("")
    }


    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF14191D),
            contentColor = Color(0xFF6E7985)
        ),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        modifier = Modifier
            .background(Color(0xFF0f1418))
            .padding(top = 2.dp, start = 2.dp, end = 2.dp)
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn(initialAlpha = 0.1f, animationSpec = tween(600))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF14191D))
            ) {
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        text = "nikunj, how much do you need?"
                    )
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.labelSmall,
                        text = "move the dial and set any amount you need upto ₹"
                    )
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        color = Color(0xFF6E7985),
                        style = MaterialTheme.typography.labelSmall,
                        text = "487,891"
                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                }
                Card(
                    shape = RoundedCornerShape(22.dp),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 150.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(horizontal = 100.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally),
                                text = "credit amount"
                            )
                            TextField(
                                textStyle = TextStyle(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally),
                                value = amountText,
                                onValueChange = { new ->
                                    amountText = new
                                },
                                leadingIcon = {
                                    Text(
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold,
                                        text = "₹"
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(
                                color = Color(0xFF7CA776),
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally),
                                text = "@1.04% monthly"
                            )
                        }
                        Text(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                                .align(Alignment.BottomCenter),
                            text = "stash is instant, money will be credited within seconds",
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Button(
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color(0xFF37469B),
                        contentColor = Color.White
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
                    Text(text = "Proceed to EMI selection")
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
                    .background(Color(0xFF14191D))
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = "credit amount")
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = "1500000")
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
private fun MainPreview() {
    MainScreen(expanded = false, toExpand = {
        val mainExpanded = true
    }, toCollapse = {
        val emiExpanded = false
    })
}

@Preview
@Composable
private fun AppPreview() {
    MyApp()

}