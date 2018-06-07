package HB;

import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import HB.Tree;
import HB.Tree.Node;

public class TestGUI extends JFrame {
	static JTextArea ta = new JTextArea(8, 20);
	JScrollPane sp = new JScrollPane(ta);
	JButton btn = new JButton("Make Tree");
	static JPanel pn = new JPanel();
	static Tree tree = new Tree();
	
	TestGUI(){
		setTitle("test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		btn.addActionListener(new MakeTree());
		
		c.add(sp);
		c.add(btn);
		pn.setLayout(new FlowLayout());
		c.add(pn);
		
		setSize(300, 600);
		setVisible(true);
		
	}
	
	public static void showTree() { //만들어진 Tree 가시화; 미완성
		if(tree.root != null) {
			Node temp = tree.root;
			pn.add(new JLabel(temp.str));
			
			while(true) {
				while(temp.child != null || temp.next != null) {
					if(temp.child != null) {
						temp = temp.child;
					}
					else if(temp.next != null) {
						temp = temp.next;
					}
					pn.add(new JLabel(temp.str));
				}
				if(temp.parent == null)
					break;
				while(temp.parent.next == null) {
					temp = temp.parent;
				}
				temp = temp.parent.next;
			}
		}
		pn.setEnabled(true);
		pn.setVisible(true);
	}

	public static void main(String[] args) {
		new TestGUI();
	}

}


class MakeTree implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Scanner s = new Scanner(TestGUI.ta.getText());
		int level = 0;
		Node temp = TestGUI.tree.root;
		TestGUI.pn.revalidate();
		TestGUI.pn.repaint();
		
		while(true) {
			try {
				String str = s.nextLine();
				int count = 0;
				char c = str.charAt(count);
				while(c == '	') {
					count++;
					c = str.charAt(count);
				}
				if(count == level) {
					temp = TestGUI.tree.add(temp, str.trim());
				}
				else if(count > level) {
					temp = TestGUI.tree.addChild(temp, str.trim());
				}
				else if(count < level) {
					while(count < level) {
						temp = temp.parent;
						level--;
					}
					temp = TestGUI.tree.add(temp, str.trim());
				}
			}
			catch(NoSuchElementException ex) {
				s.close();
				break;
			}
		}
	}
	
}
