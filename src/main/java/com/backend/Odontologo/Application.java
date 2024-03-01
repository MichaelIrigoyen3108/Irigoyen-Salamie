package com.backend.Odontologo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Application {

        public static void main(String[] args) {
                Connection connection = null;
                try {
                        Class.forName("org.h2.Driver");
                        connection = DriverManager.getConnection("jdbc:h2:~/proyecto;INIT=RUNSCRIPT FROM 'create.sql' ","michael","");
                }catch (Exception e){
                        e.printStackTrace();
                }finally {
                        try {
                                connection.close();
                        }catch (Exception ex) {
                                ex.printStackTrace();
                        }
                }
        }
}
