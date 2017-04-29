package com.coding.basic.linkedList;

import java.util.Collection;

/**
 * 尾插式单链表实现
 * 笔记详见：http://www.cnblogs.com/lyu0709/p/6770309.html
 * 
 * @author Administrator
 *
 */
public class SingleLinkedList<T> {
	private Node<T> head;	// 头结点：指向第一个结点的位置
	private Node<T> tail;	// 尾结点
	private int size;
	
	public SingleLinkedList(){
		this.head = new Node<T>();
		this.tail = new Node<T>();
		head.next = tail.next = null;
	}
	
	/**
	 * 从链表尾部开始接集合中的数据
	 * @param c
	 * @return
	 */
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
		
		Object[] objs = c.toArray();
		int len = objs.length;
		if(len==0){
			return false;
		}
		
		Node<T> successor = (index==size?tail:entry(index));		
		Node<T> predecessor = (index==0?head:entry(index-1));
		
		for(int i=0; i<len; i++){
			Node temp = new Node<T>((T)objs[i], null);	//生成目标结点
			predecessor.next = temp;
			predecessor = temp;
		}
		predecessor.next = successor;
		size += len;	
		
		return true;
	}

	private void checkIndex(int index) {
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
	}
	
	private Node<T> entry(int index) {
		checkIndex(index);
		
		Node<T> node = head.next;
		for(int i=0; i<index; i++){
			node = node.next;
		}
		
		return node;
	}
	
	public boolean add(T o){		
		return add(size, o);
	}
	
	private Node<T> addafter(T o, Node<T> node) {
		Node<T> next = node.next;
		Node<T> newNode = new Node<T>(o, next);
		node.next = newNode;
		
		return newNode;
	}
	
	public boolean add(int index , T o){
		checkIndex(index);
		
		Node<T> node = new Node<>(o, null);
		
		Node<T> prev;
		if(index == 0){
			prev = head;
		}else{
			prev = entry(index - 1);
		}
		Node<T> next = prev.next;
		
		node.next = next;
		prev.next = node;
		
		if(index == size){
			this.tail = node;
		}
		
		size++;
		
		return true;
	}
	
	public T get(int index){
		checkIndex(index);
		return entry(index).data;
	}
	
	public T remove(int index){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Index: "+index+ ", Size: "+size);
		}
		
		Node<T> prev = entry(index - 1);
		Node<T> target = prev.next;
		Node<T> next = target.next;
		
		prev.next = next;
		target.next = null;
		T t = target.data;
		target.data = null;
		
		if(index == size - 1){
			this.tail = prev;
		}
		
		size--;
		return t;
	}

	public int size(){
		return this.size;
	}
	
	public void addFirst(T o){
		add(0, o);
	}
	
	public void addLast(T o){
		add(size, o);
	}
	
	public T removeFirst(){
		return remove(0);
	}
	
	public T removeLast(){		
		return remove(size-1);
	}	
	
	private static class Node<T>{
		T data;
		Node<T> next;		
		
		Node(T t, Node next){
			this.data = t;
			this.next = next;
		}
		Node(){
			
		}
	}	
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */	
	public void subtract(LinkedList list){
		
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
	
	public static void main(String[] args){
		
	}
}
