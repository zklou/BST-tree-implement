import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class A3AVLTree <E extends Comparable <? super E>> implements Tree<E>{ //consider extending A3BSTree
	static class Node<E>{
		E data;
		Node left, right, parent;

		public Node(E d)
		{
			data = d;
		}
	}

	private Node<E> root;
	private LinkedList<Node<E>> ListForIterator;
	private int size;
	private LinkedList<Node<E>> TracePath;

	public A3AVLTree(){
		root = null;
		size = 0;
		TracePath = new LinkedList<Node<E>>();
	}

	private Node<E> searchIter(E e)
	{
		LinkedList<Node<E>> list = new LinkedList<>();
		list.add(root);
		while(true)
		{
			if(e.compareTo(list.getLast().data)>0)
			{
				if(list.getLast().right==null)
				{
					return list.getLast();
				}
				list.add(list.getLast().right);
			}else if(e.compareTo(list.getLast().data)==0)
			{
				return list.getLast();
			}else
			{
				if(list.getLast().left==null)
				{
					return list.getLast();
				}
				list.add(list.getLast().left);
			}
		}
	}

	private Node<E> search(Node<E> root, E e)
	{
		if(e.compareTo(root.data) == 0)
		{
			return root;
		}else if(e.compareTo(root.data) > 0)
		{
			if(root.right==null)
			{
				return root;
			}
			return search(root.right, e);		
		}else
		{
			if(root.left==null)
			{
				return root;
			}
			return search(root.left, e);
		}
	}

	@Override
	public boolean add(E e) {
		if(this.root==null)
		{
			root = new Node(e);
			size++;
			return true;
		}else
		{
			Node<E> tmp = search(root, e);
			Node<E> toadd = new Node<E>(e);
			if(tmp.data.compareTo(e)==0)
			{
				System.out.println(e+" is already in the tree, insertion failed.");
				return false; // duplicate
			}else if(tmp.data.compareTo(e)>0)
			{
				toadd.parent = tmp;
				tmp.left = toadd;
				size++;
			}else
			{
				toadd.parent = tmp;
				tmp.right = toadd;
				size++;
			}
			TracePath = new LinkedList<Node<E>>();
			Node<E> b = findUnbalancedNode(toadd);
			if(b == null)
			{
				return true;
			}else
			{
				Rebalance(TracePath, getBalance(b));
				return true;
			}
			
		}

	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		Iterator<E> iter = (Iterator<E>)c.iterator();
		while(iter.hasNext())
		{
			this.add(iter.next());
		}
		return true;
	}

	@Override
	public boolean remove(Object o) {
		Node<E> tmp = search(root, (E)o);
		if(tmp.data.compareTo((E)o)!=0)
		{
			System.out.println(o+" is not in the tree.");
			return false;
		}else
		{
			if(tmp.left!=null && tmp.right!=null)
			{
				this.inorderToList(root);
				int i = ListForIterator.indexOf(tmp);
				if(ListForIterator.get(i+1)!=null)
				{
					tmp.data = ListForIterator.get(i+1).data;
					operationForRemove(ListForIterator.get(i+1));
				}else
				{
					tmp.data = ListForIterator.get(i-1).data;
					operationForRemove(ListForIterator.get(i-1));
				}
			}else
			{
				operationForRemove(tmp);
			}
			size--;
			return true;
		}
	}

	private void operationForRemove(Node tmp)
	{
		if(tmp.left==null && tmp.right==null)
		{
			tmp = null;
		}else if(tmp.left==null || tmp.right==null)
		{
			if(tmp ==this.root)
			{
				if(tmp.left==null)
				{
					root=tmp.right;
					tmp=null;
				}
				else
				{
					root=tmp.left;
					tmp=null;
				}
			}else
			{
				if(tmp.parent.right == tmp)
				{
					if(tmp.left==null)
					{
						tmp.parent.right=tmp.right;
						tmp.right.parent=tmp.parent;
					}
					else
					{
						tmp.parent.right=tmp.left;
						tmp.left.parent=tmp.parent;
					}
				}else
				{
					if(tmp.left==null)
					{
						tmp.parent.left=tmp.right;
						tmp.right.parent=tmp.parent;
					}
					else
					{
						tmp.parent.left=tmp.left;
						tmp.left.parent=tmp.parent;
					}
				}
			}
			
		}
	}

	@Override
	public boolean contains(Object o) {
		Node<E> tmp = search(root, (E)o);
		if(tmp.data.compareTo((E)o) == 0)
		{
			return true;
		}else
		{
			return false;
		}
	}

	@Override
	public Iterator<E> iterator() {
		ListForIterator = new LinkedList();
		inorderToList(root);
		Iterator<E> iter = new Iterator<E>(){
			private int index=0;
			public boolean hasNext()
			{
				return ListForIterator.size()>index;
			}
			public E next()
			{
				return ListForIterator.get(index++).data;
			}
		};

		return iter;
	}

	private void inorderToList(Node root)
	{
		if(root != null)
		{
			inorderToList(root.left);
			ListForIterator.add(root);
			inorderToList(root.right);
		}
	}

	@Override
	public int height() {
		return getHeight(root);
	}

	private int getHeight(Node<E> root)
	{
		if(root==null)
		{
			return -1;
		}
		return 1+Math.max(getHeight(root.left), getHeight(root.right));
	}

	private int getBalance(Node<E> root)
	{
		return getHeight(root.left)-getHeight(root.right);
	}

	private Node<E> findUnbalancedNode(Node<E> node)
	{
		//System.out.println("Balance = "+getBalance(node));
		if(getBalance(node)==2 || getBalance(node)==-2)
		{
			if(TracePath.size() == 3)
			{
				TracePath.removeFirst();
			}
			TracePath.add(node);
			return node;
		}else if(node==this.root  || (getBalance(node) == 0 && hasChild(node)) )
		{
			return null;
		}else
		{
			if(TracePath.size() == 3)
			{
				TracePath.removeFirst();
			}
			TracePath.add(node);
			return findUnbalancedNode(node.parent);	
		}
	}

	private boolean hasChild(Node<E> n)
	{
		if(n.left!=null || n.right!=null)
		{
			return true;
		}else
		{
			return false;
		}
	}

	private void LeftRotate(Node<E> a, Node<E> b)
	{
		if(a.parent!=null)
		{
			if(isLeft(a, a.parent))
			{
				a.parent.left = b;
			}else
			{
				a.parent.right = b;
			}
		}		
		b.parent = a.parent;
		a.parent = b;
		a.right = b.left;
		b.left = a;
		if(this.root == a)
			this.root = b;
		//System.out.println("LEFT");
	}

	private void RightRotate(Node<E> b, Node<E> c)
	{
		if(c.parent!=null)
		{
			if(isLeft(c, c.parent))
			{
				c.parent.left = b;
			}else
			{
				c.parent.right = b;
			}
		}
		b.parent = c.parent;
		c.parent = b;
		c.left = b.right;
		b.right = c;
		if(this.root == c)
			this.root = b;
		//System.out.println("RIGHT");
	}

	private void Rebalance(LinkedList<Node<E>> path, int balance)
	{
		if(balance == 2)
		{
			if(path.getFirst().data.compareTo(path.get(1).data) < 0)
			{
				RightRotate(path.get(1), path.get(2));
			}else
			{
				LeftRotate(path.get(1), path.getFirst());
				RightRotate(path.getFirst(), path.get(2));
			}
		}else
		{
			if(path.getFirst().data.compareTo(path.get(1).data) > 0)
			{
				LeftRotate(path.get(2), path.get(1));
			}else
			{
				RightRotate(path.getFirst(), path.get(1));
				LeftRotate(path.get(2), path.getFirst());
			}
		}
	}


	@Override
	public int size() {
		return size;
	}

	private boolean isLeft(Node<E> a, Node<E> b)
	{
		if(b.left == a)
		{
			return true;
		}else
		{
			return false;
		}
	}

	//public LinkedList<>

	public void getChild()
	{
		ListForIterator = new LinkedList();
		inorderToList(root);
		int i = 0;
		while(i < ListForIterator.size())
		{
			Node<E> node = ListForIterator.get(i);
			System.out.println("For Node "+node.data);
			if(node.left==null)
			{
				System.out.println(" left: null");
			}else
			{
				System.out.println(" left: "+node.left.data);
			}

			if(node.right==null)
			{
				System.out.println(" right: null");
			}else
			{
				System.out.println(" right: "+node.right.data);
			}
			i++;
		}
		
	}




}
 n.left;
				}
			}
			System.out.print("For Node "+node.data);
			if(node.left==null)
			{
				System.out.print(" left: null");
			}else
			{
				System.out.print(" left: "+node.left.data);
			}

			if(node.right==null)
			{
				System.out.print(" right: null");
			}else
			{
				System.out.print(" right: "+node.right.data);
			}
			System.out.println();
		}
		
	}




}
