package org.lotka.xenonx.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import java.lang.Error

@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    hint: String = "",
    maxLength : Int = 40,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine : Boolean = true
) {

    var isPasswordToggleDirection by remember {
        mutableStateOf(keyboardType == KeyboardType.Password)
    }
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    TextField(
         modifier = modifier.fillMaxWidth()
        ,
        value = value,
        onValueChange = {
            if(it.length < maxLength){
                onValueChange(it)
            }
        },
        isError = isError,
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.body1)
        },
         singleLine = singleLine,
        visualTransformation = if(!isPasswordVisible && isPasswordToggleDirection){
            PasswordVisualTransformation()
        }else{
            VisualTransformation.None
        }
      ,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        trailingIcon = {
            if (isPasswordToggleDirection){
             IconButton(onClick = {
                 isPasswordVisible =! isPasswordVisible
             }) {
                 Icon(imageVector = if(isPasswordVisible){
                     Icons.Filled.Visibility
                 }else{
                     Icons.Filled.VisibilityOff

                 }, contentDescription ="password" )
             }
            }
        }
    )


}