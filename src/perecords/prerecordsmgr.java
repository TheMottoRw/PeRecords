/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perecords;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;


/**
 *
 * @author BPRAdmin
 */
public class prerecordsmgr{
    Mysql mysql=new Mysql("localhost","root","","perecords");
    int session=0; String selectedLfsTitle="",selectedTripName="",
            selectedPlanTitle="",selectedPlanBdgSeasName="",selectedProjectBdgSeasName="",
            selectedSourceName="",selectedProfit="",selectedProjectTitle="",selectedProjectProfitDescr="";
 public void setSession(int sessid){
    this.session=sessid;
 }
  public int getSession(){
    return  this.session;
 }
  public boolean checkSession(){//check session if exist
      if(session==0){
          return false;
      }else{
          return true;
      }
  }
  
  public boolean userLogin(String uname,String pwd){
      try{ 
    ResultSet rst=mysql.getData("SELECT restricters.*,menus.* FROM restricters,menus WHERE restricters.restr_id=menus.menu_id AND "
                    + " menus.menu_name='login' AND restricters.restr_name='"+uname+"' AND restricters.restr_password='"+pwd+"'");
     
      if(rst!=null){
           rst.last();
       int row = rst.getRow();
        rst.beforeFirst();
        
         if(row==1){
           this.session=1;
          JOptionPane.showMessageDialog(null,"Login Success");
          return true;
      }else{
          JOptionPane.showMessageDialog(null,row+"\tWrong Username or Password");
          return false;
      }
  }
      }catch(Exception ex){
          ex.printStackTrace();
      }
    return true;  
  }
  public boolean addContacts(String name,String nickname,String phone,String email,String location,String sex){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("yyyy-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;
      String qry="INSERT INTO contact(cont_name,cont_nickname,cont_phone,cont_email,cont_location,cont_sex,status,date)"
              + " VALUES('"+name+"','"+nickname+"','"+phone+"','"+email+"','"+location+"','"+sex+"','active','"+datetym+"')";
     mysql.executer("insert",qry);
 return true;
  }
  public void getContacts(JTable table){
      try{
      mysql.executer("select","SELECT * FROM contact");
      DefaultTableModel tmodel=(DefaultTableModel) table.getModel();
       tmodel.setRowCount(0);
      while(mysql.result.next()){
       tmodel.addRow(new Object[]{mysql.result.getInt(1),mysql.result.getString(2),mysql.result.getString(3)
                        ,mysql.result.getString(4),mysql.result.getString(5),mysql.result.getString(6),mysql.result.getString(7)});
      }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
  public void searchContacts(JTable table,String nameornickname){
      try{
      mysql.executer("select","SELECT * FROM contact WHERE cont_name LIKE '"+nameornickname+"%' OR "
                     + "cont_nickname LIKE '"+nameornickname+"%'");
      DefaultTableModel tmodel=(DefaultTableModel) table.getModel();
       tmodel.setRowCount(0);
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       //JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
       tmodel.addRow(new Object[]{mysql.result.getInt(1),mysql.result.getString(2),mysql.result.getString(3)
                        ,mysql.result.getString(4),mysql.result.getString(5),mysql.result.getString(6),mysql.result.getString(7)});
      }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
   public boolean updateContacts(int id,String name,String nickname,String phone,String email,String location,String sex){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;
      String qry="UPDATE contact SET cont_name='"+name+"',cont_nickname='"+nickname+"',cont_phone='"+phone+"',cont_email='"+email+"',"
              + "   cont_location='"+location+"',cont_sex='"+sex+"' WHERE cont_id="+id;
     mysql.executer("update",qry);
 return true;
  }
  public boolean deleteContacts(int id){
      String qry="DELETE FROM contact WHERE cont_id="+id;
     mysql.executer("delete",qry);
 return true;
  }
    public boolean addMedia(String nwname,String uname,String phone,String email,String password){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("yyyy-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;
      String qry="INSERT INTO media(md_nwname,md_username,md_phone,md_email,md_password,status)"
              + " VALUES('"+nwname+"','"+uname+"','"+phone+"','"+email+"','"+password+"','active')";
     mysql.executer("insert",qry);
 return true;
  }
  public void getMedia(JTable table){
      try{
      mysql.executer("select","SELECT * FROM media");
      DefaultTableModel tmodel=(DefaultTableModel) table.getModel();
       tmodel.setRowCount(0);
      while(mysql.result.next()){
          
       tmodel.addRow(new Object[]{mysql.result.getInt(1),mysql.result.getString(2),mysql.result.getString(3)
                        ,mysql.result.getString(4),mysql.result.getString(5),reversor(mysql.result.getString(6)).toString(),mysql.result.getString(7)});
      }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }

  public void searchMedia(JTable table,String nwnameoruname){
      try{
      mysql.executer("select","SELECT * FROM media WHERE md_nwname LIKE '"+nwnameoruname+"%' OR "
                     + "md_username LIKE '"+nwnameoruname+"%'");
      DefaultTableModel tmodel=(DefaultTableModel) table.getModel();
       tmodel.setRowCount(0);
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
       tmodel.addRow(new Object[]{mysql.result.getInt(1),mysql.result.getString(2),mysql.result.getString(3)
                        ,mysql.result.getString(4),mysql.result.getString(5),reversor(mysql.result.getString(6)),mysql.result.getString(7)});
      }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
   public boolean updateMedia(int id,String nwname,String uname,String phone,String email,String password){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;
      String qry="UPDATE media SET md_nwname='"+nwname+"',md_username='"+uname+"',md_phone='"+phone+"',md_email='"+email+"',"
              + "   md_password='"+password+"' WHERE md_id="+id;
     mysql.executer("update",qry);
 return true;
  }
  public boolean deleteMedia(int id){
      String qry="DELETE FROM media WHERE md_id="+id;
     mysql.executer("delete",qry);
 return true;
  }
    public boolean addLifeStyle(String lfstitle,String lfsdescr,String lfstype,String lfsstart,String lfsend){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("yyyy-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;
      String qry="INSERT INTO lifestyles(lfs_title,lfs_descr,lfs_type,lfs_start,lfs_end,lfs_status,lfs_regdate)"
              + " VALUES('"+lfstitle+"','"+lfsdescr+"','"+lfstype+"','"+lfsstart+"','"+lfsend+"','active','"+dateReal+"')";
     mysql.executer("insert",qry);
 return true;
  }
  public void getLifeStyle(JList list){
      try{
      mysql.executer("select","SELECT * FROM lifestyles");
         DefaultListModel lmodel=new DefaultListModel();
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       //JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
           lmodel.addElement(mysql.result.getString(2));
           list.setModel(lmodel);
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }

  public void searchLifeStyle(JList list,String where){
      try{
          java.util.Date datetime=new java.util.Date();
          SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
      mysql.executer("select","SELECT * FROM lifestyles WHERE "+where);
      DefaultListModel lmodel=new DefaultListModel();
      lmodel.removeAllElements();
      list.setModel(lmodel);
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
           lmodel.addElement(mysql.result.getString(2));
           list.setModel(lmodel);
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
   public boolean updateLifeStyle(int lfsid,String lfstitle,String lfsdescr,String lfstype,String lfsstart,String lfsend){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;
      String qry="UPDATE lifestyles SET lfs_title='"+lfstitle+"',lfs_descr='"+lfsdescr+"',lfs_type='"+lfstype+"',"
              + "lfs_start='"+lfsstart+"',lfs_end='"+lfsend+"' WHERE lfs_id="+lfsid;
     mysql.executer("update",qry);
 return true;
  }
  public boolean deleteLifeStyle(int id){
      String qry="DELETE FROM lifestyles WHERE lfs_id="+id;
     mysql.executer("delete",qry);
 return true;
  }
  public void futureLifeStyle(JList list){
         java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
  searchLifeStyle(list, "lfs_start>'"+dateReal+"'");
  }
  public void intimeLifeStyle(JList list){
  java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
  searchLifeStyle(list, "lfs_start<='"+dateReal+"' AND lfs_end>='"+dateReal+"'");
  }
  public void expiredLifeStyle(JList list){
  java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
  searchLifeStyle(list, "lfs_end<'"+dateReal+"'");
  }
   public void viewLifeStyle(JTextArea txtFld){
       String Content="";
  java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
   try{
       int lfs_id=returnID("SELECT lfs_id FROM lifestyles WHERE lfs_title='"+selectedLfsTitle+"'");
   ResultSet rst=mysql.getData("SELECT * FROM lifestyles WHERE lfs_id='"+lfs_id+"'");
   rst.last();
   int selrowing=rst.getRow();
   rst.beforeFirst();
   while(rst.next()){
      Content+="Viewing Life Style called \""+rst.getString(2)+"\"\n\n";
      Content+="Type:\""+rst.getString(4).toString()+"\"\n\n";
      Content+="Description:\n"+rst.getString(3)+"\n\n";
      Content+="Implementation Period: \n";
      Content+="Start on "+rst.getString(5)+" Ends On "+rst.getString(6)+"\n\n";
      Content+="Status:\t"+rst.getString(7)+"\n\n";
      Content+="Registered on:\t"+rst.getString(8)+"\n";
   }
   //JOptionPane.showMessageDialog(null, Content+" Rows "+selrowing+" ID "+lfs_id+" Selected Title "+selectedLfsTitle);
   txtFld.setText(Content);
   }catch(SQLException ex){
       ex.printStackTrace();
   }
  }
   public void setFormLfsValues(int lfsid,JTextField txtFld1,JTextArea txtArea1,JComboBox combo1,JTextField txtFld2,JTextField txtFld3){
       try{
       ResultSet rst=mysql.getData("SELECT * FROM lifestyles WHERE lfs_id='"+lfsid+"'");
      while(rst.next()){
          
          txtFld1.setText(rst.getString(2));
           txtArea1.setText(rst.getString(3));
          combo1.setSelectedItem(rst.getString(4));
           txtFld2.setText(rst.getString(5));
           txtFld3.setText(rst.getString(6));
      }
          }catch(SQLException ex){
              ex.printStackTrace();
          }
   }
   //============TRIPS======================
       public boolean addTrip(String trpname,String trppurpose,String trpfrom,String trpto,String trpdate,String trpdirection){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("yyyy-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;
      String qry="INSERT INTO trip(trp_name,trp_purpose,trp_from,trp_to,trp_date,trp_direction,trp_status,trp_regdate)"
              + " VALUES('"+trpname+"','"+trppurpose+"','"+trpfrom+"','"+trpto+"','"+trpdate+"','"+trpdirection+"','active','"+dateReal+"')";
     mysql.executer("insert",qry);
 return true;
  }
  public void getTrip(JList list){
      try{
      mysql.executer("select","SELECT * FROM trip");
         DefaultListModel lmodel=new DefaultListModel();
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
           lmodel.addElement(mysql.result.getString(2));
           list.setModel(lmodel);
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
  public static int checkDaysNum(String dayStr){//to return num of day
        int dayNum=0;
     String []daysArr=new String[8];
         daysArr[1]="Mon";daysArr[2]="Tue";daysArr[3]="Wed";
         daysArr[4]="Thu";daysArr[5]="Fri"; daysArr[6]="Sat";
         daysArr[7]="Sun";
         for(int i=1;i<daysArr.length;i++){
             if(daysArr[i].equals(dayStr)){
                 dayNum=i;
                 break;
             }
//           System.out.println(daysArr[i]);
         }
         return dayNum;
         }
public void getWeeklyTrip(JList list){
try{
            java.util.Date now=new java.util.Date();
             SimpleDateFormat sda = new SimpleDateFormat("YYYY-MM-dd");
             String dnow=sda.format(now).toString();
              java.util.Date startWeek=new java.util.Date(now.getTime() - ((checkDaysNum(now.toString().substring(0,3))-1)*24*60*60*1000));//date Obj
               java.util.Date endWeek=new java.util.Date(now.getTime() + ((7-checkDaysNum(now.toString().substring(0,3)))*24*60*60*1000));//date Obj
          searchTrip(list, "trp_date>='"+sda.format(startWeek)+"' AND trp_date<='"+sda.format(endWeek)+"'");
        }catch(Exception ex){
            ex.printStackTrace();
        }
  }
public void getMonthlyTrip(JList list){
  java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("-MM-");
    String dateReal=date.format(datetime);
  searchTrip(list, "trp_date LIKE '%"+dateReal+"%'");
  }
  public void searchTrip(JList list,String where){
      try{
      mysql.executer("select","SELECT * FROM trip WHERE "+where);
      DefaultListModel lmodel=new DefaultListModel();
     lmodel.removeAllElements();
     list.setModel(lmodel);
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
           lmodel.addElement(mysql.result.getString(2));
           list.setModel(lmodel);
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
  public void doneTrip(JList list){
      searchTrip(list,"trp_status='done'");
  }
  public void failedTrip(JList list){
      searchTrip(list,"trp_status='failed'");
  }
   public boolean updateTrip(int trpid,String trpname,String trppurpose,String trpfrom,String trpto,String trpdate,String trpdirection){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;
      String qry="UPDATE trip SET trp_name='"+trpname+"',trp_purpose='"+trppurpose+"',trp_from='"+trpfrom+"',"
              + "trp_to='"+trpto+"',trp_date='"+trpdate+"',trp_direction='"+trpdirection+"' WHERE trp_id="+trpid;
     mysql.executer("update",qry);
 return true;
  }
   public void updateTripStatus(int trpid,String status){
       String qry="UPDATE trip SET trp_dstatus='"+status+"' WHERE trp_id="+trpid;
     mysql.executer("update",qry);

   }
  public boolean deleteTrip(int id){
      String qry="DELETE FROM trip WHERE trp_id="+id;
     mysql.executer("delete",qry);
 return true;
  }
  String radioValues=""; 
  public void setFormTripValues(int trpid,JTextField trpname,JTextField trppurp,JTextField trpfrom,JTextField trpto,JTextField trpdate){
       try{
       ResultSet rst=mysql.getData("SELECT * FROM trip WHERE trp_id='"+trpid+"'");
      while(rst.next()){
          trpname.setText(rst.getString(2));
           trppurp.setText(rst.getString(3));
          trpfrom.setText(rst.getString(4));
           trpto.setText(rst.getString(5));
           trpdate.setText(rst.getString(6));
           radioValues=rst.getString(7);
      }
          }catch(SQLException ex){
              ex.printStackTrace();
          }
   }
  //============TRIP DETAILS=========
  public void addTripDetail(int trpid,String dtname,String dtamount){
      String qry="INSERT INTO tripdetail(trpdttrp_id,trpdt_detail,trpdt_amount)"
              + " VALUES('"+trpid+"','"+dtname+"','"+dtamount+"')";
     mysql.executer("insert",qry);
  }
  public void getTripDetail(int id,JTable table,JTextField txtFldTot){
      try{
          int total=0;
      mysql.executer("select","SELECT * FROM tripdetail WHERE trpdttrp_id="+id);
     DefaultTableModel tmodel=(DefaultTableModel) table.getModel();
       tmodel.setRowCount(0);
      while(mysql.result.next()){
          total+=Integer.parseInt(mysql.result.getString(4));
       tmodel.addRow(new Object[]{mysql.result.getInt(1),mysql.result.getString(3),mysql.result.getString(4)});
      }
      txtFldTot.setText(String.valueOf(total));
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  } 
  
  public boolean updateTripDetail(int trpdtid,String dtname,String dtamount){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;
      String qry="UPDATE tripdetail SET trpdt_detail='"+dtname+"',trpdt_amount='"+dtamount+"'"
              + "WHERE trpdt_id="+trpdtid;
     mysql.executer("update",qry);
 return true;
  }
    public boolean deleteTripDetail(int id,String cate){
        String qry="";
        if(cate.equals("trip")){
             qry="DELETE FROM tripdetail WHERE trpdttrp_id="+id;
        }else{
            qry="DELETE FROM tripdetail WHERE trpdt_id="+id;
        }
     mysql.executer("delete",qry);
 return true;
  }
       public void viewTrip(JTextArea txtFld){
       String Content="";
  java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
 
   try{
       int trp_id=returnID("SELECT trp_id FROM trip WHERE trp_name='"+selectedTripName+"'");
   ResultSet rst=mysql.getData("SELECT * FROM trip WHERE trp_id='"+trp_id+"'");
   rst.last();
   int selrowing=rst.getRow();
   rst.beforeFirst();
   while(rst.next()){
      Content+="Viewing Trip called \""+rst.getString(2)+"\"\n\n";
      Content+="Pupose:\""+rst.getString(3).toString()+"\"\n\n";
      Content+="From:  "+rst.getString(4)+"\t";
      Content+="To:  "+rst.getString(5)+"\n\n";
      Content+="Direction:\t"+rst.getString(7)+"\n\n";
      Content+="On "+rst.getString(6)+"\n\n";
      Content+="Status:\t"+rst.getString(8)+"\n\n";
      Content+="Registered on:\t"+rst.getString(9)+"\n";
   }
   //JOptionPane.showMessageDialog(null, Content+" Rows "+selrowing+" ID "+lfs_id+" Selected Title "+selectedLfsTitle);
   txtFld.setText(Content);
   }catch(SQLException ex){
       ex.printStackTrace();
   }
  }
        public boolean addPlan(String plntitle,String plndescr,String plnimpl,String plnstart,String plnend){
      String qry="INSERT INTO plan(pl_title,pl_descr,pl_implementation,pl_start,pl_end,pl_status)"
              + " VALUES('"+plntitle+"','"+plndescr+"','"+plnimpl+"','"+plnstart+"','"+plnend+"','waiting')";
     mysql.executer("insert",qry);
 return true;
  }
  public void getPlan(JList list){
      try{
      mysql.executer("select","SELECT * FROM plan");
         DefaultListModel lmodel=new DefaultListModel();
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       //JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
           lmodel.addElement(mysql.result.getString(2));
           list.setModel(lmodel);
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }

  public void searchPlan(JList list,String where){
      try{
      mysql.executer("select","SELECT * FROM plan WHERE "+where);
      DefaultListModel lmodel=new DefaultListModel();
      lmodel.removeAllElements();
      list.setModel(lmodel);
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
           lmodel.addElement(mysql.result.getString(2));
           list.setModel(lmodel);
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
   public boolean updatePlan(int plnid,String plntitle,String plndescr,String plnimpl,String plnstart,String plnend){
      String qry="UPDATE plan SET pl_title='"+plntitle+"',pl_descr='"+plndescr+"',pl_implementation='"+plnimpl+"',"
              + "pl_start='"+plnstart+"',pl_end='"+plnend+"' WHERE pl_id="+plnid;
     mysql.executer("update",qry);
 return true;
  }
  public boolean deletePlan(int id){
      String qry="DELETE FROM plan WHERE pl_id="+id;
     mysql.executer("delete",qry);
 return true;
  }
  public void waitingPlan(JList list){
  searchPlan(list, "pl_status='waiting'");
  }
  public void achievedPlan(JList list){
  searchPlan(list, "pl_status='achieved'");
  }
  public void failedPlan(JList list){
  searchPlan(list, "pl_status='failed'");
  }
  public boolean updatePlanStatus(int plid,String status){
      String qry="UPDATE plan SET pl_status='"+status+"' WHERE pl_id="+plid;
     mysql.executer("update",qry);
 return true;
  }
  public void setFormPlValues(int plnid,JTextField title,JTextArea descr,JTextArea impl,JTextField dateStart,JTextField dateEnd){
       try{
       ResultSet rst=mysql.getData("SELECT * FROM plan WHERE pl_id='"+plnid+"'");
      while(rst.next()){
          title.setText(rst.getString(2));
           descr.setText(rst.getString(3));
          impl.setText(rst.getString(4));
           dateStart.setText(rst.getString(5));
           dateEnd.setText(rst.getString(6));
      }
          }catch(SQLException ex){
              ex.printStackTrace();
          }
   }
  // Budget Source&ID
  public void addBudget(int plnid,String season,int expected,String dateStart,String dateEnd){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("yyyy-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;  
      String qry="INSERT INTO budget(bdgp_id,bdgp_cate,bdgseasname,bdg_expected,bdg_start,bdg_end,bdg_status,bdg_regdate)"
              + " VALUES('"+plnid+"','plan','"+season+"','"+expected+"','"+dateStart+"','"+dateEnd+"','active','"+dateReal+"')";
     mysql.executer("insert",qry);
  }
   public void getPlanBudget(JList list){
      try{
      mysql.executer("select","SELECT budget.* FROM budget,plan WHERE budget.bdgp_id=plan.pl_id AND budget.bdgp_cate='plan' AND plan.pl_title='"+selectedPlanTitle+"'");
         DefaultListModel lmodel=new DefaultListModel();
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       //JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
           lmodel.addElement(mysql.result.getString(4).toString());
           list.setModel(lmodel);
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
     public void getPlanBudgetById(int bdgid,JTextField bdgname,JTextField bdgexpected,JTextField start,JTextField end){
      try{
     ResultSet rst=mysql.getData("SELECT * FROM budget WHERE bdg_id='"+bdgid+"'");
             rst.last();
             int selrow=rst.getRow();
             rst.beforeFirst();
              JOptionPane.showMessageDialog(null, selrow);
     while(rst.next()){
                  bdgname.setText(rst.getString(4));
          bdgexpected.setText(rst.getString(5));
          start.setText(rst.getString(6));
          end.setText(rst.getString(7));
          
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
     }
    public boolean updatePlanBudget(int bdgid,String season,int expected,String dateStart,String dateEnd){
      String qry="UPDATE budget SET bdgseasname='"+season+"',bdg_expected='"+expected+"',bdg_start='"+dateStart+"',"
              + "bdg_end='"+dateEnd+"' WHERE bdg_id="+bdgid;
     mysql.executer("update",qry);
 return true;
  }
      public boolean deletePlanBudget(int id,String cate){
          String qry;
          if(cate.equals("plan")){
            qry="DELETE FROM budget WHERE bdgp_cate='plan' AND bdgp_id="+id;
          }else{ 
        qry="DELETE FROM budget WHERE bdgp_cate='plan' AND bdg_id="+id;
          }
      mysql.executer("delete",qry);
 return true;
  }
    public void addPlanSource(int srcid,String srcname,int expected){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;  
      String qry="INSERT INTO source(srcp_id,srcp_cate,srcname,src_expected,src_status,src_regdate)"
              + " VALUES('"+srcid+"','plan','"+srcname+"','"+expected+"','active','"+dateReal+"')";
     mysql.executer("insert",qry);
  }
   public void getPlanSource(JList list){
      try{
      mysql.executer("select","SELECT source.* FROM source,plan WHERE source.srcp_id=plan.pl_id AND source.srcp_cate='plan'  AND plan.pl_title='"+selectedPlanTitle+"'");
         DefaultListModel lmodel=new DefaultListModel();
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       //JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
           lmodel.addElement(mysql.result.getString(4));
           list.setModel(lmodel);
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
    public void getPlanSourceById(int id,JTextField srcname,JTextField srcexpected){
      try{
     ResultSet rst=mysql.getData("SELECT * FROM source WHERE src_id="+id+" AND source.srcp_cate='plan'");
      while(rst.next()){
          srcname.setText(rst.getString(4));
          srcexpected.setText(rst.getString(5));
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
    public boolean updatePlanSource(int srcid,String srcname,int expected){
      String qry="UPDATE source SET srcname='"+srcname+"',src_expected='"+expected+"' WHERE src_id="+srcid;
     mysql.executer("update",qry);
 return true;
  }
      public boolean deletePlanSource(int id,String cate){
           String qry;
          if(cate.equals("plan")){
             qry="DELETE FROM source WHERE srcp_cate='plan' AND srcp_id="+id;
          }else{ 
       qry="DELETE FROM source WHERE srcp_cate='plan' AND src_id="+id;
          }
      mysql.executer("delete",qry);
 return true;
  }
          public void addPlanProfit(int profpid,int profexp,String profdescr){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;  
      String qry="INSERT INTO profit(profp_id,profp_cate,profdescr,prof_expected,prof_status,prof_regdate)"
              + " VALUES('"+profpid+"','plan','"+profdescr+"','"+profexp+"','active','"+dateReal+"')";
     mysql.executer("insert",qry);
  }
   public void getPlanProfit(JList list){
      try{
      mysql.executer("select","SELECT profit.* FROM profit,plan WHERE profit.profp_id=plan.pl_id AND profit.profp_cate='plan' AND plan.pl_title='"+selectedPlanTitle+"'");
         DefaultListModel lmodel=new DefaultListModel();
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       //JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
           lmodel.addElement(mysql.result.getString(4));
           list.setModel(lmodel);
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
    public void getPlanProfitById(int id,JTextField profexp,JTextArea profdescr){
      try{
     ResultSet rst=mysql.getData("SELECT * FROM profit WHERE prof_id="+id+" AND profit.profp_cate='plan' ");
      while(rst.next()){
          profdescr.setText(rst.getString(4));
          profexp.setText(rst.getString(5));
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
    public boolean updatePlanProfit(int profid,String profdescr,int expected){
      String qry="UPDATE profit SET profdescr='"+profdescr+"',prof_expected='"+expected+"' WHERE prof_id="+profid;
     mysql.executer("update",qry);
 return true;
  }
      public boolean deletePlanProfit(int id,String cate){
            String qry;
          if(cate.equals("plan")){
             qry="DELETE FROM profit WHERE profp_cate='plan' AND profp_id="+id;
          }else{ 
       qry="DELETE FROM profit WHERE profp_cate='plan' AND prof_id="+id;
          }
       mysql.executer("delete",qry);
 return true;
  }
       //PROJECT
       public boolean addProject(String prjtitle,String prjtype,String prjplat,String prjdescr,String prjstart,String prjend){
      java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("yyyy-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme; 
           String qry="INSERT INTO project(pr_title,pr_type,pr_platform,pr_descr,pr_start,pr_end,pr_status,pr_regdate)"
              + " VALUES('"+prjtitle+"','"+prjtype+"','"+prjplat+"','"+prjdescr+"','"+prjstart+"','"+prjend+"','waiting','"+dateReal+"')";
     mysql.executer("insert",qry);
 return true;
  }
  public void getProject(JList list){
      try{
      mysql.executer("select","SELECT * FROM project");
         DefaultListModel lmodel=new DefaultListModel();
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       //JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
           lmodel.addElement(mysql.result.getString(2));
           list.setModel(lmodel);
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }

  public void searchProject(JList list,String where){
      try{
      mysql.executer("select","SELECT * FROM project WHERE "+where);
      DefaultListModel lmodel=new DefaultListModel();
      lmodel.removeAllElements();
      list.setModel(lmodel);
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
           lmodel.addElement(mysql.result.getString(2));
           list.setModel(lmodel);
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
   public boolean updateProject(int prjid,String prjtitle,String prjtype,String prjplat,String prjdescr,String prjstart,String prjend){
      String qry="UPDATE project SET pr_title='"+prjtitle+"',pr_type='"+prjtype+"',pr_platform='"+prjplat+"',pr_descr='"+prjdescr+"',pr_start='"+prjstart+"',pr_end='"+prjend+"' WHERE pr_id="+prjid;
     mysql.executer("update",qry);
 return true;
  }
  public boolean deleteProject(int id){
      String qry="DELETE FROM project WHERE pr_id="+id;
     mysql.executer("delete",qry);
 return true;
  }
  public void waitingProject(JList list,String platform){
  searchProject(list, "pr_platform='"+platform+"' AND pr_status='waiting'");
  }
  public void achievedProject(JList list,String platform){
  searchProject(list, "pr_platform='"+platform+"' AND pr_status='achieved'");
  }
  public void failedProject(JList list,String platform){
  searchProject(list, "pr_platform='"+platform+"' AND pr_status='failed'");
  }
  public boolean updateProjectStatus(int prid,String status){
      String qry="UPDATE project SET pr_status='"+status+"' WHERE pr_id="+prid;
     mysql.executer("update",qry);
 return true;
  }
  public void setFormPrValues(int prjid,JTextField title,JComboBox type,JComboBox platform,JTextArea descr,JTextField dateStart,JTextField dateEnd){
       try{
       ResultSet rst=mysql.getData("SELECT * FROM project WHERE pr_id='"+prjid+"'");
      while(rst.next()){
          title.setText(rst.getString(2));
          type.setSelectedItem(rst.getString(3));
          platform.setSelectedItem(rst.getString(4));
           descr.setText(rst.getString(5));
           dateStart.setText(rst.getString(6));
           dateEnd.setText(rst.getString(7));
      }
          }catch(SQLException ex){
              ex.printStackTrace();
          }
   }
  // Budget Source&ID
  public void addProjectBudget(int prjid,String season,int expected,String dateStart,String dateEnd){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("yyyy-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;  
      String qry="INSERT INTO budget(bdgp_id,bdgp_cate,bdgseasname,bdg_expected,bdg_start,bdg_end,bdg_status,bdg_regdate)"
              + " VALUES('"+prjid+"','project','"+season+"','"+expected+"','"+dateStart+"','"+dateEnd+"','active','"+dateReal+"')";
     mysql.executer("insert",qry);
  }
   public void getProjectBudget(JList list){
      try{
      mysql.executer("select","SELECT budget.* FROM budget,project WHERE budget.bdgp_id=project.pr_id AND budget.bdgp_cate='project' AND project.pr_title='"+selectedProjectTitle+"'");
         DefaultListModel lmodel=new DefaultListModel();
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       //JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
           lmodel.addElement(mysql.result.getString(4).toString());
           list.setModel(lmodel);
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
     public void getProjectBudgetById(int bdgid,JTextField bdgname,JTextField bdgexpected,JTextField start,JTextField end){
      try{
     ResultSet rst=mysql.getData("SELECT * FROM budget WHERE budget.bdgp_cate='project' AND bdg_id='"+bdgid+"'");
             rst.last();
             int selrow=rst.getRow();
             rst.beforeFirst();
              JOptionPane.showMessageDialog(null, selrow);
     while(rst.next()){
                  bdgname.setText(rst.getString(4));
          bdgexpected.setText(rst.getString(5));
          start.setText(rst.getString(6));
          end.setText(rst.getString(7));
          
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
     }
    public boolean updateProjectBudget(int bdgid,String season,int expected,String dateStart,String dateEnd){
      String qry="UPDATE budget SET bdgseasname='"+season+"',bdg_expected='"+expected+"',bdg_start='"+dateStart+"',"
              + "bdg_end='"+dateEnd+"' WHERE bgdp_cate='project' AND bdg_id="+bdgid;
     mysql.executer("update",qry);
 return true;
  }
      public boolean deleteProjectBudget(int id,String cate){
          String qry;
          if(cate.equals("project")){
            qry="DELETE FROM budget WHERE bdgp_cate='project' AND bdgp_id="+id;
          }else{ 
        qry="DELETE FROM budget WHERE bdgp_cate='project' AND bdg_id="+id;
          }
      mysql.executer("delete",qry);
 return true;
  }
    public void addProjectSource(int srcid,String srcname,int expected){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;  
      String qry="INSERT INTO source(srcp_id,srcp_cate,srcname,src_expected,src_status,src_regdate)"
              + " VALUES('"+srcid+"','project','"+srcname+"','"+expected+"','active','"+dateReal+"')";
     mysql.executer("insert",qry);
  }
   public void getProjectSource(JList list){
      try{
      mysql.executer("select","SELECT source.* FROM source,project WHERE source.srcp_id=project.pr_id AND source.srcp_cate='project'  AND project.pr_title='"+selectedProjectTitle+"'");
         DefaultListModel lmodel=new DefaultListModel();
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       //JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
           lmodel.addElement(mysql.result.getString(4));
           list.setModel(lmodel);
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
    public void getProjectSourceById(int id,JTextField srcname,JTextField srcexpected){
      try{
     ResultSet rst=mysql.getData("SELECT * FROM source WHERE src_id="+id+" AND source.srcp_cate='project'");
      while(rst.next()){
          srcname.setText(rst.getString(4));
          srcexpected.setText(rst.getString(5));
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
    public boolean updateProjectSource(int srcid,String srcname,int expected){
      String qry="UPDATE source SET srcname='"+srcname+"',src_expected='"+expected+"' WHERE srcp_cate='project' AND src_id="+srcid;
     mysql.executer("update",qry);
 return true;
  }
      public boolean deleteProjectSource(int id,String cate){
           String qry;
          if(cate.equals("project")){
             qry="DELETE FROM source WHERE srcp_cate='project' AND srcp_id="+id;
          }else{ 
       qry="DELETE FROM source WHERE srcp_cate='project' AND src_id="+id;
          }
      mysql.executer("delete",qry);
 return true;
  }
          public void addProjectProfit(int profpid,int profexp,String profdescr){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;  
      String qry="INSERT INTO profit(profp_id,profp_cate,profdescr,prof_expected,prof_status,prof_regdate)"
              + " VALUES('"+profpid+"','project','"+profdescr+"','"+profexp+"','active','"+dateReal+"')";
     mysql.executer("insert",qry);
  }
   public void getProjectProfit(JList list){
      try{
      mysql.executer("select","SELECT profit.* FROM profit,project WHERE profit.profp_id=project.pr_id AND profit.profp_cate='project' AND project.pr_title='"+selectedProjectTitle+"'");
         DefaultListModel lmodel=new DefaultListModel();
       mysql.result.last();
       int rowsel=mysql.result.getRow();
       mysql.result.beforeFirst();
       //JOptionPane.showMessageDialog(null,"Selected row "+rowsel);
      while(mysql.result.next()){
           lmodel.addElement(mysql.result.getString(4));
           list.setModel(lmodel);
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
    public void getProjectProfitById(int id,JTextField profexp,JTextArea profdescr){
      try{
     ResultSet rst=mysql.getData("SELECT * FROM profit WHERE prof_id="+id+" AND profit.profp_cate='project' ");
      while(rst.next()){
          profdescr.setText(rst.getString(4));
          profexp.setText(rst.getString(5));
         }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  }
    public boolean updateProjectProfit(int profid,String profdescr,int expected){
      String qry="UPDATE profit SET profdescr='"+profdescr+"',prof_expected='"+expected+"' WHERE prof_id="+profid;
     mysql.executer("update",qry);
 return true;
  }
      public boolean deleteProjectProfit(int id,String cate){
            String qry;
          if(cate.equals("project")){
             qry="DELETE FROM profit WHERE profp_cate='project' AND profp_id="+id;
          }else{ 
       qry="DELETE FROM profit WHERE profp_cate='project' AND prof_id="+id;
          }
       mysql.executer("delete",qry);
 return true;
  }
  //============PROJECT DETAILS=========
  public void addProjectDetail(int projid,String dtname,String dtstatus){
     try{
 java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("yyyy-MM-dd");
    String dateReal=date.format(datetime).toString();

 String qry="INSERT INTO project_details(proj_id,det_name,det_status,reg_date)"
              + " VALUES('"+projid+"','"+dtname+"','"+dtstatus+"','"+dateReal+"')";
     
  ResultSet result=mysql.getData("SELECT * FROM project_details WHERE det_name='"+dtname+"'");
  result.last();
  if(result.getRow()==0){
  result.beforeFirst();
     mysql.executer("insert",qry);
  }else{
      JOptionPane.showMessageDialog(null,"Sorry Details you Added Allread Exist");
  }    
    }catch(SQLException ex){
         ex.printStackTrace();
     }
  }
  public void getProjectDetail(int id,JTable table){
      try{
      mysql.executer("select","SELECT * FROM project_details WHERE proj_id="+id);
     DefaultTableModel tmodel=(DefaultTableModel) table.getModel();
       tmodel.setRowCount(0);
      while(mysql.result.next()){
       tmodel.addRow(new Object[]{mysql.result.getInt(1),mysql.result.getString(3),mysql.result.getString(4)});
     
      }
      }catch(SQLException ex){
          ex.printStackTrace();
      }
  } 
  
  public boolean updateProjectDetail(int projdtid,String dtname,String dtstatus){
       java.util.Date datetime=new java.util.Date();
       SimpleDateFormat date = 
      new SimpleDateFormat ("YYYY-MM-dd");
    String dateReal=date.format(datetime).toString();
 SimpleDateFormat tym = 
      new SimpleDateFormat ("hh:mm:ss");
    String tyme=tym.format(datetime).toString();
    String datetym=dateReal+" "+tyme;
      String qry="UPDATE project_details SET det_name='"+dtname+"',det_status='"+dtstatus+"',reg_date='"+dateReal+"'"
              + "WHERE projdt_id="+projdtid;
     mysql.executer("update",qry);
 return true;
  }
    public boolean deleteProjectDetail(int id,String cate){
        String qry="";
        if(cate.equals("project")){
             qry="DELETE FROM project_details WHERE projdt_id="+id;
        }else{
            qry="DELETE FROM project_details WHERE projdt_id="+id;
        }
     mysql.executer("delete",qry);
 return true;
  }      
    public void clearTwo(JTextField txt1,JTextField txt2){
          txt1.setText("");txt2.setText("");
      }
//===========OPTIONAL=============================
  public int returnID(String query){
      int id=0;
      try{
      ResultSet reslt=mysql.getData(query);
    while(reslt.next()){
     id=reslt.getInt(1);
  }
      }catch(SQLException ex){
          ex.printStackTrace();
   }
     return id; 
  }
      public String reversor(String str){
          String newStr="";
      for(int i=(str.length()-1);i>=0;i--){
       if(i==str.length()-1){
           newStr=String.valueOf(str.charAt(i));
       }else{
           newStr+=String.valueOf(str.charAt(i));
       }
      }
      return newStr;
  }
      public String toStrOrigin(String str){
          String strOrigin="";
      for(int i=(str.length()-1);i>=0;i--){
       if(i==str.length()-1){
           strOrigin=String.valueOf(str.charAt(i));
       }else{
           strOrigin+=String.valueOf(str.charAt(i));
       }
      }
      return strOrigin;
  }
 }
