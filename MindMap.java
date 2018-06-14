package Project;

import java.awt.*;
import java.awt.event.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.filechooser.*;

//import Project.MyComponent;
import HB.Tree;
import HB.Tree.Node;

public class MindMap extends JFrame {
   private String[] itemTitle = {"새로 만들기","열기","저장","다른 이름으로 저장","닫기","적용","변경"};
   private JPanel contentPane;
   JMenuBar mb;
   JToolBar tb;
   static JPanel TP = new JPanel();
   static JPanel MP = new JPanel();
   static JPanel AP = new JPanel();
   static JButton b1, b2, b3, b4, b5;
   static JTextArea ta;
   JLabel label;
   JPanel p;
   JLabel l1, l2, l3, l4, l5, l6;
   static JTextField tf1, tf2, tf3, tf4, tf5, tf6;
   int x, y, w, h;
   static Tree tree = new Tree();
   static Node selectedNode;
   
   JSplitPane SplitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(TP), new JScrollPane(MP));
    JSplitPane SplitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(SplitPane1),new JScrollPane(AP));

   public MindMap() {
      super("간단한 마인드맵 애플리케이션");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
      setSize(800,500);
      setVisible(true);
      MenuBar();
      ToolBar();
      add(SplitPane2);
      SplitPane1.setDividerLocation(180);
       SplitPane2.setDividerLocation(600);
       
       TP.setLayout(new BorderLayout());
       b1 = new JButton("Text Editor Pane");
       b1.setEnabled(false);
       TP.add(b1, BorderLayout.NORTH);
       
       ta = new JTextArea(10,10);
       TP.add(new JScrollPane(ta), BorderLayout.CENTER);
       
       b2 = new JButton("적용");
       b2.setBackground(Color.RED);
       b2.addActionListener(new MakeTree());
       TP.add(b2, BorderLayout.SOUTH);
      
       MP.setLayout(new BorderLayout());
       b3 = new JButton("MindMap Pane");
       b3.setEnabled(false);
       MP.setBackground(Color.BLACK);
       MP.add(b3, BorderLayout.NORTH);
       
       label = new JLabel("");
       MP.add(label, BorderLayout.CENTER);
       
       AP.setLayout(new BorderLayout());
       b4 = new JButton("Attribute Pane");
       b4.setEnabled(false);
       AP.add(b4, BorderLayout.NORTH);
       
       p = new JPanel(new GridLayout(6,2,2,2));
       l1 = new JLabel("  TEXT:");
       l2 = new JLabel("  X:");
       l3 = new JLabel("  Y:");
       l4 = new JLabel("  W:");
       l5 = new JLabel("  H:");
       l6 = new JLabel("  Color:");
       tf1 = new JTextField();
       tf2 = new JTextField();
       tf3 = new JTextField();
       tf4 = new JTextField();
       tf5 = new JTextField();
       tf6 = new JTextField();
       
      p.add(l1);
      p.add(tf1);
      tf1.setEditable(false);
      p.add(l2);
      p.add(tf2);
      p.add(l3);
      p.add(tf3);
      p.add(l4);
      p.add(tf4);
      p.add(l5);
      p.add(tf5);
      p.add(l6);
      p.add(tf6);
      AP.add(p, BorderLayout.CENTER);
      
       b5 = new JButton("변경");
       b5.setBackground(Color.RED);
       b5.addActionListener(new Attribute());
       AP.add(b5, BorderLayout.SOUTH);
       /*
       b2.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
             JButton b = (JButton)e.getSource();
             //MyComponent component = new MyComponent(ta);
             label.setText(ta.getText());
              label.setLocation(1000,1000);
              
              x=label.getX();
              y=label.getY();
              
              tf2.setText(""+x);
              tf3.setText(""+y);
          }
       });
       */
       
       //contentPane.addMouseListener(new MyMouseListener());

   }
   
   private void ToolBar() {
      // TODO Auto-generated method stub
      tb = new JToolBar();
      JButton[] ToolItems = new JButton[7];
      ToolActionListener listener = new ToolActionListener();
      for(int i=0;i<ToolItems.length;i++) {
         ToolItems[i] = new JButton(itemTitle[i]);
         ToolItems[i].addActionListener(listener);
         tb.add(ToolItems[i]);
      }
      contentPane.add(tb,BorderLayout.NORTH);
   }
   private void MenuBar() {
      // TODO Auto-generated method stub
      mb = new JMenuBar();
      JMenuItem[] menuItem = new JMenuItem[7];
      JMenu screenMenu = new JMenu("Menu Bar");
      MenuActionListener listener = new MenuActionListener();
        for(int i=0;i<menuItem.length;i++) {
           menuItem[i] = new JMenuItem(itemTitle[i]);
           menuItem[i].addActionListener(listener);
           screenMenu.add(menuItem[i]);
           screenMenu.addSeparator();
           }
        mb.add(screenMenu);
        setJMenuBar(mb);
   }
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
            public void run() {
               try {
                  MindMap frame = new MindMap();
                  frame.setVisible(true);
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
         });
   }
}

