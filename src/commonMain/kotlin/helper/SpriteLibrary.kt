package helper

import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.bitmap.asumePremultiplied
import com.soywiz.korim.color.premultiply
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SpriteLibrary {
    companion object {
        lateinit var static : SpriteLibrary
    }

    lateinit var PLAYER_IDLE : Bitmap
    lateinit var PLAYER_WALK_RIGHT : Bitmap
    lateinit var PLAYER_WALK_LEFT : Bitmap
    lateinit var PLAYER_WALK_UP : Bitmap
    lateinit var PLAYER_WALK_DOWN : Bitmap

    lateinit var PLAYER_IDLE_ANIM : SpriteAnimation
    lateinit var PLAYER_WALK_RIGHT_ANIM : SpriteAnimation
    lateinit var PLAYER_WALK_LEFT_ANIM : SpriteAnimation
    lateinit var PLAYER_WALK_UP_ANIM : SpriteAnimation
    lateinit var PLAYER_WALK_DOWN_ANIM : SpriteAnimation

    suspend fun init() {
        static = this@SpriteLibrary

        PLAYER_IDLE = resourcesVfs["bitmap/Idle.png"].readBitmapOptimized(premultiplied = false)
        PLAYER_WALK_RIGHT = resourcesVfs["bitmap/Walk_Right.png"].readBitmapOptimized(premultiplied = false)
        PLAYER_WALK_LEFT = resourcesVfs["bitmap/Walk_Right.png"].readBitmapOptimized(premultiplied = false).flipX()
        PLAYER_WALK_UP = resourcesVfs["bitmap/Walk_Up.png"].readBitmapOptimized(premultiplied = false)
        PLAYER_WALK_DOWN = resourcesVfs["bitmap/Walk_Down.png"].readBitmapOptimized(premultiplied = false)

        PLAYER_IDLE_ANIM = SpriteAnimation(
            spriteMap = SpriteLibrary.static.PLAYER_IDLE,
            spriteWidth = 32,
            spriteHeight = 32,
            columns = 8,
            rows = 1,
        )
        PLAYER_WALK_RIGHT_ANIM = SpriteAnimation(
            spriteMap = SpriteLibrary.static.PLAYER_WALK_RIGHT,
            spriteWidth = 32,
            spriteHeight = 32,
            columns = 10,
            rows = 1,
        )
        PLAYER_WALK_LEFT_ANIM = SpriteAnimation(
            spriteMap = SpriteLibrary.static.PLAYER_WALK_LEFT,
            spriteWidth = 32,
            spriteHeight = 32,
            columns = 10,
            rows = 1,
        )
        PLAYER_WALK_UP_ANIM = SpriteAnimation(
            spriteMap = SpriteLibrary.static.PLAYER_WALK_UP,
            spriteWidth = 32,
            spriteHeight = 32,
            columns = 10,
            rows = 1,
        )
        PLAYER_WALK_DOWN_ANIM = SpriteAnimation(
            spriteMap = SpriteLibrary.static.PLAYER_WALK_DOWN,
            spriteWidth = 32,
            spriteHeight = 32,
            columns = 10,
            rows = 1,
        )
    }

}