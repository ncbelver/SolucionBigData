 /*  Conexion.java: conexion con mysql, usuario y clave.

    Copyright (C) 2016  Natividad Crespo Belver. 
    Proyecto desarrollado como parte del TFG en la Universidad Internacional de la Rioja

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package DAO.BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
Clase Conexión establece la conexion a la BD donde 
se almacenan las aplicaciones sus versiones
 */
public class Conexion {
    private static Connection con = null;
    public static Connection getConnection(){
        if(con != null){
                return con;
        } else {
                try {       
                   Class.forName("com.mysql.jdbc.Driver");  
                    String USERNAME = "root"; //usuario
                    String PASSWORD = "root"; // clave
                    String HOST = "localhost"; //ip
                    String PORT = "3306"; //puerto
                    String DATABASE = "aplicacionesweb"; //esquema
                    String CLASSNAME = "com.mysql.jdbc.Driver"; 
                    String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;

                    con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                    System.out.println("Conexion establecida !");

                } catch (ClassNotFoundException cne) {
                    System.out.println("***ERROR Driver***");
                    cne.printStackTrace();
                } catch (SQLException e) {
                    System.out.println("***ERROR SQLException***"+e);				
                }
            return con;
        }
    }
   
}
