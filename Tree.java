package HB;

import java.util.Scanner;

public class Tree {
	Node root;
	int lastLevel;
	
	public class Node {
		String str;
		int level;
		Node next, prev, child, parent;
		
		public Node(String str) {
			this.str = str;
		}
	}
	
	public Tree() {}
	
	public Tree(String str){
		this.add(str);
	}
	/*
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Tree t = new Tree();
		testCommand(scanner, t);
	}
	*/
	public static void testCommand(Scanner scanner, Tree t) { //테스트용 커맨드
		while(true) {
			System.out.println("1.add, 2.addChild, 3.get, 4.getRoot, 5.set, 6.setRoot, 7.size, 8.remove, 9.clear, 0.isEmpty");
			switch (scanner.nextInt()) {
			case 1: //add
				if(t.root == null) {
					t.add(readStr(scanner));
				}
				else {
					t.add(readStr(scanner), readIntegers(scanner));
				}
				break;
			case 2: //addChild
				if(t.root.child == null) {
					t.addChild(readStr(scanner));
				}
				else {
					t.addChild(readStr(scanner), readIntegers(scanner));
				}
				break;
			case 3: //get
				System.out.println(t.get(readIntegers(scanner)));
				break;
			case 4: //getRoot
				System.out.println(t.getRoot());
				break;
			case 5: //set
				t.set(readStr(scanner), readIntegers(scanner));
				break;
			case 6: //setRoot
				t.setRoot(readStr(scanner));
				break;
			case 7: //size
				System.out.println(t.size());
				break;
			case 8: //remove
				t.remove(readIntegers(scanner));
				break;
			case 9: //clear
				t.clear();
				break;
			case 0: //isEmpty
				if(t.isEmpty())
					System.out.println("Empty");
				else
					System.out.println("Not Empty");
				break;
			default:
			}
		}
	}	
	private static String readStr(Scanner scanner) { //테스트용
		System.out.println("str");
		String str= scanner.next();
		return str;
	}
	private static int[] readIntegers(Scanner scanner) { //테스트용
		scanner.nextLine();
		System.out.print("int...>>");
		String number = (String) scanner.nextLine();
		String numberSplit[] = number.split(" ");
		int numbers[] = new int[numberSplit.length];
		for(int i = 0; i < numberSplit.length; i++)
			numbers[i] = Integer.valueOf(Integer.parseInt(numberSplit[i]));
		return numbers;
	}
	
	/*
 	private static int countLevel(Node n) { //왜 만든건지 모르겠다
		int count = 0;
		while(true) {
			if(n.parent == null)
				break;
			count++;
			n = n.parent;
		}
		return count;
	}
	*/
	
	private Node findPosition(int[] args) {
		Node temp = this.root;
		
		for(int i = 0; i < args.length; i++) {
			if(temp.child == null)
				return null; //잘못된 위치값이 입력된 경우
			temp = temp.child;
			for(int j = 0; j < args[i]; j++) {
				if(temp.next == null)
					return null; //잘못된 위치값이 입력된 경우
				temp = temp.next;
			}
		}
		return temp;
	}

	public boolean add(String str, int ... args) { //add next; args는 삽입할 위치(좌표); 삽입에 실패하면 false리턴
		if(this.root == null) { //첫번째 add만 해당
			this.root = new Node(str);
			this.root.level = 0;
			this.root.next = null;
			this.root.prev = null;
			this.root.child = null;
			this.root.parent = null;
			return true;
		}
		else {
			Node temp = this.findPosition(args);
			
			if(temp == null)
				return false; //잘못된 위치값이 입력된 경우
			
			else if(temp.next != null) { //해당 위치의 next가 존재하는 경우; 해당 위치와 next 사이에 삽입
				Node n = temp.next;
				temp.next = new Node(str);
				temp.next.prev = temp;
				temp.next.next = n;
				temp.next.child = null;
				temp.next.parent = temp.parent;
				temp.next.level = temp.level;
				return true;
			}
			else { //해당 위치의 next가 존재하지 않는 경우
				temp.next = new Node(str);
				temp.next.prev = temp;
				temp.next.next = null;
				temp.next.child = null;
				temp.next.parent = temp.parent;
				temp.next.level = temp.level;
				return true;
			}				
		}
	}
	
