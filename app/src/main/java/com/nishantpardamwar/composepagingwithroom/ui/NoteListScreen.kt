package com.nishantpardamwar.composepagingwithroom.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.nishantpardamwar.composepagingwithroom.db.entities.NoteEntity
import com.nishantpardamwar.composepagingwithroom.viewmodels.MainActivityViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization

@Composable
fun NoteListScreen(vm: MainActivityViewModel) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        AddNoteSection(onAddNote = {
            vm.addNote(it)
        })
        NoteList(notePager = vm.getNotePager(), onDelete = {
            vm.deleteNote(it)
        })
    }
}

@Composable
fun AddNoteSection(onAddNote: (NoteEntity) -> Unit) {
    var noteSectionOpened by remember { mutableStateOf(false) }
    Column(
        Modifier
            .animateContentSize { initialValue, targetValue -> }
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        if (noteSectionOpened) {

            var titleText by remember { mutableStateOf("") }
            var titleDesc by remember { mutableStateOf("") }

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
                value = titleText,
                onValueChange = { titleText = it },
                label = { Text(text = "Title") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(10.dp)),
                value = titleDesc,
                onValueChange = { titleDesc = it },
                label = { Text(text = "Description") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )

            Row(
                Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(modifier = Modifier.padding(top = 10.dp), onClick = {
                    noteSectionOpened = !noteSectionOpened
                    onAddNote(
                        NoteEntity(
                            titleText, titleDesc, System.currentTimeMillis()
                        )
                    )
                }) {
                    Text(text = "Add Note")
                }

                Button(modifier = Modifier.padding(top = 10.dp), onClick = {
                    noteSectionOpened = !noteSectionOpened
                }) {
                    Text(text = "Cancel")
                }
            }
        } else {
            Text(
                modifier = Modifier.clickable {
                    noteSectionOpened = !noteSectionOpened
                }, text = "ADD NEW NOTE"
            )
        }
    }
    Divider(thickness = 3.dp)
}

@Composable
fun NoteList(notePager: Pager<Int, NoteEntity>, onDelete: (NoteEntity) -> Unit) {
    val pager = remember { notePager }
    val lazyPagingItems = pager.flow.collectAsLazyPagingItems()
    LazyColumn {
        itemsIndexed(lazyPagingItems) { index, item ->
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                if (item == null) {
                    Text("Title: N/A", fontSize = 20.sp)
                    Text("", fontSize = 20.sp)
                    Text("", fontSize = 20.sp)
                    Divider(thickness = 3.dp)
                } else {
                    Text(text = item.noteTitle, fontSize = 20.sp)
                    Text(text = item.noteDescription, fontSize = 20.sp)
                    Text(text = item.displayCreatedAt(), fontSize = 20.sp)
                    Text(modifier = Modifier
                        .padding(bottom = 10.dp)
                        .clickable {
                            onDelete(item)
                        }
                        .align(Alignment.End), text = "Delete", fontSize = 23.sp, color = Color.Magenta)
                    Divider(thickness = 3.dp)
                }
            }
        }

        if (lazyPagingItems.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }
}