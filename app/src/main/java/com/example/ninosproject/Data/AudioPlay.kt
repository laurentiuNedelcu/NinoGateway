@file:Suppress("DEPRECATION")

package com.example.ninosproject.Data

import android.content.Context
import android.media.MediaPlayer

object AudioPlay {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var soundEffectsPlayer: MediaPlayer
    private var length: Int = 0
    private var isAudioPlaying: Boolean = false
    private var sfx: String = ""
    private var musica: String = ""
    var on = ""
    var off = ""

    fun playMusic(c: Context, id: Int, loop: Boolean) {
        if (!isAudioPlaying) {
            mediaPlayer = MediaPlayer.create(c, id)
            mediaPlayer.isLooping = loop
            mediaPlayer.start()
            isAudioPlaying = true
            if (musica == off) pauseMusic()
        }
    }

    fun playSfx(c: Context, id: Int) {
        if (sfx == on) {
            soundEffectsPlayer = MediaPlayer.create(c, id)
            soundEffectsPlayer.isLooping = false
            soundEffectsPlayer.start()
        }
    }

    fun getStrings(s1: String, s2: String) {
        on = s1
        off = s2
    }

    fun pauseMusic() {
        if (musica == off) {
            mediaPlayer.pause()
            length = mediaPlayer.currentPosition
            isAudioPlaying = false
        }
    }

    fun pause() {
        mediaPlayer.pause()
        length = mediaPlayer.currentPosition
        isAudioPlaying = false
    }

    fun resumeMusic() {
        if (musica == on && !isAudioPlaying) {
            mediaPlayer.seekTo(length)
            mediaPlayer.start()
            isAudioPlaying = true
        }
    }

    fun stopMusic() {
        if (!isAudioPlaying) {
            resumeMusic()
        }
        mediaPlayer.stop()
        mediaPlayer.release()
        isAudioPlaying = false
    }

    fun updateMusica() {
        if (musica == off)
            pauseMusic()
        else {
            resumeMusic()
        }
    }

    fun setSFXValue(sfx: String) {
        this.sfx = sfx
    }

    fun getSFX(): String {
        return sfx
    }

    fun setMusicaValue(musica: String) {
        this.musica = musica
    }

    fun getMusica(): String {
        return musica
    }
}