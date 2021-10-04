package scene

import COLLISION_COLOR
import RESOLUTION
import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Vector2D
import objects.Player
import helper.SoundPlayer
import helper.SpriteLibrary
import objects.Phone
import objects.WifiRouter
import objects.interactables.StateSwapItem
import physics.PhysicsBody
import physics.primitives.BoxCollider

class IntersectionLevel : Level() {
    override suspend fun Container.sceneInit() {
        spawnpoint = Vector2D(430, 250)
        player = Player(this@IntersectionLevel)
        phone = Phone(this@IntersectionLevel)
        SoundPlayer.playBackgroundMusic(SoundPlayer.BGM1)
        drawImages()
        WifiRouter(RESOLUTION.x / 2, RESOLUTION.y / 2, 100.0, null, this@IntersectionLevel)
    }

    override suspend fun drawImages() {
        super.drawImages()
        bg.sprite(SpriteLibrary.INTERSECTIONLEVEL_BASE).anchor(0.5, 0.5).xy(RESOLUTION.x/2, RESOLUTION.y/2)
        fg.sprite(SpriteLibrary.INTERSECTIONLEVEL_HOUSE).xy(180.0, 30.0)


        val fence_house_south = fg.sprite(SpriteLibrary.INTERSECTIONLEVEL_HOUSE_FENCE_SOUTH).xy(160.0, 205.0)
        //add collision
        val fence_house_east = fg.sprite(SpriteLibrary.INTERSECTIONLEVEL_HOUSE_FENCE_EAST).xy(494.0, 17.0)
        //add collision

        val fence_garden_north = fg.sprite(SpriteLibrary.INTERSECTIONLEVEL_GARDEN_FENCE_NORTH).xy(195.0, 445.0)
        //add collision
        val fence_garden_south = fg.sprite(SpriteLibrary.INTERSECTIONLEVEL_GARDEN_FENCE_SOUTH).xy(195.0, 625.0)
        //add collision
        val fence_garden_east = fg.sprite(SpriteLibrary.INTERSECTIONLEVEL_GARDEN_FENCE_WESTEAST).xy(468.0, 475.5)
        //add collision
        val fence_garden_west = fg.sprite(SpriteLibrary.INTERSECTIONLEVEL_GARDEN_FENCE_WESTEAST).xy(180.0, 475.5)
        //add collision

        val tree1 = fg.sprite(SpriteLibrary.INTERSECTIONLEVEL_TREE_1).xy(930.0, 30.0)
        val tree2 = fg.sprite(SpriteLibrary.INTERSECTIONLEVEL_TREE_2).xy(820.0, 110.0)
        val tree3 = fg.sprite(SpriteLibrary.INTERSECTIONLEVEL_TREE_3).xy(720.0, 30.0)

        val level = PhysicsBody()
        BoxCollider(Vector2D(630, 70), 1000.0, 5.0, level)
        BoxCollider(Vector2D(630, 710), 1000.0, 5.0, level)
        BoxCollider(Vector2D(157.0, (RESOLUTION.y/2)), 5.0, 700.0, level)
        BoxCollider(Vector2D(1120.0, (RESOLUTION.y/2)), 5.0, 700.0, level)

//        il.sprite(SpriteLibrary.LEVEL1_KITCHEN_WALL).anchor(0.0, .7).xy(691, 346)
//        fg.sprite(SpriteLibrary.LEVEL1_SIDE_WALLS).anchor(0.5, 0.0).xy(RESOLUTION.x/2, -50.0)

//        val bed = il.sprite(SpriteLibrary.LEVEL1_BED).xy(340, 130)
//        collisionList.add(bed)
//        bed.playAnimationLooped(spriteDisplayTime = 200.milliseconds)
//        val kitchenDoor = il.sprite(anchorY = 1.0).xy(700, 283)
//        StateSwapItem(this, kitchenDoor, SpriteLibrary.DOOR_SWING_LEFT, SoundPlayer.DOORCLOSE, SoundPlayer.DOOROPEN)
//        val bathroomDoor = il.sprite(anchorY = 1.0).xy(700, 478)
//        StateSwapItem(this, bathroomDoor, SpriteLibrary.DOOR_SWING_LEFT, SoundPlayer.DOORCLOSE, SoundPlayer.DOOROPEN)
//        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_NIGHT_STAND).xy(410, 160))
//        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_DESK).xy(550, 130))
//        il.sprite(SpriteLibrary.LEVEL1_PLANT).xy(660, 130)
//        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_COUCH).xy(340, 450))
//        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_COUCH_TABLE).xy(420, 490))
//        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_BOOK_SHELF).anchor(0.0, 0.9).xy(335, 400))
//        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_KITCHEN).xy(780, 130))
//        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_BATHTUB).anchor(0, 1).xy(808, 400))
//        il.sprite(SpriteLibrary.LEVEL1_KITCHEN_SHELF).xy(925, 219)
//        collisionList.add(il.sprite(SpriteLibrary.LEVEL1_ROUTER_TABLE).anchor(0, 1).xy(850, 554))
//        il.sprite(SpriteLibrary.LEVEL1_ROUTER).anchor(0, 1).xy(850, 554).playAnimationLooped(spriteDisplayTime = 200.milliseconds)
//
//        //Colliders
//        collisionList.add(fg.solidRect(700, 10, COLLISION_COLOR).xy(300, 650))
//        collisionList.add(fg.solidRect(700, 10, COLLISION_COLOR).xy(300, 140))
//        collisionList.add(fg.solidRect(10, 700, COLLISION_COLOR).xy(325, 140))
//        collisionList.add(fg.solidRect(10, 700, COLLISION_COLOR).xy(947, 140))
//        collisionList.add(fg.solidRect(300, 30, COLLISION_COLOR).xy(700, 315))
//        collisionList.add(fg.solidRect(10, 120, COLLISION_COLOR).xy(700, 270))
//        collisionList.add(fg.solidRect(300, 30, COLLISION_COLOR).xy(700, 495))
//        collisionList.add(fg.solidRect(10, 80, COLLISION_COLOR).xy(700, 450))
//        collisionList.add(fg.solidRect(10, 60, COLLISION_COLOR).xy(700, 130))
    }

    override suspend fun nextScene() {
//        println("Level3")
//        sceneDestroy()
//        sceneContainer.changeTo<Level2>()

    }
}
