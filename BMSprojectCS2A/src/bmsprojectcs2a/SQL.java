 package bmsprojectcs2a;

import java.sql.*;
        
public class SQL {
   Connection con;
   Statement st;
   ResultSet rs;
   
   SQL(){
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db_smiu","root","");
           st = con.createStatement();
           System.out.println("Database Is Connected");
       }catch(Exception e){
           System.out.println(e);
       }
   }
   
   public int registercustomer(String name, String fname, String dob, String gender, String email, String address, String city, int postal){
       int cid = -1;
       String sql = "INSERT INTO `customer_details`(`C_NAME`, `C_FNAME`, `C_DOB`, `C_GENDER`, `C_EMAIL`, `C_ADDRESS`, `C_CITY`, `C_POSTAL`) VALUES ('"+name+"','"+fname+"','"+dob+"','"+gender+"','"+email+"','"+address+"','"+city+"',"+postal+")";
       
       try{
           st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
           ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            cid = rs.getInt(1);
        }    
       }catch(Exception e){
           System.out.println(e);
       }
       return cid;
   }
   
   public int registeraccount(int cid ,long AN, int PC, String AT){
    int stat = 0;
    String sql = "INSERT INTO `account_details`(`C_ID`,`A_CARD`, `A_PIN`, `A_TYPE`, AMOUNT) VALUES ('"+cid+"','"+AN+"','"+PC+"','"+AT+"',0)";
    
    try{
           st.executeUpdate(sql);
           stat=1;
       }catch(Exception e){
           System.out.println(e);
       }
       return stat;
    }
    
    public ResultSet MatchLoginDetails(long CardNumber, int PinCode){
        String sql = "select * from account_details where A_CARD='"+CardNumber+"' and A_PIN='"+PinCode+"' ";
        try{
            rs =st.executeQuery(sql);
        }catch(Exception e){
           System.out.println(e);
        }
        return rs;
    }
    
    public int cancelregisteration(int cid) {
        int stat = 0;

        String sql = "DELETE FROM `customer_details` WHERE `C_ID` = '"+cid+"' ";

        try {
            st.executeUpdate(sql);
            stat = 1;
        } catch (Exception e) {
            System.out.println(e);
        }

    return stat;
    }
    
    public int changepin(long CN, int pin) {
        int stat = 0;

        String sql = "UPDATE `account_details` SET `A_PIN`='"+pin+"' WHERE `A_CARD` = '"+CN+"' ";

        try {
            st.executeUpdate(sql);
            stat = 1;
        } catch (Exception e) {
            System.out.println(e);
        }

    return stat;
    }
    
    public int deposit(long CN, int cash) {
        int stat = 0;

        String sql = "UPDATE `account_details` SET `AMOUNT`= AMOUNT + '"+cash+"' WHERE `A_CARD` = '"+CN+"' ";

        try {
            st.executeUpdate(sql);
            stat = 1;
        } catch (Exception e) {
            System.out.println(e);
        }

    return stat;
    }
    
    public int transactions(long CN ,String TT, int cash){
    int stat = 0;
    String sql = "INSERT INTO `transactions`(`A_CARD`,`T_TYPE`,AMOUNT) VALUES ('"+CN+"','"+TT+"','"+cash+"')";
    
    try{
           st.executeUpdate(sql);
           stat=1;
       }catch(Exception e){
           System.out.println(e);
       }
       return stat;
    }
   
    public int withdraw(long CN, int cash) {
        int stat = 0;

        String sql = "UPDATE `account_details` SET `AMOUNT`= AMOUNT - '"+cash+"' WHERE `A_CARD` = '"+CN+"' ";

        try {
            st.executeUpdate(sql);
            stat = 1;
        } catch (Exception e) {
            System.out.println(e);
        }

    return stat;
    }
    
    public ResultSet balanceinquiry(long CN){
        String sql = "select A_CARD,AMOUNT from account_details where A_CARD='"+CN+"' ";
        try{
            rs =st.executeQuery(sql);
        }catch(Exception e){
           System.out.println(e);
        }
        return rs;
    }
    
    public ResultSet getrecords(long CN){
        String sql = "select * from transactions WHERE A_CARD = '"+CN+"'";
        try{
            rs = st.executeQuery(sql);
        }catch (Exception e){
            System.out.println(e);
        }
        return rs;
    }
    
    public int billtransactions(long CN ,String TT, int cash, String cno){
    int stat = 0;
    String sql = "INSERT INTO `transactions`(`A_CARD`,`T_TYPE`,AMOUNT,CONSUMER_NO) VALUES ('"+CN+"','"+TT+"','"+cash+"','"+cno+"')";
    
    try{
           st.executeUpdate(sql);
           stat=1;
       }catch(Exception e){
           System.out.println(e);
       }
       return stat;
    }
    
    public int transfertransactions(long CN ,String TT, int cash, String tno){
    int stat = 0;
    String sql = "INSERT INTO `transactions`(`A_CARD`,`T_TYPE`,AMOUNT,BENEFICIARY_ACCOUNT_NO) VALUES ('"+CN+"','"+TT+"','"+cash+"','"+tno+"')";
    
    try{
           st.executeUpdate(sql);
           stat=1;
       }catch(Exception e){
           System.out.println(e);
       }
       return stat;
    }
    
    public ResultSet getaccount(long BN){
        String sql = "select c.C_NAME, c.C_EMAIL from customer_details c "
                + "JOIN account_details a ON a.C_ID=c.C_ID WHERE a.A_CARD = '"+BN+"' ";
        try{
            rs = st.executeQuery(sql);
        }catch (Exception e){
            System.out.println(e);
        }
        return rs;
    }
    
   public static void main(String[] args){
       SQL s =new SQL();
   }  
}
