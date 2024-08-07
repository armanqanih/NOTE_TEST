package org.lotka.xenonx.presentation.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.ui.navigation.ScreensNavigation
import org.lotka.xenonx.presentation.util.Dimension.PaddingMedium

@Composable
fun LoginScreen(
    navController: NavController
){

  Column(
      verticalArrangement = Arrangement.Center,
      modifier = Modifier
          .fillMaxWidth()
          .padding(PaddingMedium),

      ) {

      Text(text = stringResource(R.string.Login),
          color = MaterialTheme.colors.onBackground,

          )

  }

}