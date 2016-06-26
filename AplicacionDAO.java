 /*  AplicacionDAO.java: contine los metodos de acceso a datos de las aplicaciones, consultas, alta, modificación y eliminación

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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.BaseDatos.Conexion;
import DAO.AplicacionBEAN;

public class AplicacionDAO {  
	private final Connection conectarBD; // crear la vble de conexion a la BBDD	 
	public AplicacionDAO(){		
		conectarBD = Conexion.getConnection();  // abrir la conexión a la BBDD . aplicacionesweb(aplicaciones, usuarios, deptos, areas, ciudades...)
	}
/********************************************************************************* 
        nuevaAplicacion . Parametros: aplicaonBEAN . origen: AltaAplicacion.jsp
******************************************************************************/
    public void nuevaAplicacion(AplicacionBEAN aplicaciones){  //dar de alta una nueva aplicacion. utiliza AplicacionBEAN para representar cada aplicacion
      
        try {            
            String sql = "INSERT INTO aplicaciones (CODIGO, NOMBRE, DESCRIPCION,IPHOST,IPLOG,IDTIPO,IDIDIOMA,IDUSER,IDDEPTO,IDAREA,"
                    + "IDCIUDAD, IDPAIS,FECHAALTA) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE())"; //alta de una aplicacion,con los valores minimos

            PreparedStatement ps = conectarBD.prepareStatement(sql); // sentencia para ejec el insert

            ps.setString(1, aplicaciones.getCodigo());   // reemplaza el primer ? del insert                        
            ps.setString(2,aplicaciones.getNombre());
            ps.setString(3,aplicaciones.getDescripcion());
            ps.setString(4,aplicaciones.getIPhost());
            ps.setString(5,aplicaciones.getIPlog());
            ps.setInt(6,aplicaciones.getIdTipo());
            ps.setInt(7,aplicaciones.getIdIdioma());
            ps.setInt(8,aplicaciones.getIdUser());
            ps.setInt(9,aplicaciones.getIdDepto());
            ps.setInt(10,aplicaciones.getIdArea());                                              
            ps.setString(11, aplicaciones.getIdCiudad()); 
            ps.setString(12,aplicaciones.getIdPais());                              
            ps.executeUpdate();// ejecuta el insert

        } catch (SQLException e) {
            System.out.println(" ERROR ");
                e.printStackTrace();
        }
    }
/* *******************************************************************
    eliminarAplicacion . param: id aplicacion. Eliminación física 
   *****************************************************************/
    public void eliminarAplicacion(int id){//eliminar una aplicacion
        try {
            String sql = "DELETE FROM aplicaciones WHERE ID=?"; // sql para eliminar una aplicacion. ? es el id de la aplicacion
            PreparedStatement ps = conectarBD.prepareStatement(sql); //sentencia para ejecucion de sql
            ps.setInt(1, id); // valor de ?, id de la aplicacion

            ps.executeUpdate(); //ejecutar la sentencia

        } catch (SQLException e) {
             System.out.println(" ERROR ");
             e.printStackTrace();
        }
    }
/* ***************************************************
baja lógica de una aplicacion
**********************************************************/
    public void bajaAplicacion(int id){     
        try {
            String sql = "UPDATE APLICACIONES SET FECHABAJA = SYSDATE() WHERE ID = ?"; 

            PreparedStatement ps = conectarBD.prepareStatement(sql);
            ps.setInt(1, id); 

            ps.executeUpdate();			 
        } catch (SQLException e) {
             System.out.println(" ERROR ");
              e.printStackTrace();
        }
    }   
/* ***************************************************
modificar datos de aplicacion desde pantalla
**********************************************************/
    public void editarAplicacion(AplicacionBEAN aplicaciones){        
        try {
            String sql = "UPDATE APLICACIONES SET CODIGO=? ,NOMBRE=? , DESCRIPCION = ? , IPHOST = ?, IPLOG = ? WHERE ID = ?"; 

            PreparedStatement ps = conectarBD.prepareStatement(sql);
            ps.setString(1, aplicaciones.getCodigo());   
            ps.setString(2, aplicaciones.getNombre()); 
            ps.setString(3, aplicaciones.getDescripcion()); 
            ps.setString(4, aplicaciones.getIPhost());
            ps.setString(5, aplicaciones.getIPlog());
            ps.setInt(6, aplicaciones.getID());

            ps.executeUpdate();		 
        } catch (SQLException e) {
             System.out.println(" ERROR ");
                e.printStackTrace();
        }
    }	
