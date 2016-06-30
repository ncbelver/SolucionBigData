''' valorarAplic.py: se recibe un fichero en formato json, el cual contiene información de los eventos generados por aplicaciones Web al ejecutarse 
		en el servidor donde está alojado. Los campos que se reciben a través de este archivo son:
		 fechaAcceso ,usuario, aplicacion, proceso, iniProc, finProc, evento, dispositivo ,navegador, latitud, longitud, duración
		Este proceso Python realiza diferentes acciones para el pre-procesamiento de información del log:  
			tasar eventos   + Calcular Opinion media + codificar + ordenar +  Opinion de usuario. 
		Los campos de salida del fichero json transformado son usuario, aplicacion, opinion

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
#!/usr/bin/python
# json  aaaa-mm-dd.json
# tasar eventos   + Calcular Opinion media + codificar + ordenar +  Opinion de usuario
# mapa : fusuario, aplicacion, opinion

import logging
import time
from optparse import OptionParser
from time import strftime
from datetime import timedelta, date, time, datetime
import time
import re
import json
from operator import itemgetter, attrgetter
import os


# Calcular duraccion del proceso :  fin_proceso - inicio_proceso 
def ponderar_eventos(origen): 
    print "Tasar Eventos"
    nombre = origen.replace(".json", "")+'_p.json'
    ponderar = open(nombre, 'w+')
     
    sinCtrol = open(origen,'r')
    while True:
        linea = sinCtrol.readline()
        if not linea: break
        evento = json.loads(linea)

        #calcular duracion del proces
        if evento['evento']=='OK':
            valor = 10
        elif evento['evento']=='TIMEOUT':
            valor = 6
        elif evento['evento']=='ERRORSISTEMA':
            valor = 4
        else:
            valor = 1

        evento['opinion'] =  str(valor)
        json.dump(evento, ponderar)
        ponderar.write('\n')

    return nombre

#calcula la media de opiniones de un usuario sobre una aplicacion
def calcMedia(origen):
    print "Calcular Opinion media"
    nombre = origen.replace("_p.json", "")+'_cal.json'   
    salida= open(nombre, 'w+')    
 
    entrada1 = open(origen,'r')    
    while True:
        linea = entrada1.readline()
        if not linea: break
        evento = json.loads(linea)
        listaValores = evento.values()
        totalOpinion = 0
        s=0
        #recorre el json calculando la media de opiniones por usuario y aplicacion
        entrada2 = open(origen,'r')
        linea2 = entrada2.readline()
        while linea2 != "":
            evento2 = json.loads(linea2)
            listaValores2 = evento2.values()
         #   print  evento['opinion'] +"        "+ evento['usuario']
            if (evento['usuario'] == evento2['usuario'] and evento['aplicacion'] == evento2['aplicacion']):
                 totalOpinion  = (float(totalOpinion)+ float(evento2['opinion']))
                 s+=1
            linea2 = entrada2.readline()
 
        evento['opinion']  = str(totalOpinion/s)[0:4]  
        json.dump(evento, salida)
        salida.write('\n')      
    return nombre

# Codificar aplicaciones
def codificar(origen): 
    print "Codificar aplicacion"
    nombre = origen.replace("_cal.json", "")+'_pp.json'
    codif = open(nombre, 'w+')
     
    sinCtrol = open(origen,'r')
    while True:
        linea = sinCtrol.readline()
        if not linea: break
        evento = json.loads(linea)

        #calcular duracion del proces
        if evento['aplicacion']=='GOT':
            valor = 1 
        elif evento['aplicacion']=='GPP':
            valor = 2
        elif evento['aplicacion']=='SIA':
            valor = 3
        elif evento['aplicacion']=='IBI':
            valor = 4
        elif evento['aplicacion']=='GCA':
            valor = 5
        elif evento['aplicacion']=='VIO':
            valor = 6
        elif evento['aplicacion']=='MIT':
            valor = 7
        elif evento['aplicacion']=='VBS':
            valor = 8
        else:
            valor = 9

        evento['aplicacion'] =  str(valor)
        json.dump(evento, codif)
        codif.write('\n')

    return nombre

# Ordenar por usuario
def ordenar(origen):
    print "Ordenar por usuario"
    nombre = origen.replace("_pp.json", "")+'_ord.json'
    salida = open(nombre, 'w+')
    entrada = open(origen,'r')
    lineas = []
    lista = []
       
    while True:
        linea = entrada.readline()
        if not linea: break
        linea = linea.strip()
        evento = json.loads(linea)        
        lineas.append(evento)
 
    #ordenar por usuario
    lineas = sorted(lineas, key=lambda k: k['usuario'], reverse=True)
    # Eliminar duplicados   
    for linea in lineas:
         if linea not in lista:
             lista.append(linea)
             
    for linea in lista:
        json.dump(linea, salida)
        salida.write('\n') 
    return nombre

# Generar archivo de opiniones por usuario y aplicacion
def usuAplOpi(origen):    
    print "Campos de Opinion"
    nombre = origen.replace("_ord.json", "")+'_op.json'
   
    conOpinion= open(nombre, 'w+')    
    a=0
    
    sinOpinion = open(origen,'r')    
    while True:
        linea = sinOpinion.readline()
        if not linea: break
        evento = json.loads(linea)
        listaValores = evento.keys()
      
        for i in listaValores:
            if (i != 'usuario' and i != 'aplicacion' and i != 'opinion'):
                del evento[i]             

        json.dump(evento, conOpinion)
        conOpinion.write('\n')
        a+=1

    return nombre
 
def main(): 
    nombreFichero = datetime.now().strftime("%Y-%m-%d")+".json"    
    print usuAplOpi(ordenar(codificar(calcMedia(ponderar_eventos(nombreFichero))))) ," con formato usuario - aplicacion - opinion" 
    
    raiz = datetime.now().strftime("%Y-%m-%d")+"_"
    ext = ".json"  
    os.remove(raiz+"pp"+ext)
    os.remove(raiz+"p"+ext)
    os.remove(raiz+"cal"+ext)
    os.remove(raiz+"ord"+ext)
    print "Ficheros auxiliares eliminados"
    
if __name__ == "__main__":
    main()
