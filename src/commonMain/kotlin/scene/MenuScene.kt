package scene


import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korge.input.onClick
import helper.SoundPlayer


class MenuScene : Scene() {
    override suspend fun Container.sceneInit() {
        SoundPlayer.playBackgroundMusic("sample_music.mp3")


        val start = RoundRect(300.0, 50.0, 5.0, fill = Colors["#b9aea0"]).xy(500, 300)
        val starttext = text ("Start", textSize = 34.0, color = RGBA(0,0,0,255)).xy(600,305)
        val donate = RoundRect(300.0, 50.0, 5.0, fill = Colors["#b9aea0"]).xy(500, 360)
        val donatetext = text ("Donate", textSize = 34.0, color = RGBA(0,0,0,255)).xy(600,365)
        val eric = text("diese Einnahmen gehen an Eric's neuen Laptop danke!", textSize = 34.0, color = RGBA(255,255,255,0)).xy(300,420)

        val volumeiiii = RoundRect(50.0, 25.0, 5.0,fill = Colors["949494"],).xy(1100, 500)
        val volumeiii = RoundRect(50.0, 25.0, 5.0,fill = Colors["949494"],).xy(1100, 526)
        val volumeii = RoundRect(50.0, 25.0, 5.0,fill = Colors["00CD00"],).xy(1100, 552)
        val volumei = RoundRect(50.0, 25.0, 5.0,fill = Colors["00CD00"],).xy(1100, 578)
        val volume = RoundRect(50.0, 25.0, 5.0,fill = Colors["00CD00"],).xy(1100, 604)
        val mute = RoundRect(50.0, 25.0, 5.0,fill = Colors["949494"],).xy(1100, 630)
        val mutetext = text ("Mute", textSize = 10.0, color = RGBA(100,0,0,255)).xy(1114,637)

        addChild(start)
        addChild(donate)
        addChild(starttext)
        addChild(donatetext)
        addChild(eric)
        addChild(volume)
        addChild(volumei)
        addChild(volumeii)
        addChild(volumeiii)
        addChild(volumeiiii)
        addChild(mute)
        addChild(mutetext)

        donate.onClick {
            eric.color = RGBA(255,255,255,255)
        }
        start.onClick {
            sceneContainer.changeTo<Level1>()
        }
        mute.onClick{
            volume.fill = Colors["949494"]
            volumei.fill = Colors["949494"]
            volumeii.fill = Colors["949494"]
            volumeiii.fill = Colors["949494"]
            volumeiiii.fill = Colors["949494"]
            SoundPlayer.changeVolume(0.0)
        }
        volume.onClick{
            volume.fill = Colors["00CD00"]
            volumei.fill = Colors["949494"]
            volumeii.fill = Colors["949494"]
            volumeiii.fill = Colors["949494"]
            volumeiiii.fill = Colors["949494"]
            SoundPlayer.changeVolume(0.1)
        }
        volumei.onClick{
            volume.fill = Colors["00CD00"]
            volumei.fill = Colors["00CD00"]
            volumeii.fill = Colors["949494"]
            volumeiii.fill = Colors["949494"]
            volumeiiii.fill = Colors["949494"]
            SoundPlayer.changeVolume(0.25)
        }
        volumeii.onClick{
            volume.fill = Colors["00CD00"]
            volumei.fill = Colors["00CD00"]
            volumeii.fill = Colors["00CD00"]
            volumeiii.fill = Colors["949494"]
            volumeiiii.fill = Colors["949494"]
            SoundPlayer.changeVolume(0.45)
        }
        volumeiii.onClick{
            volume.fill = Colors["00CD00"]
            volumei.fill = Colors["00CD00"]
            volumeii.fill = Colors["00CD00"]
            volumeiii.fill = Colors["00CD00"]
            volumeiiii.fill = Colors["949494"]
            SoundPlayer.changeVolume(0.60)
        }
        volumeiiii.onClick{
            volume.fill = Colors["00CD00"]
            volumei.fill = Colors["00CD00"]
            volumeii.fill = Colors["00CD00"]
            volumeiii.fill = Colors["00CD00"]
            volumeiiii.fill = Colors["00CD00"]
            SoundPlayer.changeVolume(0.75)
        }
    }
}
