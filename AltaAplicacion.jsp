 /*   AltaAplicacion.jsp: Muestra formulario de entrada de datos para dar de alta una nueva aplicación. Acciones: Guardar , Atrás

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
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/estilos.css" type="text/css" />
      <link rel="stylesheet" href="css/stlAltaModif.css" rel="stylesheet" type="text/css" media="screen" />
    <script language="javascript" type="text/javascript" src="js/validacionesFrm.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Alta de una nueva Aplicacion</title>
</head>
<body>
<div id="formAplic" > 
    <h2>Nueva Aplicación</h2>    
    <form method="POST" action="GestorAplicaciones" name="AltaAplicacion">
	<input type="hidden" name="action" value="altaAplicacion" />
        
        <label for="name">Código: <span class="required">*</span></label>
        <input type="text" id="codigo" name="codigo" value="" placeholder="p.e. AAA" required="required" autofocus="autofocus" maxlength="3"/>
                
        <label for="name">Nombre: <span class="required">*</span></label>
        <input type="text" id="nombre" name="nombre" value="" placeholder="p.e Búscador de Contenidos" required="required" />
         
        <label for="name">Descripción: <span class="required">*</span></label>
        <input type="text" id="descripcion" name="descripcion" value=""  placeholder="p.e Busca Contenidos Históricos"required="required" data-minlength="5"> 
        
        <label for="name">IP Host <span class="required">*</span></label>
        <input type="text" id="IPhost" name="IPhost" value="" placeholder="p.e 192.168.22.66"  maxlength="19"/>
         
        <label for="name">IP Log <span class="required">*</span></label>
        <input type="text" id="IPlog" name="IPlog" value="" placeholder="p.e 192.168.22.62"  maxlength="19"/>
         
        <label for="enquiry">Tipo Aplicación: </label>
        <select id="enquiry" name="idTipo">
            <option value="1">Centro</option>
            <option value="2">Departamento</option>
            <option value="3">Especial</option>
            <option value="4">General</option>
            <option value="5">Personal</option>
        </select>    
        
      <label for="enquiry">Idioma: </label>
        <select id="enquiry" name="idIdioma" >
            <option value="1">Español</option>
        </select>       
            <label for="enquiry">Responsable: </label>
        <select id="enquiry" name="idUser">
           <option value="1">Director Servicios Centrales</option>
           <option value="2">Gestor de Cuentas</option>           
           <option value="3">Inspectores</option>           
           <option value="4">Supervisor Aplicaciones</option>                      
        </select>          
        <label for="enquiry">Área: </label>
        <select id="enquiry" name="idArea">
            <option value="1">Control Administrativo</option>
            <option value="2">Desarrollos Instrumentales</option>
            <option value="3">Inventario Regular</option>
            <option value="4">Mantenimiento Estructural</option>
        </select>
        <label for="enquiry">Departamento: </label>
        <select id="enquiry" name="idDepto">
            <option value="1">Gestión Interna</option>
        </select>   
       <label for="enquiry">Ciudad: </label>
        <select id="enquiry" name="idCiudad">
            <option value="ESP">Madrid</option>
        </select>       
        <label for="enquiry">País: </label>
        <select id="enquiry" name="idPais">
            <option value="ESP">España</option>
        </select>    
        
       <input type="button"  value="Guardar" id="submit-button" onclick="validarAA()" />         
        <input type="reset" value="Atrás" onclick="location.href='MostrarAplicaciones.jsp'" id="submit-button"/>
        <p id="req-field-desc"><span class="required">*</span> Campo obligatorio</p>
    </form>
</div>
</body>
</html>