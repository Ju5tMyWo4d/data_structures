import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BinarySearchTreeTest {

    @Nested
    inner class AddElements {
        @Test
        fun `add multiple elements`() {
            val bt = BinarySearchTree(7, 13, 2, 4, 2, -10, 64, 0, 4)
            assertArrayEquals(arrayOf(-10, 0, 2, 2, 4, 4, 7, 13, 64), bt.toSortedArrayList().toArray())
        }

        @Test
        fun `add one element`() {
            val bt = BinarySearchTree(7)
            assertArrayEquals(arrayOf(7), bt.toSortedArrayList().toArray())
        }

        @Test
        fun `add elements that are lower than previous`() {
            val bt = BinarySearchTree<Int>()
            bt.add(15)
            bt.add(8)
            bt.add(3)
            bt.add(1)
            bt.add(0)
            assertArrayEquals(arrayOf(0, 1, 3, 8, 15), bt.toSortedArrayList().toArray())
        }
    }

    @Nested
    inner class RemoveElements {
        @Test
        fun `remove multiple elements`() {
            val bt = BinarySearchTree(7, 13, 2, 4, 2, -10, 64, 0, 4)
            bt.remove(4)
            bt.remove(13)
            bt.remove(0)
            assertArrayEquals(arrayOf(-10, 2, 2, 4, 7, 64), bt.toSortedArrayList().toArray())
        }

        @Test
        fun `remove elements from empty tree`() {
            val bt = BinarySearchTree<Int>()
            bt.remove(4)
            assertArrayEquals(emptyArray(), bt.toSortedArrayList().toArray())
        }

        @Test
        fun `remove root`() {
            val bt = BinarySearchTree(8, 3, 10, 1, 6, 14, 4, 7, 13)
            bt.remove(8)
            assertArrayEquals(arrayOf(1, 3, 4, 6, 7, 10, 13, 14), bt.toSortedArrayList().toArray())
        }

        @Test
        fun `remove node without children`() {
            val bt = BinarySearchTree(8, 3, 10, 1, 6, 14, 4, 7, 13)
            bt.remove(13)
            assertArrayEquals(arrayOf(1, 3, 4, 6, 7, 8, 10, 14), bt.toSortedArrayList().toArray())
        }

        @Test
        fun `remove node with one child`() {
            val bt = BinarySearchTree(8, 3, 10, 1, 6, 14, 4, 7, 13)
            bt.remove(14)
            assertArrayEquals(arrayOf(1, 3, 4, 6, 7, 8, 10, 13), bt.toSortedArrayList().toArray())
        }

        @Test
        fun `remove node with two children`() {
            val bt = BinarySearchTree(8, 3, 10, 1, 6, 14, 4, 7, 13)
            bt.remove(3)
            assertArrayEquals(arrayOf(1, 4, 6, 7, 8, 10, 13, 14), bt.toSortedArrayList().toArray())
        }

        @Test
        fun `remove node that is lowest in subtree`() {
            val bt = BinarySearchTree(8, 3, 10, 1, 6, 14, 4, 7, 13, 15)
            bt.remove(14)
            assertArrayEquals(arrayOf(1, 3, 4, 6, 7, 8, 10, 13, 15), bt.toSortedArrayList().toArray())
        }
    }

    @Nested
    inner class GetElements {

        @Test
        fun `get minimal value`() {
            val bt = BinarySearchTree(7, 58, 3, 1, -42, 20, 2)
            assertEquals(-42, bt.min)
        }

        @Test
        fun `get minimal value with only one element`() {
            val bt = BinarySearchTree(7)
            assertEquals(7, bt.min)
        }

        @Test
        fun `get minimal value when elements are not distinct`() {
            val bt = BinarySearchTree(7, 10, 4, 4, -20, -20, 10, 32, 49)
            assertEquals(-20, bt.min)
        }

        @Test
        fun `get maximal value`() {
            val bt = BinarySearchTree(7, 58, 3, 1, -42, 20, 2)
            assertEquals(58, bt.max)
        }

        @Test
        fun `get maximal value with only one element`() {
            val bt = BinarySearchTree(7)
            assertEquals(7, bt.max)
        }

        @Test
        fun `get maximal value when elements are not distinct`() {
            val bt = BinarySearchTree(7, 10, 4, 4, -20, -20, 10, 32, 49, 49)
            assertEquals(49, bt.max)
        }
    }

    @Test
    fun `create empty tree`() {
        val bt = BinarySearchTree<Int>()
        assertArrayEquals(emptyArray(), bt.toSortedArrayList().toArray())
    }

    @Test
    fun `return sorted array`() {
        val bt = BinarySearchTree(7, 13, 2, 4, 2, -10, 64, 0, 4)
        assertArrayEquals(arrayOf(-10, 0, 2, 2, 4, 4, 7, 13, 64), bt.toSortedArrayList().toArray())
    }
}