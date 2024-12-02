package com.example.textfield

import  androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicListScreen() {
    val items = rememberSaveable { mutableStateOf(ArrayList<String>()) }
    val newItem = rememberSaveable { mutableStateOf("") }
    Box {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.DarkGray,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Динамический список",
                    color = Color.White,
                    fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()

            ) { }
            Column(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .background(
                        Color.LightGray,
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    )
                    .padding(16.dp)
            ) {
                items.value.forEachIndexed { index, item ->
                    Text(
                        text = item,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 2.dp)
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(16.dp))
                            .clickable {
                                val currentItems = items.value.toMutableList()
                                if (index < currentItems.size) {
                                    currentItems.removeAt(index)
                                    items.value = ArrayList(currentItems)
                                }
                            }
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    )
                }
            }

            OutlinedTextField(
                value = newItem.value,
                onValueChange = { newItem.value = it },
                label = { Text("Введите текст") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.Black
                ),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )

            TextButton(onClick = {
                if (newItem.value.isNotBlank()) {
                    items.value.add(newItem.value)
                    items.value = ArrayList(items.value)
                    newItem.value = ""
                }
            }) {
                Text("Добавить")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DynamicListScreenPreview() {
    DynamicListScreen()
}
