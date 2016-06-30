 /*  ObtenerRecomendacion: Permite a través de la librería MAHOUT la generación de recomendaciones a partir de una fichero de opiniones de usuario, opinionesUsuario.csv,
 sobre las aplicaciones web que utiliza. Campos del fichero de entrada: usuario, código Aplicación, valoración usuario.
Las recomendaciones generadas se vuelcan en un fichero de salida: Recomendaciones.txt

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
package analisis;
 
	import org.apache.log4j.BasicConfigurator;
	import contenido.*;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.AveragingPreferenceInferrer;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.BufferedWriter;
 import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

	public class ObtenerRecomendación {

	  public static void main(String[] args) throws IOException, TasteException, SAXException, ParserConfigurationException {
		  BasicConfigurator.configure(); 
		  String datosEntrada = "Datos/opinionesUsuario.csv"; //Datos Estudio
		  FileDataModel datosEstudio = new FileDataModel(new File(datosEntrada)); //Datos Estudio  
		  String aplicIdsNombre = "Datos/nombresAplicaciones.xml"; // xml nombres aplicaciones
		  long userId = Long.parseLong("479924"); //id usuario a estudio
		   BufferedWriter bw = new BufferedWriter(new FileWriter("Datos/Recomendaciones.txt")); 
		  
			 // * * * análisis información XML : ID, NOMBRE APLICACION 
		    InputSource infoXML = new InputSource(new FileInputStream(aplicIdsNombre));
		    SAXParserFactory desglose = SAXParserFactory.newInstance();
		    desglose.setValidating(false);
		    SAXParser sp = desglose.newSAXParser();
		    Contenido contenidoXML = new Contenido();
		    sp.parse(infoXML, contenidoXML);
		    
/* Recomendar x aplicacion   */
		bw.write("\n * * * * El usuario: "+userId+" según la valoración de sus aplicaciones, realiza las siguientes recomendaciones:");
		//bw.write("\n Recomendar aplicaciones similares al Usuario: "+userId+ ", analizando la valoración de sus aplicaciones ");
	    ItemSimilarity similitudAplic = new LogLikelihoodSimilarity(datosEstudio); ////Create an ItemSimilarity    
	    ItemBasedRecommender recomendar = new GenericItemBasedRecommender(datosEstudio, similitudAplic);//recomendacion por info aplicacion
	    List<RecommendedItem> recomendaciones = recomendar.recommend(userId, 5);// calcular las mejores recomendaciones  del usuario indicado
	    InfoSalidaFichero.mostrarRecomendaciones(recomendaciones, contenidoXML.map, bw);  
/* Recomendar x similitud  	*/    
	    Long aplicId1 = Long.parseLong("1");   		Long aplicId2 = Long.parseLong("8");  
		bw.write(" \n  \n * * * *  Obtener la similitud entre dos aplicaciones: "+String.valueOf(aplicId1)+"  y  "+aplicId2);
	    ItemSimilarity itemSimilarity = new LogLikelihoodSimilarity(datosEstudio);//Create an ItemSimilarity
	    double res = itemSimilarity.itemSimilarity(aplicId1, aplicId2);
	    bw.write("\n Resultado: " + res);
 /*RecomxSimilAplic   */
			Long aplicId = Long.parseLong("3");    // id aplicacion
		    Integer numRecs = Integer.parseInt("80");   
		    bw.write(" \n   \n * * * *  Similitud entre la aplicación: "+aplicId+" y el resto de aplicaciones:");
		    ItemSimilarity itemSimilarity2 = new LogLikelihoodSimilarity(datosEstudio);//crear item de similitud  
		    ItemBasedRecommender recommender2 =  new GenericItemBasedRecommender(datosEstudio, itemSimilarity2);//Recomendar segun info aplicacion     
		    List<RecommendedItem> simItems = recommender2.mostSimilarItems(aplicId, numRecs);//Similitud de una aplicacion con el resto de aplicaciones
		    InfoSalidaFichero.mostrarRecomendaciones(simItems, contenidoXML.map, bw); 	    
  /*Recomendaciones de Usuarios  */
	    Integer neighborhoodSize = Integer.parseInt("5");  //proximidad 		
		  boolean verSimilitud = Boolean.parseBoolean("True");
		   FileDataModel dataModel = new FileDataModel(new File(datosEntrada)); //datos de estudio
		   bw.write("\n  \n * * * *   Fuente de Datos: \n Total Usuarios: " + dataModel.getNumUsers() +
				   "\t\t Total Aplicaciones: " + dataModel.getNumItems());
		    UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
		    userSimilarity.setPreferenceInferrer(new AveragingPreferenceInferrer(dataModel)); 
		    UserNeighborhood neighborhood =  new NearestNUserNeighborhood(neighborhoodSize, userSimilarity, dataModel); 
		    Recommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, userSimilarity);
		    bw.write("\n\n  Usuario: " + userId);  bw.write("\t\t\t Mostrar sus preferencias: ");
		    InfoSalidaFichero.mostrarPreferencias(dataModel, userId, contenidoXML.map, bw); 
		    bw.write("\n");
		    if (verSimilitud) {
		      long[] users = neighborhood.getUserNeighborhood(userId);
		      for (int i = 0; i < users.length; i++) {
		        long neighbor = users[i];
		        InfoSalidaFichero.mostrarSimilitudes(dataModel, userId, neighbor, contenidoXML.map,bw);
		      }
		      bw.write("");
		    }
	    bw.close();
	  }
	}