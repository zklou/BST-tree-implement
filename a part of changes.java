private Node<E > search (E e){
  Node<E> current = root;
  while(true){
    for(current.data > 0){
      if(current.right = null){
        return current;
      } else {
        current = current.right;
      }
    } else if(current.data < 0){
      if(current.left = null){
        return current;
      } else {
        current = current.left;
      }
    } else {
      return current;
    }
  }
}



@Override
public boolean contains(Object o) {
  if(data == o){
    return true;
  } else{
    return false;
  }
}

@Override
public int height() {
  return height;
}

private int height(){
  if(e == null){
    return height;
  }
}
