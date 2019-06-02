package com.example.ninosproject.Data

import com.example.ninosproject.R

object LevelGallery{

    private val level_map_1 = arrayOf(
        intArrayOf(0,   0,  0,  0,  0,  0,  0, 10,  0,  1,  0,  0,  0,  1, 30,  0,  0,  0,  0, 43,  0,  1,  0,  0,  0),
        intArrayOf(0,  -1,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0, 70,  1,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0),
        intArrayOf(2,   2,  2,  7,  0,  0,  0,  8,  2,  5,  0,  0,  0,  6,  2,  7,  0,  0,  0,  8,  2,  5,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(50,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0, 60,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 61,  0, 41,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(2,   2,  2,  7,  0,  0,  0,  8,  2,  3,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 20),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0, 20,  1,  0, 42,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0, 10,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0)
    )
    private val level_map_2 = arrayOf(
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  10, 0,  1,  0, 41,  0,  1, 41,  0,  1, 43,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  1,  0,  0,  1,  0,  0,  0,  0,  0, 10,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  8,  5,  0,  0,  6,  7,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  70, 1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0, -1,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(2,   7,  0,  0,  0,  0,  0,  0,  8,  5,  0,  0,  0,  0,  0, 61,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(50,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(2,   7,  0,  0,  0,  0,  0,  0,  8,  3,  0,  0,  0,  4,  7,  0,  0,  8,  2,  3,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  1,  0,  0,  0,  0, 70,  1,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  20, 1,  0,  42, 0,  1,  0,  0,  0,  0,  0,  1,  0,  0, 10,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  1, 30,  0,  0,  10, 0,  1,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0)
    )
    private val level_map_3 = arrayOf(
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  4,  2,  2,  2,  2,  3, 10,  0,  0,  0,  0,  0,  0, 10,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0, 70,  1, 30,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  8,  3,  0,  0,  0),
        intArrayOf(4,   2,  2,  2,  2,  5,  0,  0,  0,  0,  6,  2,  2,  2,  7,  0,  0,  0,  0,  0,  0,  6,  3,  0,  0),
        intArrayOf(1,  50,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 62,  0,  0,  0,  0,  0,  0, 41,  0,  1,  0,  0),
        intArrayOf(1,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 63,  0,  0,  0,  1,  0,  0),
        intArrayOf(6,   2,  2,  2,  3,  0,  0,  4,  2,  7,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 42,  0,  1,  0,  0),
        intArrayOf(0,   0,  0,  0,  1,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0),
        intArrayOf(0,   0,  0,  0,  6,  2,  2,  5,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  5,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 10,  0,  8,  5,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0)
    )
    private val level_map_4 = arrayOf(
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0, 63,  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 63, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0,  42,  0,  0,  0,  0,  0,  0, 0, 0, 0, 42, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0, 0, 63, 0, 0, 0, 0, 63, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(50,  0,  0,  0,  0,  0,  0,  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0,   0,  0,  0, 70,  8,  2,  2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 7, 0, 0, 0, 0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0, 64,  0, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 0),
        intArrayOf(0,  43,  0,  0,  0,  0,  0,  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 41, 0),
        intArrayOf(0,   0,  0,  0,  0,  0, 64,  0, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0, 0, 0, 0, 0, 0, 0, 0, 41, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0)
    )
    private val level_map_5 = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 70, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 60, 0, 60, 0, 65, 0, 65, 0, 0, 0, 60, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65, 0, 65, 0, 64, 0, 65, 0, 65, 0, 10, 0, 41, 0),
        intArrayOf(50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 65, 0, 65, 0, 65, 0, 65, 0, 10, 0, 65, 0, 65, 0, 65, 0, 65, 0, 64, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(2, 2, 2, 2, 2, 2, 2, 7, 0, 0, 0, 65, 0, 0, 0, 0, 0, 0, 0, 65, 0, 65, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 60, 0, 0, 0, 0, 0, 0, 0, 42, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    )
    private val level_map_6 = arrayOf(
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 60,  0,  0,  0,  0,  0,  0,  0,  0, 43,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(2,   2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  7,  0,  0,  8,  2,  2,  2,  2,  2,  2,  2,  7),
        intArrayOf(30,  0,  0,  0,  0,  0,  0,  0, 63,  0,  0,  0,  0,  0,  0,  0, 64,  0,  0,  0, 64,  0, 41,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(10,  0,  0,  0, 63,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  2,  2,  2,  2,  2,  2,  2,  7),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0, 70,  8,  7,  0),
        intArrayOf(2,   2,  2,  2,  2,  2,  2,  2,  7,  0,  0,  8,  2,  2,  2,  2,  5,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(50,  0,  0, 61,  0,  0,  0,  0,  0,  0,  0,  0,  0, 61,  0,  0,  0,  0,  0,  0, -1,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(2,   2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  7),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0)
    )
    private val level_map_7 = arrayOf(
        intArrayOf(0, 0, 0, 0, 42, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 43, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 62, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 61, 0, 0, 0, 0, -1, 0, 0),
        intArrayOf(0, 41, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 42, 0, 65, 0, 65, 0, 65, 0, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 60, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 41, 0, 65, 0, 65, 0, 65, 0, 64, 0, 0, 0, 0, 0, 0, 0, 43, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 61, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 61, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 42, 0, 0, 70, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 30, 0, 0, 0, 42, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    )
    private val level_map_8 = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 43, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 65, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 7),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 10, 0, 65, 0, 65, 0, 65, 0, 65, 0, 64, 0, 65, 0, 65, 0, 42, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 65, 0, 65, 0, 64, 0, 65, 0, 65, 0, 10, 0, 65, 0, 65, 0, 4, 2, 2, 7),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 60, 0, 65, 0, 10, 0, 65, 0, 65, 0, 65, 0, 65, 0, 1, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0),
        intArrayOf(2, 2, 2, 2, 2, 2, 7, 0, 0, 65, 0, 65, 0, 65, 0, 60, 0, 4, 2, 2, 2, 5, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 1, 50, 0, -1, 0, 10, 0, 0, 0, 43, 0, 1, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0)
    )
    private val level_map_9 = arrayOf(
        intArrayOf(0,   0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 65,  0, 65,  0, 64,  0, 65,  0),
        intArrayOf(0,   0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  2,  2,  7,  0,  0,  8,  7),
        intArrayOf(0,  42,  0, 41,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0, 64,  0,  0,  0, 10,  0),
        intArrayOf(0,   0,  0,  0,  0,  1, 43,  0,  4,  2,  7,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0, 70,  1,  0,  0,  1,  0,  0,  0,  0,  0,  0, 43,  0,  1,  0,  0,  0,  0, 62, 43,  0),
        intArrayOf(0,   0,  0,  0,  0,  6,  2,  2,  5,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0, -1,  0,  0,  0,  0,  0,  0, 63,  0,  8,  2,  2,  2,  2,  5, 50,  0, 65,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  8,  2,  2,  2,  2,  3,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  4,  2,  2,  3,  0,  0,  0,  0,  0,  0, 43,  0,  1, 60,  0, 64,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  1, 30,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  1,  0,  0,  6,  2,  7,  0,  0,  0,  0, 61,  0,  1,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0)
    )
    private val level_map_10 = arrayOf(
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0),
        intArrayOf(0,   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0)
    )

    private val level_1: Nivel = Nivel(level_map_1, R.drawable.enigma1,2)
    private val level_2: Nivel = Nivel(level_map_2, R.drawable.enigma2,3)
    private val level_3: Nivel = Nivel(level_map_3, R.drawable.enigma3,1)
    private val level_4: Nivel = Nivel(level_map_4, R.drawable.enigma4,6)
    private val level_5: Nivel = Nivel(level_map_5, R.drawable.enigma5,1)
    private val level_6: Nivel = Nivel(level_map_6, R.drawable.enigma6,1)
    private val level_7: Nivel = Nivel(level_map_7, R.drawable.enigma7,12)
    private val level_8: Nivel = Nivel(level_map_8, R.drawable.enigma8,8)
    private val level_9: Nivel = Nivel(level_map_9, R.drawable.enigma9,12)
    private val level_10: Nivel = Nivel(level_map_10, R.drawable.enigma10,1)

    val levels = arrayListOf(level_1, level_2, level_3, level_4, level_5, level_6, level_7, level_8, level_9, level_10)
}
