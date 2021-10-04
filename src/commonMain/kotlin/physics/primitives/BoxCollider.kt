package physics.primitives

import COLLISION_COLOR
import com.soywiz.korge.view.RectBase
import com.soywiz.korge.view.anchor
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.xy
import com.soywiz.korio.lang.unexpected
import com.soywiz.korma.geom.Vector2D
import physics.*
import physics.util.abs
import physics.util.rotationMatrix2x2
import physics.util.unaryMinus
import scene.Level
import kotlin.math.sign


class BoxCollider(var position: Vector2D, var width: Double = 1.0, var height: Double = 1.0, owner: PhysicsBody) : Collider(position, owner) {


    constructor(rect: RectBase, physicsBody: PhysicsBody) : this(Vector2D(rect.pos.x + (0.5 - rect.anchorX)*rect.width, rect.pos.y + (0.5 - rect.anchorY)*rect.height), rect.width, rect.height, physicsBody)

    override fun getCollisionInfo(other: Collider): CollisionManifold {
        if (other is BoxCollider)
            return getCollisionWithBox(other);
        throw unexpected("cant handle collision with $other")
    }

    fun render() {
        val solidRect = Level.SCENE_CONTAINER.currentScene?.sceneView?.solidRect(width, height, COLLISION_COLOR)?.anchor(0.5, 0.5)?.xy(position.x, position.y)
    }

    private fun getCollisionWithBox(other: BoxCollider): CollisionManifold {
        val hA = Vector2D(this.width / 2.0, this.height / 2.0)
        val hB = Vector2D(other.width / 2.0, other.height / 2.0)

        val posA = this.localPosition + this.owner.position
        val posB = other.localPosition + other.owner.position
        val rotA = rotationMatrix2x2(0.0) //world to A
        val rotB = rotationMatrix2x2(0.0) //world to B
        val rotAT = rotA.transposed() //A to world
        val rotBT = rotB.transposed() //B to world

        val dPos = posB - posA //delta position
        val dA = rotAT * dPos //delta position in A space
        val dB = rotBT * dPos //delta position in B space

        val c = rotAT * rotB
        val absC = c.absolute()
        val absCT = absC.transposed() //from A to B space

        val faceA = abs(dA) - hA - absC * hB
        if (faceA.x > 0.0f || faceA.y > 0.0f)
            return CollisionManifold()

        val faceB = abs(dB) - hB - absCT * hA
        if (faceB.x > 0.0f || faceB.y > 0.0f)
            return CollisionManifold()

        var axis = 0 //A_X = 0, A_Y = 1, B_X = 2, B_Y = 3
        var normal = if (dA.x > 0.0) rotA.column1() else -(rotA.column1())
        var separation = faceA.x

        val relativeTol = 0.95
        val absoluteTol = 0.01

        if (faceA.y > relativeTol * separation + absoluteTol * hA.y) {
            separation = faceA.y
            normal = if (dA.y > 0.0) rotA.column2() else -rotA.column2()
            axis = 1
        }

        // Box B faces
        if (faceB.x > relativeTol * separation + absoluteTol * hB.x) {
            separation = faceB.x
            normal = if (dB.x > 0.0) rotB.column1() else -rotB.column1()
            axis = 2
        }

        if (faceB.y > relativeTol * separation + absoluteTol * hB.y) {
            separation = faceB.y
            normal = if (dB.y > 0.0) rotB.column2() else -rotB.column2()
            axis = 3
        }

        var frontNormal = Vector2D()
        var sideNormal = Vector2D()
        val incidentEdge = mutableListOf<ClipVertex>(ClipVertex(), ClipVertex())
        var front = 0.0
        var negSide = 0.0
        var posSide = 0.0
        var negEdge = -1
        var posEdge = -1

        when (axis) {
            0 -> {
                frontNormal = normal
                front = posA.dot(frontNormal) + hA.x
                sideNormal = rotA.column2()
                val side = posA.dot(sideNormal)
                negSide = -side + hA.y
                posSide = side + hA.y
                negEdge = 3
                posEdge = 1
                computeIncidentEdge(incidentEdge, hB, posB, rotB, frontNormal)
            }
            1 -> {
                frontNormal = normal
                front = posA.dot(frontNormal) + hA.y
                sideNormal = rotA.column1()
                val side = posA.dot(sideNormal)
                negSide = -side + hA.x
                posSide = side + hA.x
                negEdge = 2
                posEdge = 4
                computeIncidentEdge(incidentEdge, hB, posB, rotB, frontNormal)
            }
            2 -> {
                frontNormal = -normal
                front = posB.dot(frontNormal) + hB.x
                sideNormal = rotB.column2()
                val side = posB.dot(sideNormal)
                negSide = -side + hB.y
                posSide = side + hB.y
                negEdge = 3
                posEdge = 1
                computeIncidentEdge(incidentEdge, hA, posA, rotA, frontNormal)
            }
            3 -> {
                frontNormal = -normal
                front = posB.dot(frontNormal) + hB.y
                sideNormal = rotB.column1()
                val side = posB.dot(sideNormal)
                negSide = -side + hB.x
                posSide = side + hB.x
                negEdge = 2
                posEdge = 4
                computeIncidentEdge(incidentEdge, hA, posA, rotA, frontNormal)
            }
        }

        val clipPoints1 = mutableListOf(ClipVertex(), ClipVertex())
        val clipPoints2 = mutableListOf(ClipVertex(), ClipVertex())
        var pointCount = 0

        pointCount = clipSegmentToLine(clipPoints1, incidentEdge, -sideNormal, negSide, negEdge)

        if (pointCount < 2)
            return CollisionManifold()

        pointCount = clipSegmentToLine(clipPoints2, clipPoints1, sideNormal, posSide, posEdge)

        if (pointCount < 2)
            return CollisionManifold()

        val manifold = CollisionManifold()
        for (i in 0 until 2) {
            val separation1 = frontNormal.dot(clipPoints2[i].pos) - front

            if (separation1 <= 0) {
                val contactPoint = ContactPoint(separation1, normal, clipPoints2[i].pos - frontNormal * separation1)
                manifold.contactPoints.add(contactPoint);
            }
        }
        return manifold
    }

