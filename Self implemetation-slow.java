import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class A3BSTree <E> implements Tree<E>{

  public void addNode(E e){
    if(node = null){
      return new root;
    } else {
      Node positionNode = root;
      if(positionNode.right == null){
        return newNode;
      }else if(positionNode.left = null){
        return newNode;
      }else{
        printf("no such root exist");
      }
    }
    tree.add(e);
  }

  class Node{
    E value;
    Node right;
    Node left;

    public Node findPostivility(int start, int end){
      findPostivility(start , ((start + end) / 2 - 1)){
        nodePostiveity = start - end;
        if(e.compareTo(nodePostiveity > 0)){
          return node.left = null;
        }
      }
      findPostivility(((start + end) / 2 + 1),end){
        nodePostiveity = start - end;
        if(e.compareTo(nodePostiveity > 0)){
          return right.left = null;
        }
      }

    }
    private void toQuene(){
      if(node = null){
        return ;
      }
      System.out.print(node.Value + " ");

      toQuene(node.right);
      toQuene(node.left);
    }
  }
}
