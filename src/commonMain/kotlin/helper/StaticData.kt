package helper

import com.soywiz.klock.Stopwatch

class StaticData {
    companion object {
        var timeSinceStart = Stopwatch().start()
    }
}
