'''   generarLOGS.py: se genera un fichero en formato json, el cual contiene información de los eventos generados por aplicaciones Web al ejecutarse 
    en el servidor donde está alojado. Los campos que se reciben a través de este archivo son:
   fechaAcceso ,usuario, aplicacion, proceso, iniProc, finProc, evento, dispositivo ,navegador, latitud, longitud
   Todos los campos se generan aleatoriamente y en algunos valores se introducen datos incorrectos para un posterior pre-procesamiento de información del log.


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
# 
# Generar fichero de EventosLOG 
# mapa : fechaAcceso,usuario,aplicacion,proceso,iniProc,finProc,evento,dispositivo,navegador,latitud,longitud
#

from random import randrange
import random
import logging
import time
from optparse import OptionParser
from time import strftime
from datetime import timedelta, date, time, datetime
import time
import os

# generacion aleatoria de datos del log
def fechaAcceso():
    fechaAcceso = strftime("%Y-%m-%dT%H:%M:%S")
    return fechaAcceso

def usuario():
    usuario = randrange(471000,479999)
    return usuario

def aplicacion():
     aplicacion = random.choice(['got', 'GPP', 'S!IA','I,BI','G.CA','vio','mi.T','vB.S','S!CA',''])   
     return aplicacion

def proceso():
    proceso = random.choice(['ALTA','BAJA','ACTUAL.IZA.CION','CONS.ULTA','EXPO!RTACION','IMPRES;ION','login',''])
    return proceso

def iniProc():
    iniProc = time.strftime('%H:%M:%S')
    return iniProc 

def finProc():
    duracion = randrange(5,59)
    finProc = (datetime.now()+timedelta(minutes=duracion)).strftime("%H:%M:%S")
    return finProc

def evento():
    evento = random.choice(['ok','ERROR.DATOS','TIM:EOUT','ER:RORSI!STEMA',''])  
    return evento

def dispositivo():
    dispositivo = random.choice(['PC','TABLETA','MOVIL',''])   
    return dispositivo

def navegador():
    navegador = random.choice(['EXPLORADOR','CHROME','MOZILA','SAFARY',''])  
    return navegador

def latitud():
    latitud = random.uniform(43.3543266,37.4003885)
    return latitud

def longitud():
    longitud = random.uniform(-7.73758308,-2.348789139)
    return longitud

def generar_lineas_LOG(ficheroLOG):    
    linea = ('{"acceso":"%s","usuario":"%s","aplicacion":"%s","proceso":"%s","iniProc":"%s","finProc":"%s", "evento":"%s","dispositivo":"%s","navegador":"%s","latitud":"%s","longitud":"%s"}'%(fechaAcceso(), usuario(),aplicacion(), proceso(),iniProc(),finProc(),evento(), dispositivo(),navegador(),latitud(),longitud()))
    ficheroLOG.write(linea)
    ficheroLOG.write('\n')    


def main(): 
    nombreFichero = datetime.now().strftime("%Y-%m-%d")+"_eventosLOG.json"
    ficheroLOG=open(nombreFichero,'w')
    
    # Generar eventos LOG durante 1/2min
    inicioGenerador = datetime.now()
    finGenerador = datetime.now()+timedelta(seconds=0.5)  
    while inicioGenerador < finGenerador:
        generar_lineas_LOG(ficheroLOG)
        inicioGenerador = datetime.now()
    ficheroLOG.close()
    print "Fichero ", nombreFichero," se han generado ", len(open(nombreFichero).readlines()), " eventos."

  
if __name__ == "__main__":
    main()
