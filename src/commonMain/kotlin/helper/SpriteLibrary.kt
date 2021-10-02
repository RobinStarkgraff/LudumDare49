package helper

import com.soywiz.korge.view.Sprite
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class SpriteLibrary {
    companion object {
        lateinit var static : SpriteLibrary
    }

    lateinit var PLAYER_IDLE : Bitmap

    suspend fun init() {
        static = this@SpriteLibrary

        PLAYER_IDLE = resourcesVfs["bitmap/Idle.png"].readBitmap()
    }

}