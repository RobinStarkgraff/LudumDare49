package scene

import RESOLUTION
import com.soywiz.klock.milliseconds
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

class Level1 : Level() {
    override suspend fun Container.sceneInit() {
        spawnpoint = Vector2D(430, 250)
        player = Player(this@Level1)
        phone = Phone(this@Level1)
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
        bg.sprite(SpriteLibrary.LEVEL1_FLAT).anchor(0.5, 0.0).xy(RESOLUTION.x / 2, -50.0)
        bg.sprite(SpriteLibrary.LEVEL1_LIGHT).anchor(0.5, 0.0).xy(RESOLUTION.x / 2, -50.0).blendMode = BlendMode.ADD
        il.sprite(SpriteLibrary.LEVEL1_BATH_WALL).anchor(0.0, 0.9).xy(691, 537)
        il.sprite(SpriteLibrary.LEVEL1_KITCHEN_WALL).anchor(0.0, .7).xy(691, 346)
        fg.sprite(SpriteLibrary.LEVEL1_SIDE_WALLS).anchor(0.5, 0.0).xy(RESOLUTION.x / 2, -50.0)
        val level = PhysicsBody()
        val bed = il.sprite(SpriteLibrary.LEVEL1_BED).xy(340, 130)
        BoxCollider(bed, level)
        bed.playAnimationLooped(spriteDisplayTime = 200.milliseconds)
        val lamp = il.sprite(anchorY = 1.0).xy(450, 190)
        StateSwapItem(this, lamp, SpriteLibrary.LEVEL1_LAMP, SoundPlayer.SWITCHOFF, SoundPlayer.SWITCHON)
        val kitchenDoor = il.sprite(anchorY = 1.0).xy(700, 283)
        val kitchenDoorCol = BoxCollider(Vector2D(kitchenDoor.pos.x, kitchenDoor.pos.y - 100), 10.0, 150.0, level)
        StateSwapItem(this, kitchenDoor, SpriteLibrary.DOOR_SWING_LEFT, SoundPlayer.DOORCLOSE, SoundPlayer.DOOROPEN, collider = kitchenDoorCol)
        val bathroomDoor = il.sprite(anchorY = 1.0).xy(700, 478)
        val bathroomDoorCol = BoxCollider(Vector2D(bathroomDoor.pos.x, bathroomDoor.pos.y - 100), 10.0, 150.0, level)
        StateSwapItem(this, bathroomDoor, SpriteLibrary.DOOR_SWING_LEFT, SoundPlayer.DOORCLOSE, SoundPlayer.DOOROPEN, collider = bathroomDoorCol)
        BoxCollider(il.sprite(SpriteLibrary.LEVEL1_NIGHT_STAND).xy(410, 160), level)
        BoxCollider(il.sprite(SpriteLibrary.LEVEL1_DESK).xy(550, 130), level)
        val keys = PickupItem(this, il.image(SpriteLibrary.KEY_INGAME).xy(933, 230), SpriteLibrary.KEY_INVENTORY)
        il.sprite(SpriteLibrary.LEVEL1_PLANT).xy(660, 130)
        BoxCollider(il.sprite(SpriteLibrary.LEVEL1_COUCH).xy(340, 450), level)
        BoxCollider(il.sprite(SpriteLibrary.LEVEL1_COUCH_TABLE).xy(420, 490), level)
        BoxCollider(il.sprite(SpriteLibrary.LEVEL1_BOOK_SHELF).anchor(0.0, 0.9).xy(335, 400), level)
        BoxCollider(il.sprite(SpriteLibrary.LEVEL1_KITCHEN).xy(780, 130), level)
        BoxCollider(il.sprite(SpriteLibrary.LEVEL1_BATHTUB).anchor(0, 1).xy(808, 400), level)
        il.sprite(SpriteLibrary.LEVEL1_KITCHEN_SHELF).xy(925, 219)
        BoxCollider(il.sprite(SpriteLibrary.LEVEL1_ROUTER_TABLE).anchor(0, 1).xy(850, 554), level)
        val router = il.sprite(SpriteLibrary.LEVEL1_ROUTER).anchor(0, 1).xy(850, 554)
        router.playAnimationLooped(spriteDisplayTime = 200.milliseconds)
        WifiRouter(router.x, router.y, 400.0, this)

        val levelDoor = il.sprite(anchorY = 1.0).xy(811, 655)
        objective = ObjectiveItem(
            StateSwapItem(
                this,
                levelDoor,
                SpriteLibrary.DOOR_SWING_RIGHT,
                SoundPlayer.DOORCLOSE,
                SoundPlayer.DOOROPEN,
                inventoryItem = keys
            )
        )

        //Colliders
        BoxCollider(Vector2D(650, 660), 700.0, 5.0, level)
        BoxCollider(Vector2D(650, 160), 700.0, 5.0, level)
        BoxCollider(Vector2D(330, 490), 5.0, 700.0, level)
        BoxCollider(Vector2D(947, 490), 5.0, 700.0, level)
        BoxCollider(Vector2D(850, 340), 300.0, 30.0, level)
        BoxCollider(Vector2D(705, 325), 10.0, 120.0, level)
        BoxCollider(Vector2D(850, 520), 300.0, 30.0, level)
        BoxCollider(Vector2D(705, 490), 10.0, 80.0, level)
        BoxCollider(Vector2D(705, 160), 10.0, 60.0, level)
    }

    override suspend fun nextScene() {
        sceneDestroy()
        sceneContainer.changeTo<Level2>()
    }
}
