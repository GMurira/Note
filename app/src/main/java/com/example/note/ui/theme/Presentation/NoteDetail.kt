package com.example.note.ui.theme.Presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.note.data.Constants
import com.example.note.data.Constants.noteDetailPlaceHolder
import com.example.note.ui.theme.NoteTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteDetail( noteId: Int, navController: NavController, viewModel: NoteViewModel ){
    /**
     * Define a coroutine scope used to collect the note details
     */
    val scope = rememberCoroutineScope()

    val note = remember {
        mutableStateOf(noteDetailPlaceHolder)
    }

    LaunchedEffect(true) {
        scope.launch(Dispatchers.IO) {
            note.value = viewModel.getNote(noteId) ?: noteDetailPlaceHolder
        }
    }
    NoteTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Scaffold ( topBar = { GenericAppBar(
                title = note.value.title,
                onIconClick = { navController.navigate(Constants.noteDetailNavigation(note.value.id ?: 0)) },
                icon = {  },
                iconState = remember {
                    mutableStateOf(true)
                }
            )}){
                Column (modifier = Modifier.fillMaxSize()){
                    if (note.value.imageUri != null && note.value.imageUri!!.isNotEmpty()){
                        Image(
                            painter = rememberAyncImagePainter{
                                                              ImageRequest
                            },
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}