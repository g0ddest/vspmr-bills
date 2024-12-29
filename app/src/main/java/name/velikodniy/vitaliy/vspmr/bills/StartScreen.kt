package name.velikodniy.vitaliy.vspmr.bills

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun StartScreen(viewModel: BillsViewModel) {
    val navController = rememberNavController()
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "О приложении") },
            text = {
                MarkdownText(
                    """Это приложение предназначено для просмотра законопроектов Верховного Совета ПМР. Пожалуйста, обратите внимание, что это не является официальным приложением Верховного Совета ПМР и не связано с ним. Приложение собирает и отображает лишь общедоступные данные с официального сайта Верховного совета [vspmr.org](https://vspmr.org) в удобном формате без каких-либо изменений.
                    
Приложение не собирает никаких данных о пользователях и использует доступ к интернету исключительно для обновления списка законопроектов. Политика обработки данных доступна по [ссылке](https://vspmr.vitaliy.velikodniy.name/static/privacy.txt)."""
                )
            },
            confirmButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text("OK")
                }
            }
        )
    }
    MyTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Row(
                                Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Законопроекты ВС ПМР",
                                    color = colorResource(id = R.color.colorSurface),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 23.sp
                                )
                                IconButton(
                                    onClick = { showDialog = true },
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(
                                            color = colorResource(id = R.color.colorText),
                                            shape = CircleShape
                                        )
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = "Информация",
                                        tint = colorResource(id = R.color.colorSurface)
                                    )
                                }
                            }
                            Text(
                                text = "приложение не представляет ВС ПМР",
                                color = colorResource(id = R.color.colorSurface),
                                fontWeight = FontWeight.Light
                            )
                        }

                    },
                    backgroundColor = colorResource(id = R.color.colorText),
                    contentColor = colorResource(id = R.color.colorSurface),
                    elevation = 12.dp
                )
            }
        ) { innerPadding ->
            AppNavHost(
                navController = navController,
                viewModel = viewModel,
                contentPadding = innerPadding
            )
        }
    }
}