import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
 * Created by JFormDesigner on Fri Aug 13 15:52:19 PDT 2021
 */



/**
 * @author unknown
 */
public class CompoundInterest extends JFrame {

    //Instantiation of Connection1 class
    //connection to database
    Connection1 con=new Connection1();
    Connection connObj= con.connect();


    public CompoundInterest() throws SQLException, ClassNotFoundException {
        initComponents();
    }

    //add button event
    private void btnAddActionPerformed(ActionEvent e) throws SQLException {

        //declaration of variables
        String custNo;
        String custName;
        String deposit;
        String noOfYears;
        String savingOption;

        //getting the values from textfields
        custNo=txtCustNo.getText();
        custName=txtCustName.getText();
        deposit=txtCustDeposit.getText();
        noOfYears=txtYears.getText();
        savingOption=(String)cboSavings.getSelectedItem();

        //validation for empty fields
        if(custNo.equals("")||custName.equals("")||deposit.equals("")||noOfYears.equals("")||savingOption.equals("")){
            JOptionPane.showMessageDialog(null,"Field should not be empty!!!");
            return;
        }

        //checking for data whether database already has same cust no or not
        String query2="Select * from savingstable where custno=?";
        PreparedStatement query=connObj.prepareStatement(query2);

        query.setString(1,custNo);
        ResultSet rs=query.executeQuery();

        //if record already exist then empty fields
        if(rs.isBeforeFirst()){
            JOptionPane.showMessageDialog(null,"The record is existing");
            txtCustNo.setText("");
            txtCustName.setText("");
            txtCustDeposit.setText("");
            txtYears.setText("");

            return;
        }

        //if record doesnt exist then new record is inserted
        String query3="Insert into savingstable values(?,?,?,?,?)";
        query=connObj.prepareStatement(query3);
        query.setString(1,custNo);
        query.setString(2,custName);
        query.setString(3,deposit);
        query.setString(4,noOfYears);
        query.setString(5,savingOption);

        query.executeUpdate();

        JOptionPane.showMessageDialog(null,"Record added");

        updateTable();
    }

    //edit button event
    private void btnEditActionPerformed(ActionEvent e) throws SQLException {


        DefaultTableModel df=(DefaultTableModel) recordTable.getModel();
        int index=recordTable.getSelectedRow();

        //declarations
        String custNo;
        String custName;
        String deposit;
        String noOfYears;
        String savingOption;

        //fetching values from textfields
        custNo=txtCustNo.getText();
        custName=txtCustName.getText();
        deposit=txtCustDeposit.getText();
        noOfYears=txtYears.getText();
        savingOption=(String)cboSavings.getSelectedItem();

        //validation for empty fields
        if(custNo.equals("")||custName.equals("")||deposit.equals("")||noOfYears.equals("")||savingOption.equals("")){
            JOptionPane.showMessageDialog(null,"Field should not be empty!!!");
            return;
        }

        String oldvalue=df.getValueAt(index,0).toString();

        //update query
        String query4="Update savingstable set custno=?,custname=?,cdep=?,nyears=?,savtype=? where custno=?";
        PreparedStatement query=connObj.prepareStatement(query4);

        query.setString(1, custNo);
        query.setString(2, custName);
        query.setString(3, deposit);
        query.setString(4, noOfYears);
        query.setString(5, savingOption);
        query.setString(6, oldvalue);

        query.executeUpdate();

        JOptionPane.showMessageDialog(null, "Record edited ");

        updateTable();
    }

    //delete button event
    private void btnDeleteActionPerformed(ActionEvent e) throws SQLException {
        //declarations
        String custNo;
        String custName;
        String deposit;
        String noOfYears;
        String savingOption;

        //fetching values from textfields
        custNo=txtCustNo.getText();
        custName=txtCustName.getText();
        deposit=txtCustDeposit.getText();
        noOfYears=txtYears.getText();
        savingOption=(String)cboSavings.getSelectedItem();

        //validation for empty fields
        if(custNo.equals("")||custName.equals("")||deposit.equals("")||noOfYears.equals("")||savingOption.equals("")){
            JOptionPane.showMessageDialog(null,"Field should not be empty!!!");
            return;
        }


        PreparedStatement query;
        //deletion query
        query = connObj.prepareStatement("Delete from savingstable where custno = ?");
        query.setString(1, custNo);

        query.executeUpdate();

        //msg display
        JOptionPane.showMessageDialog(null, "Record deleted ");

        //emptying the textfields
        txtCustNo.setText("");
        txtCustName.setText("");
        txtCustDeposit.setText("");
        txtYears.setText("");
        //method calling
        updateTable();
    }

