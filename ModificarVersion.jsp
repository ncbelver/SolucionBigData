/*  ModificarVersion.jsp: muestra un formulario con los datos almacenados en BBDD de una version para permitir su modificación. Acciones: guardar, atrás

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
 <%@page import="DAO.VersionBEAN"%>
<%@page import="DAO.VersionDAO"%>
<%@page import="DAO.AplicacionBEAN"%>
<%@page import="DAO.AplicacionDAO"%>
<%@ page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/estilos.css" type="text/css" />
      <link rel="stylesheet" href="css/stlAltaModif.css"  type="text/css" media="screen" />
      <script language="javascript" type="text/javascript" src="js/validacionesFrm.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
<title>Alta de una nueva Versión</title>
</head>
<body> 
	<%
        AplicacionDAO daoa = new AplicacionDAO();
        VersionDAO daov = new VersionDAO();        
        int IDa = Integer.parseInt((String)request.getAttribute("ida")); 
        int IDv = Integer.parseInt((String)request.getAttribute("idv")); 
        
        AplicacionBEAN aplicacion = (AplicacionBEAN)daoa.datosAplicacion(IDa );        
        VersionBEAN version = (VersionBEAN)daov.datosUltimaVersion(IDa,IDv);         
        %> 
<div id="formAplic" > 
    <h2>Modificar datos de la Versión:&nbsp;&nbsp;&nbsp;<b><%= version.getIdVersion()%></b><br> de la Aplicación: &nbsp;&nbsp;&nbsp;<b><%= aplicacion.getNombre()%></b> </h2> 
   
    <form method="POST" action="GestorVersiones" name="ModificarVersion">
       	<input type="hidden" name="action" value= "modificar" />  
        <input type="hidden" name="idAplic" value="<%= version.getIdAplic() %>" />   
         <input type="hidden" name="idVersion" value="<%= version.getIdVersion() %>" />  
       
        <label for="name">Descripción: <span class="required">*</span></label>
        <input type="text" id="descripcion" name="descripcion" value="<%=version.getDescripcion()%>" required="required" />        
     
        <label for="name">Numero Requisitos Iniciales: <span class="required">*</span></label>
        <input type="text" id="numIniReq" name="numIniReq" value="<%=version.getNumIniReq()%>"  required="required" autofocus="autofocus" maxlength="2"/>
             
        <label for="name">Numero Requisitos Finales: <span class="required">*</span></label>
        <input type="text" id="numFinReq" name="numFinReq" value="<%=version.getNumFinReq()%>" required="required" autofocus="autofocus"  maxlength="2" />
        
       <label for="name">Fecha Inicio:   <span class="required">*</span></label>
      <input type="text" id="fechaIni" name="fechaIni" value="<%= version.getFechaInicial().substring(8,10)%>/<%= version.getFechaInicial().substring(5,7)%>/<%= version.getFechaInicial().substring(0,4)%>" maxlength="10"/>
      
       <label for="name">Fecha Implantación: <span class="required">*</span></label>
       <input type="text" id="fechaImplantacion" name="fechaImplantacion" value="<%= version.getFechaImplantacion().substring(8,10)%>/<%= version.getFechaImplantacion().substring(5,7)%>/<%= version.getFechaImplantacion().substring(0,4)%>" maxlength="10" />
        
          <label for="name">Numero Defectos en Desarrollo: <span class="required">*</span></label>
        <input type="text" id="defectosDesarrollo" name="defectosDesarrollo" value="<%=version.getDefectosDesarrollo() %>"   maxlength="2"  />
         <label for="name">Numero Defectos Funcionales: <span class="required">*</span></label>
        <input type="text" id="defectosFuncionales" name="defectosFuncionales" value="<%=version.getDefectosFuncionales() %>"  maxlength="2" />
          <label for="name">Numero Defectos por Arquitectura: <span class="required">*</span></label>
        <input type="text" id="defectosArquitectura" name="defectosArquitectura" value="<%=version.getDefectosArquitectura() %>"  maxlength="2"  />
         
        <p id="req-field-desc"><span class="required">*</span> Campo obligatorio</p>   
        
        <input type="reset" value="Atrás" onclick="location.href='MostrarVersiones.jsp?aplicID=<%= IDa %>'" id="submit-button"/>
         <input type="button"  value="Guardar" id="submit-button" onclick="validarMV()" />  
       
    </form>
</div>
</body>
</html>