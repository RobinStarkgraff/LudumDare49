package scene

import RESOLUTION
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import helper.SoundPlayer
import helper.SpriteLibrary
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import objects.Phone
import objects.Player
import objects.interactables.ObjectiveItem
import objects.interactables.StateSwapItem
import physics.PhysicsBody
import physics.primitives.BoxCollider

class LevelCafe : Level() {
    override suspend fun Container.sceneInit() {
        spawnpoint = Vector2D(500, 400)
        player = Player(this@LevelCafe)
        phone = Phone(this@LevelCafe)
        SoundPlayer.playBackgroundMusic(SoundPlayer.BGM1, -0.28)
        drawImages()
    }

    override fun downloadComplete() {
        sceneView.addUpdater {
            if (objective.completed) {
                GlobalScope.launch { nextScene() }
            }
        }
    }

    override lateinit var objective: ObjectiveItem

    override suspend fun drawImages() {
        super.drawImages()
        val level = PhysicsBody()

        bg.sprite(SpriteLibrary.CAFE_BACKGROUND).anchor(0.5, 0.0).xy(RESOLUTION.x / 2, -50.0)

        BoxCollider(il.sprite(SpriteLibrary.CAFE_CHAIR).anchor(0.5, 0.9).xy(500, 360), level).cutInHalf()
        BoxCollider(il.sprite(SpriteLibrary.CAFE_CHAIR_ALT).anchor(0.5, 0.9).xy(400, 360), level).cutInHalf()
        BoxCollider(il.sprite(SpriteLibrary.CAFE_TABLE).anchor(0.5, 0.9).xy(450, 360), level).cutInHalf()

        BoxCollider(il.sprite(SpriteLibrary.CAFE_CHAIR).anchor(0.5, 0.9).xy(650, 415), level).cutInHalf()
        BoxCollider(il.sprite(SpriteLibrary.CAFE_CHAIR_ALT).anchor(0.5, 0.9).xy(550, 415), level).cutInHalf()
        BoxCollider(il.sprite(SpriteLibrary.CAFE_TABLE_ALT).anchor(0.5, 0.9).xy(600, 415), level).cutInHalf()

        BoxCollider(il.sprite(SpriteLibrary.CAFE_CHAIR_ALT).anchor(0.5, 0.9).xy(550, 285), level).cutInHalf()
        BoxCollider(il.sprite(SpriteLibrary.CAFE_TABLE_ALT).anchor(0.5, 0.9).xy(590, 315), level).cutInHalf()

        BoxCollider(il.sprite(SpriteLibrary.CAFE_CHAIR).anchor(0.5, 0.9).xy(435, 430), level).cutInHalf()
        BoxCollider(il.sprite(SpriteLibrary.CAFE_TABLE).anchor(0.5, 0.9).xy(385, 440), level).cutInHalf()

        val levelDoor = il.sprite(anchorY = 1.0).xy(808, 470)
        StateSwapItem(this, levelDoor, SpriteLibrary.DOOR_SWING_RIGHT, SoundPlayer.DOORKEYS, SoundPlayer.DOORKEYS)

        //Colliders
        BoxCollider(Vector2D(650, 160), 700.0, 5.0, level)
        BoxCollider(Vector2D(650, 470), 700.0, 5.0, level)
        BoxCollider(Vector2D(337, 490), 15.0, 700.0, level)
        BoxCollider(Vector2D(933, 490), 15.0, 700.0, level)

        BoxCollider(Vector2D(867, 156), 350.0, 230.0, level)
        BoxCollider(Vector2D(353, 160), 350.0, 330.0, level)
    }

    override suspend fun nextScene() {
        super.nextScene()
        sceneDestroy()
        sceneContainer.changeTo<MenuScene>()
    }
}
