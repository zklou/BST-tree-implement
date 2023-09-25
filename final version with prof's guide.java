import java.util.Collection;
import java.util.Iterator;

public class A3BSTree <E> implements Tree<E>{

	public void addNode(E e){
		if(this.root = null){
			root = new root;
			return true;
			count++;
		} else {
			Node positionNode = root;
			if(positionNode.right == null){
				node.right = toadd;
				count ++;
				return true;
			}else if(positionNode.left = null){
				node.left = toadd;
				count ++;
				return true;
			}else{
				printf("Already exist root" + e);
				return false;
			}
		}
		tree.add(e);
	}

	public A3BSTree(){
		// TODO Auto-generated method stub
	}

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		Iterator<E> iter = (Iterator<E>)c.ititerator();
		while(iter.hasNext()){
			this.add(iter.next());
		}
				// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean remove(Object o) {
		Node<E> tmp = search(root,(E)o);{
			if(tmp.data.compareTo((E)op))!=0){
				System.out.println("Not found on tree" + o);
				return false;
			}else{
				if(tmp.left == null && tmp.right == null){
					Iterator<E> iter = (Iterator<E>)c.ititerator();
					while(iter.next != tmp){
						iter.next();
					}
					if(iter.next == tmp){

					})
					tmp = null;
				} else if (tep.left == null || tmp.right == null){
					if(tmp.parent.right == tmp){
						if(tmp.left == null){
							tmp.parent.right = tmp right;
							tmp.parent.right = tmp parents;
						} else {
							tmp.parent.right = tmp left;
							tmp.parent.left = tmp parents;
						}
					} else {
						if(tmp.right == null){
							tmp.parent.left = tmp right;
							tmp.parent.right = tmp parents;
						} else {
							tmp.parent.left = tmp left;
							tmp.parent.left = tmp parents;
						}
					}
				}
			}
		}
	}

	@Override
	public boolean contains(Object o) {
		Node<E> tmp = search(root,(E)o);
		if(tmp.data.compartTo((E)o)){
			return true;
		}else{
			return false;
		}
		// TODO Auto-generated method stub
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
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return getHeight(root);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
