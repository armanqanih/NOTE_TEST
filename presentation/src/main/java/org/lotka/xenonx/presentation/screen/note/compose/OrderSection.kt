package org.lotka.xenonx.presentation.screen.note.compose

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import org.lotka.xenonx.domain.util.NoteOrder


@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.DATA,
    onOrderChange: (NoteOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                selected = noteOrder == NoteOrder.TITLE,
                onSelect = { onOrderChange(NoteOrder.TITLE) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = noteOrder == NoteOrder.DATA,
                onSelect = { onOrderChange(NoteOrder.DATA) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                selected = noteOrder == NoteOrder.COLOR,
                onSelect = { onOrderChange(NoteOrder.COLOR) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

    }
}