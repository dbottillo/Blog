package com.danielebottillo.blog

sealed class Food {
    class Pizza(val slices: Int): Food()
    class Sushi(val pieces: Int): Food()
}