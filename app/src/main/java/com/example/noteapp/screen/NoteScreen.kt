package com.example.noteapp.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.noteapp.R
import com.example.noteapp.components.NoteInputText
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.noteapp.components.NoteItem
import com.example.noteapp.components.SaveButton
import com.example.noteapp.data.NoteData
import com.example.noteapp.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    noteList: List<Note>, onAddNote: (Note) -> Unit, onRemoveNote: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val buttonStatus by remember(title, description) {
        if (title.isNotEmpty() && description.isNotEmpty()) mutableStateOf(true) else mutableStateOf(
            false
        )
    }
    val context: Context = LocalContext.current
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, navigationIcon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Icon",
            )
        }, actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Menu, contentDescription = "action Icon"
                )
            }
        }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
        )
    }) { it ->
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            val requester = FocusRequester()
            NoteInputText(
                text = title, label = "Title", onTextChanges = {
                    title = it
                }, modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
            )
            NoteInputText(
                text = description, label = "Add a note", onTextChanges = {
                    description = it
                }, modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
            )
            SaveButton(enable = buttonStatus) {
                Log.i(
                    "TAG",
                    "NoteScreen: $title && $description && -->" + " ${title.isEmpty()} && ${description.isEmpty()}"
                )
                onAddNote(Note(title = title, description = description))
                title = ""
                description = ""
                Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
            }
            Divider(modifier = Modifier.padding(12.dp))
            LazyColumn {
                items(items = noteList) { note ->
                    NoteItem(note = note, onClick = {
                        onRemoveNote(note)
                    })
                }
            }
        }
    }
}

@Composable
@Preview(name = "Note Screen Preview", showBackground = true)
fun NoteScreenPreview() {
    NoteScreen(noteList = NoteData().getNotesList(), onAddNote = {}, onRemoveNote = {})
}