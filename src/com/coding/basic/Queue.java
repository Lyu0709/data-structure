package com.coding.basic;
/*
 * 先进先出（FIFO）
 */
public interface Queue {
	
	public void enQueue(Object o);
	
	public Object deQueue();
	
	public boolean isEmpty();
	
	public int size();

}
