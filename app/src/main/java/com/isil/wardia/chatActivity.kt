package com.isil.wardia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isil.wardia.ui.theme.WardIATheme
import androidx.compose.foundation.layout.heightIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log



data class ChatMensaje(
    val texto: String,
    val esUsuario: Boolean
)

class chatActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        fun getOrCreateUserId(context: Context): String {
            val sharedPref = context.getSharedPreferences("wardia_prefs", Context.MODE_PRIVATE)
            var userId = sharedPref.getString("user_id", null)

            if (userId == null) {
                userId = java.util.UUID.randomUUID().toString()
                sharedPref.edit().putString("user_id", userId).apply()
            }

            return userId
        }
        super.onCreate(savedInstanceState)
        val userId = getOrCreateUserId(this)
        setContent {
            WardIATheme {
                val mensajes = remember {
                    mutableStateListOf(
                        ChatMensaje("Bienvenido a WardIA, ¿en qué puedo ayudarte?", false)
                    )
                }
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.White,
                                titleContentColor = colorResource(id = R.color.oAzul)
                            ),
                            navigationIcon = {
                                IconButton(onClick = {
                                    startActivity(Intent(this@chatActivity, AdministracionActivity::class.java))
                                }) {
                                    Icon(
                                        Icons.Default.ArrowBack,
                                        contentDescription = "back",
                                        tint = colorResource(id = R.color.oAzul) // Tu nuevo color personalizado
                                    )
                                } },
                            title = { Text(
                                text = "WardIA",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.beauti)), // ← Usa tu fuente personalizada
                                color = colorResource(id = R.color.oAzul),
                                modifier = Modifier.padding(top = 8.dp)
                            ) },
                            modifier = Modifier.height(50.dp)
                        )
                    }
                    ) { innerPadding ->
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(Color.White)
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 12.dp, vertical = 10.dp),
                            verticalArrangement = Arrangement.Bottom
                        ) {

                            LazyColumn(
                                modifier = Modifier.weight(1f).fillMaxWidth(),
                                verticalArrangement = Arrangement.Bottom,
                                reverseLayout = true
                            ) {
                                items(mensajes) { mensaje ->
                                    ChatBubble(mensaje)
                                }
                            }


                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(
                                        color = Color(0x0D000000), // #000000 al 5%
                                        shape = RoundedCornerShape(25.dp)
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = Color(0x33000000), // #000000 al 20%
                                        shape = RoundedCornerShape(25.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 2.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    var mensajeUsuario by remember { mutableStateOf("") }

                                    OutlinedTextField(
                                        value = mensajeUsuario,
                                        onValueChange = { mensajeUsuario = it },
                                        placeholder = {
                                            Text(text = "Escribe algo...",
                                                color = Color(0x80000000)) },
                                        textStyle = LocalTextStyle.current.copy(color = Color(0x80000000)),
                                        shape = RoundedCornerShape(25.dp),
                                        colors = TextFieldDefaults.colors(
                                            unfocusedContainerColor = Color.Transparent,
                                            focusedContainerColor = Color.Transparent,
                                            disabledContainerColor = Color.Transparent,
                                            cursorColor = Color.Black,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            disabledIndicatorColor = Color.Transparent
                                        ),
                                        modifier = Modifier
                                            .weight(1f)
                                    )

                                    IconButton(
                                        onClick = {
                                            if(mensajeUsuario.isNotBlank()){
                                                val mensajeParaApi = mensajeUsuario
                                                mensajes.add(0, ChatMensaje(mensajeParaApi, true))
                                                mensajeUsuario = ""

                                                val request = ChatRequest(
                                                    user_id = userId,
                                                    message = mensajeParaApi
                                                )

                                                val call = RetrofitClient.api.sendMessage(request)

                                                call.enqueue(object : Callback<ChatResponse> {

                                                    override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                                                        if (response.isSuccessful) {
                                                            val respuesta = response.body()?.response ?: "Respuesta vacía"
                                                            mensajes.add(0, ChatMensaje(respuesta, false))
                                                        } else {
                                                            mensajes.add(0, ChatMensaje("Error del servidor: ${response.code()}", false))
                                                        }
                                                    }

                                                    override fun onFailure(
                                                        call: Call<ChatResponse>,
                                                        t: Throwable
                                                    ) {
                                                        Log.e("ChatError", "Error: ${t.localizedMessage}", t)
                                                        mensajes.add(0, ChatMensaje("No se pudo conectar al servidor", false))
                                                    }

                                                })

                                            }

                                        },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Send,
                                            contentDescription = "Enviar",
                                            tint = Color(0x4D000000)
                                        )
                                    }
                                }//donde estara el input y el boton
                            }//contenedor del input
                        }//body
                    }
                }
            }

        }
    }
}


@Composable
fun ChatBubble(mensaje: ChatMensaje) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = if (mensaje.esUsuario) Arrangement.End else Arrangement.Start
    ) {
        if (!mensaje.esUsuario) {
            Column( modifier = Modifier
                .padding(top = 4.dp) // para bajarlo un poco y alinearlo visualmente
                .heightIn(min = 40.dp) ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(colorResource(R.color.pNaranja)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.robot),
                        contentDescription = "Bot",
                        modifier = Modifier.size(30.dp) // Ajusta el tamaño de la imagen dentro del círculo
                    )
                }

            }
        }

        Box(
            modifier = Modifier
                .padding(start = if (!mensaje.esUsuario) 4.dp else 0.dp)
                .widthIn(max = 220.dp) // ← burbuja de tamaño máximo
                .background(
                    color = if (mensaje.esUsuario) Color.White else colorResource(R.color.pNaranja),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = if (mensaje.esUsuario) 1.dp else 0.dp,
                    color = if (mensaje.esUsuario) colorResource(R.color.oAzul) else Color.Transparent,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 10.dp, vertical = 6.dp)
        ) {
            Text(
                text = mensaje.texto,
                color = if (mensaje.esUsuario) colorResource(R.color.oAzul) else Color.White,
                fontSize = 14.sp
            )
        }
    }
}






