package physics.util

import com.soywiz.korma.geom.Point
import physics.Matrix2x2
import kotlin.math.cos
import kotlin.math.abs
import kotlin.math.sin


fun rotationMatrix2x2(angle: Double): Matrix2x2 {
    val radians = angle / 180 * kotlin.math.PI
    val s = sin(radians)
    val c = cos(radians)
    return Matrix2x2(c, -s, s, c)
}

fun abs(point: Point) : Point = Point(abs(point.x),abs(point.y))