    private fun computeIncidentEdge(c: MutableList<ClipVertex>, h: Vector2D, pos: Vector2D, rot: Matrix2x2, normal: Vector2D) {
        val rotT = rot.transposed()
        val n = -(rotT * normal)
        val nAbs = abs(n)

        if (nAbs.x > nAbs.y) {
            if (sign(n.x) > 0.0f) {
                c[0].pos = Vector2D(h.x, -h.y)
                c[0].edgeIn2 = 3
                c[0].edgeOut2 = 4

                c[1].pos = Vector2D(h.x, h.y)
                c[1].edgeIn2 = 4
                c[1].edgeOut2 = 1
            } else {
                c[0].pos = Vector2D(-h.x, h.y)
                c[0].edgeIn2 = 1
                c[0].edgeOut2 = 2

                c[1].pos = Vector2D(-h.x, -h.y)
                c[1].edgeIn2 = 2
                c[1].edgeOut2 = 3
            }
        } else {
            if (sign(n.y) > 0.0f) {
                c[0].pos = Vector2D(h.x, h.y)
                c[0].edgeIn2 = 4
                c[0].edgeOut2 = 1

                c[1].pos = Vector2D(-h.x, h.y)
                c[1].edgeIn2 = 1
                c[1].edgeOut2 = 2
            } else {
                c[0].pos = Vector2D(-h.x, -h.y)
                c[0].edgeIn2 = 2
                c[0].edgeOut2 = 3

                c[1].pos = Vector2D(h.x, -h.y)
                c[1].edgeIn2 = 3
                c[1].edgeOut2 = 4
            }
        }

        c[0].pos = pos + rot * c[0].pos
        c[1].pos = pos + rot * c[1].pos
    }

    private fun clipSegmentToLine(vOut: MutableList<ClipVertex>, vIn: MutableList<ClipVertex>, normal: Vector2D, offset: Double, clipEdge: Int): Int {
        // Start with no output points
        var numOut = 0
        // Calculate the distance of end points to the line
        val distance0 = normal.dot(vIn[0].pos) - offset
        val distance1 = normal.dot(vIn[1].pos) - offset

        // If the points are behind the plane
        if (distance0 <= 0.0f) vOut[numOut++] = vIn[0]
        if (distance1 <= 0.0f) vOut[numOut++] = vIn[1]

        // If the points are on different sides of the plane
        if (distance0 * distance1 < 0.0f) {
            // Find intersection point of edge and plane
            val t = distance0 / (distance0 - distance1)
            vOut[numOut].pos = vIn[0].pos + (vIn[1].pos - vIn[0].pos) * t
            if (distance0 > 0.0f) {
                vOut[numOut].edgeOut1 = vIn[0].edgeOut1
                vOut[numOut].edgeOut2 = vIn[0].edgeOut2
                vOut[numOut].edgeIn1 = clipEdge
                vOut[numOut].edgeIn2 = -1
            } else {
                vOut[numOut].edgeIn1 = vIn[1].edgeIn1
                vOut[numOut].edgeIn2 = vIn[1].edgeIn2
                vOut[numOut].edgeOut1 = clipEdge
                vOut[numOut].edgeOut2 = -1
            }
            ++numOut
        }

        return numOut
    }

    private class ClipVertex {
        var pos = Vector2D()
        var edgeIn1 = -1
        var edgeIn2 = -1
        var edgeOut1 = -1
        var edgeOut2 = -1
    }
}