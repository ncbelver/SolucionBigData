 /*   MostrarVersiones.jsp: Muestra listado de versiones de una aplicacion web, permite acciones: alta, modificar, eliminar  

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
 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="DAO.VersionBEAN" %>
<%@ page import="DAO.VersionDAO" %>
<%@page import="DAO.AplicacionBEAN"%>
<%@page import="DAO.AplicacionDAO"%>
<%@ page import="java.util.*" %>
<html>
<head> 
    <link rel="stylesheet" href="css/stlListado.css" rel="stylesheet" type="text/css" media="screen" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />   

<title>Mostrar Versiones</title>
</head>
<body>
     <%
	AplicacionDAO daoa = new AplicacionDAO();
        VersionDAO daov = new VersionDAO();
        
        String id = request.getParameter("aplicID");
        int ID = Integer.parseInt(id);
        String datos="N";
        
        AplicacionBEAN aplicacion = (AplicacionBEAN)daoa.datosAplicacion( ID );
	List<VersionBEAN> versionList = daov.datosVersionesAplicacion(ID);
	Iterator<VersionBEAN> itr = versionList.iterator();
        VersionBEAN versiones = null;%>
  <div id="formAplic" > 
      <h2>Listado de Versiones de la Aplicación:  <%= aplicacion.getNombre()%><img width=350 height=1 src="images/fondo.png"> 
          <a class=boton1 href="AltaVersion.jsp?aplicID=<%= ID %>" >Nueva Versión</a> </h2>
  <table>  
	<tr>
            <th>Versión </th><th>Descripción</th><th>Número Requisitos</th> <th>Fecha Inicio</th><th>Fecha Implantación</th><th>Defectos DES/FUNC/ARQ</th>
	</tr>
	<tr>
            <% 
                    while(itr.hasNext()) {
                        datos = "S";                   
                    versiones = itr.next();
	    %>
                        <td><%= versiones.getIdVersion() %></td>			
			<td><%= versiones.getDescripcion()%></td>
                         <td><%= versiones.getNumFinReq() %></td>
                         <td><%= versiones.getFechaInicial().substring(8,10)%>/<%= versiones.getFechaInicial().substring(5,7)%>/<%= versiones.getFechaInicial().substring(0,4)%></td>
                    <td><%= versiones.getFechaImplantacion().substring(8,10)%>/<%= versiones.getFechaImplantacion().substring(5,7)%>/<%= versiones.getFechaImplantacion().substring(0,4)%></td>
                        <td><%= versiones.getDefectosDesarrollo() %> / <%= versiones.getDefectosFuncionales()%> / <%= versiones.getDefectosArquitectura() %></td>	
		</tr>
		<% } %>	
</table>
      <% if (datos != "N"){%>
<form method="POST" action="GestorVersiones">
    <input type="hidden" name="action" value="consultarFormulario" >
    <input type="hidden" name="idAplic" value=<%= versiones.getIdAplic() %> >
    <input type="hidden" name="idVersion" value=<%= versiones.getIdVersion() %> >
    <input type="submit" value="Modificar última Versión" id="submit-button" />
</form>
<form method="POST" action="GestorVersiones">
    <input type="hidden" name="action" value="eliminar" >
    <input type="hidden" name="idAplic" value=<%= versiones.getIdAplic() %> >
    <input type="hidden" name="idVersion" value=<%= versiones.getIdVersion() %> >
    <input type="submit" value="Eliminar última Versión" id="submit-button" />
</form>    
<%}%> 
 <form method="POST" action="GestorAplicaciones">  
     <input type="submit" value="Atras" id="submit-button" />
    <input type="hidden" name="action" value="editarFormulario" >
    <input type="hidden" name="ID" value="<%= ID %>" >
</form>
</div>
  
       
</body>
</html>
