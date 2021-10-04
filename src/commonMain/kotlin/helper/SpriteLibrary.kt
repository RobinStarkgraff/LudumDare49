package helper

import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korim.bitmap.*
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
        lateinit var SPEECH_BUBBLE: NinePatchBmpSlice
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
        lateinit var LEVEL1_LAMP: SpriteAnimation

        lateinit var INTERSECTIONLEVEL_BASE: Bitmap
        lateinit var INTERSECTIONLEVEL_HOUSE: Bitmap
        lateinit var INTERSECTIONLEVEL_GARDEN_FENCE_NORTH: Bitmap
        lateinit var INTERSECTIONLEVEL_GARDEN_FENCE_SOUTH: Bitmap
        lateinit var INTERSECTIONLEVEL_GARDEN_FENCE_WESTEAST: Bitmap
        lateinit var INTERSECTIONLEVEL_HOUSE_FENCE_SOUTH: Bitmap
        lateinit var INTERSECTIONLEVEL_HOUSE_FENCE_EAST: Bitmap
        lateinit var INTERSECTIONLEVEL_TREE_1: Bitmap
        lateinit var INTERSECTIONLEVEL_TREE_2: Bitmap
        lateinit var INTERSECTIONLEVEL_TREE_3: Bitmap
        lateinit var INTERSECTIONLEVEL_CAR: SpriteAnimation

        lateinit var PARK_PARK: Bitmap
        lateinit var PARK_CROCUP: SpriteAnimation
        lateinit var PARK_CROCDOWN: SpriteAnimation
        lateinit var PARK_TURTLE: SpriteAnimation
        lateinit var PARK_CAULIFLOWER: Bitmap
        lateinit var PARK_GATE_TOP: Bitmap
        lateinit var PARK_LILY_PAD: Bitmap
        lateinit var PARK_PUMPKINS:Bitmap
        lateinit var PARK_ROCK: Bitmap
        lateinit var PARK_TURNIPS: Bitmap
        lateinit var PARK_WALL: Bitmap
        lateinit var PARK_WALL_BOTTOM: Bitmap
        lateinit var PARK_WIFI_STONE: Bitmap
        lateinit var PARK_LANDINGSTAGE: Bitmap
        lateinit var PARK_TREE_ONE: Bitmap
        lateinit var PARK_TREE_TWO: Bitmap
        lateinit var PARK_TREE_THREE: Bitmap

        lateinit var CAFE_BACKGROUND: Bitmap
        lateinit var CAFE_CHAIR: Bitmap
        lateinit var CAFE_CHAIR_ALT: Bitmap
        lateinit var CAFE_TABLE: Bitmap
        lateinit var CAFE_TABLE_ALT: Bitmap


        lateinit var KEY_INGAME: Bitmap
        lateinit var KEY_INVENTORY: Bitmap

        lateinit var DOOR: Bitmap

        lateinit var DOOR_SWING_RIGHT: SpriteAnimation
        lateinit var DOOR_SWING_LEFT: SpriteAnimation
        lateinit var PHONE: SpriteAnimation


        suspend fun init() {
            PLAYER_IDLE_ANIM = loadAnim("art/bitmap/Idle.png", Player.SCALE, 32, 8)
            PLAYER_WALK_RIGHT_ANIM = loadAnim("art/bitmap/Walk_Right.png", Player.SCALE, 32, 10)
            PLAYER_WALK_LEFT_ANIM = loadAnim("art/bitmap/Walk_Right.png", Player.SCALE, 32, 10, true)
            PLAYER_WALK_UP_ANIM = loadAnim("art/bitmap/Walk_Up.png", Player.SCALE, 32, 10)
            PLAYER_WALK_DOWN_ANIM = loadAnim("art/bitmap/Walk_Down.png", Player.SCALE, 32, 10)

            SPEECH_BUBBLE = loadBitmap("art/bitmap/unknown.png", 3.0).asNinePatchSimple(18, 9, 39, 27)
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
            LEVEL1_LAMP = loadAnim("art/bitmap/lamp.png", 3.0, Vector2D(14, 28), 2)

            INTERSECTIONLEVEL_BASE = loadBitmap("art/background/InnerCity.png", 3.3)
            INTERSECTIONLEVEL_HOUSE = loadBitmap("art/background/house.png", 3.0)
            INTERSECTIONLEVEL_GARDEN_FENCE_NORTH = loadBitmap("art/background/fence_segment_1.png", 3.0)
            INTERSECTIONLEVEL_GARDEN_FENCE_SOUTH = loadBitmap("art/background/fence_segment_2.png", 3.0)
            INTERSECTIONLEVEL_GARDEN_FENCE_WESTEAST = loadBitmap("art/background/fence_segment_5.png", 3.0)
            INTERSECTIONLEVEL_HOUSE_FENCE_SOUTH = loadBitmap("art/background/fence_segment_3.png", 3.0)
            INTERSECTIONLEVEL_HOUSE_FENCE_EAST = loadBitmap("art/background/fence_segment_4.png", 3.0)
            INTERSECTIONLEVEL_TREE_1 = loadBitmap("art/background/tree_1.png", 3.0)
            INTERSECTIONLEVEL_TREE_2 = loadBitmap("art/background/tree_2.png", 3.0)
            INTERSECTIONLEVEL_TREE_3 = loadBitmap("art/background/tree_3.png", 3.0)
            INTERSECTIONLEVEL_CAR = loadAnim("art/bitmap/car.png", 2.0, Vector2D(40, 24), 3)

            PARK_PARK = loadBitmap("park/background/park.png", 3.0)
            PARK_LANDINGSTAGE = loadBitmap("park/background/landingstage.png", 3.0)
            PARK_CROCUP = loadAnim("park/objects/CrocDoc.png",3.0, Vector2D(13, 48), 4)
            PARK_CROCDOWN = loadAnim("park/objects/CrocDoc_Down.png",3.0, Vector2D(13, 48), 4)
            PARK_TURTLE = loadAnim("park/objects/turtle.png",3.0, Vector2D(26, 10), 4)
            PARK_CAULIFLOWER = loadBitmap("park/objects/cauliflower.png", 3.0)
            PARK_GATE_TOP = loadBitmap("park/objects/gate_top.png", 3.0)
            PARK_LILY_PAD = loadBitmap("park/objects/lily_pad.png", 3.0)
            PARK_PUMPKINS = loadBitmap("park/objects/pumpkins.png", 3.0)
            PARK_ROCK = loadBitmap("park/objects/rock.png", 3.0)
            PARK_TURNIPS = loadBitmap("park/objects/turnips.png", 3.0)
            PARK_WALL = loadBitmap("park/objects/wall.png", 3.0)
            PARK_WALL_BOTTOM = loadBitmap("park/objects/wall_bottom.png", 3.0)
            PARK_WIFI_STONE = loadBitmap("park/objects/wifi_stone.png", 3.0)
            PARK_TREE_ONE = loadBitmap("park/objects/tree1.png",3.0)
            PARK_TREE_TWO = loadBitmap("park/objects/tree2.png",3.0)
            PARK_TREE_THREE = loadBitmap("park/objects/tree3.png",3.0)

            CAFE_BACKGROUND = loadBitmap("art/background/cafe_background.png",3.0)
            CAFE_CHAIR = loadBitmap("art/furniture/chair.png",3.0)
            CAFE_CHAIR_ALT = loadBitmap("art/furniture/chair_alt.png",3.0)
            CAFE_TABLE = loadBitmap("art/furniture/table.png",3.0)
            CAFE_TABLE_ALT = loadBitmap("art/furniture/table_alt.png",3.0)

            KEY_INGAME = loadBitmap("art/inventory/key_map.png", 3.0)
            KEY_INVENTORY = loadBitmap("art/inventory/key_inventory.png", 3.0)

            DOOR = loadBitmap("art/objects/door.png", 3.0)

            DOOR_SWING_RIGHT = loadAnim("bitmap/Door_Sheet.png", Player.SCALE, Vector2D(18, 41), 2)
            DOOR_SWING_LEFT = loadAnim("bitmap/Door_Sheet.png", Player.SCALE, Vector2D(18, 41), 2, true)
            PHONE = loadAnim("art/bitmap/phone.png", Player.SCALE, Vector2D(64, 109), 6)
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
