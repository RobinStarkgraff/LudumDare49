package scene

import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import helper.SoundPlayer
import helper.SpriteLibrary
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import manager.DownloadManager
import objects.MovingObject
import objects.Phone
import objects.Player
import objects.WifiRouter
import objects.interactables.StateSwapItem

class Level1 : Level() {
    override suspend fun Container.sceneInit() {
        player = Player(this@Level1)
        downloadManager = DownloadManager(this@Level1)
        val death = solidRect(50, 50).xy(200, 200)
        death.anchor(0.5, 1.0)
        //collisionList.add(death)
        val router = WifiRouter(500.0, 500.0, 300.0, null, this@Level1)
        val movingObject = MovingObject(
            25.0,
            25.0,
            5.0,
            listOf(Vector2D(0, 0), Vector2D(300, 0), Vector2D(300, 300), Vector2D(0, 300)),
            true,
            this@Level1
        )
        deathZoneList.add(movingObject.image)
        val phone = Phone()
        addChild(phone.container)
        StateSwapItem(this@Level1, sprite(anchorX = 0.0, anchorY = 1.0).xy(200.0,200.0), SpriteLibrary.static.DOOR_SWING_LEFT)

        GlobalScope.launch { SoundPlayer.playBackgroundMusic(SoundPlayer.BGM1) }
        sceneView.addUpdater {
            sceneView.children.sortBy {
                it.pos.y
            }
        }
    }

    override suspend fun nextScene() {
        println("Level2")
        sceneContainer.changeTo<Level2>()
        sceneDestroy()
    }
}
