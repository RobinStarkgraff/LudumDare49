package physics.util

import com.soywiz.korma.geom.Point

fun Point.squareMagnitude() : Double = x * x + y * y
operator fun Point.unaryMinus() = Point(-x, -y)