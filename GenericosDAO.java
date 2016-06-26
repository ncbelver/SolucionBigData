 /*  GenericosDAO.java: contine metodos de consulta a datos genericos 

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
package DAO;

import DAO.BaseDatos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

 
public class GenericosDAO {
     private Connection conectarBD; // crear la vble de conexion a la BBDD	 
    public GenericosDAO(){		
            conectarBD = Conexion.getConnection();  // abrir la conexión a la BBDD . aplicacionesweb(aplicaciones, usuarios, deptos, areas, ciudades...)
    }
    /* Recupera responsable por id responsable*/
    public GenericosBEAN getResponsable(int id){   		
        GenericosBEAN responsable = new GenericosBEAN();
        try {
               String sql = " SELECT usuario FROM responsable where idUsuario= ?;";  // sql de consulta
                PreparedStatement ps = conectarBD.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            responsable.setNombre(rs.getString("usuario"));
                        }
                                      
            } catch (SQLException e) {
                System.out.println(" ERROR ");
                    e.printStackTrace();
            }
            return responsable;
    }
    public List getTodosResponsables(){  // recupera todos los responsables		
        List lista_responsables = new ArrayList();  // lista de resposables
            try {
                String sql = " SELECT * FROM responsable order by idUsuario";  // sql de consulta

                PreparedStatement ps = conectarBD.prepareStatement(sql);				
                ResultSet rs = ps.executeQuery();

                while(rs.next()){   
                        GenericosBEAN responsable = new GenericosBEAN();
                        responsable.setID(rs.getInt("id")); 
                        responsable.setNombre(rs.getString("nombre"));                                                                  
					
                        lista_responsables.add(responsable);  
                }
            } catch (SQLException e) {
                 System.out.println(" ERROR ");
                 e.printStackTrace();
            }
            return lista_responsables;
	}
        /* Recupera Area por id area*/
    public GenericosBEAN getArea(int id){   		
        GenericosBEAN area = new GenericosBEAN();
        try {
               String sql = " SELECT nombre FROM area where idArea= ?;";  // sql de consulta
                PreparedStatement ps = conectarBD.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            area.setNombre(rs.getString("nombre"));
                        }
                                       
            } catch (SQLException e) {
                System.out.println(" ERROR ");
                    e.printStackTrace();
            }
            return area;
    }
    public List getTodasAreas(){  // recupera todos los areas		
        List lista = new ArrayList();  // lista de areas
            try {
                String sql = " SELECT * FROM area order by idArea";  // sql de consulta

                PreparedStatement ps = conectarBD.prepareStatement(sql);				
                ResultSet rs = ps.executeQuery();

                while(rs.next()){   
                        GenericosBEAN area = new GenericosBEAN();
                        area.setID(rs.getInt("id")); 
                        area.setNombre(rs.getString("nombre"));                                                                  
					
                        lista.add(area);  
                }
            } catch (SQLException e) {
                 System.out.println(" ERROR ");
                 e.printStackTrace();
            }
            return lista;
	}
/*
   Recupera el numero de versiones de cada aplicacion 
    */
     public List versionesxAplicaciones(){   		
        List lista = new ArrayList();   
            try {
                String sql = " SELECT a.nombre nom, count(v.idVersion) nve \n" +
                            "FROM aplicaciones as a , versiones as v \n" +
                            "where a.id = v.idAplic   and   fechaBaja is null  and fechaAlta <= (select sysdate()) \n" +
                            "group by a.nombre order by a.nombre";  // sql de consulta

                PreparedStatement ps = conectarBD.prepareStatement(sql);				
                ResultSet rs = ps.executeQuery();

                while(rs.next()){   
                        GenericosBEAN vxa = new GenericosBEAN();                        
                        vxa.setNombre(rs.getString("nom"));    
                        vxa.setID(rs.getInt("nve")); 
					
                        lista.add(vxa);  
                }
            } catch (SQLException e) {
                 System.out.println(" ERROR ");
                 e.printStackTrace();
            }
            return lista;
	}
    /*
   Recupera el numero de defectos ( desarrollo + funcionales + arquitectura ) de cada version de un aplicacion 
    */
     public List defectosxversion(int id){   	
        List lista = new ArrayList();  // lista  
            try {
                String sql = " SELECT descripcion nom, (defectosArquitectura+defectosDesarrollo+defectosFuncionales) def\n" +
                                "FROM versiones  where idAplic = ?;";  // sql de consulta
                
                PreparedStatement ps = conectarBD.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();                

                while(rs.next()){   
                        GenericosBEAN vxa = new GenericosBEAN();                        
                        vxa.setNombre(rs.getString("nom"));    
                        vxa.setID(rs.getInt("def")); 
					
                        lista.add(vxa);  
                }
            } catch (SQLException e) {
                 System.out.println(" ERROR ");
                 e.printStackTrace();
            }
            return lista;
	}
    
}
