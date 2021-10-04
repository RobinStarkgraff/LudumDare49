package scene


import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import helper.SoundPlayer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MenuScene : Scene() {
    override suspend fun Container.sceneInit() {
        SoundPlayer.playBackgroundMusic(SoundPlayer.BGMMENU)


        val start = roundRect(300.0, 50.0, 5.0, fill = Colors["#b9aea0"]).xy(500, 300)
        val starttext = text("Start", textSize = 34.0, color = RGBA(0, 0, 0, 255)).xy(600, 305)
        val donate = roundRect(300.0, 50.0, 5.0, fill = Colors["#b9aea0"]).xy(500, 360)
        val donatetext = text("Donate", textSize = 34.0, color = RGBA(0, 0, 0, 255)).xy(600, 365)
        val eric = text(
            "diese Einnahmen gehen an Eric's neuen Laptop danke!",
            textSize = 34.0,
            color = RGBA(255, 255, 255, 0)
        ).xy(300, 420)

        val volumeiiii = roundRect(50.0, 25.0, 5.0, fill = Colors["949494"]).xy(1100, 500)
        val volumeiii = roundRect(50.0, 25.0, 5.0, fill = Colors["949494"]).xy(1100, 526)
        val volumeii = roundRect(50.0, 25.0, 5.0, fill = Colors["949494"]).xy(1100, 552)
        val volumei = roundRect(50.0, 25.0, 5.0, fill = Colors["949494"]).xy(1100, 578)
        val volume = roundRect(50.0, 25.0, 5.0, fill = Colors["949494"]).xy(1100, 604)
        val mute = roundRect(50.0, 25.0, 5.0, fill = Colors["949494"]).xy(1100, 630)
        val mutetext = text("Mute", textSize = 10.0, color = RGBA(100, 0, 0, 255)).xy(1114, 637)

        val volumeMap = mapOf(volume to 0.2, volumei to 0.4, volumeii to 0.6, volumeiii to 0.8, volumeiiii to 1.0)


        donate.onClick {
            GlobalScope.launch {SoundPlayer.playSound(SoundPlayer.MENUCLICK)}
            eric.color = RGBA(255, 255, 255, 255)
        }
        start.onClick {
            GlobalScope.launch {SoundPlayer.playSound(SoundPlayer.MENUCLICK)}
            sceneContainer.changeTo<Level2>()
        }

        val grey = Colors["949494"]
        val green = Colors["00CD00"]

        fun volumeColorChange(button: RoundRect) {
            for ((key, value) in volumeMap.entries) {
                if (key == button) {
                    key.fill = green
                    SoundPlayer.changeVolume(value)
                } else if (value < volumeMap.getValue(button)) {
                    key.fill = green
                } else {
                    key.fill = grey
                }
            }
        }

        mute.onClick {
            for ((key, value) in volumeMap.entries) {
                GlobalScope.launch {SoundPlayer.playSound(SoundPlayer.MENUCLICK)}
                key.fill = grey
                SoundPlayer.changeVolume(0.0)
            }
        }

        for ((key, value) in volumeMap.entries) {
            key.onClick {
                GlobalScope.launch {SoundPlayer.playSound(SoundPlayer.MENUCLICK)}
                volumeColorChange(key)
            }
        }
    }
}
