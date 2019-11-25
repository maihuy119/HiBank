/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maihu
 */
public class NewClass1 {
    public static void main(String[] args)//throws Exception
    {
        //BufferedReader Nhap=new BufferedReader(new InputStreamReader(System.in));
    
        //System.out.print("Nhap Ho Ten cua ban : ");
        //String HoTen= Nhap.readLine();
        String s=new String("mai nguyen quoc huy");
        String s1=new String();
        s=s.trim();// Tac dung la de loai bo hai dau cach dau va cuoi Ten
        int k;
        for(k=s.length()-1;k>=0;k--)
        {
            s1=s.substring(k,k+1);
            if(s1.equals(" ")) break;
        }
        System.out.println("Ten: "+ s.substring(k+1));
        int i;
        for(i=0;i<=s.length();i++)
        {
           s1=s.substring(i,i+1);
            if(s1.equals(" ")) break;
        }
        System.out.println("Ho: "+ s.substring(0,i));
        int j = 0;
    
        if(j>i&&j<k)
        {
            s1=s.substring(j,j+1);
        }
        System.out.println("Ten Dem: "+s.substring(i+1,k));
    
    }
}
