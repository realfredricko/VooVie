package com.example.voovie.screens

import android.Manifest
import android.content.Intent
import android.graphics.drawable.Icon
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voovie.data.model.Movie
import java.util.Locale

@Composable
fun Voovie(
    viewModel: VoovieViewModel,
    navController:NavController,
    speechRecognizer: SpeechRecognizer,speechRecognizerIntent: Intent
) {
    var isListening by remember { mutableStateOf(false) }
//    val scope = rememberCoroutineScope()
//val movies = viewModel.collectAsState(initial = emptyList())
    //Permission Launcher for microphone
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
//Success
            isListening(true)
            //Start speech recognition
            startSpeechRecognition.launch(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH.apply {
                putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                putExtra(RecognizerIntent.EXTRA_PROMPT, "Say a movie name...")
            }))
        }
    }
    //Speech recognition launcher
    val startSpeechRecognition = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        isListening = false
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            val spokenText =
                result.data?.getStringArrayExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0) ?: ""
            //Navigate to movie list screen with the spoken tex
            navController.navigate("VoovieList/$spokenText")
        }
    }
    Surface(
        modifier = Modifier
            .fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "VooVie",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 32.dp)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {
                        permissionLauncher.launch(
                            Manifest.permission.RECORD_AUDIO
                        )
                    },
                    modifier = Modifier
                        .size(120.dp),
                ) {
                    Icon(
                        Icons.Filled.Mic,
                        contentDescription = "Microphone",
                        modifier = Modifier.size(80.dp),
                        tint = if (isListening) Color.Red else MaterialTheme.colorScheme.primary
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            //Text helper
            Text(
                text = if (isListening) "Listening..." else "Tap microphone to search movie",
                modifier = Modifier.padding(bottom = 32.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}j

