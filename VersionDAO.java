 /*  VersionDAO.java: contine los metodos de acceso a datos de las versiones, consultas, alta, modificación y eliminación

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
import DAO.VersionBEAN;

public class VersionDAO {  
	private final Connection conectarBD; // crear la vble de conexion a la BBDD	 

  public VersionDAO(){		
    conectarBD = Conexion.getConnection();  // abrir la conexión a la BBDD . versionesweb(versiones, usuarios, deptos, areas, ciudades...)
	}  
 
 /* ***************************************************
recuperar la lista de versiones de una aplicacion activa
**********************************************************/
  public List datosVersionesAplicacion(int id){  // recupera todas las versiones		
        List lista_versiones = new ArrayList();  // lista de versiones	
        try {
            String sql = "SELECT idAplic, idVersion, descripcion, numFinReq,  fechaInicial, \n" +
                         "fechaImplantacion, defectosDesarrollo, defectosFuncionales,defectosArquitectura  FROM versiones where idAplic = ? ";  // sql de consulta de versiones de una aplicacion
            PreparedStatement ps = conectarBD.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){  //por cada registro crea una clase VersionBEAN 
                    VersionBEAN versiones = new VersionBEAN();
                    versiones.setIdAplic(rs.getInt("idAplic"));
                    versiones.setIdVersion(rs.getInt("idVersion"));					
                    versiones.setDescripcion(rs.getString("descripcion"));
                    versiones.setNumFinReq(rs.getInt("numFinReq"));	
                    versiones.setFechaInicial(rs.getString("fechaInicial"));	
                    versiones.setFechaImplantacion(rs.getString("fechaImplantacion"));
                    versiones.setDefectosDesarrollo(rs.getInt("defectosDesarrollo"));
                     versiones.setDefectosFuncionales(rs.getInt("defectosFuncionales"));
                    versiones.setDefectosArquitectura(rs.getInt("defectosArquitectura"));	
						
                    lista_versiones.add(versiones); // almacena cada versionBEAN dentro de la lista de Versions
            }
        } catch (SQLException e) {
             System.out.println(" ERROR ");
                e.printStackTrace();
        }
        return lista_versiones;
    }
/* ***************************************************
recuperar datos de la ultima version de una aplicacion activa
**********************************************************/ 
  public VersionBEAN datosUltimaVersion(int ida, int idv){  // buscar una Version por el id de la Version			
    VersionBEAN versiones = new VersionBEAN(); 
        try {
            String sql = "SELECT idAplic, IDVersion, descripcion, numIniReq, numFinReq, fechaInicial, fechaImplantacion, "
                    + "defectosDesarrollo, defectosFuncionales, defectosArquitectura \n" +
                        "FROM versiones where idAplic=? and idVersion = ?";  // sql recupera datos de la Version (id)
            PreparedStatement ps = conectarBD.prepareStatement(sql);
            ps.setInt(1, ida);
            ps.setInt(2, idv);          
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                versiones.setIdAplic(rs.getInt("idAplic"));
                versiones.setIdVersion(rs.getInt("idVersion"));						
                versiones.setDescripcion(rs.getString("descripcion"));
                versiones.setNumIniReq(rs.getInt("numIniReq"));
                versiones.setNumFinReq(rs.getInt("numFinReq"));
                versiones.setFechaInicial(rs.getString("fechaInicial"));						
                versiones.setFechaImplantacion(rs.getString("fechaImplantacion"));
                versiones.setDefectosDesarrollo(rs.getInt("defectosDesarrollo"));
                versiones.setDefectosFuncionales(rs.getInt("defectosFuncionales"));
                versiones.setDefectosArquitectura(rs.getInt("defectosArquitectura"));
            }                                      
        } catch (SQLException e) { 
            System.out.println(" ERROR ");
            e.printStackTrace();
        }
        return versiones;
    }
 /* ***************************************************
recuperar datos de una version de una aplicacion activa
**********************************************************/    
  public VersionBEAN consultar(int aplicId, int versionId){  // buscar una Version por el id de la Version			
    VersionBEAN versiones = new VersionBEAN();

        try {
            String sql = "SELECT idAplic, numVersion, descripcion, numFinReq, date_format(fechaInicial,'%d/%m/%Y') as fechaInicial, \n" +
                        "date_format(fechaImplantacion,'%d/%m/%Y') as fechaImplantacion, defectosArquitectura \n" +
                        "FROM versiones where idAplic=? and numVersion =?";  // sql recupera datos de la Version (id)
            PreparedStatement ps = conectarBD.prepareStatement(sql);
            ps.setInt(1, aplicId);
             ps.setInt(2, versionId);
            ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    versiones.setIdAplic(rs.getInt("idAplic"));
                    versiones.setIdVersion(rs.getInt("numVersion"));						
                     versiones.setDescripcion(rs.getString("descripcion"));
                      versiones.setNumFinReq(rs.getInt("numFinReq"));
                    versiones.setFechaInicial(rs.getString("fechaInicial"));						
                     versiones.setFechaImplantacion(rs.getString("fechaImplantacion"));
                      versiones.setDefectosArquitectura(rs.getInt("defectosArquitectura"));
                }   

        } catch (SQLException e) { 
            System.out.println(" ERROR ");
            e.printStackTrace();
        }
        return versiones;
    }
