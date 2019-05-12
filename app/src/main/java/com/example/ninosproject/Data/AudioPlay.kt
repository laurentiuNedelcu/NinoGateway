@file:Suppress("DEPRECATION")

package com.example.ninosproject.Data

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Build

object AudioPlay{

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaPlayerAux: MediaPlayer
    private lateinit var soundPool: SoundPool
    private var length: Int = 0
    private var isAudioPlaying: Boolean = false



    fun playMusic(c: Context, id: Int, loop: Boolean) {
        if (!isAudioPlaying){
            mediaPlayer = MediaPlayer.create(c,id)
            mediaPlayer.isLooping = loop
            mediaPlayer.start()
            isAudioPlaying = true
        }
    }

    fun playMusicAux(c: Context, id: Int){
        mediaPlayerAux = MediaPlayer.create(c,id)
        mediaPlayerAux.isLooping = false
        mediaPlayerAux.start()
    }

    fun pauseMusic() {
        mediaPlayer.pause()
        length = mediaPlayer.currentPosition
    }

    fun resumeMusic() {
        mediaPlayer.seekTo(length)
        mediaPlayer.start()
    }

    fun disableSFX(){
        soundPool.release()
    }

    fun enableSFX(){
        soundPool = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val audio: AudioAttributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()

            SoundPool.Builder().setMaxStreams(5).setAudioAttributes(audio).build()
        } else {
            SoundPool(5,AudioManager.STREAM_MUSIC,100)
        }
    }

    fun stopMusic() {
        if(isAudioPlaying){
            mediaPlayer.stop()
            mediaPlayer.release()
            isAudioPlaying = false
        }
    }

    fun getSoundPool(): SoundPool {
        return soundPool
    }

}