	public Node add(Node node, String str) {
		if(node == null) {
			node = new Node(str);
			node.level = 0;
			return node;
		}
		else {
			node.next = new Node(str);
			node.next.prev = node;
			node.next.parent = node.parent;
			node.next.level = node.level;
			return node.next;
		}
	}
	
	public boolean addChild(String str, int ... args) {	//add child; args는 삽입할 위치(좌표); 삽입에 실패하면 false리턴; child가 있는 노드에 새로운 child를 추가하려면 add()를 사용해야함
		if(this.root.child == null) {
			this.root.child = new Node(str);
			this.root.child.parent = this.root;
			this.root.child.level = this.root.level + 1;
			return true;
		}
		else {
			Node temp = this.findPosition(args);
			
			if(temp == null)
				return false; //잘못된 위치값이 입력된 경우
			
			else if(temp.child != null)
				return false; //해당 위치에 child가 이미 존재함
			else {
				temp.child = new Node(str);
				temp.child.parent = temp;
				temp.child.level = temp.level + 1;
				return true;
			}
		}
	}
	
	public Node addChild(Node node, String str) {
		if(node.child == null) {
			node.child = new Node(str);
			node.child.parent = node;
			node.child.level = node.level + 1;
			return node.child;
		}
		else
			return null;
	}

	public void clear() { 
		this.root = null;
	}
	
	public String getRoot() {
		return this.root.str;
	}

	public String get(int ... args) { //root의 String은 getRoot()로 호출
		Node temp = this.findPosition(args);
		
		if(temp == null)
			return null; //잘못된 위치값이 입력된 경우
		
		return temp.str;
	}

	public boolean isEmpty() {
		if(this.root == null)
			return true;
		else
			return false;
	}
	
	public boolean remove(int ... args) { //반드시 인자를 넣을 것; 해당 노드의 child도 모두 삭제; root는 clear()를 사용; 삭제에 실패하면 false리턴
		Node temp = this.findPosition(args);
		
		if(temp == null)
			return false; //잘못된 위치값이 입력된 경우
		
		else {
			if(temp.prev == null) {
				if(temp.next == null) {
					temp.parent.child = null;
					temp = null;
					return true;
				}
				else {
					temp.parent.child = temp.next;
					temp.next.prev = null;
					temp = null;
					return true;
				}
			}
			else {
				if(temp.next == null) {
					temp.prev.next = null;
					temp = null;
					return true;
				}
				else {
					temp.prev.next = temp.next;
					temp.next.prev = temp.prev;
					temp = null;
					return true;
				}
			}
		}
	}

	public String setRoot(String str) { //root의 String 수정; 리턴값은 root의 이전 String값
		String tempStr = this.root.str;
		this.root.str = str;
		return tempStr;
	}
	
	public String set(String str, int ... args) { //해당 위치의 String 수정; 리턴값은 해당 위치의 이전 String값; root는 setRoot()를 사용
		Node temp = this.findPosition(args);
		
		if(temp == null)
			return null; //잘못된 위치값이 입력된 경우
		
		String tempStr = temp.str;
		temp.str = str;
		return tempStr;
	}

	public int size() { //Element 수 리턴
		int count = 0;
		Node temp = this.root;
		
		if(temp == null)
			return 0;
		
		count++; //root
		while(true) {
			while(temp.child != null) {
				count++;
				temp = temp.child;
			}
			while(temp.next != null) {
				count++;
				temp = temp.next;
			}
			if(temp.parent == null)
				return count;
			temp = temp.parent;
			while(temp.next == null) {
				if(temp.parent == null)
					return count;
				temp = temp.parent;
			}
			count++;
			temp = temp.next;
			if(temp.next == null && temp.child == null)
				return count;
		}
	}
}
