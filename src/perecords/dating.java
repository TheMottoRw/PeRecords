/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dating {

    public static void main( String[] args )
    {
       // compareDates("2017-05-01", "2016-12-12");//output will be Date1 is after Date2
    week();
    }
 public static int checkDaysNum(String dayStr){
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
    public static void compareDates(String d1,String d2)
    {
        try{
        
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            Date date1 = sdf.parse(d1);
            Date date2 = sdf.parse(d2);
//check where week start and ends
            Date now=new Date();
             SimpleDateFormat sda = new SimpleDateFormat("YYYY-MM-dd");
            String dnow=sda.format(now).toString();
            Date nowTemp=sda.parse(dnow);
              Date startWeek=new Date(nowTemp.getTime() - (checkDaysNum(now.toString().substring(0,3))*24*60*60*1000));//date Obj
               Date endWeek=new Date(nowTemp.getTime() + ((7-checkDaysNum(now.toString().substring(0,3)))*24*60*60*1000));//date Obj
            System.out.println("Date1 "+sdf.format(date1));
            System.out.println("Date2 "+sdf.format(date2));
                
            if(date1.after(date2)){
                              System.out.println(sdf.format(date1)+" is after "+sdf.format(date2)+"="+sdf.format(startWeek).toString()+"=> Now "+sdf.format(now).toString()+"=>Week "+sdf.format(startWeek)+" to "+sdf.format(endWeek));
                
            }
            // before() will return true if and only if date1 is before date2
            if(date1.before(date2)){
                System.out.println(date1+" is before "+date2);
            }
            if(date1.equals(date2)){
                System.out.println(date1+" is equal "+date2);
            }
        }catch(ParseException ex){
            ex.printStackTrace();
        }
    }
        public static void week(){
            try{
            Date now=new Date();
             SimpleDateFormat sda = new SimpleDateFormat("YYYY-MM-dd");
             String dnow=sda.format(now).toString();
              Date startWeek=new Date(now.getTime() - ((checkDaysNum(now.toString().substring(0,3))-1)*24*60*60*1000));//date Obj
               Date endWeek=new Date(now.getTime() + ((7-checkDaysNum(now.toString().substring(0,3)))*24*60*60*1000));//date Obj
            System.out.println("Now "+now);
            System.out.println("Now Formated "+dnow);
                
            System.out.println("Now "+sda.format(now).toString()+"=>Week "+sda.format(startWeek)+" to "+sda.format(endWeek));
          
        }catch(Exception ex){
            ex.printStackTrace();
        }
        }
}