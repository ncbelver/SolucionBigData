 /*  AnalizarAplicaciones.jsp: Visualiza un gráfico tipo barras, con la información de todas las aplicaciones recuperada de la BBDD

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
<%@ page import="DAO.GenericosBEAN" %>
<%@ page import="DAO.GenericosDAO" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
 	GenericosDAO dao = new GenericosDAO();
	List<GenericosBEAN> list = dao.versionesxAplicaciones(); //consulta del Gráfico
	Iterator<GenericosBEAN> itr = list.iterator();
        GenericosBEAN nxv = null;
        DefaultCategoryDataset data = new DefaultCategoryDataset();  
        int id;     
        while(itr.hasNext()) {
                    nxv = itr.next();
            
            id = Integer.parseInt(nxv.getID()+"");       // eje y 
            data.setValue(id,"Número de Versiones",nxv.getNombre()); //eje x
                        
        }
         // generar el grafico  BARRAS
            JFreeChart grafico1 = ChartFactory.createBarChart("Aplicaciones Activas 2016", "Nombre Aplicaciones", "Número de Versiones", data, PlotOrientation.VERTICAL, true, true, true);
            
            //visualizar el gráico
            response.setContentType("img/JPEG");
            OutputStream sa1=response.getOutputStream();
            //ver el grafico
            ChartUtilities.writeChartAsJPEG(sa1,grafico1,1600,800);      
  
        %>
    </body>
</html>
