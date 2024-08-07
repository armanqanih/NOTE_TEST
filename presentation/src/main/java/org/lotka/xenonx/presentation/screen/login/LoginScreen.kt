package org.lotka.xenonx.presentation.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.composable.StandardTextField
import org.lotka.xenonx.presentation.ui.navigation.ScreensNavigation
import org.lotka.xenonx.presentation.util.Dimension.SpaceLarge
import org.lotka.xenonx.presentation.util.Dimension.SpaceMedium
import org.lotka.xenonx.presentation.util.Dimension.SpaceSmall

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {

    val state = viewModel.state.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()
        .padding(
            start = SpaceLarge,
            end = SpaceLarge,
            top = SpaceLarge,
            bottom = 50.dp
        )
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
             ,

            ) {

            Text(
                text = stringResource(R.string.Login),
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                value = state.userName,
                hint = stringResource(R.string.enter_user_name),
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnterUserName(it))
                },
                singleLine = true,
                keyboardType = KeyboardType.Text,
                isError = false,

                )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                value = state.password,
                hint = stringResource(R.string.Password),
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnterPassword(it))
                },
                singleLine = true,
                keyboardType = KeyboardType.Password,
                isError = false,

                )


        }

        Text(text = buildAnnotatedString {
            append(stringResource(R.string.dont_have_an_account))
            append(" ")
            withStyle(style = SpanStyle(MaterialTheme.colors.primary)
            ) {
                append(stringResource(R.string.signup))
            }
            append()

        },
            style = MaterialTheme.typography.body1,
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(bottom = SpaceMedium)


        )


    }

}