    //table row clicked event
    private void recordTableMouseClicked(MouseEvent e) {

        //fetching the index from jtable
        DefaultTableModel df=(DefaultTableModel) recordTable.getModel();
        int index=recordTable.getSelectedRow();
        txtCustNo.setText(df.getValueAt(index,0).toString());
        txtCustName.setText(df.getValueAt(index,1).toString());
        txtCustDeposit.setText(df.getValueAt(index,2).toString());
        txtYears.setText(df.getValueAt(index,3).toString());
        cboSavings.setSelectedItem(df.getValueAt(index,4).toString());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        llblCustNo = new JLabel();
        txtCustNo = new JTextField();
        lblCustName = new JLabel();
        txtCustName = new JTextField();
        llblDeposit = new JLabel();
        txtCustDeposit = new JTextField();
        lblYears = new JLabel();
        txtYears = new JTextField();
        lblSavings = new JLabel();
        cboSavings = new JComboBox<>();
        scrollPane1 = new JScrollPane();
        recordTable = new JTable();
        scrollPane2 = new JScrollPane();
        displayTable = new JTable();
        btnAdd = new JButton();
        btnEdit = new JButton();
        btnDelete = new JButton();

        //======== this ========
        setTitle("Compound Interest");
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[475,fill]unrel" +
            "[331,fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[32]rel" +
            "[28]" +
            "[23]" +
            "[39]" +
            "[37]" +
            "[]" +
            "[]"));

        //---- llblCustNo ----
        llblCustNo.setText("Enter the Customer Number");
        contentPane.add(llblCustNo, "cell 0 0");
        contentPane.add(txtCustNo, "cell 1 0 12 1");

        //---- lblCustName ----
        lblCustName.setText("Enter the Customer Name");
        contentPane.add(lblCustName, "cell 0 1");
        contentPane.add(txtCustName, "cell 1 1 12 1");

        //---- llblDeposit ----
        llblDeposit.setText("Enter the Initial Deposit");
        contentPane.add(llblDeposit, "cell 0 2");
        contentPane.add(txtCustDeposit, "cell 1 2 12 1");

        //---- lblYears ----
        lblYears.setText("Enter the number of years");
        contentPane.add(lblYears, "cell 0 3");
        contentPane.add(txtYears, "cell 1 3 12 1");

        //---- lblSavings ----
        lblSavings.setText("Choose the type of savings");
        contentPane.add(lblSavings, "cell 0 4");

        //---- cboSavings ----
        cboSavings.setModel(new DefaultComboBoxModel<>(new String[] {
            "Savings-Deluxe",
            "Savings-Regular"
        }));
        contentPane.add(cboSavings, "cell 1 4 12 1");

        //======== scrollPane1 ========
        {

            //---- recordTable ----
            recordTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    recordTableMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(recordTable);
        }
        contentPane.add(scrollPane1, "cell 0 5");

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(displayTable);
        }
        contentPane.add(scrollPane2, "cell 1 5 12 1");

        //---- btnAdd ----
        btnAdd.setText("Add");
        btnAdd.addActionListener(e -> {
            try {
                btnAddActionPerformed(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnAdd, "cell 0 6");

        //---- btnEdit ----
        btnEdit.setText("Edit");
        btnEdit.addActionListener(e -> {
            try {
                btnEditActionPerformed(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnEdit, "cell 0 6");

        //---- btnDelete ----
        btnDelete.setText("Delete");
        btnDelete.addActionListener(e -> {
            try {
                btnDeleteActionPerformed(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnDelete, "cell 0 6");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    //main method
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //instantiating jframe object
        CompoundInterest myForm=new CompoundInterest();
        //making the form visible
        myForm.setVisible(true);
        //calling updatetable method
        myForm.updateTable();

    }

    //method for updation of data in JTable
    public void updateTable() throws SQLException {
        //query for retrieving all records
        String query1="Select * from savingstable";

        //fetching results
        PreparedStatement query=connObj.prepareStatement(query1, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        ResultSet rs=query.executeQuery();

        DefaultTableModel df=(DefaultTableModel) recordTable.getModel();

        rs.last();

        int q=rs.getRow();

        rs.beforeFirst();

        String[][] array=new String[0][];
        if(q>0){
            array=new String[q][5];
        }

        int i=0;

        //getting the values from database
        while(rs.next()){
            array[i][0]=rs.getString("custno");
            array[i][1]=rs.getString("custname");
            array[i][2]=rs.getString("cdep");
            array[i][3]=rs.getString("nyears");
            array[i][4]=rs.getString("savtype");
            ++i;

        }

        //updating the jtable
        String[] cols={"Number","Name","Deposit","Years","Type of Savings"};
        DefaultTableModel model = new DefaultTableModel(array,cols);
        recordTable.setModel(model);

        recordTable.setDefaultEditor(Object.class, null);


    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel llblCustNo;
    private JTextField txtCustNo;
    private JLabel lblCustName;
    private JTextField txtCustName;
    private JLabel llblDeposit;
    private JTextField txtCustDeposit;
    private JLabel lblYears;
    private JTextField txtYears;
    private JLabel lblSavings;
    private JComboBox<String> cboSavings;
    private JScrollPane scrollPane1;
    private JTable recordTable;
    private JScrollPane scrollPane2;
    private JTable displayTable;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
