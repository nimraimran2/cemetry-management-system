/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cemetrysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;

public class sql_model {
    Connection con;
    Statement st;
    ResultSet rs;
    
    sql_model(){
    
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      con=DriverManager.getConnection("jdbc:mysql://localhost:3306/grave_management_system","root","");
      st=con.createStatement();
      System.out.println("DB is connected");
    }catch(Exception e){
       System.out.println(e);
      }
    }
    
    public ResultSet MatchLoginDetails(String username,String password){
    String sql="select * from Employee where E_USERNAME='"+username+"' and E_PASSWORD='"+password+"'";
     
    try{
      rs=st.executeQuery(sql);
    }catch(Exception e){
        System.out.println(e);
    }
     return rs;
    }
    public int Signup(String name,String username,String password,String cnic,String contact,String address){
     int status=0;
     String sql="INSERT INTO Employee(E_NAME,E_USERNAME,E_PASSWORD,E_CNIC,E_CONTACT,E_ADDRESS)VALUES('"+name+"','"+username+"','"+password+"','"+cnic+"','"+contact+"','"+address+"')";
     
     try{
      st.executeUpdate(sql);
      status=1;
     }catch(Exception e){
         System.out.println(e);
     }
     return status;
    }
    
    public int AddDeceased(String name,String cnic,String age,String gender,String dod,String religion){
     int did=-1;
     
     String sql="INSERT INTO deceased_info(D_NAME,D_CNIC_B_FORM,D_AGE,D_GENDER,D_DOD,D_RELIGION)VALUES('"+name+"','"+cnic+"','"+age+"','"+gender+"','"+dod+"','"+religion+"')";
     
     try{
      st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
      ResultSet rs = st.getGeneratedKeys();
      if(rs.next()){
        did = rs.getInt(1);
      }
     }catch(Exception e){
         System.out.println(e);
     }
     return did;
    }
    public int AddPlot(String plotno,String section,String status){
     int stat=0;
     String sql="INSERT INTO plot_info(PLOT_NO,SECTION_NO,STATUS)VALUES('"+plotno+"','"+section+"','"+status+"')";
     
     try{
      st.executeUpdate(sql);
      stat=1;
     }catch(Exception e){
         System.out.println(e);
     }
     return stat;
    }
    public ResultSet getplotrecords(){
      String sql="select * from plot_info";
      try{
      rs=st.executeQuery(sql);
      }catch(Exception e){
      System.out.println(e);
      }
      return rs;
    }
    public int Deleteplot(String plotno, String secno){
      int status=0;  
      String sql="delete from plot_info where PLOT_NO='"+plotno+"' AND SECTION_NO='"+secno+"'";
      try{
      st.executeUpdate(sql);
      status=1;
      }catch(Exception e){
       System.out.println(e);
      }
      return status; 
    }
    public ResultSet searchplot(String search){
      String sql="select * from plot_info where PLOT_NO like '%"+search+"%'or SECTION_NO='"+search+"' or STATUS like '"+search+"'";
      try{
     rs= st.executeQuery(sql);
      }catch(Exception e){
      System.out.println(e);
      }
      return rs;
    }
    public ResultSet getplotrecordsm(){
      String sql="select * from plot_info where STATUS ='Available' AND PLOT_NO like '%M%'";
      try{
      rs=st.executeQuery(sql);
      }catch(Exception e){
      System.out.println(e);
      }
      return rs;
    }
    public ResultSet getplotrecordsh(){
      String sql="select * from plot_info where STATUS ='Available' AND PLOT_NO like '%H%'";
      try{
      rs=st.executeQuery(sql);
      }catch(Exception e){
      System.out.println(e);
      }
      return rs;
    }
    public ResultSet getplotrecordsc(){
      String sql="select * from plot_info where STATUS ='Available' AND PLOT_NO like '%C%'";
      try{
      rs=st.executeQuery(sql);
      }catch(Exception e){
      System.out.println(e);
      }
      return rs;
    }
    
    public int cancelRegistration(int did){
     int stat=0;
     String sql="delete from deceased_info where D_ID = '"+did+"' ";
      try{
      st.executeUpdate(sql);
      stat=1;
      }catch(Exception e){
       System.out.println(e);
      }
      return stat; 
    }
    
    public int AddBurialM(int did,String plotno,String section,String date,String length,String width,String depth){
     int gidm= -1;
     String sql="INSERT INTO grave_info(D_ID,PLOT_NO,SECTION_NO,BURIAL_DATE,G_LENGTH,G_WIDTH,G_DEPTH)VALUES('"+did+"','"+plotno+"','"+section+"','"+date+"','"+length+"','"+width+"','"+depth+"')";
     
     try{
      st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
      ResultSet rs = st.getGeneratedKeys();
      if(rs.next()){
          gidm = rs.getInt(1);
      }
     }catch(Exception e){
         System.out.println(e);
     }
     return gidm;
    }
    
    public int AddBurialC(int did,String plotno,String section,String date,String length,String width,String depth){
        int gidc= -1;
        String sql="INSERT INTO grave_info(D_ID,PLOT_NO,SECTION_NO,BURIAL_DATE,G_LENGTH,G_WIDTH,G_DEPTH)VALUES('"+did+"','"+plotno+"','"+section+"','"+date+"','"+length+"','"+width+"','"+depth+"')";
     
        try{
            st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()){
                gidc = rs.getInt(1);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return gidc;
    }
    
    public int AddCremation(int did,String plotno,String section,String date){
     int cid= -1;
     String sql="INSERT INTO cremation_info(D_ID,PLOT_NO,SECTION_NO,CREMATION_DATE)VALUES('"+did+"','"+plotno+"','"+section+"','"+date+"')";
     
     try{
        st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
            if(rs.next()){
                cid = rs.getInt(1);
            }
     }catch(Exception e){
         System.out.println(e);
     }
     return cid;
    }
    
    public int Payment(int did,String plotno,String section,String date){
     int stat=0;
     String sql="INSERT INTO cremation_info(D_ID,PLOT_NO,SECTION_NO,CREMATION_DATE)VALUES('"+did+"','"+plotno+"','"+section+"','"+date+"')";
     
     try{
      st.executeUpdate(sql);
      stat=1;
     }catch(Exception e){
         System.out.println(e);
     }
     return stat;
    }
    public int cancelPayment(int did){
     int stat=0;
     String sql="delete from deceased_info where D_ID = '"+did+"' ";
     String sql1="delete from grave_info where D_ID = '"+did+"' ";
      try{
      st.executeUpdate(sql);
      st.executeUpdate(sql1);
      stat=1;
      }catch(Exception e){
       System.out.println(e);
      }
      return stat; 
    }
    public int cancelPayment1(int did){
     int stat=0;
     String sql="delete from deceased_info where D_ID = '"+did+"' ";
     String sql1="delete from cremation_info where D_ID = '"+did+"' ";
      try{
      st.executeUpdate(sql);
      st.executeUpdate(sql1);
      stat=1;
      }catch(Exception e){
       System.out.println(e);
      }
      return stat; 
    }
    public int AddPayment(int did, Integer gid, Integer cid,
                      String name, String cnic, String contact,
                      String paddress, String amount, String pmethod) {

    int stat = 0;
    String addressvalue;
    if("".equals(paddress)){
        addressvalue = "NULL";
    }else {
        addressvalue = paddress;
    }
    String sql;
    if (cid == null) {
        sql = "INSERT INTO payment_info " +
              "(D_ID, GRAVE_ID, CREMATION_ID, NAME, CNIC, CONTACT, PICKUP_ADDRESS, AMOUNT, P_METHOD) " +
              "VALUES ('"+did+"','"+gid+"', NULL, '"+name+"','"+cnic+"','"+contact+"','"+addressvalue+"','"+amount+"','"+pmethod+"')";
    } else {
        sql = "INSERT INTO payment_info " +
              "(D_ID, GRAVE_ID, CREMATION_ID, NAME, CNIC, CONTACT, PICKUP_ADDRESS, AMOUNT, P_METHOD) " +
              "VALUES ('"+did+"', NULL ,'"+cid+"','"+name+"','"+cnic+"','"+contact+"','"+addressvalue+"','"+amount+"','"+pmethod+"')";
    }

    try {
        st.executeUpdate(sql);
        stat = 1;
    } catch (Exception e) {
        System.out.println(e);
    }

    return stat;
}

    public int updatestatus(String plotno,String section){
     int stat=0;
     String sql = "UPDATE plot_info SET status='Occupied' " + "WHERE PLOT_NO='"+plotno+"' AND SECTION_NO='"+section+"'";
     
      try{
      st.executeUpdate(sql);
      stat=1;
      }catch(Exception e){
       System.out.println(e);
      }
      return stat; 
    }
    
    public ResultSet showRecords(){
    ResultSet rs = null;
    String sql =
        "SELECT pay.NAME, pay.CNIC, pay.CONTACT, pay.AMOUNT, pay.created_date, pay.created_time, " +
        "g.PLOT_NO, g.SECTION_NO, " +
        "p.D_NAME, p.D_CNIC_B_FORM " +
        "FROM payment_info pay " +
        "JOIN grave_info g ON pay.GRAVE_ID = g.GRAVE_ID " +
        "JOIN deceased_info p ON g.D_ID = p.D_ID";

    try{
        rs = st.executeQuery(sql);
    }catch(Exception e){
        System.out.println(e);
    }
    return rs;
}
    public ResultSet searchRecords(String search){
    ResultSet rs = null;

    String sql =
        "SELECT " +
        "pay.NAME, pay.CNIC, pay.CONTACT, pay.AMOUNT, pay.created_date, pay.created_time, " +
        "g.PLOT_NO, g.SECTION_NO, " +
        "p.D_NAME, p.D_CNIC_B_FORM " +
        "FROM payment_info pay " +
        "JOIN grave_info g ON pay.GRAVE_ID = g.GRAVE_ID " +
        "JOIN deceased_info p ON g.D_ID = p.D_ID " +
        "WHERE " +
        "g.PLOT_NO LIKE '%"+search+"%' " +
        "OR g.SECTION_NO LIKE '%"+search+"%' " +
        "OR p.D_NAME LIKE '%"+search+"%' " +
        "OR p.D_CNIC_B_FORM LIKE '%"+search+"%' " +
        "OR pay.CNIC LIKE '%"+search+"%' " +
        "OR pay.NAME LIKE '%"+search+"%'";

    try{
        rs = st.executeQuery(sql);
    }catch(Exception e){
        System.out.println(e);
    }
    return rs;
}

   
    public static void main (String[]args){
     sql_model db = new sql_model();
    }
  }
