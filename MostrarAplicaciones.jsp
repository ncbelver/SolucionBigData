 /*   MostrarAplicaciones.jsp: Muestra listado de aplicaciones web activas, permite acciones: alta, modificar, eliminar, ver gráficos... 

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
<!DOCTYPE html >
<%@ page import="DAO.AplicacionBEAN" %>
<%@ page import="DAO.AplicacionDAO" %>
<%@ page import="DAO.GenericosBEAN" %>
<%@ page import="DAO.GenericosDAO" %>
<%@ page import="java.util.*" %>
<html>
<head> 
    <link rel="stylesheet" href="css/stlListado.css" rel="stylesheet" type="text/css" media="screen" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" /> 
<title>Mostrar Aplicaciones WEB</title>
</head>
<body >

<p align="left"><img width=640 height=200 src="images/cabecera.png" alt="Aplicaciones WEB"  ></p>
  <div id="formAplic" > 
<h2>Listado de Aplicaciones <img width=670 height=1 src="images/fondo.png"> <a class=boton1 href="AltaAplicacion.jsp" >Nueva Aplicación</a><img width=6 height=1 src="images/fondo.png"> 
 <a class=boton1 href="AnalizarAplicaciones.jsp" >Análisis Gráfico</a><img width=6 height=1 src="images/fondo.png"> <a class=boton1 href="ExcelAplicaciones.jsp" >a Excel</a></h2> 
  <table>  
	<tr>
            <th>Código </th><th>Nombre</th><th>Descripción</th> <th>Área</th><th>Responsable</th><th>Fecha Creación</th>   
            <th>Modificar</th><th>Eliminar</th>  <th>Ver Análisis</th>
	</tr>
	<tr>
            <%
        GenericosDAO res = new GenericosDAO();  
	AplicacionDAO dao = new AplicacionDAO();
	List<AplicacionBEAN> aplicList = dao.datosAplicacionesActivas();
	Iterator<AplicacionBEAN> itr = aplicList.iterator();
        AplicacionBEAN aplicaciones = null;
                while(itr.hasNext()) {
                    aplicaciones = itr.next();
		%>
                        <td><%= aplicaciones.getCodigo()%></td>
			<td><%= aplicaciones.getNombre()%></td>
			<td><%= aplicaciones.getDescripcion()%></td>			
			<td><%= res.getArea(aplicaciones.getIdUser()).getNombre()%></td>
                        <td><%= res.getResponsable(aplicaciones.getIdUser()).getNombre() %></td>
                        <td><%= aplicaciones.getFechaAlta().substring(8,10)%>/<%= aplicaciones.getFechaAlta().substring(5,7)%>/<%= aplicaciones.getFechaAlta().substring(0,4)%></td>
                     
			<td>
                            <form method="POST" action="GestorAplicaciones">
                                <button class="imgEditar"></button>
                                <input type="hidden" name="action" value="editarFormulario" >
                                <input type="hidden" name="ID" value="<%= aplicaciones.getID() %>" >
                            </form>
                            
                        </td>
			<td>
                            <form method="POST" action="GestorAplicaciones">
                                <button class="imgEliminar" onclick="if(!confirm('Desea eliminar la aplicacion WEB?')) return false;"></button>
                                <input type="hidden" name="action" value="eliminar" >
                                <input type="hidden" name="ID" value="<%= aplicaciones.getID() %>" >
                            </form>
                            
                        </td>
                        <td>  <button class="imgAnalizar" onclick="location.href='AnalizarAplicacion.jsp?ID=<%= aplicaciones.getID() %>'"></button>  </td>
		</tr>
		<%
			}
		%>	
</table>
</div>
</body>
</html>
