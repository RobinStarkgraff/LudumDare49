package helper

import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.format.readBitmap
import com.soywiz.korim.format.readBitmapOptimized
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Vector2D
import objects.Player

class SpriteLibrary {
    companion object {

        lateinit var PLAYER_IDLE_ANIM: SpriteAnimation
        lateinit var PLAYER_WALK_RIGHT_ANIM: SpriteAnimation
        lateinit var PLAYER_WALK_LEFT_ANIM: SpriteAnimation
        lateinit var PLAYER_WALK_UP_ANIM: SpriteAnimation
        lateinit var PLAYER_WALK_DOWN_ANIM: SpriteAnimation
        lateinit var LEVEL1_FLAT: Bitmap
        lateinit var LEVEL1_KITCHEN_WALL: Bitmap
        lateinit var LEVEL1_BATH_WALL: Bitmap
        lateinit var LEVEL1_BED: SpriteAnimation
        lateinit var LEVEL1_NIGHT_STAND: Bitmap
        lateinit var LEVEL1_DESK: Bitmap
        lateinit var LEVEL1_PLANT: Bitmap
        lateinit var LEVEL1_BOOK_SHELF: Bitmap
        lateinit var LEVEL1_COUCH: Bitmap
        lateinit var LEVEL1_COUCH_TABLE: Bitmap
        lateinit var LEVEL1_BATHTUB: Bitmap
        lateinit var LEVEL1_KITCHEN: Bitmap
        lateinit var LEVEL1_LIGHT: Bitmap
        lateinit var LEVEL1_KITCHEN_SHELF: Bitmap
        lateinit var LEVEL1_ROUTER_TABLE: Bitmap
        lateinit var LEVEL1_ROUTER: SpriteAnimation
        lateinit var LEVEL1_SIDE_WALLS: Bitmap

        lateinit var DOOR_SWING_RIGHT: SpriteAnimation
        lateinit var DOOR_SWING_LEFT: SpriteAnimation
        lateinit var PHONE: SpriteAnimation


        suspend fun init() {
            PLAYER_IDLE_ANIM = loadAnim("art/bitmap/Idle.png", Player.SCALE, 32, 8)
            PLAYER_WALK_RIGHT_ANIM = loadAnim("art/bitmap/Walk_Right.png", Player.SCALE, 32, 10)
            PLAYER_WALK_LEFT_ANIM = loadAnim("art/bitmap/Walk_Right.png", Player.SCALE, 32, 10, true)
            PLAYER_WALK_UP_ANIM = loadAnim("art/bitmap/Walk_Up.png", Player.SCALE, 32, 10)
            PLAYER_WALK_DOWN_ANIM = loadAnim("art/bitmap/Walk_Down.png", Player.SCALE, 32, 10)

            LEVEL1_FLAT = loadBitmap("art/background/frog_flat.png", 3.0)
            LEVEL1_BED = loadAnim("art/furniture/bed.png", 3.0, Vector2D(21, 43), 8)
            LEVEL1_NIGHT_STAND = loadBitmap("art/furniture/night_stand.png", 3.0)
            LEVEL1_DESK = loadBitmap("art/furniture/desk.png", 3.0)
            LEVEL1_PLANT = loadBitmap("art/objects/flower_pot.png", 3.0)
            LEVEL1_BOOK_SHELF = loadBitmap("art/furniture/bookshelf.png", 3.0)
            LEVEL1_COUCH = loadBitmap("art/furniture/couch.png", 3.0)
            LEVEL1_COUCH_TABLE = loadBitmap("art/furniture/couchtable.png", 3.0)
            LEVEL1_BATHTUB = loadBitmap("art/furniture/bathtub.png", 3.0)
            LEVEL1_KITCHEN = loadBitmap("art/furniture/kitchen_assembly.png", 3.0)
            LEVEL1_KITCHEN_WALL = loadBitmap("art/background/kitchenWalls.png", 3.0)
            LEVEL1_BATH_WALL = loadBitmap("art/background/bathroomWalls.png", 3.0)
            LEVEL1_LIGHT = loadBitmap("art/foreground/FrogFlat_lightOverlay.png", 3.0)
            LEVEL1_KITCHEN_SHELF = loadBitmap("art/furniture/kitchen_shelf.png", 3.0)
            LEVEL1_ROUTER_TABLE = loadBitmap("art/furniture/router_table.png", 3.0)
            LEVEL1_SIDE_WALLS = loadBitmap("art/foreground/SideWall.png", 3.0)
            LEVEL1_ROUTER = loadAnim("art/furniture/router.png", 3.0, Vector2D(21, 14), 14)

            DOOR_SWING_RIGHT = loadAnim("bitmap/Door_Sheet.png", Player.SCALE, Vector2D(18, 41), 2)
            DOOR_SWING_LEFT = loadAnim("bitmap/Door_Sheet.png", Player.SCALE, Vector2D(18, 41), 2, true)
            PHONE = loadAnim("art/bitmap/Phone.png", Player.SCALE, Vector2D(64, 109), 5)
        }

        suspend fun loadBitmap(path: String, scale: Double): Bitmap {
            var bitmap = resourcesVfs[path].readBitmap().toBMP32()
            bitmap = bitmap.scaleLinear(scale, scale)
            return bitmap
        }

        private suspend fun loadAnim(
            path: String,
            scale: Double,
            size: Int,
            frameCount: Int,
            flip: Boolean = false
        ): SpriteAnimation {
            return loadAnim(path, scale, Vector2D(size, size), frameCount, flip)
        }

        suspend fun loadAnim(
            path: String,
            scale: Double,
            size: Vector2D,
            frameCount: Int,
            flip: Boolean = false
        ): SpriteAnimation {
            var bmp = resourcesVfs[path].readBitmapOptimized(premultiplied = false)
            if (flip) bmp = bmp.flipX()
            bmp = bmp.toBMP32().scaleLinear(scale, scale)

            return SpriteAnimation(
                spriteMap = bmp,
                spriteWidth = (size.x * scale).toInt(),
                spriteHeight = (size.y * scale).toInt(),
                columns = frameCount,
                rows = 1
            )
        }
    }
}
