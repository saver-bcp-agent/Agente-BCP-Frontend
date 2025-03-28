package com.isil.wardia

import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract.Columns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.isil.wardia.ui.theme.WardIATheme
import kotlinx.coroutines.delay
import androidx.compose.ui.graphics.PathEffect


class AdministracionActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WardIATheme {

                val ahorroCards = listOf("Universidad","Casa", "Carro")
                Scaffold(modifier = Modifier.fillMaxSize(),

                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.White,
                                titleContentColor = colorResource(id = R.color.oAzul)
                            ),
                            title = { Text(
                                text = "Administrador de dinero",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.beauti)), // ← Usa tu fuente personalizada
                                color = colorResource(id = R.color.oAzul),
                                modifier = Modifier.padding(top = 8.dp)
                            ) },

                            actions = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "back",
                                        tint = colorResource(id = R.color.oAzul) // Tu nuevo color personalizado
                                    )
                                } },
                            modifier = Modifier.height(50.dp)
                        )
                    },
                    floatingActionButton = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.BottomEnd
                        ){
                            var showMessage by remember { mutableStateOf(true) }

                            LaunchedEffect(Unit){
                                delay(7000)
                                showMessage = false
                            }
                            if (showMessage){
                                Box(
                                    modifier = Modifier
                                        .padding(end = 80.dp, bottom = 95.dp)
                                        .background(
                                            color = colorResource(R.color.sNaranja),
                                            shape = RoundedCornerShape(24.dp)
                                        )
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                ){
                                    Text(
                                        text = "Te ayudo a crear\nwuardaditos",
                                        color = colorResource(R.color.pNaranja),
                                        fontSize = 14.sp,
                                        lineHeight = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.beauti)),
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .padding(end = 20.dp, bottom = 20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                Button(
                                    onClick = {
                                        startActivity(Intent(this@AdministracionActivity, chatActivity::class.java))
                                    },
                                    shape = RoundedCornerShape(50),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorResource(R.color.pNaranja)
                                    ),
                                    contentPadding = PaddingValues(12.dp),
                                    modifier = Modifier.size(64.dp)
                                ){
                                    Image(
                                        painter = painterResource(id = R.drawable.robot),
                                        contentDescription = "Chatea conmigo",
                                        modifier = Modifier.size(50.dp)
                                    )
                                }
                                Text(
                                    text = "Chatea\nconmigo",
                                    color = colorResource(id = R.color.oAzul),
                                    fontSize = 14.sp,
                                    lineHeight = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.beauti)),
                                    modifier = Modifier.padding(top = 4.dp),
                                    fontWeight = FontWeight.Medium
                                )
                            }

                        }
                    }
                    ) { innerPadding ->
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            verticalArrangement = Arrangement.Top
                        ){
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(20.dp)) {
                                    Icon(
                                        Icons.Default.Menu, // tu ícono de menú
                                        contentDescription = "Menú",
                                        tint = Color(0xFF2B3545), // o el color que estés usando
                                        modifier = Modifier.size(19.dp)
                                    )
                                }
                                Text(
                                    text = "Menú",
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(start = 6.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily(Font(R.font.beauti)), // ← Usa tu fuente personalizada
                                    color = colorResource(id = R.color.oAzul)
                                )


                            }
                            Column (modifier = Modifier.padding(horizontal = 20.dp) ){
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(text = "Wardaditos",
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(bottom = 5.dp),
                                        fontWeight = FontWeight.ExtraBold,
                                        fontFamily = FontFamily(Font(R.font.beauti)), // ← Usa tu fuente personalizada
                                        color = colorResource(id = R.color.oAzul))
                                }
                                Row( modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    )
                                    {
                                        Button(
                                            onClick = { /* acción */ },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = colorResource(R.color.sNaranja) // naranja suave
                                            ),
                                            shape = RoundedCornerShape(50),
                                            modifier = Modifier.width(140.dp)
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.money), // cambia por tu imagen
                                                contentDescription = "Abonar",
                                                modifier = Modifier
                                                    .size(20.dp) // ajusta según lo pequeño que lo quieras
                                                    .padding(end = 4.dp)
                                            )
                                            Text(
                                                text = "Abonar",
                                                fontSize = 16.sp,
                                                modifier = Modifier.padding(start = 6.dp),
                                                fontWeight = FontWeight.ExtraBold,
                                                fontFamily = FontFamily(Font(R.font.beauti)), // ← Usa tu fuente personalizada
                                                color = colorResource(id = R.color.pNaranja)
                                            )
                                        }

                                        Button(
                                            onClick = { /* acción */ },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color.LightGray // naranja suave
                                            ),
                                            shape = RoundedCornerShape(50),
                                            modifier = Modifier.width(140.dp)
                                        ) {
                                            Icon(
                                                Icons.Default.Add,
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                            Text(
                                                text = "Nuevo",
                                                fontSize = 16.sp,
                                                modifier = Modifier.padding(start = 6.dp),
                                                fontWeight = FontWeight.ExtraBold,
                                                fontFamily = FontFamily(Font(R.font.beauti)), // ← Usa tu fuente personalizada
                                                color = Color.White
                                            )
                                        }

                                    }//row de botones
                                }//row contenedo de botones

                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(2),
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 10.dp, bottom = 10.dp),
                                    verticalArrangement = Arrangement.spacedBy(10.dp),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    content = {
                                        items(ahorroCards.size) { index ->
                                            CardWuardadito(title = ahorroCards[index])
                                        }

                                        item {
                                        CardCrearWuardadito()
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardWuardadito(title: String) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .height(160.dp)
            .fillMaxWidth()
            .border(0.65.dp, Color.Black.copy(alpha = 0.1f), shape = RoundedCornerShape(24.dp))

    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(color = Color(0xFF3A71F5), shape = RoundedCornerShape(50)),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.ahorro),
                        contentDescription = "ahorro",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cerrar",
                    tint = Color.LightGray,
                    modifier = Modifier.size(18.dp)
                )
            }

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.oAzul),
                fontFamily = FontFamily(Font(R.font.beauti)),
                modifier = Modifier.padding(top = 6.dp)
            )
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(6.dp)
                    .background(Color(0x80002A8E), shape = RoundedCornerShape(50))
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f) // ← Aquí tú puedes controlar el porcentaje de llenado
                        .height(6.dp)
                        .background(Color(0xFF3A71F5), shape = RoundedCornerShape(50)) // azul fuerte
                )
            }
            Text(
                text = "S/.0",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.oAzul),
                fontFamily = FontFamily(Font(R.font.beauti))
            )

            Text(
                text = "Total ahorrado",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black.copy(alpha = 0.5f),
                fontFamily = FontFamily(Font(R.font.beauti))
            )

        }
    }
}

@Composable
fun CardCrearWuardadito() {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(160.dp)
            .fillMaxWidth()
            .background(Color.Transparent)
            .drawBehind {
                val strokeWidth = 2.dp.toPx()
                val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                drawRoundRect(
                    color = Color.Gray,
                    size = size,
                    style = androidx.compose.ui.graphics.drawscope.Stroke(
                        width = strokeWidth,
                        pathEffect = pathEffect
                    ),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(24.dp.toPx())
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.smile),
                contentDescription = "Agregar",
                tint = Color.Gray,
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = "¡Crea wuardaditos\nmanualmente o con IA!",
                fontSize = 11.sp,
                color = Color.Gray,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.beauti)),
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}
