import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Hospital{
    static private JLabel hospitalBranchSelect;
    static private JButton cityAhmedabad, cityBengaluru, cityChennai, cityHydrabad, cityJaipur;
    static private JButton cityKolkata, cityMumbai, cityNewDelhi, cityPune, citySurat;
    static private JButton hospitalLogout, hospitalExit;

    static private Connection conn;

    static private JFrame hospitalFrame;

    private void cityCaller(String a){
        new Branch(a);
        hospitalFrame.dispose();
    }

    private void hospitalLogoutFunc(){
        new Bank();
        hospitalFrame.dispose();
    }

    public Hospital(){

        hospitalFrame = new JFrame();

//        super("Welcome");
        hospitalFrame.setLayout(null);

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

        hospitalBranchSelect = new JLabel("Select the Branch: ");
        cityAhmedabad = new JButton("Ahmedabad");
        cityBengaluru = new JButton("Bengaluru");
        cityChennai = new JButton("Chennai");
        cityHydrabad = new JButton("Hydrabad");
        cityJaipur = new JButton("Jaipur");
        cityKolkata = new JButton("Kolkata");
        cityMumbai = new JButton("Mumbai");
        cityNewDelhi = new JButton("New Delhi");
        cityPune = new JButton("Pune");
        citySurat = new JButton("Surat");
        hospitalLogout = new JButton("Logout");
        hospitalExit = new JButton("Exit");
        hospitalFrame.add(hospitalBranchSelect);
        hospitalFrame.add(cityAhmedabad);
        hospitalFrame.add(cityBengaluru);
        hospitalFrame.add(cityChennai);
        hospitalFrame.add(cityHydrabad);
        hospitalFrame.add(cityJaipur);
        hospitalFrame.add(cityKolkata);
        hospitalFrame.add(cityMumbai);
        hospitalFrame.add(cityNewDelhi);
        hospitalFrame.add(cityPune);
        hospitalFrame.add(citySurat);
        hospitalFrame.add(hospitalLogout);
        hospitalFrame.add(hospitalExit);
        hospitalBranchSelect.setBounds(60, 20, 80, 20);
        cityAhmedabad.setBounds(60, 20, 80, 20);
        cityBengaluru.setBounds(180, 20, 80, 20);
        cityChennai.setBounds(60, 60, 80, 20);
        cityHydrabad.setBounds(180, 60, 80, 20);
        cityJaipur.setBounds(60, 100, 80, 20);
        cityKolkata.setBounds(180, 100, 80, 20);
        cityMumbai.setBounds(60, 140, 80, 20);
        cityNewDelhi.setBounds(180, 140, 80, 20);
        cityPune.setBounds(60, 180, 80, 20);
        citySurat.setBounds(180, 180, 80, 20);
        hospitalLogout.setBounds(180, 220, 80 ,20);
        hospitalExit.setBounds(180, 260, 80, 20);

        cityAhmedabad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Ahmedabad");
            }
        });
        cityBengaluru.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Bengaluru");
            }
        });
        cityChennai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Chennai");
            }
        });
        cityHydrabad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Hydrabad");
            }
        });
        cityJaipur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Jaipur");
            }
        });
        cityKolkata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Kolkata");
            }
        });
        cityMumbai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Mumbai");
            }
        });
        cityNewDelhi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("New Delhi");
            }
        });;
        cityPune.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Pune");
            }
        });;
        citySurat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityCaller("Surat");
            }
        });
        hospitalLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hospitalLogoutFunc();
            }
        });
        hospitalExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        hospitalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hospitalFrame.setSize(640, 480);
        hospitalFrame.setVisible(true);

    }
//    public static void main(String args[]){
//        new Hospital();
//    }
}
