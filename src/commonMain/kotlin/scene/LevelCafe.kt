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
import objects.WifiRouter
import objects.interactables.ObjectiveItem
import objects.interactables.PickupItem
import objects.interactables.StateSwapItem
import physics.PhysicsBody
import physics.primitives.BoxCollider

class LevelCafe : Level() {
    override suspend fun Container.sceneInit() {
        spawnpoint = Vector2D(830, 377)
        player = Player(this@LevelCafe)
        phone = Phone(this@LevelCafe)
        SoundPlayer.playBackgroundMusic(SoundPlayer.BGM1, -0.28)
        drawImages()
        //Specific setup
        phone!!.downloadDisabled = true

        sceneView.addUpdater { if (objective.completed) phone!!.downloadDisabled = false }
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

        il.sprite(SpriteLibrary.DOOR, anchorY = 1.0).xy(808, 470)


        val keys = PickupItem(this, il.image(SpriteLibrary.KEY_INGAME).xy(350, 244), SpriteLibrary.KEY_INVENTORY)

        val hiddenBarista = bg.sprite(SpriteLibrary.KEY_INGAME).xy(820, 200)

        objective = ObjectiveItem(
            StateSwapItem(
                this,
                hiddenBarista,
                SpriteLibrary.LEVEL1_LAMP,
                null,
                null,
                inventoryItem = keys,
                interactionDistance = 100.0
            )
        )

        WifiRouter(600.0, 150.0, 200.0, this)

        bg.sprite(SpriteLibrary.CAFE_BACKGROUND).anchor(0.5, 0.0).xy(RESOLUTION.x / 2, -50.0)

        //Colliders
        BoxCollider(Vector2D(650, 160), 700.0, 5.0, level).render()
        BoxCollider(Vector2D(650, 470), 700.0, 5.0, level).render()
        BoxCollider(Vector2D(337, 490), 15.0, 700.0, level).render()
        BoxCollider(Vector2D(933, 490), 15.0, 700.0, level).render()

        BoxCollider(Vector2D(867, 156), 350.0, 230.0, level).render()
        BoxCollider(Vector2D(353, 160), 350.0, 330.0, level).render()
    }

    override suspend fun nextScene() {
        super.nextScene()
        sceneDestroy()
        sceneContainer.changeTo<MenuScene>()
    }
}
