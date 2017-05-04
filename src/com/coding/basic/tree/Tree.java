package com.coding.basic.tree;

public class Tree<T> {
	private TreeNode<T>[] nodes;	//	结点数组
	private int root;	//	根的位置
	private int size;		//结点数
	
	public Tree(){
		nodes = new TreeNode[20];
	}
		
	
	class TreeNode<T>{
		private T data;
		private int parent;
		
		public TreeNode(T data, int parent){
			this.data = data;
			this.parent = parent;
		}
		
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		public int getParent() {
			return parent;
		}
		public void setParent(int parent) {
			this.parent = parent;
		}
	}
}
