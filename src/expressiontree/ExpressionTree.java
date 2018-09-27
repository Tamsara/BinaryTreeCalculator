/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressiontree;
import java.util.Stack; 
import java.util.Scanner;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JLabel;
/**
 *
 * @author Ying
 */
class Node { 
  
    char value; 
    Node left, right; 
  
    Node(char item) { 
        value = item; 
        left = right = null; 
    } 
}

public class ExpressionTree {
    public JTree tree;
private JLabel selectedLabel;
boolean isOperator(char c) { 
        if (c == '+' || c == '-'
                || c == '*' || c == '/'
                || c == '^') { 
            return true; 
        } 
        return false; 
    } 
  
    // Utility function to do inorder traversal 
    void inorder(Node t) { 
        if (t != null) { 
            //System.out.println(t.left);
            inorder(t.left); 
            System.out.print(t.value + " "); 
            inorder(t.right); 
        } 
    } 
   public String convert(String postfix){
  Stack<String> s = new Stack<>();
  for(int i = 0; i < postfix.length(); i++){
   char c = postfix.charAt(i);
   if(isOperator(c)){
    String b = s.pop();
    String a = s.pop();
    s.push("("+a+c+b+")");
   }
   else
    s.push(""+c);
  }
   
  return s.pop();
 }
    // Returns root of constructed tree for given 
    // postfix expression 
    Node constructTree(char postfix[]) { 
        Stack<Node> st = new Stack(); 
        Node t, t1, t2; 
  
        // Traverse through every character of 
        // input expression 
        for (int i = 0; i < postfix.length; i++) { 
  
            // If operand, simply push into stack 
            if (!isOperator(postfix[i])) { 
                t = new Node(postfix[i]); 
                st.push(t); 
            } else // operator 
            { 
                t = new Node(postfix[i]); 
  
                // Pop two top nodes 
                // Store top 
                t1 = st.pop();      // Remove top 
                t2 = st.pop(); 
  
                //  make them children 
                t.right = t1; 
                t.left = t2; 
  
                // System.out.println(t1 + "" + t2); 
                // Add this subexpression to stack 
                st.push(t); 
            } 
        } 
  
        //  only element will be root of expression 
        // tree 
        t = st.peek(); 
        st.pop(); 
  
        return t; 
    } 
    public int checkcountoperation(String s){
    int num=0;
    int n = s.length();
    for (int i = 0; i < n; i++) {
           String s1=s.substring(i,i+1);
           if(s1.compareTo("+")==0 ||s1.compareTo("-") ==0 ||s1.compareTo("*")==0|| s1.compareTo("/")==0){
               num++;
           } 
}
    return num;
}
       public boolean checkoperation(String s){
    boolean ans=true;
    int n = s.length();
    for (int i = 0; i < n; i++) {
           String s1=s.substring(i,i+1);
           if(s1.compareTo("+")==0 ||s1.compareTo("-") ==0 ||s1.compareTo("*")==0|| s1.compareTo("/")==0||s1.compareTo("0")==0 ||s1.compareTo("1") ==0 ||s1.compareTo("2")==0|| s1.compareTo("3")==0 || s1.compareTo("4")==0|| s1.compareTo("5")==0|| s1.compareTo("6")==0|| s1.compareTo("7")==0|| s1.compareTo("8")==0|| s1.compareTo("9")==0){
               ans = true;
           } else{
               ans = false;
               System.out.println("you can input Operator only + - * / or you can input Operand only 0 - 9");
               break;
               
           }  
}
    return ans;
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ExpressionTree et = new ExpressionTree(); 
       DefaultMutableTreeNode root1 = new DefaultMutableTreeNode("+");
       DefaultMutableTreeNode parent = new DefaultMutableTreeNode("*");
       DefaultMutableTreeNode parent2 = new DefaultMutableTreeNode("*");
       DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("-");
       Scanner scan = new Scanner(System.in);
      System.out.print("Enter your Postfix Expression: ");
      String postfix= scan.nextLine();
     int num = et.checkcountoperation(postfix);
      if((num <= 10) && (et.checkoperation(postfix))){
        char[] charArray = postfix.toCharArray(); 
        Node root = et.constructTree(charArray); 
        System.out.println("Infix : "+et.convert(postfix));
        //System.out.print("( "); 
        //et.inorder(root); 
        //System.out.print(") "); 
      }else{
              System.out.println("you can't input oparation more than 10 character");
          }
       parent.add(new DefaultMutableTreeNode("2"));
       child1.add(new DefaultMutableTreeNode("5"));
       child1.add(new DefaultMutableTreeNode("1"));
       parent.add(child1);
       parent2.add(new DefaultMutableTreeNode("3"));
       parent2.add(new DefaultMutableTreeNode("2"));
       root1.add(parent);
       root1.add(parent2);
        JFrame frame = new JFrame("Creating a JTree Component!");
                JTree tree = new JTree(root1);
                frame.add(tree);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setUndecorated(true);
                frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
                frame.setSize(200,200);
                frame.setVisible(true);
                JLabel selectedLabel = new JLabel();
                frame.add(selectedLabel, BorderLayout.SOUTH);
                tree.getSelectionModel().addTreeSelectionListener(
                new TreeSelectionListener() {
                @Override
        public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
            .getLastSelectedPathComponent();
        selectedLabel.setText(selectedNode.getUserObject().toString());
                }
            });
    }
    
}
