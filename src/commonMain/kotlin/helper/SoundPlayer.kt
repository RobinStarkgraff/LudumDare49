package helper

import com.soywiz.korau.sound.SoundChannel
import com.soywiz.korau.sound.readMusic
import com.soywiz.korau.sound.readSound
import com.soywiz.korio.file.std.resourcesVfs

class SoundPlayer {
    companion object {
        private const val SOUND_FOLDER = "sound/"
        private const val MUSIC_FOLDER = "music/"

        // Every sound file should be referenced by a const in order for us to reference them across the entire code
        const val SAMPLE_SOUND = "sample_sound.mp3"
        const val SAMPLE_MUSIC = "sample_music.mp3"
        const val BGM1 = "bgm_08.mp3"

        var volume = 0.75

        private var musicChannel: SoundChannel? = null

        suspend fun playSound(soundPath: String) {
            val sound = resourcesVfs[SOUND_FOLDER + soundPath].readSound()
            sound.volume = volume
            sound.play()
        }

        suspend fun playBackgroundMusic(musicPath: String) {
            musicChannel?.stop()
            val music = resourcesVfs[MUSIC_FOLDER + musicPath].readMusic()
            musicChannel = music.playForever()
            musicChannel?.volume = volume
        }

        fun changeVolume(volume: Double) {
            this.volume = volume
            musicChannel?.volume = volume
        }
    }
}
