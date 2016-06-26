 /* GestorVersiones.java: Servlet que permite la ejecución de las acciones necesarias para la gestión de versiones de Aplicaciones web.

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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.RequestDispatcher;


import DAO.VersionDAO;
import DAO.VersionBEAN;
import javax.servlet.RequestDispatcher;

public class GestorVersiones extends HttpServlet {	 
	private VersionDAO dao; 
	
        public GestorVersiones() {
            super();
            dao = new VersionDAO();	
	}		
	 
        protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {               
            String redirect = "";	         
            String action = request.getParameter("action");
            String uId = request.getParameter("idAplic");	
            String nv =  request.getParameter("numVersion");  

/* NUEVA VERSION DE UNA APLICACION SELECCIONADA */            
            if (  action.equalsIgnoreCase("altaVersion")){
/*
          GESTOR DE VERSIONES ->  ACCION: ALTAVERSION. origen:MOSTRARVERSIONES.jsp . Destino: VERSIONDAO: NUEVAVERSION()
*/       
		int ID = Integer.parseInt(uId);
		VersionBEAN version = new VersionBEAN();			
		version.setIdAplic(ID); 		
		version.setDescripcion(request.getParameter("descripcion"));  
		version.setNumIniReq(Integer.parseInt((String)request.getParameter("numReqIni")));
                version.setNumFinReq(Integer.parseInt((String)request.getParameter("numReqFin"))); 
                version.setNumFinReq(Integer.parseInt((String)request.getParameter("numUsu"))); 
                version.setNumFinReq(Integer.parseInt((String)request.getParameter("idRol"))); 
                version.setFechaInicial(request.getParameter("fechaIni")); 
                version.setFechaImplantacion(request.getParameter("fechaImplantacion"));
                version.setDefectosFuncionales(Integer.parseInt((String)request.getParameter("defectosFuncionales")));
                version.setDefectosDesarrollo(Integer.parseInt((String)request.getParameter("defectosDesarrollo")));
                version.setDefectosArquitectura(Integer.parseInt((String)request.getParameter("defectosArquitectura")));
		dao.nuevaVersion(version);

                redirect = "/MostrarVersiones.jsp?aplicID="+ID;
	}   else if ( action.equalsIgnoreCase("modificar")){
/**************************************************************************************************************************************
           GESTOR DE VERSIONES ->  ACCION: MODIFICAR. origen:MODIFICARVERSION.jsp . Destino: VERSIONDAO: MODIFICARULTIMAVERSION()
******************************************************************************************************************************************/                
		int ID = Integer.parseInt(uId);
		VersionBEAN version = new VersionBEAN();		  
		version.setIdAplic(Integer.parseInt((String)request.getParameter("idAplic"))); 
                version.setIdVersion(Integer.parseInt((String)request.getParameter("idVersion"))); 		
		version.setDescripcion(request.getParameter("descripcion")); 
		version.setNumIniReq(Integer.parseInt((String)request.getParameter("numIniReq")));                
                version.setNumFinReq(Integer.parseInt((String)request.getParameter("numFinReq"))); 
                version.setFechaInicial(request.getParameter("fechaIni"));    
                version.setFechaImplantacion(request.getParameter("fechaImplantacion"));
                version.setDefectosDesarrollo(Integer.parseInt((String)request.getParameter("defectosDesarrollo")));    
                version.setDefectosFuncionales(Integer.parseInt((String)request.getParameter("defectosFuncionales")));                        
                version.setDefectosArquitectura(Integer.parseInt((String)request.getParameter("defectosArquitectura")));
		dao.modificarVersion(version);                

                redirect = "/MostrarVersiones.jsp?aplicID="+ID;
             } else if(action.equalsIgnoreCase("eliminar")){
/**************************************************************************
GESTOR DE VERSIONES . ACCION = ELIMINAR . ORIGEN:LISTADO VERSIONES .
************************************************************************************/                
		 
                int aid = Integer.parseInt((String)request.getParameter("idAplic"));
                int nver = Integer.parseInt((String)request.getParameter("idVersion"));                                 
		dao.eliminarVersion(aid,nver);  
                
		redirect = "/MostrarVersiones.jsp?aplicID="+aid;
	 } else if(action.equalsIgnoreCase("consultarFormulario")){
/*************************************************************************************************************
    GESTOR DE VERSIONES -> CONSULTAR FORMULARIO. ORIGEN:LISTADO VERSIONES.JSP / DESTINO: MODIFICARVERSION.JSP
*****************************************************************************************************************/               
                request.setAttribute("ida", request.getParameter("idAplic"));
                request.setAttribute("idv", request.getParameter("idVersion"));

                  redirect = "/ModificarVersion.jsp";
         } else {    
             redirect = "/MostrarVersiones.jsp?aplicID="+uId;              
         }	 
            RequestDispatcher rd = request.getRequestDispatcher(redirect);
            rd.forward(request, response);
	}
}
