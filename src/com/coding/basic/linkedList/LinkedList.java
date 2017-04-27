package com.coding.basic.linkedList;

import java.util.Collection;
import java.util.NoSuchElementException;

import com.coding.basic.Iterator;
import com.coding.basic.List;

/**
 * 底层实现是一个双向循环链表。
 * 双向循环链表的笔记详见：
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class LinkedList<T> implements List<T> {
	private Node<T> head;		
	private int size;
	
	public LinkedList(){
		head = new Node<T>();
		head.previous = head.next = head;
	}
	
	public LinkedList(Collection<? extends T> c){
		this();
		addAll(c);
	}
	
	public boolean addAll(Collection<? extends T> c) {
		return addAll(size, c);
		
	}
	/**
	 * 
	 * @param index 指定Collection中插入的第一个元素的位置
	 * @param c
	 * @return
	 */
	public boolean addAll(int index, Collection<? extends T> c){
		checkIndex(index);
		
		Object[] a = c.toArray();
		int numNew = a.length;
		if(numNew==0){
			return false;
		}
		//新的节点要接在successor之前，接在predecessor之后
		Node<T> successor = (index==size ? head : entry(index));
		Node<T> predecessor = successor.previous;
		for(int i=0; i<numNew; i++){
			Node node = new Node((T)a[i], successor, predecessor);
			predecessor.next = node;
			predecessor = node;
		}
		successor.previous = predecessor;
		size += numNew;		
		return true;
	}

	private void checkIndex(int index) {
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("Index: "+index+ ", Size: "+size);
		}
	}

	private Node<T> entry(int index) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Index: "+index+ ", Size: "+size);
		}
		
		Node<T> node = head;
		if(index < (size >> 1)){
			for(int i=0; i<index; i++){
				node = node.next;
			}
		}else{
			for(int i=size; i>index; i--){
				node = node.previous;
			}
		}		
		return node;
	}

	public boolean add(T o){
		addbefore(o, head);	
		return true;
	}
	
	private Node<T> addbefore(T o, Node<T> node) {
		Node<T> newNode = new Node(o, node, node.previous);
		node.previous.next = newNode;
		node.previous = newNode;
		size++;
		return newNode;
	}

	public boolean add(int index , T o){
		checkIndex(index);
		
		Node node = head;
		for(int i = 0; i < index; i++){
			node = node.next;
		}
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = node.next;
		node.next = newNode;
		size++;
		return true;
	}
	public T get(int index){
		Node<T> node = entry(index);
		return (T)node.data;	
	}
	
	public T remove(int index){
		Node<T> node = entry(index);		
		T t = remove(node);
				
		return t;		
	}
	
	public T remove(Node<T> node){
		if(node == head){
			throw new NoSuchElementException();
		}		
		T t = node.data;
		node.previous.next = node.next;
		node.next.previous = node.previous;
		node.previous = node.next = null;
		size--;
		
		return t;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(T o){
		addbefore(o, head.next);
	}
	
	public void addLast(T o){
		addbefore(o, head);
	}
	
	public T removeFirst(){
		return remove(0);
	}
	
	public T removeLast(){		
		return remove(size-1);
	}
	
	public Iterator iterator (){
		return new LinkedListIterator<T>();
	}
	
	private class LinkedListIterator<T> implements Iterator{
		//最近一次返回的节点
		private Node<T> lastReturn;
		
		private int index;		
		
		@Override
		public boolean hasNext() {			
			return index < size;
		}

		@Override
		public T next() {
			
			return null;
		}
		
	}
		
	private static class Node<T>{
		T data;
		Node<T> previous;
		Node<T> next;		
		
		Node(T t, Node next, Node previous){
			this.data = t;
			this.previous = previous;
			this.next = next;
		}
		Node(){
			
		}
	}
	
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){
		if(null == head || null == head.next){
			return;
		}
		//先把head节点之后的Node顺序倒序，无非就是前节点变后节点，后节点变前节点
		Node prev = null;
		Node curr = head.next;
		Node next;
		while(curr != null){
			prev = curr.previous;
			next = curr.next;
			curr.previous = next;
			curr.next = prev;
			prev = curr;
			curr = next;			
		}
		//处理head节点
		head.data = null;
		head = prev;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf(){
		
		
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length){
		
		
		
	}
	
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public int[] getElements(LinkedList list){
		
		
		return null;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */	
	public void subtract(LinkedList list){
		
	}
	
	/**
	 * 传入数据删除节点
	 * @param obj
	 */
	public void remove(Object obj){
		
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues(){
		
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max){
		
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public LinkedList intersection(LinkedList list){
		
		return null;
	}
}
