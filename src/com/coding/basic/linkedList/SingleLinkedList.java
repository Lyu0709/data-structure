package com.coding.basic.linkedList;

import java.util.Collection;

/**
 * 尾插式单链表实现
 * 
 * @author Administrator
 *
 */
public class SingleLinkedList<T> {
	private Node<T> head;	// 头结点
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
		
		//若0<index<=size，则原链表需要从腰上斩断然后再接新的结点，斩断后的长度为：
		size = size - (size - index);
		/*
		 * 若index=0，则从头开始接数据，原数据舍弃
		 * 若0<index<size，则tail也要变化
		 * 若index=size，则tail不变
		 */
		if(index == 0){
			tail.data = null;
			head.next = tail;
		}else if(index < size){
			tail = entry(index - 1);
		}
		
		for(int i=0; i<len; i++){
			Node temp = new Node<T>((T)objs[i], null);	//生成目标结点
			tail.next = temp;	//将目标结点挂在链表的尾巴上
			tail = temp;	//此时该结点为尾巴结点
			size++;	//长度增长1
		}
		
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
		
		return true;
	}
	
	private Node<T> addbefore(T o, Node<T> node) {
		
		return null;
	}
	
	public boolean add(int index , T o){
		return true;
	}
	
	public T get(int index){
		checkIndex(index);
		return entry(index).data;
	}
	
	public T remove(int index){
		checkIndex(index);
		
		
		return null;
	}
	
	public T remove(Node<T> node){
		
		return null;
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(T o){
		
	}
	
	public void addLast(T o){
		
	}
	
	public T removeFirst(){
		return null;
	}
	
	public T removeLast(){
		
		return null;
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
}
