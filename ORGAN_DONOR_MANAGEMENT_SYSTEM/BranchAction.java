import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class BranchAction{

    static private JLabel organSelectText;
    static private JComboBox organSelect;
    static private JButton organButton;

    static private Connection conn;

    static private String branch;
    static private String action;

    static private int val;

    static private JFrame branchActionFrame;

    private void organAction(String branch, String action){
        System.out.println("Test");
        try{
            Statement stmt4 = conn.createStatement();
            organSelect.setSelectedItem("Kidney");
            String rr2 = "select "+(String)organSelect.getSelectedItem()+" from OrganBank where BranchName = '"+branch+"';";
            System.out.println(rr2);
            ResultSet rs4 = stmt4.executeQuery(rr2);
            rs4.next();
            if(action.equals("withdraw")) {
                val = rs4.getInt(organSelect.getActionCommand()) -11;
            }else if(action.equals("deposit")){
                val = rs4.getInt(organSelect.getActionCommand()) + 1;
            }

        }catch(Exception e3){
            System.out.println(e3.getMessage());
        }

        try{
            Statement stmt5 = conn.createStatement();
            String rr6 = "update OrganBank set "+organSelect.getActionCommand()+" = "+Integer.toString(val)+" where BranchName = '"+branch+"';";
            System.out.println(rr6);
            boolean rs6 = stmt5.execute(rr6);

            Statement stmt6 = conn.createStatement();
            String strSelect6 = "select * from Hospitals";
            ResultSet rset2 = stmt6.executeQuery(strSelect6);
            while(rset2.next()){
                System.out.println(rset2.getString("Name"));
            }
            JOptionPane.showMessageDialog(null, "Operation Performed Successfully.");
            branchActionFrame.dispose();
        }catch(Exception e2){
            System.out.println(e2.getMessage());
            JOptionPane.showMessageDialog(null, "Internal Error.");
            branchActionFrame.dispose();
        }
    }

    public BranchAction(String bran, String acti){

        this.branch = bran;
        this.action = acti;

        branchActionFrame = new JFrame();

//        super("Sign Up Box");
        branchActionFrame.setLayout(null);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?useSSL=false", "root", "xxxx");
            Statement stmt = conn.createStatement();
            String strSelect = "select * from Hospitals";
            ResultSet rset = stmt.executeQuery(strSelect);
             while(rset.next()){
                System.out.println(rset.getString("Name"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        organSelectText = new JLabel("Select Organ");
        organSelect = new JComboBox();
        organSelect.addItem("Kidney");
        organSelect.addItem("Liver");
        organSelect.addItem("Lung");
        organSelect.addItem("Heart");
        organSelect.addItem("Pancreas");
        organSelect.addItem("Intestine");

        organButton = new JButton("Accept");

        branchActionFrame.add(organSelectText);
        branchActionFrame.add(organSelect);
        branchActionFrame.add(organButton);

        organSelectText.setBounds(20, 20, 80, 20);
        organSelect.setBounds(20, 60, 80, 20);
        organButton.setBounds(20, 100, 80, 20);

        organButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                organAction(branch, action);
            }
        });
        branchActionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        branchActionFrame.setSize(640, 480);
        branchActionFrame.setVisible(true);

    }

//    public static void main(String args[]){
//        new BranchAction(branch, action);
//    }
}