class ToolActionListener implements ActionListener{
   public void actionPerformed(ActionEvent e) {
      JButton b = (JButton)e.getSource();
      if(b.getText().equals("새로 만들기")) {
      }
      else if(b.getText().equals("열기")) {
      }
      else if(b.getText().equals("저장")) {
      }
      else if(b.getText().equals("다른 이름으로 저장")){
      }
      else if(b.getText().equals("닫기")){
         System.exit(0);
      }
      else if(b.getText().equals("적용")) {
      }
      else if(b.getText().equals("변경")) {
      }
   }
}

class MyMouseListener implements MouseListener, MouseMotionListener {
	int clickedLocationX, clickedLocationY;
	
    public void mousePressed(MouseEvent e) {
    	clickedLocationX = e.getX();
    	clickedLocationY = e.getY();
    }
    public void mouseClicked(MouseEvent e) {
  	  Node node = (Node)e.getSource();
  	  MindMap.selectedNode = node;
  	  MindMap.tf1.setText(node.get());
  	  MindMap.tf2.setText(String.valueOf(node.X));
  	  MindMap.tf3.setText(String.valueOf(node.Y));
  	  MindMap.tf4.setText(String.valueOf(node.W));
  	  MindMap.tf5.setText(String.valueOf(node.H));
  	  MindMap.tf6.setText(node.color.substring(2));
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {
    	Node node = (Node)e.getSource();
    	int tempX = e.getX() - clickedLocationX;
    	int tempY = e.getY() - clickedLocationY;
    	clickedLocationX = e.getX();
    	clickedLocationY = e.getY();
    	node.X += tempX;
    	node.Y += tempY;
    	node.setLocation(node.X, node.Y);
    }
    public void mouseMoved(MouseEvent e) {}
}

class MenuActionListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
         String cmd = e.getActionCommand();
         switch(cmd) {
         case "새로 만들기" :
            break;
         case "열기" :
            break;
         case "저장" :
            break;
         case "다른 이름으로 저장" :
            break;
         case "닫기" :
            System.exit(0);
            break;
         case "적용" :
            break;
         case "변경" :
            break;
         }
     }
}

class MakeTree implements ActionListener { //JTextArea를 읽어 Tree를 만드는 버튼리스너

	@Override
	public void actionPerformed(ActionEvent e) {
		MindMap.MP.removeAll();
		MindMap.MP.setBackground(Color.BLACK);
		MindMap.MP.add(MindMap.b3);
		Scanner s = new Scanner(MindMap.ta.getText());
		int level = 0;
		Node temp = MindMap.tree.root; //tree의 root
		
		while(true) {
			try {
				String str = s.nextLine(); //한 줄을 읽고
				int count = 0;
				char c = str.charAt(count);
				
				while(c == '	') { //탭의 개수를 카운트
					count++;
					c = str.charAt(count);
				}
				if(count == level) { //위의 노드와 같은 레벨이면 add
					temp = MindMap.tree.add(temp, str.trim());
					temp.addMouseListener(new MyMouseListener());
					MindMap.MP.add(temp);
				}
				else if(count > level) { //위의 노드보다 높은 레벨(자식)이면 addChild
					temp = MindMap.tree.addChild(temp, str.trim());
					temp.addMouseListener(new MyMouseListener());
					MindMap.MP.add(temp);
					level++;
				}
				else if(count < level) { //위의 노드보다 낮은 레벨이면 
					while(count < level) { //현재 노드와 레벨이 같아질 때까지 올라가다가
						temp = temp.parent;
						level--;
					}
					temp = MindMap.tree.add(temp, str.trim()); //add
					temp.addMouseListener(new MyMouseListener());
					MindMap.MP.add(temp);
				}
			}
			catch(NoSuchElementException ex) { //더이상 읽을 줄이 없으면 break
				temp = MindMap.tree.root;
				s.close();
				break;
			}
		}
	}
}

class Attribute implements ActionListener { //속성 수정 "적용"버튼리스너

	@Override
	public void actionPerformed(ActionEvent e) {
		Node node = MindMap.selectedNode;
		node.X = Integer.parseInt(MindMap.tf2.getText());
		node.Y = Integer.parseInt(MindMap.tf3.getText());
		node.W = Integer.parseInt(MindMap.tf4.getText());
		node.H = Integer.parseInt(MindMap.tf5.getText());
		node.color = MindMap.tf6.getText();
		node.setBounds(node.X, node.Y, node.W, node.H);
		node.setBackground(new Color(Integer.parseInt(node.color, 16)));
	}
	
}
