package com.example.noteapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.noteapp.model.Note
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChanges: (String) -> Unit,
    onImeAction: () -> Unit = {

    }
) {
    val keyBoardControl = LocalSoftwareKeyboardController.current
    TextField(
        value = text, onValueChange = onTextChanges,
        label = {
            Text(text = label)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor
            = MaterialTheme.colorScheme.primary
        ),
        maxLines = maxLine,
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyBoardControl?.hide()
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        modifier = modifier
    )
}

@Composable
fun SaveButton(
    modifier: Modifier = Modifier,
    text: String = "Save",
    enable: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enable,
        colors = ButtonDefaults.buttonColors(disabledContainerColor = Color.LightGray)
    ) {
        Text(text)
    }
}


@Composable
fun NoteItem(
    note: Note,
    onClick:(Note) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
    shape = RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp)
    ) {
        Column(modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { onClick(note) }) {
            Text(
                text = note.title, color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.description, color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleSmall
            )
            Text(text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")))
        }
    }

}

@Composable
@Preview
fun ShowButton() {
    NoteItem(note = Note(title = "Saleel", description = "Shaik Saleel Khan"),{

    })
}

@Composable
@Preview
fun ShowPreview() {
    var text by remember {
        mutableStateOf("")
    }
    NoteInputText(modifier = Modifier,
        text = text,
        label = "Title",
        1,
        { it ->
            text = it
        })
}