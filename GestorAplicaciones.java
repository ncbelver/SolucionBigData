 /* GestorAplicaciones.java: Servlet que permite la ejecución de las acciones necesarias para la gestión de Aplicaciones web.

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
 package Gestor;

import java.io.IOException;
import java.io.PrintWriter; 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AplicacionDAO;
import DAO.AplicacionBEAN;
import java.util.List;
 
public class GestorAplicaciones extends HttpServlet {      
    private AplicacionDAO dao; 

    public GestorAplicaciones() {
        super();
        dao = new AplicacionDAO();	
    }
		
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
        String redirect = "";	
        Boolean existeCodigo = false;
        String uId = request.getParameter("id");			
        String action = request.getParameter("action"); 
 
        if( action.equalsIgnoreCase("altaAplicacion")){System.out.println("GESTORAPLICACIONES. ACCION: altaAplicacion   - D ");  
/*
           GESTOR DE APLICACIONES ->  ACCION: ALTAAPLICACION. origen:AltaAplicacion.jsp . Destino: AplicacionDAO: nuevaAplicacion()
*/            
            /* comprueba si existe el código*/
            List<String> listacodigos = dao.listaCodigos();
            for (String nombre : listacodigos) {             
                 if (nombre.equals(request.getParameter("codigo").toUpperCase())) { 
                    existeCodigo=true;
                     redirect = "/AltaAplicacion.jsp";
                 }
            }
            
            if (!existeCodigo){
            /* guarda datos de la nueva aplicacion */
            AplicacionBEAN aplicaciones = new AplicacionBEAN();	
            aplicaciones.setCodigo(request.getParameter("codigo").toUpperCase());     
            aplicaciones.setNombre(request.getParameter("nombre"));
            aplicaciones.setDescripcion(request.getParameter("descripcion"));
            aplicaciones.setIPhost(request.getParameter("IPhost"));
            aplicaciones.setIPlog(request.getParameter("IPlog"));
            aplicaciones.setIdTipo(Integer.parseInt(request.getParameter("idTipo")));
            aplicaciones.setIdIdioma(Integer.parseInt(request.getParameter("idIdioma")));
            aplicaciones.setIdUser(Integer.parseInt(request.getParameter("idUser")));
            aplicaciones.setIdDepto(Integer.parseInt(request.getParameter("idDepto")));
            aplicaciones.setIdArea(Integer.parseInt(request.getParameter("idArea")));
            aplicaciones.setIdCiudad(request.getParameter("idCiudad"));
            aplicaciones.setIdPais(request.getParameter("idPais"));
            
            dao.nuevaAplicacion(aplicaciones);  
            }
        } else if(action.equalsIgnoreCase("eliminar")){
/*
           GESTOR DE APLICACIONES ->  ACCION: ELIMINAR. origen: MOSTRARAPLICACIONES.jsp . Destino: AplicacionDAO: bajaAplicacion()
*/  
            String utilistaeurId = request.getParameter("ID");
            int uid = Integer.parseInt(utilistaeurId);
            dao.bajaAplicacion(uid);
            
            request.setAttribute("aplicaciones",dao.datosAplicacionesActivas());
            redirect = "/MostrarAplicaciones.jsp";
        } else if(action.equalsIgnoreCase("editar")){ 
/*
           GESTOR DE APLICACIONES ->  ACCION: EDITAR . origen:MODIFICARAplicacion.jsp . Destino: AplicacionDAO: editarAplicacion()
*/    

            String aplicacionesId = request.getParameter("id");
            int uid = Integer.parseInt(aplicacionesId);            
            AplicacionBEAN aplicaciones = new AplicacionBEAN();
            aplicaciones.setID(uid);              
            aplicaciones.setCodigo(request.getParameter("codigo"));
            aplicaciones.setNombre(request.getParameter("nombre"));
            aplicaciones.setDescripcion(request.getParameter("descripcion"));
            aplicaciones.setIPhost(request.getParameter("IPhost"));
            aplicaciones.setIPlog(request.getParameter("IPlog"));            
            dao.editarAplicacion(aplicaciones);
            
            request.setAttribute("aplicaciones", aplicaciones);            
            redirect = "/MostrarAplicaciones.jsp";
        }  else if(action.equalsIgnoreCase("editarFormulario")){ 
/*
GESTOR DE APLICACIONES ->  ACCION: editarformulario. origen:listadoAplicaciones.jsp .  
*/ 
            request.setAttribute("id", request.getParameter("ID"));
            redirect = "/ModificarAplicacion.jsp";
        } else if(action.equalsIgnoreCase("listado")){  
/*
GESTOR DE APLICACIONES ->  ACCION: LISTADO. origen:listadoAplicaciones.jsp .  
*/ 
            request.setAttribute("aplicaciones", dao.datosAplicacionesActivas());

        } else{               
            redirect = "/MostrarAplicaciones.jsp";
        }
	 
        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);
    }
}