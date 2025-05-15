package com.aravind.composefitnessapp.ui.screen.todo

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aravind.composefitnessapp.ui.theme.Black
import java.util.UUID


data class Task(
    val id: String,
    val text: String,
    val isCompleted: Boolean = false
)


@Composable
fun ToDoApp( navController: NavController, onBack: () -> Unit,) {
    var taskText by remember { mutableStateOf("") }
    var tasks by remember { mutableStateOf(listOf<Task>()) }

    val gradient = Brush.linearGradient(
        listOf(Color(0xFF6366F1), Color(0xFFA855F7), Color(0xFFEC4899))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "To-Do List",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = taskText,
                onValueChange = { taskText = it },
                singleLine = true,
                textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White.copy(alpha = 0.1f), shape = MaterialTheme.shapes.medium)
                    .padding(12.dp)
                   // .blur(4.dp)
                ,

                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (taskText.isEmpty()) {
                            Text(
                                text = "Add new task",
                                color = Color.White.copy(alpha = 0.5f),
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }

                }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                if (taskText.isNotBlank()) {
                    tasks = tasks + Task(id = UUID.randomUUID().toString(), text = taskText)
                    taskText = ""
                }
            }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {
            items(tasks) { task ->
                ToDoItem(
                    task = task,
                    onToggleComplete = {
                        tasks = tasks.map {
                            if (it.id == task.id) it.copy(isCompleted = !it.isCompleted) else it
                        }
                    },
                    onDelete = {
                        tasks = tasks.filterNot { it.id == task.id }
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total Tasks: ${tasks.size}",
                color = Color.White.copy(alpha = 0.8f)
            )
            Text(
                text = "Completed: ${tasks.count { it.isCompleted }}",
                color = Color.White.copy(alpha = 0.8f)
            )
        }

    }


    Box(modifier = Modifier.fillMaxWidth()) {
        IconButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Black
            )
        }
    }
}



@Composable
fun ToDoItem(
    task: Task,
    onToggleComplete: () -> Unit,
    onDelete: () -> Unit
) {
    val background = if (task.isCompleted) {
        Color.White.copy(alpha = 0.05f)
    } else {
        Color.White.copy(alpha = 0.1f)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(background, shape = MaterialTheme.shapes.medium)
            .padding(16.dp)

    ) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = { onToggleComplete() }
        )

        Text(
            text = task.text,
            color = if (task.isCompleted) Color.LightGray else Color.White,
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = onDelete) {
            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
        }
    }
}






////////////////preview

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE), // اللون الأساسي
    secondary = Color(0xFF03DAC6), // اللون الثانوي
    background = Color(0xFF121212), // خلفية داكنة
    surface = Color(0xFF121212), // سطح داكن
    onPrimary = Color.White, // النصوص أو الرموز على اللون الأساسي
    onBackground = Color.White, // النصوص على الخلفية
    onSurface = Color.White // النصوص على السطح
)

// التطبيق بتاعك باستخدام الثيم
@Composable
fun ToDoAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme, // هنا بنستخدم الألوان
        content = content // المحتوى اللي هيتعرض
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewToDoApp() {
    ToDoAppTheme {
        // هنا تضع الشاشة اللي حابب تعرضها مع الـ Theme
        ToDoApp( navController = rememberNavController(),
            onBack={})
    }
}







