import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class BinarySearchTreeTest {

    public static class AddElements {

        @Test
        public void addMultipleElements() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>(7, 13, 2, 4, 2, -10, 64, 0, 4);
            Assert.assertArrayEquals(new Integer[] {-10, 0, 2, 2, 4, 4, 7, 13, 64}, bt.toSortedArrayList().toArray());
        }

        @Test
        public void addOneElement() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>(7);

            assertThat(bt.toSortedArrayList()).containsOnly(7);
        }

        @Test
        public void addElementsThatAreLowerThanPrevious() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>();
            bt.add(15);
            bt.add(8);
            bt.add(3);
            bt.add(1);
            bt.add(0);
            Assert.assertArrayEquals(new Integer[] {0, 1, 3, 8, 15}, bt.toSortedArrayList().toArray());
        }
    }

    public static class RemoveElements {
        @Test
        public void removeMultipleElements() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>(7, 13, 2, 4, 2, -10, 64, 0, 4);
            bt.remove(4);
            bt.remove(13);
            bt.remove(0);
            Assert.assertArrayEquals(new Integer[] {-10, 2, 2, 4, 7, 64}, bt.toSortedArrayList().toArray());
        }

        @Test
        public void removeElementsFromEmptyTree() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>();
            bt.remove(4);
            Assert.assertArrayEquals(new Integer[]{}, bt.toSortedArrayList().toArray());
        }

        @Test
        public void removeRoot() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>(8, 3, 10, 1, 6, 14, 4, 7, 13);
            bt.remove(8);
            Assert.assertArrayEquals(new Integer[] {1, 3, 4, 6, 7, 10, 13, 14}, bt.toSortedArrayList().toArray());
        }

        @Test
        public void removeNodeWithoutChildren() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>(8, 3, 10, 1, 6, 14, 4, 7, 13);
            bt.remove(13);
            Assert.assertArrayEquals(new Integer[] {1, 3, 4, 6, 7, 8, 10, 14}, bt.toSortedArrayList().toArray());
        }

        @Test
        public void removeNodeWithOneChild() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>(8, 3, 10, 1, 6, 14, 4, 7, 13);
            bt.remove(14);
            Assert.assertArrayEquals(new Integer[] {1, 3, 4, 6, 7, 8, 10, 13}, bt.toSortedArrayList().toArray());
        }

        @Test
        public void removeNodeWithTwoChildren() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>(8, 3, 10, 1, 6, 14, 4, 7, 13);
            bt.remove(3);
            Assert.assertArrayEquals(new Integer[] {1, 4, 6, 7, 8, 10, 13, 14}, bt.toSortedArrayList().toArray());
        }

        @Test
        public void removeNodeThatIsLowestInSubtree() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>(8, 3, 10, 1, 6, 14, 4, 7, 13, 15);
            bt.remove(14);
            Assert.assertArrayEquals(new Integer[] {1, 3, 4, 6, 7, 8, 10, 13, 15}, bt.toSortedArrayList().toArray());
        }
    }

    public static class GetElements {

        @Test
        public void getMinimalValue() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>(7, 58, 3, 1, -42, 20, 2);
            Assert.assertEquals(new Integer(-42), bt.getMin());
        }

        @Test
        public void getMinimalValueWithOnlyOneElement() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>(7);
            Assert.assertEquals(new Integer(7), bt.getMin());
        }

        @Test
        public void getMinimalValueWhenElementsAreNotDistinct() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>(7, 10, 4, 4, -20, -20, 10, 32, 49);
            Assert.assertEquals(new Integer(-20), bt.getMin());
        }

        @Test
        public void getMaximalValue() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>(7, 58, 3, 1, -42, 20, 2);
            Assert.assertEquals(new Integer(58), bt.getMax());
        }

        @Test
        public void getMaximalValueWithOnlyOneElement() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>(7);
            Assert.assertEquals(new Integer(7), bt.getMax());
        }

        @Test
        public void getMaximalValueWhenElementsAreNotDistinct() {
            BinarySearchTree<Integer> bt = new BinarySearchTree<>(7, 10, 4, 4, -20, -20, 10, 32, 49, 49);
            Assert.assertEquals(new Integer(49), bt.getMax());
        }
    }

    @Test
    public void createEmptyTree() {
        BinarySearchTree<Integer> bt = new BinarySearchTree<>();
        Assert.assertArrayEquals(new Integer[] {}, bt.toSortedArrayList().toArray());
    }

    @Test
    public void returnSortedArray() {
        BinarySearchTree<Integer> bt = new BinarySearchTree<>(7, 13, 2, 4, 2, -10, 64, 0, 4);
        Assert.assertArrayEquals(new Integer[] {-10, 0, 2, 2, 4, 4, 7, 13, 64}, bt.toSortedArrayList().toArray());
    }
}