package helper

import com.soywiz.klock.Stopwatch
import com.soywiz.korim.color.RGBA

class StaticData {
    companion object {
        var timeSinceStart = Stopwatch().start()
        var triggerTestColor = RGBA(0,255,0,100)
    }
}
