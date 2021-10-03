package helper

import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korim.format.readBitmapOptimized
import com.soywiz.korio.file.std.resourcesVfs
import objects.Player

class SpriteLibrary {
    companion object {
        lateinit var static: SpriteLibrary
    }


    lateinit var PLAYER_IDLE_ANIM: SpriteAnimation
    lateinit var PLAYER_WALK_RIGHT_ANIM: SpriteAnimation
    lateinit var PLAYER_WALK_LEFT_ANIM: SpriteAnimation
    lateinit var PLAYER_WALK_UP_ANIM: SpriteAnimation
    lateinit var PLAYER_WALK_DOWN_ANIM: SpriteAnimation

    lateinit var DOOR_SWING_RIGHT: SpriteAnimation
    lateinit var DOOR_SWING_LEFT: SpriteAnimation


    suspend fun init() {
        static = this@SpriteLibrary

        PLAYER_IDLE_ANIM = loadAnim("bitmap/Idle.png", Player.SCALE, 32, 8)
        PLAYER_WALK_RIGHT_ANIM = loadAnim("bitmap/Walk_Right.png", Player.SCALE, 32, 10)
        PLAYER_WALK_LEFT_ANIM = loadAnim("bitmap/Walk_Right.png", Player.SCALE, 32, 10, true)
        PLAYER_WALK_UP_ANIM = loadAnim("bitmap/Walk_Up.png", Player.SCALE, 32, 10)
        PLAYER_WALK_DOWN_ANIM = loadAnim("bitmap/Walk_Down.png", Player.SCALE, 32, 10)

        DOOR_SWING_RIGHT = loadAnim("bitmap/Door_Sheet.png", Player.SCALE, 18, 41, 2)
        DOOR_SWING_LEFT = loadAnim("bitmap/Door_Sheet.png", Player.SCALE, 18, 41, 2, true)
    }

    private suspend fun loadAnim(
        path: String,
        scale: Double,
        size: Int,
        frameCount: Int,
        flip: Boolean = false
    ): SpriteAnimation {
        return loadAnim(path, scale, size, size, frameCount, flip)
    }

    private suspend fun loadAnim(
        path: String,
        scale: Double,
        sizeWidth: Int,
        sizeHeight: Int,
        frameCount: Int,
        flip: Boolean = false
    ): SpriteAnimation {
        var bmp = resourcesVfs[path].readBitmapOptimized(premultiplied = false)
        if (flip) bmp = bmp.flipX()
        bmp = bmp.toBMP32().scaleLinear(scale, scale)

        return SpriteAnimation(
            spriteMap = bmp,
            spriteWidth = (sizeWidth * scale).toInt(),
            spriteHeight = (sizeHeight * scale).toInt(),
            columns = frameCount,
            rows = 1
        )
    }

}
