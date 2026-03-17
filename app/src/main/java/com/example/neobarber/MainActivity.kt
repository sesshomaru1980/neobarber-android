package com.example.neobarber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCorp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    AppNavGraph(navController)
                }
            }
        }
    }
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("booking") { BookingScreen(navController) }
    }
}

private val BluePrimary = Color(0xFF1245FF)
private val DarkTop = Color(0xFF3A1D21)
private val DarkBottom = Color(0xFF1C2E50)
private val LightBg = Color(0xFFF1F1F1)
private val CardOverlay = Color(0x66595959)
private val GrayButton = Color(0xFFA8A8A8)
private val CalendarBg = Color(0xFFEDE6F4)
private val SelectedDay = Color(0xFF71618F)

@Composable
fun DarkBarberBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(DarkTop, DarkBottom)
                )
            )
    )
}

@Composable
fun LogoBarber(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_neobarber),
        contentDescription = "Logo NeoBarber",
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        DarkBarberBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(28.dp))
            LogoBarber(modifier = Modifier.size(120.dp))

            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = "Bienvenido a NeoBarber\n¡Comencemos!",
                color = Color.White,
                fontSize = 22.sp,
                lineHeight = 30.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.18f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "NB",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SmallActionButton(
                    text = "Iniciar sesión",
                    bg = BluePrimary,
                    textColor = Color.White,
                    onClick = { navController.navigate("login") }
                )
                SmallActionButton(
                    text = "Registrar",
                    bg = Color(0xFFEDEDED),
                    textColor = Color.Black,
                    onClick = { navController.navigate("register") }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun SmallActionButton(
    text: String,
    bg: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 2.dp),
        colors = ButtonDefaults.buttonColors(containerColor = bg),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.height(34.dp)
    ) {
        Text(text = text, color = textColor, fontSize = 12.sp)
    }
}

@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        DarkBarberBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(28.dp))
            LogoBarber(modifier = Modifier.size(118.dp))
            Spacer(modifier = Modifier.height(28.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = CardOverlay)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "INICIAR SESION",
                        color = Color.White,
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    LoginFieldLabel("Email")
                    AuthTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = "Email"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LoginFieldLabel("Password")
                    AuthTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = "Contraseña",
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { navController.navigate("booking") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = BluePrimary),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text("Iniciar")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Olvidaste tu contraseña?",
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier.clickable { }
                    )
                }
            }
        }
    }
}

@Composable
fun LoginFieldLabel(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = { Text(placeholder) },
        visualTransformation = if (isPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(6.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White
        )
    )
}

@Composable
fun RegisterScreen(navController: NavHostController) {
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBg)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        LogoBarber(modifier = Modifier.size(56.dp))
        Spacer(modifier = Modifier.height(6.dp))

        Text("Registrarse", fontSize = 17.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(6.dp))
        Text("Crea una cuenta nueva", color = Color.Gray, fontSize = 11.sp)

        Spacer(modifier = Modifier.height(20.dp))

        PurpleOutlinedField("Nombre", "Nombre", nombre, { nombre = it }, highlight = true)
        Spacer(modifier = Modifier.height(12.dp))
        PurpleOutlinedField("Teléfono (opcional)", "Número", telefono, { telefono = it })
        Spacer(modifier = Modifier.height(12.dp))
        PurpleOutlinedField("E-mail", "E-mail", email, { email = it })
        Spacer(modifier = Modifier.height(12.dp))
        PurpleOutlinedField("Contraseña", "Contraseña", password, { password = it }, password = true)

        Spacer(modifier = Modifier.height(38.dp))

        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = BluePrimary),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text("Registrar")
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun PurpleOutlinedField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    highlight: Boolean = false,
    password: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontSize = 12.sp, color = Color(0xFF3C3C3C))
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            placeholder = { Text(placeholder) },
            visualTransformation = if (password) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(2.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF8F8F8),
                unfocusedContainerColor = Color(0xFFF8F8F8),
                focusedBorderColor = if (highlight) Color(0xFF9B59FF) else Color.LightGray,
                unfocusedBorderColor = if (highlight) Color(0xFF9B59FF) else Color.LightGray
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navController: NavHostController) {
    val hours = listOf("Hora", "09:00 AM", "10:00 AM", "11:00 AM", "02:00 PM", "03:00 PM")
    var expanded by remember { mutableStateOf(false) }
    var selectedHour by remember { mutableStateOf(hours.first()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBg)
            .padding(horizontal = 12.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Reservar",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray)
        )
        Box(
            modifier = Modifier
                .width(108.dp)
                .height(2.dp)
                .background(BluePrimary)
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text("Selecciona un Día", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = CalendarBg)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                HeaderCalendarRow()
                Spacer(modifier = Modifier.height(10.dp))

                val labels = listOf("S", "M", "T", "W", "T", "F", "S")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    labels.forEach {
                        Text(it, fontSize = 10.sp, color = Color(0xFF5E5E5E))
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                val weeks = listOf(
                    listOf("26", "27", "28", "29", "30", "31", "1"),
                    listOf("2", "3", "4", "5", "6", "7", "8"),
                    listOf("9", "10", "11", "12", "13", "14", "15"),
                    listOf("16", "17", "18", "19", "20", "21", "22"),
                    listOf("23", "24", "25", "26", "27", "28", "29"),
                    listOf("30", "1", "2", "3", "4", "5", "6")
                )

                weeks.forEach { week ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        week.forEach { day ->
                            CalendarDay(day = day, selected = day == "3")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {}) { Text("Cancel", fontSize = 10.sp) }
                    TextButton(onClick = {}) { Text("OK", fontSize = 10.sp) }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))
        Text("Selecciona una hora", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(10.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedHour,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .menuAnchor()
                    .width(120.dp),
                textStyle = LocalTextStyle.current.copy(fontSize = 12.sp),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                shape = RoundedCornerShape(4.dp)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                hours.forEach { hour ->
                    DropdownMenuItem(
                        text = { Text(hour) },
                        onClick = {
                            selectedHour = hour
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = GrayButton),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Volver")
            }

            Button(
                onClick = { },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = BluePrimary),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Siguiente")
            }
        }

        Spacer(modifier = Modifier.height(26.dp))
    }
}

@Composable
fun HeaderCalendarRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("‹", fontSize = 16.sp, color = Color(0xFF444444))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Sep", fontSize = 10.sp)
            Text("ˇ", fontSize = 10.sp)
            Text("›", fontSize = 10.sp)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("‹", fontSize = 10.sp)
            Text("2024", fontSize = 10.sp)
            Text("ˇ", fontSize = 10.sp)
            Text("›", fontSize = 10.sp)
        }
    }
}

@Composable
fun CalendarDay(day: String, selected: Boolean) {
    Box(
        modifier = Modifier
            .size(25.dp)
            .clip(CircleShape)
            .background(if (selected) SelectedDay else Color.Transparent)
            .wrapContentSize(Alignment.Center),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day,
            fontSize = 10.sp,
            color = if (selected) Color.White else Color(0xFF767676)
        )
    }
}