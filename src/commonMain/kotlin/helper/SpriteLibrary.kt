package helper

import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.resourcesVfs
import objects.Player

class SpriteLibrary {
    companion object {

        lateinit var PLAYER_IDLE_ANIM: SpriteAnimation
        lateinit var PLAYER_WALK_RIGHT_ANIM: SpriteAnimation
        lateinit var PLAYER_WALK_LEFT_ANIM: SpriteAnimation
        lateinit var PLAYER_WALK_UP_ANIM: SpriteAnimation
        lateinit var PLAYER_WALK_DOWN_ANIM: SpriteAnimation


        suspend fun init() {
            PLAYER_IDLE_ANIM = loadAnim("art/bitmap/Idle.png", Player.SCALE, 32, 8)
            PLAYER_WALK_RIGHT_ANIM = loadAnim("art/bitmap/Walk_Right.png", Player.SCALE, 32, 10)
            PLAYER_WALK_LEFT_ANIM = loadAnim("art/bitmap/Walk_Right.png", Player.SCALE, 32, 10, true)
            PLAYER_WALK_UP_ANIM = loadAnim("art/bitmap/Walk_Up.png", Player.SCALE, 32, 10)
            PLAYER_WALK_DOWN_ANIM = loadAnim("art/bitmap/Walk_Down.png", Player.SCALE, 32, 10)
        }

        suspend fun loadAnim(
            path: String,
            scale: Double,
            size: Int,
            frameCount: Int,
            flip: Boolean = false
        ): SpriteAnimation {
            var bmp = resourcesVfs[path].readBitmapOptimized(premultiplied = false)
            if (flip) bmp = bmp.flipX()
            bmp = bmp.toBMP32().scaleLinear(scale, scale)

            return SpriteAnimation(
                spriteMap = bmp,
                spriteWidth = (size * scale).toInt(),
                spriteHeight = (size * scale).toInt(),
                columns = frameCount,
                rows = 1
            )
        }
    }
}
