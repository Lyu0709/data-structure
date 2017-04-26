package com.coding.basic.linkedList;


/**
 * 单链表实现
 * 
 * @author Administrator
 *
 */
public class SingleLinkedList<T> {
	
	
	private static class Node<T>{
		T data;
		Node<T> next;		
		
		Node(T t, Node next, Node previous){
			this.data = t;
			this.next = next;
		}
		Node(){
			
		}
	}	
}
