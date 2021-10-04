package scene

import COLLISION_COLOR
import RESOLUTION
import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import objects.Player
import helper.SoundPlayer
import helper.SpriteLibrary
import manager.DownloadManager
import objects.interactables.StateSwapItem

class Level1 : Level() {
    override suspend fun Container.sceneInit() {
        spawnpoint = Vector2D(430, 250)
        player = Player(this@Level1)
        downloadManager = DownloadManager(this@Level1)
        SoundPlayer.playBackgroundMusic(SoundPlayer.BGM1)
        drawImages()
    }

    override suspend fun drawImages() {
        super.drawImages()
        bg.sprite(SpriteLibrary.LEVEL1_FLAT).anchor(0.5, 0.0).xy(RESOLUTION.x/2, -50.0)
        fg.sprite(SpriteLibrary.LEVEL1_LIGHT).anchor(0.5, 0.0).xy(RESOLUTION.x/2, -50.0).blendMode = BlendMode.ADD
        il.sprite(SpriteLibrary.LEVEL1_BATH_WALL).anchor(0.0, 0.9).xy(691, 537)
        il.sprite(SpriteLibrary.LEVEL1_KITCHEN_WALL).anchor(0.0, .7).xy(691, 346)
        fg.sprite(SpriteLibrary.LEVEL1_SIDE_WALLS).anchor(0.5, 0.0).xy(RESOLUTION.x/2, -50.0)

        val bed = il.sprite(SpriteLibrary.LEVEL1_BED).xy(340, 130)
        collisionList.add(bed)
        bed.playAnimationLooped(spriteDisplayTime = 200.milliseconds)
        val kitchenDoor = il.sprite(anchorY = 1.0).xy(700, 283)
        StateSwapItem(this, kitchenDoor, SpriteLibrary.DOOR_SWING_LEFT)
        val bathroomDoor = il.sprite(anchorY = 1.0).xy(700, 478)
        StateSwapItem(this, bathroomDoor, SpriteLibrary.DOOR_SWING_LEFT)
        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_NIGHT_STAND).xy(410, 160))
        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_DESK).xy(550, 130))
        il.sprite(SpriteLibrary.LEVEL1_PLANT).xy(660, 130)
        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_COUCH).xy(340, 450))
        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_COUCH_TABLE).xy(420, 490))
        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_BOOK_SHELF).anchor(0.0, 0.9).xy(335, 400))
        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_KITCHEN).xy(780, 130))
        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_BATHTUB).anchor(0, 1).xy(808, 400))
        il.sprite(SpriteLibrary.LEVEL1_KITCHEN_SHELF).xy(925, 219)
        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_ROUTER_TABLE).anchor(0, 1).xy(850, 554))
        il.sprite(SpriteLibrary.LEVEL1_ROUTER).anchor(0, 1).xy(850, 554).playAnimationLooped(spriteDisplayTime = 200.milliseconds)

        //Colliders
        collisionList.add(fg.solidRect(700, 10, COLLISION_COLOR).xy(300, 650))
        collisionList.add(fg.solidRect(700, 10, COLLISION_COLOR).xy(300, 140))
        collisionList.add(fg.solidRect(10, 700, COLLISION_COLOR).xy(325, 140))
        collisionList.add(fg.solidRect(10, 700, COLLISION_COLOR).xy(947, 140))
        collisionList.add(fg.solidRect(300, 30, COLLISION_COLOR).xy(700, 315))
        collisionList.add(fg.solidRect(10, 120, COLLISION_COLOR).xy(700, 270))
        collisionList.add(fg.solidRect(300, 30, COLLISION_COLOR).xy(700, 495))
        collisionList.add(fg.solidRect(10, 80, COLLISION_COLOR).xy(700, 450))
        collisionList.add(fg.solidRect(10, 60, COLLISION_COLOR).xy(700, 130))
    }

    override suspend fun nextScene() {
        println("Level2")
        sceneContainer.changeTo<Level2>()
        sceneDestroy()
    }
}
