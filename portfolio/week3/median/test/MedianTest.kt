// COMP2850 Portfolio: Week 3
// Tests for median()

import io.kotest.core.spec.style.FreeSpec
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.floats.plusOrMinus

const val tolerance = 0.000001f

@Suppress("Unused")
class MedianTest : FreeSpec({
    "Exception when size=0" {
        shouldThrow<IllegalArgumentException> {
            median(listOf())
        }
    }

    // Write four more tests here
    "Median when size=1" {
        // 1个元素：中位数就是它本身
        median(listOf(5)) shouldBe 5.0
    }

    "Median when size=2" {
        // 2个元素：中位数是两个元素的平均值
        median(listOf(2, 8)) shouldBe 5.0
    }

    "Median when size=3" {
        // 3个元素，打乱顺序：中位数应为排序后的中间值
        median(listOf(9, 1, 5)) shouldBe 5.0
    }

    "Median when size=4" {
        // 4个元素，打乱顺序：中位数应为排序后中间两个数的平均值
        val result = median(listOf(10, 1, 3, 8))

        // 使用文件顶部预设的 tolerance 进行浮点数断言比较安全
        result.toFloat() shouldBe (5.5f plusOrMinus tolerance)
        // 如果不想强转类型，也可以直接写 result shouldBe 5.5
    }

})