package com.example.note.ui.theme.Presentation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.note.data.Constants
import com.example.note.data.Constants.noteDetailPlaceHolder
import com.example.note.ui.theme.NoteTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteDetail(
    noteId: Int,
    navController: NavController,
    viewModel: NoteViewModel
){
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
                            painter = rememberAsyncImagePainter(
                                ImageRequest
                                    .Builder(LocalContext.current)
                                    .data(data = Uri.parse(note.value.imageUri))
                                    .build()
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxHeight(0.3f)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = note.value.title,
                        modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = note.value.dateUpdated,
                        modifier = Modifier.padding(top = 24.dp, start = 12.dp, end = 24.dp),
                        color = Color.Gray
                        )
                    Text(
                        text = note.value.note,

                        modifier = Modifier.padding(12.dp)
                }
            }
        }
    }
}