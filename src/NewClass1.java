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
    String str = "123456789";
        if (str.matches("0\\d{9}")) {
            System.out.println("ok");
        }
    }
}
