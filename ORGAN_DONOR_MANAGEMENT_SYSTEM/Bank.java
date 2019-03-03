import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Bank{

    //Declaring UI components
    private JLabel loginUser, loginPass;
    static private JTextField loginUserVal, loginPassVal;
    private JButton loginLogin, loginSignup, loginExit;
    static private JFrame bankFrame;

//    static Statement stmt, st1, st2, st3, st4, st5;
    static private Connection conn;

    private void bankLoginFunc(){
        String user = loginUserVal.getText();
        String pass = loginPassVal.getText();

        if(user == null || pass == null){
            JOptionPane.showMessageDialog(null, "Please enter necessary fields.");
        }else{
            try{
                Statement stmt = conn.createStatement();
                String rr = "select pass from Hospitals where Name = '" + user + "';";
                System.out.println(rr);
                ResultSet rs1 = stmt.executeQuery(rr);
                if(rs1.next()){
                    System.out.println(rs1.getString("Pass"));
                    System.out.println(pass);
                    if (pass.equals(rs1.getString("Pass"))) {
                        System.out.println("Login!");
                        JOptionPane.showMessageDialog(null, "Login Successful.");
                        new Hospital();
                        bankFrame.dispose();
                    }else{
                        System.out.println("Invalid Password for given Username");
                        JOptionPane.showMessageDialog(null, "Invalid Password for given Username");
                    }
                }
                else{
                    System.out.println("No login");
                    JOptionPane.showMessageDialog(null, "Invalid Username and Password. Please Try Again");
                }

            }catch(Exception e2){
                JOptionPane.showMessageDialog(null, "Internal Error. Please check database connectivity.");
            }
        }
    }

    private void bankSignupFunc(){
        new BankSignup();
        bankFrame.dispose();
    }

    public Bank() {

        bankFrame = new JFrame();

//        super("MahaLakshmi Organ Bank"); //Name of Bank
        bankFrame.setLayout(null);

        //Attempt to connect to database.
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?useSSL=false", "root", "xxxx");
            Statement stmt = conn.createStatement();
            String strSelect = "select * from OrganBank";
            ResultSet rset = stmt.executeQuery(strSelect);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        loginUser = new JLabel("Username: ");
        loginUserVal = new JTextField(15);
        loginPass = new JLabel("Password:");
        loginPassVal = new JTextField(15);
        loginLogin = new JButton("Login");
        loginSignup = new JButton("Sign up");
        loginExit = new JButton("Exit");
        bankFrame.add(loginLogin);
        bankFrame.add(loginSignup);
        bankFrame.add(loginExit);
        bankFrame.add(loginUser);
        bankFrame.add(loginUserVal);
        bankFrame.add(loginPass);
        bankFrame.add(loginPassVal);
        loginUser.setBounds(60, 20, 80, 20);
        loginUserVal.setBounds(180, 20, 80, 20);
        loginPass.setBounds(60, 60, 80, 20);
        loginPassVal.setBounds(180, 60, 80, 20);
        loginLogin.setBounds(60, 100, 80, 20);
        loginSignup.setBounds(180, 100, 80, 20);
        loginExit.setBounds(60, 140, 80, 20);
        loginLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                bankLoginFunc();
            }
        });
        loginSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankSignupFunc();
            }
        });
        loginExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bankFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bankFrame.setSize(640, 480);
        bankFrame.setVisible(true);
    }


    public static void main(String args[]){
        new Bank();
    }
}
