package org.lotka.xenonx.presentation.screen.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import org.lotka.xenonx.domain.model.Post
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.screen.post.compose.ActionRow
import org.lotka.xenonx.presentation.theme.HintGray
import org.lotka.xenonx.presentation.theme.TextGray
import org.lotka.xenonx.presentation.util.Constants.MAX_DESCRIPTION_LINE

@Composable
fun PostScreen(
    post: Post
) {

    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxWidth()) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)

        ) {
            Image(
                painter = painterResource(id = R.drawable.post),
                contentDescription = "profile image",
            )

            ActionRow(userName = "Arman Sherwamii"
            , modifier = Modifier.fillMaxWidth(),
                onLikeClicked = { isLiked ->

                },
                onCommentClicked = {

                },
                onShareClicked = {

                },
                onUserNameClicked = {userName ->

                }
            )
            Text(
                text = buildAnnotatedString {
                    append(text = post.description)
                    withStyle(
                        SpanStyle(
                            color = HintGray
                        )
                    ) {
                        append((stringResource(R.string.read_more)))
                    }
                },
                style = MaterialTheme.typography.body2,
                overflow = TextOverflow.Ellipsis,
                maxLines = MAX_DESCRIPTION_LINE,
                )

            Row (modifier = Modifier.fillMaxWidth()
            , horizontalArrangement = Arrangement.SpaceBetween
            ){


                Text(
                    text = stringResource(R.string.liked_by_x_people,post.likes)
                    , style = MaterialTheme.typography.body2
                    , color = TextGray
                )

                Text(
                    text = stringResource(R.string.comment)
                    , style = MaterialTheme.typography.body2
                    , color = TextGray
                )
            }

        }

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "profile image",
            modifier = Modifier.clip(CircleShape)
        )

    }}

