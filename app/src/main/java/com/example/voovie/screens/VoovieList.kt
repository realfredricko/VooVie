package com.example.voovie.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun VoovieList(movies: List<Movie>) {
    Column {
        movies.forEach { movie ->
            VoovieItem(movie = movie)
        }
    }
}

@Composable
fun VoovieItem(movie: Movie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = rememberImagePainter("https://image.tmdb.org/t/p/w500${movie.poster_path}"),
            contentDescription = movie.title,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(movie.title, style = MaterialTheme.typography.h6)
            Text(movie.overview, style = MaterialTheme.typography.body2)
            Text("Release Date: ${movie.release_date}", style = MaterialTheme.typography.caption)
        }
    }
}