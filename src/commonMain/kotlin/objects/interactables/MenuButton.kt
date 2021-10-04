package objects.interactables


import com.soywiz.korge.input.onClick
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.RoundRect
import com.soywiz.korge.view.Text
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korio.async.launch
import kotlinx.coroutines.GlobalScope
import scene.Level
import scene.MenuScene


class MenuButton(var container: Container) {
    var menu: RoundRect
    var menutext: Text

    init {
        menutext = Text("Main Menu", textSize = 34.0, color = RGBA(0, 0, 0, 255)).xy(1070,100)
        menu = RoundRect(200.0, 50.0, 5.0, fill = Colors["#b9aea0"]).xy(1050,100)
        container.addChild(menu)
        container.addChild(menutext)

        menu.onClick{
            GlobalScope.launch {
                Level.SCENE_CONTAINER.changeTo<MenuScene>()
            }
        }
    }
}
