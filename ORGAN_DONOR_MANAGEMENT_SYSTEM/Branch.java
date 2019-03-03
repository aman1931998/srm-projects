import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Branch{
    static private JLabel organSelect, kidney, liver, lung, heart, pancreas, intestine,kidneyVal, liverVal, lungVal, heartVal, pancreasVal, intestineVal;
    static private JButton withdrawWithdraw, depositDeposit, branchBranchWindow, branchLogout, branchExit;

    static private String branch;
    static private Connection conn;

    static private JFrame branchFrame;

    private void getBranchData(){
        try {
            Statement stmt2 = conn.createStatement();
            String strSelect = "select * from OrganBank where BranchName = '"+branch+"';";
            System.out.println(strSelect);
            ResultSet rset2 = stmt2.executeQuery(strSelect);
            rset2.next();
            System.out.println(rset2.getString("Kidney"));
            System.out.println(rset2.getString("Liver"));
            System.out.println(rset2.getString("Lung"));
            System.out.println(rset2.getString("Heart"));
            System.out.println(rset2.getString("Pancreas"));
            System.out.println(rset2.getString("Intestine"));

            kidneyVal = new JLabel(rset2.getString("Kidney"));
            liverVal = new JLabel(rset2.getString("Liver"));
            lungVal = new JLabel(rset2.getString("Lung"));
            heartVal = new JLabel(rset2.getString("Heart"));
            pancreasVal = new JLabel(rset2.getString("Pancreas"));
            intestineVal = new JLabel(rset2.getString("Intestine"));

        }catch(Exception ex2){
            System.out.println(ex2.getMessage());
        }
    }

    private void withdrawFunc(String bran){BranchAction withdraw = new BranchAction(bran, "withdraw");}

    private void depositFunc(String bran){BranchAction withdraw = new BranchAction(bran, "deposit"); }

    private void branchWindowFunc(){
        new Hospital();
        branchFrame.dispose();
    }

    private void branchLogoutFunc(){
        new Bank();
        branchFrame.dispose();
    }

    public Branch(String bran){

        branchFrame = new JFrame();

        branchFrame.setLayout(null);

        branch = bran;

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

        organSelect = new JLabel("Organ Info for current Branch is displayed below.");
        kidney = new JLabel("Kidney: ");
        liver = new JLabel("Liver: ");
        lung = new JLabel("Lung: ");
        heart = new JLabel("Heart: ");
        pancreas = new JLabel("Pancreas: ");
        intestine = new JLabel("Intestine: ");

        getBranchData();

        withdrawWithdraw = new JButton("Withdraw");
        depositDeposit = new JButton("Deposit");

        branchBranchWindow = new JButton("Back to Branch Selection.");
        branchLogout = new JButton("Logout");
        branchExit = new JButton("Exit");

        organSelect.setBounds(60, 20, 80, 20);
        kidney.setBounds(60, 60, 80, 20);
        kidneyVal.setBounds(180, 60, 80, 20);
        liver.setBounds(60, 100, 80, 20);
        liverVal.setBounds(180, 100, 80, 20);
        lung.setBounds(60, 140, 80, 20);
        lungVal.setBounds(180, 140, 80, 20);
        heart.setBounds(60, 180, 80, 20);
        heartVal.setBounds(180, 180, 80, 20);
        pancreas.setBounds(60, 220, 80, 20);
        pancreasVal.setBounds(180, 220, 80, 20);
        intestine.setBounds(60, 260, 80, 20);
        intestineVal.setBounds(180, 260, 80, 20);
        withdrawWithdraw.setBounds(60, 300, 80, 20);
        depositDeposit.setBounds(180, 300, 80, 20);
        branchBranchWindow.setBounds(60, 340, 260, 20);
        branchLogout.setBounds(60, 380, 80, 20);
        branchExit.setBounds(180, 380, 80, 20);

        branchFrame.add(organSelect);
        branchFrame.add(kidney);
        branchFrame.add(kidneyVal);
        branchFrame.add(liver);
        branchFrame.add(liverVal);
        branchFrame.add(lung);
        branchFrame.add(lungVal);
        branchFrame.add(heart);
        branchFrame.add(heartVal);
        branchFrame.add(pancreas);
        branchFrame.add(pancreasVal);
        branchFrame.add(intestine);
        branchFrame.add(intestineVal);
        branchFrame.add(withdrawWithdraw);
        branchFrame.add(depositDeposit);
        branchFrame.add(branchBranchWindow);
        branchFrame.add(branchLogout);
        branchFrame.add(branchExit);

        withdrawWithdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdrawFunc(bran);
            }
        });
        depositDeposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositFunc(bran);
            }
        });
        branchBranchWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                branchWindowFunc();
            }
        });
        branchLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                branchLogoutFunc();
            }
        });
        branchExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        branchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        branchFrame.setSize(640, 480);
        branchFrame.setVisible(true);
    }
//    public static void main(){
//        new Branch(branch);
//    }
}
