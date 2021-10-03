package helper

import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.format.readBitmap
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

    suspend fun init() {
        static = this@SpriteLibrary

        PLAYER_IDLE = resourcesVfs["bitmap/Idle.png"].readBitmap()
        PLAYER_WALK_RIGHT = resourcesVfs["bitmap/Walk_Right.png"].readBitmap()
        PLAYER_WALK_LEFT = resourcesVfs["bitmap/Walk_Right.png"].readBitmap().flipX()
        PLAYER_WALK_UP = resourcesVfs["bitmap/Walk_Up.png"].readBitmap()
        PLAYER_WALK_DOWN = resourcesVfs["bitmap/Walk_Down.png"].readBitmap()
    }

}