//4**) Сделать дерево с балансировкой, черно-белое или AVL
package chrvk.javaelementary.hw9.task4;

public class Main {
    public static void main(String[] args) {
        RedBlackTree bst = new RedBlackTree();
        bst.insert(8);
        bst.insert(18);
        bst.insert(5);
        bst.insert(15);
        bst.insert(17);
        bst.insert(25);
        bst.insert(40);
        bst.insert(80);
        bst.deleteNode(25);
        bst.prettyPrint();
    }
}