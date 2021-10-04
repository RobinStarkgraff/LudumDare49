package physics

import com.soywiz.korma.geom.Point
import kotlin.math.abs

class Matrix2x2(var a: Double, var b: Double, var c: Double, var d: Double) {
    operator fun times(p: Point): Point = Point(p.x * a + p.y * c, p.x * b + p.y * d)
    operator fun times(m: Matrix2x2): Matrix2x2 = Matrix2x2(
            a * m.a + b * m.c,
            a * m.b + b * m.d,
            c * m.a + d * m.c,
            c * m.b + d * m.d
    )

    fun column1() : Point = Point(a,c)
    fun column2() : Point = Point(b,d)
    fun transposed(): Matrix2x2 = Matrix2x2(a, c, b, d)
    fun absolute(): Matrix2x2 = Matrix2x2(abs(a), abs(b), abs(c), abs(d))

}