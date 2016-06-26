 /*   AltaVersion.jsp: Muestra formulario de entrada de datos para dar de alta una version de una aplicación. Acciones: Guardar, Atrás

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
    <<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
<title>Alta de una nueva Versión</title>
</head>
<body> 
	<%
        AplicacionDAO daoa = new AplicacionDAO();   
        String id = request.getParameter("aplicID");
        int ID = Integer.parseInt(id);
        AplicacionBEAN aplicacion = (AplicacionBEAN)daoa.datosAplicacion( ID );      
        %> 
<div id="formAplic" > 
    <h2>Nueva Versión de la Aplicación</h2><h1> <%= aplicacion.getNombre()%> </h1> 
    <form method="POST" action="GestorVersiones" name="AltaVersion">
       <input type="hidden" name="action" value= "altaVersion" />              
       <input type="hidden" name="idAplic" value="<%=ID%>" />  
       
        <label for="name">Descripción: <span class="required">*</span></label>
        <input  id="descripcion" name="descripcion" required="required" autofocus="autofocus"/>        
     
        <label for="name">Numero Requisitos Iniciales: <span class="required">*</span></label>
        <input type="text" id="numReqIni" name="numReqIni" required="required" autofocus="autofocus" maxlength="2" />
             
        <label for="name">Numero Requisitos Finales: <span class="required">*</span></label>
        <input type="text" id="numReqFin" name="numReqFin"  required="required" autofocus="autofocus" maxlength="2"  />
        
         <label for="name">Numero Usuarios de Pruebas: <span class="required">*</span></label>
        <input type="text" id="numUsu" name="numUsu"   maxlength="2"  />
        
        <label for="enquiry">Rol de Usuario: </label>
        <select id="enquiry" name="idRol">
            <option value="1">Supervisor</option>
            <option value="2">Personal</option>
        </select>
        
        <label for="name">Fecha Inicio:    <span class="required">*</span></label>
      <input type="text" id="fechaIni" name="fechaIni" value="" maxlength="10" />
      
       <label for="name">Fecha Implantación:   <span class="required">*</span></label>
       <input type="text" id="fechaImplantacion" name="fechaImplantacion" value=""  maxlength="10" />      
       
          <label for="name">Numero Defectos en Desarrollo: <span class="required">*</span></label>
        <input type="text" id="defectosDesarrollo" name="defectosDesarrollo" value=""  maxlength="2"   />
        
          <label for="name">Numero Defectos Funcionales: <span class="required">*</span></label>
        <input type="text" id="defectosFuncionales" name="defectosFuncionales" value=""  maxlength="2" />
        
          <label for="name">Numero Defectos por Arquitectura: <span class="required">*</span></label>
        <input type="text" id="defectosArquitectura" name="defectosArquitectura" value=""  maxlength="2"  />
         
        <p id="req-field-desc"><span class="required">*</span> Campo obligatorio</p>   
        
        <input type="reset" value="Atrás" onclick="location.href='MostrarVersiones.jsp?aplicID=<%= ID %>'" id="submit-button"/>
        <input type="button"  value="Guardar" id="submit-button" onclick="validarAV()" />     
       
    </form>
</div>
</body>
</html>

 