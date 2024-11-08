package com.example.androidstudio_koala_template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidstudio_koala_template.ui.theme.AndroidStudioKoalaTemplateTheme
import androidx.compose.ui.graphics.vector.ImageVector

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        enableEdgeToEdge()
        setContent {
            AndroidStudioKoalaTemplateTheme {
                disenoapp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun disenoapp() {
    var selectedIcon by remember { mutableStateOf(Icons.Default.Favorite) }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Repte 01",
            modifier = Modifier.padding(top = 20.dp),
            color = Color.Blue,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        SelectorComponent(onIconSelected = { icon -> selectedIcon = icon })

        Spacer(modifier = Modifier.height(250.dp))

        minmax()

        Spacer(modifier = Modifier.height(200.dp))

        Box(
            modifier = Modifier
                .size(80.dp)
                .wrapContentSize(Alignment.Center)
        ) {
            Icon(
                imageVector = selectedIcon,
                contentDescription = "Icono seleccionado",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun minmax() {
    Row(modifier = Modifier.fillMaxWidth().padding(start = 40.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text("Min:")
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .width(80.dp)
                    .height(46.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text("Max:")
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .width(80.dp)
                    .height(46.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorComponent(onIconSelected: (imageVector: ImageVector) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf(
        Pair("Email", Icons.Default.Email),
        Pair("Call", Icons.Default.Call),
        Pair("Search", Icons.Default.Search),
        Pair("Add", Icons.Default.Add),
        Pair("Home", Icons.Default.Home),
        Pair("Favorite", Icons.Default.Favorite),
        Pair("Settings", Icons.Default.Settings),
        Pair("Person", Icons.Default.Person),
        Pair("Share", Icons.Default.Share),
    )

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            TextField(
                value = "Tria un Icon",
                onValueChange = {},
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                readOnly = true,
                modifier = Modifier.menuAnchor(),
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
                colors = androidx.compose.material3.TextFieldDefaults.textFieldColors(
                    containerColor = Color.Blue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                options.forEach { (label, icon) ->
                    DropdownMenuItem(
                        text = { Text(text = label, color = Color.White) },
                        onClick = {
                            onIconSelected(icon)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    AndroidStudioKoalaTemplateTheme {
        disenoapp()
    }
}