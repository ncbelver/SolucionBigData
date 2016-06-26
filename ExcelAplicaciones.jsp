 /*  ExcelAplicaciones.jsp: Visualiza información de aplicaciones y versiones recuperada de la BBDD en excel

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
<%@page import="java.io.*"%>
<%@page import="org.jfree.chart.*"%>
<%@page import="org.jfree.chart.plot.*"%>
<%@page import="org.jfree.data.general.*"%>
<%@page import="org.jfree.data.category.*"%>
 <%@ page import="DAO.AplicacionBEAN" %>
<%@ page import="DAO.AplicacionDAO" %>
 <%@ page import="DAO.VersionBEAN" %>
<%@ page import="DAO.VersionDAO" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            function aExcel(){
                vExcel = window.open("excel.jsp","","status=0,toolbar=0,location=0,menubar=0,resizable=0,width=400,height=200");
            }
    </script>
        <title>JSP Page</title>
    </head>
    <body>
      <table>  
	<tr>
            <th>Código </th><th>Nombre</th><th>Descripción</th> <th>IPHost</th><th>Fecha Creación</th>
            <th>Ultima Versión</th><th>Descripción</th><th>Número Requisitos</th><th>Fecha Implantación</th><th>Defectos DES.FUNC.ARQ</th>   
	</tr>
	<tr>
            <%
     response.setHeader("Content-Disposition","attachment;filename=\"excel.xls\"");
	AplicacionDAO dao = new AplicacionDAO();
        VersionDAO daov = new VersionDAO();
         
	List<AplicacionBEAN> aplicList = dao.datosAplicacionesActivas();
	Iterator<AplicacionBEAN> itr = aplicList.iterator();
        AplicacionBEAN aplicaciones = null;
                while(itr.hasNext()) {
                    aplicaciones = itr.next();                    
		%>
                        <td><%= aplicaciones.getCodigo()%></td>
			<td><%= aplicaciones.getNombre()%></td>
			<td><%= aplicaciones.getDescripcion()%></td>
                         <td><%= aplicaciones.getIPhost()%></td>
                         <td><%= aplicaciones.getFechaAlta().substring(8,10)%>/<%= aplicaciones.getFechaAlta().substring(5,7)%>/<%= aplicaciones.getFechaAlta().substring(0,4)%></td>
            <% //ULTIMA VERSION  ultVersionAplicacion
            VersionBEAN ultVersion = daov.datosUltimaVersion(aplicaciones.getID(),dao.ultVersionAplicacion(aplicaciones.getID()));                    
	    %>
                    <td><%= ultVersion.getIdVersion() %></td>			
		    <td><%= ultVersion.getDescripcion()%></td>
                    <td><%= ultVersion.getNumFinReq() %></td>                        
                    <td><%= ultVersion.getFechaImplantacion().substring(8,10)%>/<%= ultVersion.getFechaImplantacion().substring(5,7)%>/<%= ultVersion.getFechaImplantacion().substring(0,4)%></td>
                    <td><%= ultVersion.getDefectosDesarrollo() %> .<%= ultVersion.getDefectosFuncionales()%>. <%= ultVersion.getDefectosArquitectura() %></td>	
	   </tr>
		<%
                }
		%>	
</table>
<input type="button" name=" " value="Exportar a Excel" onclick="aExcel();" >
    </body>
</html>
