package com.aravind.composefitnessapp.ui.screen.registeruser

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aravind.composefitnessapp.R
import com.aravind.composefitnessapp.ui.screen.loginuser.LoginScreen
import com.aravind.composefitnessapp.ui.theme.Black
import com.aravind.composefitnessapp.ui.theme.GradientEnd
import com.aravind.composefitnessapp.ui.theme.GradientStart
import com.aravind.composefitnessapp.ui.theme.Grey2
import com.aravind.composefitnessapp.ui.theme.Grey3
import com.aravind.composefitnessapp.ui.theme.Pink1
import com.aravind.composefitnessapp.ui.theme.poppinsFamily
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

fun registerUser(
    fullName: String,
    phone: String,
    email: String,
    password: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
                val user = hashMapOf(
                    "fullName" to fullName,
                    "phone" to phone,
                    "email" to email
                )

                // Save basic user data to Firestore in the "users" collection
                db.collection("users").document(userId).set(user)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { e -> onError(e.message ?: "Failed to save user") }
            } else {
                onError(task.exception?.message ?: "Registration failed")
            }
        }
}
fun updateProfile(
    gender: String="male",
    dateOfBirth: String,
    weight: String,
    height: String,
    age:String
) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val db = FirebaseFirestore.getInstance()


    userId?.let {
        val profileData = hashMapOf(
            "gender" to gender,
            "dob" to dateOfBirth,
            "weight" to weight,
            "height" to height,
            "age" to age
        )


        db.collection("users").document(it)
            .update(profileData as Map<String, Any>)

    }
}

fun isValidEgyptianPhone(phone: String): Boolean {
    return Regex("^(010|011|012|015)\\d{8}$").matches(phone)
}
fun validatePassword(password: String): String? {
    if (password.length < 8) return "Password must be at least 8 characters"
    if (!password.any { it.isLetter() }) return "Password must contain letters"
    if (!password.any { it.isDigit() }) return "Password must contain numbers"
    if (!password.any { "!@#\$%^&*()_+-=[]|,./?><".contains(it) }) return "Password must contain special characters"
    return null
}

/////////////////////////////////////////////////

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterUserScreen(onRegisterSuccess: () -> Unit,onNavigateToLogin: () -> Unit) {

    var passwordError by rememberSaveable { mutableStateOf<String?>(null) }
    var phoneError by rememberSaveable { mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }



    Column(modifier = Modifier.fillMaxSize().background(Color.White), verticalArrangement = Arrangement.SpaceBetween) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, top = 20.dp, bottom = 20.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = "Hey there,",
                    color = Black,
                    fontSize = 16.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Create an Account",
                    color = Black,
                    fontSize = 20.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(30.dp))

                var fullName by remember { mutableStateOf("") }

                TextField(
                    singleLine = true,
                    value = fullName,
                    onValueChange = { fullName = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = {
                        Text(
                            text = "Full Name",
                            color = Grey3,
                            fontSize = 12.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painterResource(R.drawable.ic_leading_full_name),
                            contentDescription = null
                        )
                    },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Grey2,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                var phoneNumber by remember { mutableStateOf("") }
                TextField(
                    singleLine = true,
                    value = phoneNumber,
                    onValueChange = {
                        if (it.length <= 11 && it.all { c -> c.isDigit() }) {
                            phoneNumber = it
                            phoneError = false
                        }
                    },
                    isError = phoneError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    label = {
                        Text(
                            text = "Phone Number",
                            color = Grey3,
                            fontSize = 12.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painterResource(R.drawable.ic_leading_phone),
                            contentDescription = null
                        )
                    },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Grey2,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    )
                )

                if (phoneError) {
                    Text(
                        text = "Invalid phone number. It must start with 010, 011, 012, or 015 and be 11 digits long.",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                var email by remember { mutableStateOf("") }
                TextField(
                    singleLine = true,
                    value = email,
                    onValueChange = { email = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    label = {
                        Text(
                            text = "Email",
                            color = Grey3,
                            fontSize = 12.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painterResource(R.drawable.ic_leading_email),
                            contentDescription = null
                        )
                    },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Grey2,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))
                var password by remember { mutableStateOf("") }
                TextField(
                    singleLine = true,
                    value = password,
                    onValueChange = { password = it
                        passwordError = validatePassword(it)
                        },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    label = {
                        Text(
                            text = "Password",
                            color = Grey3,
                            fontSize = 12.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painterResource(R.drawable.ic_leading_password),
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (passwordVisible) "Hide password" else "Show password"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Grey2,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    isError = passwordError != null,
                    supportingText = {
                        passwordError?.let {
                            Text(text = it, color = Color.Red, fontSize = 12.sp)
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            // in below line we are setting
            // the state of our checkbox.
            val checkedState = remember { mutableStateOf(false) }
            // in below line we are displaying a row
            // and we are creating a checkbox in a row.
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    // below line we are setting
                    // the state of checkbox.
                    checked = checkedState.value,
                    // below line is use to add on check
                    // change to our checkbox.
                    onCheckedChange = { checkedState.value = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Pink1,
                        uncheckedColor = Grey3,
                        checkmarkColor = Color.White
                    )
                )
                // below line is use to add text to our check box and we are
                // adding padding to our text of checkbox
                Text(
                    text = buildAnnotatedString {
                        append("By continuing you accept our ")
                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                            append("Privacy Policy")
                        }
                        append(" and ")
                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                            append("Terms of Use")
                        }
                    }, color = Grey3,
                    fontSize = 10.sp,
                    lineHeight = 13.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Normal
                )

            }
        }

        Column(
            modifier = Modifier.padding(start = 25.dp, end = 25.dp, bottom = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp)

                    .background(
                        brush = Brush.horizontalGradient(listOf(GradientStart, GradientEnd)),
                        shape = ButtonDefaults.shape
                    )
                    .height(55.dp),
                onClick = {

                    onRegisterSuccess()

                          },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    "Register", color = Color.White, fontSize = 16.sp,
                    fontFamily = poppinsFamily, fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Divider(
                    color = Grey3, modifier = Modifier
                        .height(1.dp)
                        .weight(0.45f)
                )
                Text(
                    "Or",
                    color = Black,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.weight(0.1f)
                )
                Divider(
                    color = Grey3, modifier = Modifier
                        .height(1.dp)
                        .weight(0.45f)
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_signup_google),
                    contentDescription = "Signup Google",
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {

                            })
                        .size(50.dp)
                )
                Spacer(modifier = Modifier.width(30.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_signup_fb),
                    contentDescription = "Signup Google",
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {

                        })
                )
            }
            val loginString = "Login"
            val annotatedString = buildAnnotatedString {
                append("Already have an account? ")
                withStyle(
                    style = SpanStyle(
                        color = Pink1,
                        fontWeight = FontWeight.SemiBold
                    ),
                ) {
                    pushStringAnnotation(tag = loginString, annotation = loginString)
                    append(loginString)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            ClickableText(text = annotatedString, style = TextStyle(
                color = Black,
                fontSize = 14.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal
            ), onClick = { offset ->
                annotatedString.getStringAnnotations(offset, offset)
                    .firstOrNull()?.let { span ->
                        if (span.tag == loginString) {
                            onNavigateToLogin()// هذا هو السطر الصحيح للتنقل
                        }

                        println("Clicked on ${span.item}")
                    }
            })


        }
    }
}


@Preview
@Composable
fun RegisterUserPreview() {
    RegisterUserScreen(onRegisterSuccess = {}, onNavigateToLogin = {})
}