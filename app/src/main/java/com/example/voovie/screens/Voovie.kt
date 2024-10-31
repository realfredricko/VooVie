package com.example.voovie.screens

import android.content.Intent
import android.graphics.drawable.Icon
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voovie.data.model.Movie

@Composable
fun Voovie(
    voovieViewModel: VoovieViewModel,
    speechRecognizer: SpeechRecognizer,speechRecognizerIntent: Intent
) {
//    var query by remember { mutableStateOf(TextFieldValue("")) }
//    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("VooVie", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))
IconButton(onClick = {
speechRecognizer.startListening(
    speechRecognizerIntent
)
},
    modifier = Modifier
        .size(30.dp),
    ){
Icon(Icons.Filled.Mic,
    contentDescription = "Microphone",
    modifier = Modifier.
size(30.dp))
}
//        BasicTextField(
//            value = query,
//            onValueChange = { query = it },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp),
//            decorationBox = { innerTextField ->
//                Box(
//                    modifier = Modifier
//                        .background(MaterialTheme.colorScheme.primaryContainer)
//                        .padding(8.dp)
//                ) {
//                    if (query.text.isEmpty()) Text("Search for a movie")
//                    innerTextField()
//                }
//            }
//        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
//            scope.launch {
//                viewModel.searchMovies(query.text)
//            }
        })
        {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

//        if (viewModel.movies.isNotEmpty()) {
//            VoovieList(movies = viewModel.movies)
//        }
    }
}

