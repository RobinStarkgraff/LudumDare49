package scene


import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korge.input.onClick


class MenuScene : Scene() {
    override suspend fun Container.sceneInit() {


        val start = RoundRect(300.0, 50.0, 5.0, fill = Colors["#b9aea0"],).xy(500,300 )
        val starttext = text ("Start", textSize = 34.0, color = RGBA(0,0,0,255)).xy(600,305)
        val donate = RoundRect(300.0, 50.0, 5.0, fill = Colors["#b9aea0"],).xy(500,360)
        val donatetext = text ("Donate", textSize = 34.0, color = RGBA(0,0,0,255)).xy(600,365)
        val eric = text("diese Einnahmen gehen an Eric's neuen Laptop danke!", textSize = 34.0, color = RGBA(255,255,255,0)).xy(300,420)

        addChild(start)
        addChild(donate)
        addChild(starttext)
        addChild(donatetext)
        addChild(eric)
        donate.onClick {
            eric.color = RGBA(255,255,255,255)
        }
        start.onClick {
            sceneContainer.changeTo<Level1>()
        }
    }
}
