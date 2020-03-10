import com.sun.istack.internal.NotNull;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private class BinaryTreeNode {
        private BinaryTreeNode left;
        private BinaryTreeNode right;
        private T value;

        BinaryTreeNode(T value) {
            this.value = value;
            left = null;
            right = null;
        }

        BinaryTreeNode(T value, BinaryTreeNode left, BinaryTreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private BinaryTreeNode root;

    BinarySearchTree() {
        root = null;
    }

    BinarySearchTree(T el) {
        root = new BinaryTreeNode(el);
    }

    BinarySearchTree(T el, T ...args) {
        this(el);

        for(T e : args) {
            add(e);
        }
    }

    public void add(T el) {
        BinaryTreeNode tmp = root;
        while(tmp != null) {
            if(tmp.value.compareTo(el) > 0) {
                if(tmp.right == null) {
                    tmp.right = new BinaryTreeNode(el);
                    break;
                }
                tmp = tmp.right;
            } else {
                if(tmp.left == null) {
                    tmp.left = new BinaryTreeNode(el);
                    break;
                }
                tmp = tmp.left;
            }
        }
    }

    public boolean contains(T value) {
        BinaryTreeNode tmp = root;
        while(tmp != null) {
            if(tmp.value.compareTo(value) == 0) return true;

            if(tmp.value.compareTo(value) > 0) {
                tmp = tmp.right;
            } else {
                tmp = tmp.left;
            }
        }

        return false;
    }

    public void remove(T value) {
        Pair<BinaryTreeNode, BinaryTreeNode> tmp = find(value);
        if(tmp == null) return;

        BinaryTreeNode parent = tmp.getKey();
        BinaryTreeNode node = tmp.getValue();

        if(parent == null) {
            if(node.right == null) {
                root = node.left;
            } else if(node.left == null) {
                root = node.right;
            } else {
                Pair<BinaryTreeNode, BinaryTreeNode> tmpMin = getMinNode(node.right);
                BinaryTreeNode minParent = tmpMin.getKey();
                BinaryTreeNode minNode = tmpMin.getValue();

                if(minParent != null) {
                    minParent.left = minNode.right;
                    minNode.right = node.right;
                }
                minNode.left = node.left;

                root = minNode;
            }
        } else {
            if(node.right == null) {
                if(parent.left == node) {
                    parent.left = node.left;
                } else {
                    parent.right = node.left;
                }
            } else if(node.left == null) {
                if(parent.left == node) {
                    parent.left = node.right;
                } else {
                    parent.right = node.right;
                }
            } else {
                Pair<BinaryTreeNode, BinaryTreeNode> tmpMin = getMinNode(node.right);
                BinaryTreeNode minParent = tmpMin.getKey();
                BinaryTreeNode minNode = tmpMin.getValue();
                minNode.left = node.left;

                if(minParent != null) {
                    minParent.left = minNode.right;
                    minNode.right = node.right;
                }

                if(parent.right == node) {
                    parent.right = minNode;
                } else {
                    parent.left = minNode;
                }
            }
        }
    }

    public ArrayList<T> toSortedArrayList() {
        ArrayList<T> ar = new ArrayList<>();
        traverse(ar::add);
        return ar;
    }

    public void traverse(Consumer<T> call) {
        traverse(root, call);
    }

    public T getMin() {
        BinaryTreeNode tmp = root;
        while(tmp != null) {
            if(tmp.left == null) {
                return tmp.value;
            }

            tmp = tmp.left;
        }

        return null;
    }

    public T getMax() {
        BinaryTreeNode tmp = root;
        while(tmp != null) {
            if(tmp.right == null) {
                return tmp.value;
            }

            tmp = tmp.right;
        }

        return null;
    }


    private Pair<BinaryTreeNode, BinaryTreeNode> find(T value) {
        BinaryTreeNode tmpParent = null;
        BinaryTreeNode tmpNode = root;
        while(tmpNode != null && tmpNode.value != value) {
            tmpParent = tmpNode;
            if(tmpNode.value.compareTo(value) > 0) {
                tmpNode = tmpNode.right;
            } else {
                tmpNode = tmpNode.left;
            }
        }

        if(tmpNode == null) {
            return null;
        } else {
            return new Pair<>(tmpParent, tmpNode);
        }
    }

    private void traverse(BinaryTreeNode node, Consumer<T> call) {
        if(node == null) return;

        traverse(node.left, call);
        call.accept(node.value);
        traverse(node.right, call);
    }

    private Pair<BinaryTreeNode, BinaryTreeNode> getMinNode(@NotNull BinaryTreeNode node) {
        BinaryTreeNode tmpParent = null;
        BinaryTreeNode tmpNode = node;

        while(tmpNode.left != null) {
            tmpParent = tmpNode;
            tmpNode = tmpNode.left;
        }

        return new Pair<>(tmpParent, tmpNode);
    }
}
