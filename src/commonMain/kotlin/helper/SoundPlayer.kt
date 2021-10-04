package helper

import com.soywiz.korau.sound.*
import com.soywiz.korio.file.std.resourcesVfs

class SoundPlayer {
    companion object {
        private val continuousSounds = mutableListOf<SoundChannel>()

        private const val SOUND_FOLDER = "sound/"
        private const val MUSIC_FOLDER = "music/"

        // Every sound file should be referenced by a const in order for us to reference them across the entire code
        const val SAMPLE_SOUND = "sample_sound.mp3"
        const val SAMPLE_MUSIC = "sample_music.mp3"
        const val BGM1 = "bgm_08.mp3"

        const val BGMMENU = "bgm_menu_01.mp3"

        const val FOOTSTEPS = "footsteps2long.mp3"

        const val DOOROPEN = "door_open.wav"
        const val DOORCLOSE = "door_close.wav"

        const val SWITCHON = "switch_on.mp3"
        const val SWITCHOFF = "switch_off.mp3"

        const val MENUCLICK = "menuclick.mp3"

        var volume = 0.0

        private var musicChannel: SoundChannel? = null

        suspend fun setContinuousSound(soundPath: String): SoundChannel {
            val sound = resourcesVfs[SOUND_FOLDER + soundPath].readSound()
            val channel = sound.playForever()
            channel.volume = volume
            channel.togglePaused()
            return channel
        }

        fun startContinuousSound(channel: SoundChannel) {
            if (channel.playing) {
                return
            }

            channel.reset()
            channel.togglePaused()
        }

        fun stopContinuousSound(channel: SoundChannel) {
            if (!channel.playing) {
                return
            }

            channel.togglePaused()
        }

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
        suspend fun playBackgroundMusic(musicPath: String, volumeadjust: Double) {
            musicChannel?.stop()
            val music = resourcesVfs[MUSIC_FOLDER + musicPath].readMusic()
            musicChannel = music.playForever()
            if (volume == 0.0) musicChannel?.volume = volume else musicChannel?.volume = volume + volumeadjust
        }

        fun changeVolume(volume: Double) {
            this.volume = volume
            musicChannel?.volume = volume
            for (sound in continuousSounds) {
                sound.volume = volume
            }
        }
    }
}