/***************************************************
guardar NUEVA VERSION de una aplicacion activa
**********************************************************/ 
	public void nuevaVersion(VersionBEAN version){  //dar de alta una nueva Version. utiliza VersionBEAN para representar cada aplicacion
            VersionBEAN ver = new VersionBEAN();
            int  a = 1;
            try {
			String sql0 ="select max(idVersion)+1 valor from versiones where idAplic=?";
                        PreparedStatement ps0 = conectarBD.prepareStatement(sql0);
                         ps0.setInt(1, version.getIdAplic()); 
                         ResultSet rs = ps0.executeQuery();
                          if(rs.next()){ 
                              a =  rs.getInt(1) ;
                              if ( a == 0 )   a++;
                          }
                       
                        String sql = "INSERT INTO VERSIONES (idAplic, idVersion, descripcion, numIniReq, numFinReq,"
                                + "defectosDesarrollo,defectosFuncionales,  defectosArquitectura,fechaInicial,fechaImplantacion, NUMUSUARIOS, IDROLUSUARIO)"
                                  + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"; //alta de una Version,con los valores minimos
		
                        PreparedStatement ps = conectarBD.prepareStatement(sql); // sentencia para ejec el insert

			ps.setInt(1, version.getIdAplic());   // reemplaza el primer ? del insert                     
			ps.setInt(2, a); 
                        ps.setString(3,version.getDescripcion());
                        ps.setInt(4,version.getNumIniReq());
                        ps.setInt(5,version.getNumFinReq());
                        ps.setInt(6,version.getDefectosDesarrollo());
                        ps.setInt(7,version.getDefectosFuncionales());                       
                        ps.setInt(8,version.getDefectosArquitectura());
                        String fi = version.getFechaInicial();
                        String[] fechaf = fi.split("/");
                        fi = fechaf[2]+"-"+fechaf[1]+"-"+fechaf[0];  
                        ps.setString(9, fi);
                        String ff = version.getFechaImplantacion();
                        fechaf = ff.split("/");
                        ff = fechaf[2]+"-"+fechaf[1]+"-"+fechaf[0];                         
                        ps.setString(10, ff);
                        ps.setInt(11,version.getNumUsu());
                        ps.setInt(12,version.getIdRol());
                        ps.executeUpdate();// ejecuta el insert
			
		} catch (SQLException e) {
                    System.out.println(" ERROR ");
			e.printStackTrace();
		}
	}
 /* ***************************************************
elimiar fisicamente datos de una version de una aplicacion activa
**********************************************************/  
	public void eliminarVersion(int aplicId, int nver){//eliminar una Version     
        try {
                String sql = "DELETE FROM versiones WHERE idAplic=? and idVersion=?"; // sql para eliminar una Version. ? es el id de la aplicacion y Version		
                PreparedStatement  ps = conectarBD.prepareStatement(sql); //sentencia para ejecucion de sql		
                ps.setInt(1, aplicId); // valor de ?, id de la Version		
                ps.setInt(2, nver);
                ps.executeUpdate(); //ejecutar la sentencia		
            } catch (SQLException e) {
                 System.out.println(" ERROR ");
                    e.printStackTrace();
            }
	}
/******************************************************************************
GESTOR VERSIONES -> MODIFICAR VERSION . ORIGEN: MODIFICAR VERSION.JSP         
*******************************************************************************/
    public void modificarVersion(VersionBEAN version){  
        try {
            
            String sql = "UPDATE versiones set descripcion=?, numIniReq=?, numFinReq=?,defectosDesarrollo =?,defectosFuncionales=?,"
                    + " defectosArquitectura= ?, fechaInicial = ? , fechaImplantacion= ?  where idAplic=? and idVersion =? "; //alta de una Version,con los valores minimos
			PreparedStatement ps = conectarBD.prepareStatement(sql); // sentencia para ejec el insert

                        ps.setString(1,version.getDescripcion());
                        ps.setInt(2,version.getNumIniReq());
                        ps.setInt(3,version.getNumFinReq());
                        ps.setInt(4,version.getDefectosDesarrollo());
                        ps.setInt(5,version.getDefectosFuncionales());                        
                        ps.setInt(6,version.getDefectosArquitectura()); 
                        String fi = version.getFechaInicial();
                        String[] fechaf = fi.split("/");
                        fi = fechaf[2]+"-"+fechaf[1]+"-"+fechaf[0];  
                        ps.setString(7, fi);
                        String ff = version.getFechaImplantacion();
                        fechaf = ff.split("/");
                        ff = fechaf[2]+"-"+fechaf[1]+"-"+fechaf[0];                         
                        ps.setString(8, ff);                        
			ps.setInt(9, version.getIdAplic()); 
                        ps.setInt(10, version.getIdVersion());                    
			
                         ps.executeUpdate();// ejecuta el insert
        } catch (SQLException e) {
             System.out.println(" ERROR ");
             e.printStackTrace();
        }
    }
		
}
