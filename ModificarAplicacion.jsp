 /*  ModificarAplicacion.jsp: muestra un formulario con los datos almacenados en BBDD de una aplicaciones para permitir su modificación. Acciones:consultar versiones, guardar, atrás

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
<%@page import="DAO.AplicacionBEAN"%>
<%@page import="DAO.AplicacionDAO"%>

<html>
    <head>
    <link rel="stylesheet" href="css/estilos.css" type="text/css" />    
      <link rel="stylesheet" href="css/stlAltaModif.css" rel="stylesheet" type="text/css" media="screen" />  
          <script language="javascript" type="text/javascript" src="js/validacionesFrm.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>Modificar Datos Aplicacion</title> 
    </head>
    <body>
        <%
            AplicacionDAO dao = new AplicacionDAO();
            int idd = Integer.parseInt((String)request.getAttribute("id"));
            AplicacionBEAN aplicaciones = (AplicacionBEAN)dao.datosAplicacion(idd);
        %>
    <div id="formAplic" > 
    <h2>Modificar datos de la Aplicación</h2>    
    <form method="POST" action="GestorAplicaciones" name="editarFormulario">
    <input type="hidden" name="action" value="editar" /> 
    <input type="hidden" name="id" value="<%= aplicaciones.getID() %>" /> 
     <input type="hidden" name="codigo" value="<%= aplicaciones.getCodigo() %>" /> 

        <label for="name">Código: <span class="required">*</span></label>
        <input type="text" value="<%= aplicaciones.getCodigo()%>" required="required" autofocus="autofocus" maxlength="3" disabled/>
                
        <label for="name">Nombre: <span class="required">*</span></label>
        <input type="text" id="nombre" name="nombre" value="<%= aplicaciones.getNombre()%>"   required="required" maxlength="45" />
         
        <label for="name">Descripción: <span class="required">*</span></label>
        <input type="text" id="descripcion" name="descripcion" value="<%= aplicaciones.getDescripcion()%>" required="required" maxlength="150"/>
        
        <label for="name">IP Host <span class="required">*</span></label>
        <input type="text" id="IPhost" name="IPhost" value="<%= aplicaciones.getIPhost()%>" placeholder="p.e. 192.168.22.66"  maxlength="19"/>
         
        <label for="name">IP Log <span class="required">*</span></label>
        <input type="text" id="IPlog" name="IPlog" value="<%= aplicaciones.getIPlog()%>" placeholder="p.e. 192.168.22.62" maxlength="19" />
        
        <span id="loading"></span>   
            <a class=boton1 href="MostrarVersiones.jsp?aplicID=<%= aplicaciones.getID() %>" >Versiones</a>        
        <input type="button"  value="Guardar" id="submit-button" onclick="validarMA()" />  <input type="reset" value="Atrás" onclick="location.href='MostrarAplicaciones.jsp'" id="submit-button"/>
        <p id="req-field-desc"><span class="required">*</span> Campo obligatorio</p>
    </form>
</div>                        
                        
    </body>
</html>
