package helper

import ConfigModule.size
import com.soywiz.korge.input.mouse
import com.soywiz.korge.view.*
import com.soywiz.korge.view.views
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.text.TextAlignment
import kotlin.math.round

class ScreenDebugger (var container: Container){
    private var text : Text = Text("test",50.0,RGBA.float(1,1,1),alignment = TextAlignment.CENTER).xy(200,50)

    init {
        container.addChild(text)
        container.mouse.onMoveAnywhere { updateText() }
    }
    suspend fun updateText(){
        text.text = "${round(com.soywiz.korge.view.views().globalMouseX)}, ${round(views().globalMouseY)}"


    }


}