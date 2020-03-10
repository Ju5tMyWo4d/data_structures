import java.util.ArrayList

class BinarySearchTree<T : Comparable<T>> {
    private var root: BinaryTreeNode<T>?
    val max: T?
        get() {
            var tmp = root
            while(tmp?.right != null) {
                tmp = tmp.right
            }
            return tmp?.value
        }
    val min: T?
        get() {
            var tmp = root
            while(tmp?.left != null) {
                tmp = tmp.left
            }
            return tmp?.value
        }

    constructor() {
        root = null
    }

    constructor(value: T) {
        root = BinaryTreeNode(value, null)
    }

    constructor(first: T, vararg els: T): this(first) {
        for(el in els) {
            this.add(el)
        }
    }

    fun add(value: T) {
        if(root != null) {
            var parent = root

            while(parent != null) {
                parent = if(value > parent.value) {
                    if(parent.right != null) {
                        parent.right
                    } else {
                        parent.right = BinaryTreeNode(value)
                        break
                    }
                } else {
                    if(parent.left != null) {
                        parent.left
                    } else {
                        parent.left = BinaryTreeNode(value)
                        break
                    }
                }
            }
        } else {
            root = BinaryTreeNode(value)
        }
    }

    fun remove(value: T) {
        val (parent, node) = find(value) ?: return

        if(parent == null) {
            root = when {
                node.right == null -> {
                    node.left
                }
                node.left == null -> {
                    node.right
                }
                else -> {
                    val (minParent, minNode) = getMinNode(node.right ?: return)
                    if(minParent != null) {
                        minParent.left = minNode.right
                        minNode.right = node.right
                    }
                    minNode.left = node.left

                    minNode
                }
            }
        } else {
            when {
                node.right == null -> {
                    if(parent.right === node) {
                        parent.right = node.left
                    } else {
                        parent.left = node.left
                    }
                }
                node.left == null -> {
                    if(parent.right === node) {
                        parent.right = node.right
                    } else {
                        parent.left = node.right
                    }
                }
                else -> {
                    val (minParent, minNode) = getMinNode(node.right ?: return)
                    minNode.left = node.left

                    if(minParent != null) {
                        minParent.left = minNode.right
                        minNode.right = node.right
                    }

                    if(parent.right === node) {
                        parent.right = minNode
                    } else {
                        parent.left = minNode
                    }
                }
            }
        }
    }

    fun toSortedArrayList(): ArrayList<T> {
        val al = ArrayList<T>()
        traverse{al.add(it)}
        return al
    }

    fun traverse(call: (T) -> Unit) {
        traverse(root, call)
    }

    private fun find(value: T): ParentNodePair<T>? {
        var tmpParent: BinaryTreeNode<T>? = null
        var tmpNode = root

        while(tmpNode != null && tmpNode.value != value) {
            tmpParent = tmpNode
            tmpNode = if(value > tmpNode.value) {
                tmpNode.right
            } else {
                tmpNode.left
            }
        }

        return if(tmpNode == null) null else ParentNodePair(tmpParent, tmpNode)
    }

    private fun getMinNode(node: BinaryTreeNode<T>): ParentNodePair<T> {
        var tmpParent: BinaryTreeNode<T>? = null
        var tmpNode = node
        while(tmpNode.left != null) {
            tmpParent = tmpNode
            tmpNode = tmpNode.left ?: throw ConcurrentModificationException()
        }
        return ParentNodePair(tmpParent, tmpNode)
    }

    private fun traverse(node: BinaryTreeNode<T>?, call: (T) -> Unit) {
        node ?: return

        traverse(node.left, call)
        call(node.value)
        traverse(node.right, call)
    }
}

private data class BinaryTreeNode<T>(var value: T, var left: BinaryTreeNode<T>? = null, var right: BinaryTreeNode<T>? = null)

private typealias ParentNodePair<T> = Pair<BinaryTreeNode<T>?, BinaryTreeNode<T>> // pair of nodes: first - parent, second - child