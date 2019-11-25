
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class NewClass {
    public static void main(String[] args) {
        String sName = "Mai Nguyen Quoc Huy";
        Date day = new Date();
        int ngay = day.getDate();
        int thang = day.getMonth()+1;
        String strThang = null;
        if (thang<10) {
            strThang="0"+String.valueOf(thang);
        } else {
            strThang=String.valueOf(thang);
        }
        String ten = sName.substring(sName.lastIndexOf(" ")+1);
        String id = ten + Character.toString(sName.charAt(0));
        for (int i = 0; i < sName.length(); i++) {
            char ch = sName.charAt(i);
            if (Character.toString(ch).equalsIgnoreCase(" ")) {
                id = id.concat(Character.toString(sName.charAt(i+1)));
            }
        }
        id = id.substring(0, id.length()-1).toLowerCase()+ngay+strThang;
        System.out.println(id);
    }
}