/* ***************************************************
recuperar datos de una aplicacion
**********************************************************/
    public AplicacionBEAN datosAplicacion(int id){  // buscar una aplicacion por el id de la aplicacion			
        AplicacionBEAN aplicaciones = new AplicacionBEAN();
        try {
            String sql = "SELECT * FROM aplicaciones WHERE id=?";  // sql recupera datos de la aplicacion (id)
            PreparedStatement ps = conectarBD.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                aplicaciones.setID(rs.getInt("id"));
                aplicaciones.setCodigo(rs.getString("codigo"));
                aplicaciones.setNombre(rs.getString("nombre"));
                aplicaciones.setDescripcion(rs.getString("descripcion"));
                aplicaciones.setIPhost(rs.getString("IPhost"));
                aplicaciones.setIPlog(rs.getString("IPlog"));
            }                                      
        } catch (SQLException e) {
            System.out.println(" ERROR ");
            e.printStackTrace();
        }
        return aplicaciones;
    }
/* ***************************************************
recuperar la lista de aplicaciones activas
**********************************************************/
    public List datosAplicacionesActivas(){  // recupera todas las aplicaciones		
        List lista = new ArrayList();  // lista de aplicaciones
            try {
                String sql = " SELECT * FROM aplicaciones \n" +
                          "    where fechaBaja is null  and fechaAlta <= (select sysdate())\n" +
                          "    order by codigo";  // sql de consulta

                PreparedStatement ps = conectarBD.prepareStatement(sql);				
                ResultSet rs = ps.executeQuery();

                while(rs.next()){  //por cada registro crea una clase AplicacionBEAN 
                    AplicacionBEAN aplicaciones = new AplicacionBEAN();
                    aplicaciones.setID(rs.getInt("id"));
                    aplicaciones.setCodigo(rs.getString("codigo"));
                    aplicaciones.setNombre(rs.getString("nombre"));
                    aplicaciones.setDescripcion(rs.getString("descripcion"));
                    aplicaciones.setIPhost(rs.getString("IPhost"));
                    aplicaciones.setIPlog(rs.getString("IPlog"));
                    aplicaciones.setIdTipo(rs.getInt("idTipo"));
                    aplicaciones.setIdIdioma(rs.getInt("idIdioma"));
                    aplicaciones.setIdUser(rs.getInt("idUser"));
                    aplicaciones.setIdDepto(rs.getInt("idDepto"));
                    aplicaciones.setIdArea(rs.getInt("idArea"));
                    aplicaciones.setIdCiudad(rs.getString("idCiudad"));
                    aplicaciones.setIdPais(rs.getString("idPais"));
                    aplicaciones.setFechaAlta(rs.getString("fechaAlta"));
                    aplicaciones.setFechaBaja(rs.getString("fechaBaja"));                                    
						
                    lista.add(aplicaciones); // almacena cada AplicacionBEAN dentro de la lista de Aplicaciones
                }
            } catch (SQLException e) {
                 System.out.println(" ERROR ");
                    e.printStackTrace();
            }
            return lista;
	}
/* ***************************************************
recuperar la lista de códigos de aplicaciones activas
**********************************************************/
    public List listaCodigos(){  // recupera todas las aplicaciones	
        List lista = new ArrayList();
        
        try {
            String sql = " SELECT distinct(codigo)  FROM aplicaciones \n" +
                          "    where fechaBaja is null  and fechaAlta <= (select sysdate())\n" +
                          "    order by codigo";  // sql de consulta
            PreparedStatement ps = conectarBD.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){ 
                 lista.add(rs.getString(1)) ;                 
            }            
        } catch (SQLException e) {
             System.out.println(" ERROR ");
                e.printStackTrace();
        }
        return lista;
    }     
/* ***************************************************
recuperar ultima version de un aplicacion
**********************************************************/
    public int ultVersionAplicacion(int id){  // buscar una aplicacion por el id de la aplicacion			
        int ult=0;
        try {
            String sql = "SELECT max(v.idVersion) ult\n" +
                        "FROM aplicacionesweb.aplicaciones as a , aplicacionesweb.versiones as v \n" +
                        "where a.id = v.idAplic   and   a.id = ?;";  // sql recupera datos de la aplicacion (id)
            PreparedStatement ps = conectarBD.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); 
            if(rs.next()){ 
                ult =  rs.getInt(1) ;
            }                                        
        } catch (SQLException e) {
            System.out.println(" ERROR ");
            e.printStackTrace();
        }
        return ult;
    }
}