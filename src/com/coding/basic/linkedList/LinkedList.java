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
			for(int i=0; i<=index; i++){
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
//		System.out.println("remove(i): i="+index+" data="+node.data);
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
		
		void clear(){
			this.data = null;
			this.previous = null;
			this.next = null;
		}
	}
	
	public String toString(){
		if(size==0){
			return null;
		}
		StringBuilder sb = new StringBuilder("(");
		Node<T> node = head.next;
		for(int i=0; i<size-1; i++){			
			sb.append(node.data+",");
			node = node.next;
		}
		sb.append(node.data+")");
		return sb.toString();
	}
	
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){
		if(null == head || null == head.next){
			return;
		}
		
		Node<T> curr = head.previous;	// 最后一个结点
		
		Node<T> firstNode = head.next;
		head.next = head.previous;
		head.previous = firstNode;
		
		while(curr != head){
			Node<T> prev = curr.previous;
			Node<T> next = curr.next;
			curr.previous = next;
			curr.next = prev;
			curr = prev;
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf(){
		if(size==0){
			return;
		}
		
		int firstHalfAmount = size / 2;
		for(int i=0; i<firstHalfAmount; i++){
			Node<T> curr = head.next;
			head.next = curr.next;
			curr.next.previous = head;
			curr.data = null;
			curr.previous = null;
			curr.next = null;
			size--;
		}
		//head.next.previous = head;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length){
		if(i+length>size){
			throw new IndexOutOfBoundsException();
		}
		
		for(int j=0; j<length; j++){
			remove(i);
//			i++;
		}
		
		
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
		if(list.size()==0){
			return null;
		}
		int[] result = new int[list.size()];
		for(int i=0; i<list.size(); i++){
			Integer j = (Integer)list.get(i);
			Integer t = (Integer)this.entry(j).data;
			result[i] = t;
		}
		
		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */	
	public void subtract(LinkedList list){
		for(int i=0; i<list.size(); i++){
			remove(list.get(i));
		}
	}
	
	/**
	 * 传入数据删除节点
	 * @param obj
	 */
	public void remove(Object obj){
		Node<T> node = head.next;
		Node<T> temp;
		int num = 0;
		for(int i=0; i<size; i++){
//			System.out.println("obj: "+obj+" node.data: "+node.data);
			
			if(node.data.equals(obj)){
				node.previous.next = node.next;
				node.next.previous = node.previous;
				temp = node.next;
				node.clear();				
				num++;
				node = temp;
			}else{
				node = node.next;
			}
			
			
		}
		size -= num;
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
	
	public static void main(String[] args) {
		
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		
		LinkedList indexList = new LinkedList();
		indexList.add(1);
		indexList.add(2);

		System.out.println(list.toString());

//		list.reverse();
		
//		list.removeFirstHalf();
		
//		list.remove(3, 2);
		
//		int[] result = list.getElements(indexList);
//		for(int i=0; i<result.length; i++){
//			System.out.println(result[i]);
//		}
		
//		list.remove((Integer)3);
		
		list.subtract(indexList);
		
		
		
		System.out.println(list.toString());
		
	}
}
