package helper

import com.soywiz.korge.input.mouse
import com.soywiz.korge.view.*
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.text.TextAlignment

class ScreenDebugger (var container: Container){
    private var text : Text = Text("test",15.0,RGBA.float(1,1,1),alignment = TextAlignment.CENTER).xy(75,25)

    init {
        container.addChild(text)
        container.mouse.onMoveAnywhere { updateText() }
    }

    private fun updateText() = text.setText(container.mouse.lastPosGlobal.toString())
}