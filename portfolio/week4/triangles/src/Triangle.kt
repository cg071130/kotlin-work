// COMP2850 Portfolio: Week 4
// Triangle class

import kotlin.math.sqrt

class Triangle(
    val sideA: Double,
    val sideB: Double,
    val sideC: Double
) {
    init {
        // 检查边长合法性：任意两边之和大于第三边，且每条边长大于 0
        require(sideA > 0 && sideB > 0 && sideC > 0) { "Sides must be positive numbers" }
        require(sideA + sideB > sideC && sideA + sideC > sideB && sideB + sideC > sideA) {
            "Sides do not form a valid triangle"
        }
    }

    // 周长
    val perimeter: Double
        get() = sideA + sideB + sideC

    // 半周长
    val semiPerimeter: Double
        get() = perimeter / 2.0

    // 面积（海伦公式）
    val area: Double
        get() {
            val s = semiPerimeter
            return sqrt(s * (s - sideA) * (s - sideB) * (s - sideC))
        }

    // 格式化输出：例如 Triangle(3.0, 4.0, 5.0)
    override fun toString(): String {
        return "Triangle($sideA, $sideB, $sideC)"
    }
}