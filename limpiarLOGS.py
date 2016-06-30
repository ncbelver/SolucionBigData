'''   limpiarLOGS.py:se recibe un fichero en formato json, el cual contiene información de los eventos generados por aplicaciones Web
   al ejecutarse en el servidor donde está alojado. Los campos que se reciben a través de este archivo son:
	 fechaAcceso ,usuario, aplicacion, proceso, iniProc, finProc, evento, dispositivo ,navegador, latitud, longitud
	 Algunos valores poseen datos incorrectos, este proceso Python realiza diferentes acciones para el  pre-procesamiento de información del 
	 log: limpiar_campos_en_blanco  + eliminar caracteres incorrectos + calcular_duracion 
	 Los campos de salida del fichero json transformado son: fechaAcceso, usuario, aplicacion, proceso, iniProc, finProc, evento, 
		dispositivo, navegador, latitud, longitud, duracion

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
'''
 #!/usr/bin/env python
#  
# acciones:: limpiar_campos_en_blanco  + eliminar caracteres incorrectos + calcular_duracion
# mapa : fechaAcceso, usuario, aplicacion, proceso, iniProc, finProc, evento, dispositivo, navegador, latitud, longitud, duracion

import re
import json
import datetime
from datetime import datetime, date, time, timedelta
import os

# Eliminar accesos incorrectos. No se permiten campos en blanco
def limpiar_campos_en_blanco(origen):
    print "limpiar_campos_en_blanco"
    nombre = origen.replace("_eventosLOG.json", "")+'_1.json'   
    sinBlancos= open(nombre, 'w+')

    conBlancos = open(origen,'r')
    while True:
        linea = conBlancos.readline()
        if not linea: break
        evento = json.loads(linea)           
        cargarEvento = True   
        listaValores = evento.values()
        
        a = 0
        while a < len(listaValores):
            if listaValores[a]=='':
                cargarEvento = False
            a +=1
            
        if (cargarEvento):
            json.dump(evento, sinBlancos)
            sinBlancos.write('\n') 

    return nombre

# Eliminar caracteres erroneos de los campos : Aplicacion, Proceso, Evento
def eliminar_caracteres_incorrectos(origen):
    print "eliminar caracteres incorrectos"
    nombre = origen.replace("_1.json", "")+'_2.json'  
    ctrolCaracteres = open(nombre, 'w+')
     
    sinCtrol = open(origen,'r')
    while True:
        linea = sinCtrol.readline()
        if not linea: break
        evento = json.loads(linea)
        evento['aplicacion']=evento['aplicacion'].replace(':', '').replace('!', '').replace('.', '').replace(',', '').replace(';', '').encode('utf-8').upper()
        evento['proceso']=evento['proceso'].replace(':', '').replace('!', '').replace('.', '').replace(',', '').replace(';', '').encode('utf-8').upper()
        evento['evento']=evento['evento'].replace(':', '').replace('!', '').replace('.', '').replace(',', '').replace(';', '').encode('utf-8').upper()        
        json.dump(evento, ctrolCaracteres)
        ctrolCaracteres.write('\n')        
    return nombre

# Calcular duraccion del proceso :  fin_proceso - inicio_proceso 
def calcular_duracion(origen):
    print "calcular_duracion"
    nombre = origen.replace("_2.json", "")+'.json'
    calculoDuracion = open(nombre, 'w+')
     
    sinCtrol = open(origen,'r')
    while True:
        linea = sinCtrol.readline()
        if not linea: break
        evento = json.loads(linea)

          #calcular duracion del proceso
        fechafin = datetime(int(nombre[0:10].split("-")[0]), int(nombre[0:10].split("-")[1]),int(nombre[0:10].split("-")[2]),int(evento['finProc'].split(":")[0]),int(evento['finProc'].split(":")[1]),int(evento['finProc'].split(":")[2]))
        fechaini = datetime(int(nombre[0:10].split("-")[0]), int(nombre[0:10].split("-")[1]),int(nombre[0:10].split("-")[2]),int(evento['iniProc'].split(":")[0]),int(evento['iniProc'].split(":")[1]),int(evento['iniProc'].split(":")[2]))
        duracion = fechafin - fechaini
    
        evento['duracion'] =  str(duracion)
        json.dump(evento, calculoDuracion)
        calculoDuracion.write('\n')

    return calculoDuracion
         
#bloque principal
def main():
    #f = raw_input('Nombre del fichero para limpiar  (aaaa-mm-dd) :')
    #nombreFichero =  f+"_eventosLOG.json"  
    nombreFichero = datetime.now().strftime("%Y-%m-%d")+"_eventosLOG.json"
      
      # solicitar Eliminar eventos con campos en blanco + eliminar caracteres incorrectos + calcular duracion del proceso  
    calcular_duracion(eliminar_caracteres_incorrectos(limpiar_campos_en_blanco(nombreFichero)))

    raiz = datetime.now().strftime("%Y-%m-%d")+"_"
    ext = ".json"  
    os.remove(raiz+"eventosLOG"+ext)
    os.remove(raiz+"1"+ext)
    os.remove(raiz+"2"+ext)
    print "Fichero "+ datetime.now().strftime("%Y-%m-%d") +".json transformado correctamente. \n Ficheros auxiliares eliminados"

if __name__ == "__main__":
    main()
    

