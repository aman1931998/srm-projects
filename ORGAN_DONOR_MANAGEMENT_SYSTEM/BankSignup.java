import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class BankSignup{
    static private JLabel signupUser, signupPass, signupCity;
    static private JTextField signupUserVal, signupPassVal, signupCityVal;
    static private JButton signupSignup;

    static private Connection conn;
    static private JFrame bankSignupFrame;

    private void signupSignupFunc(){
        String user = signupUserVal.getText();
        String pass = signupPassVal.getText();
        String city = signupCityVal.getText();
        if (user==null || pass==null || city==null) {
            JOptionPane.showMessageDialog(null, "Please complete the form.");
        }
        System.out.println("Test");
        try{
            Statement stmt = conn.createStatement();
            String rr = "insert into Hospitals values('"+user+"', '"+pass+"', '"+city+"');";
            System.out.println(rr);
            boolean rs1 = stmt.execute(rr);

            Statement stmt2 = conn.createStatement();
            String strSelect2 = "select * from Hospitals";
            ResultSet rset2 = stmt2.executeQuery(strSelect2);
            while(rset2.next()){
                System.out.println(rset2.getString("Name"));
            }
            JOptionPane.showMessageDialog(null, "Account Created Successfully.");
            new Bank();
            bankSignupFrame.dispose();
        }catch(Exception e2){
            System.out.println(e2.getMessage());
            JOptionPane.showMessageDialog(null, "Username Already Exists.");  //Internal Error?
        }
    }

    public BankSignup(){

        bankSignupFrame = new JFrame();
//        super("Sign Up Box");
    bankSignupFrame.setLayout(null);
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

        signupUser = new JLabel("Username");
        signupPass = new JLabel("Password");
        signupCity = new JLabel("City");
        signupUserVal = new JTextField(15);
        signupPassVal = new JTextField(15);
        signupCityVal = new JTextField(15);
        signupSignup = new JButton("Create Account");
        signupUser.setBounds(60, 20, 80, 20);
        signupUserVal.setBounds(180, 20, 80, 20);
        signupPass.setBounds(60, 60, 80, 20);
        signupPassVal.setBounds(180, 60, 80, 20);
        signupCity.setBounds(60, 100, 80, 20);
        signupCityVal.setBounds(180, 100, 80, 20);
        signupSignup.setBounds(180, 140, 80, 20);
        bankSignupFrame.add(signupSignup);
        bankSignupFrame.add(signupUser);
        bankSignupFrame.add(signupUserVal);
        bankSignupFrame.add(signupPass);
        bankSignupFrame.add(signupPassVal);
        bankSignupFrame.add(signupCity);
        bankSignupFrame.add(signupCityVal);
        signupSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signupSignupFunc();
            }
        });
        bankSignupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bankSignupFrame.setSize(640, 480);
        bankSignupFrame.setVisible(true);
    }
//    public static void main(String args[]){
//        new BankSignup();
//    }
}
