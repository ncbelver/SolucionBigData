/*  validacionesFrm.js: validaciones realizadas en las jsp : AltaAplicacion (validarAA); ModificarAplicacion (validarMA); AltaVersion (validarAV); ModificarVersion (validarMV)

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
 
/* comprueba el valor de los campos FECHA . rango años 2012 & 2018*/
function existeFecha(fecha){
      var fechaf = fecha.split("/");
      var day = fechaf[0];  if (day > 31) return false;
      var month = fechaf[1];  if (month > 12) return false;     
      var year = fechaf[2];  if ((year < 2012)||(year > 2019)) return false;      
      var date = new Date(year,month,'0');
      if((day-0)>(date.getDate()-0))  return false;       
      return true;
}
 /*
 * Validar datos de entrada del formulario AltaAplicacion.jsp
 */
 function validarAA() {   
 var todoCorrecto = true;
    var formulario = document.AltaAplicacion;
    for (var i=0; i<formulario.length; i++) { 
        
        if(formulario[i].type ==='text') {
            if (formulario[i].value == null || formulario[i].value.length === 0 || /^\s*$/.test(formulario[i].value)){
                alert (formulario[i].name+ ' no puede estar vacío o contener sólo espacios en blanco.')    ;  
                todoCorrecto=false;
                formulario[i].name.focus();
            }
        }
        if (formulario[i].name === 'IPhost' || formulario[i].name === 'IPlog'){           
                var re = /^(([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]).){3}([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$/ ; //formato IP 
                var formatoCorrecto = re.exec(formulario[i].value);                 
                if (!formatoCorrecto)  {
                    alert (formulario[i].name+ ' no posee el formato IP correcto [nnnn.nnnn.nnnn.nnnn]')    ;  
                    todoCorrecto = false;
                    formulario[i].name.focus();
                }
        }             
    }
    if (todoCorrecto === true) {formulario.submit();}
}

/*****************************************************************
 * Validar datos de entrada del formulario AltaVersion.jsp
 *****************************************************************/
 function validarAV() {
    var todoCorrecto = true;
    var formulario = document.AltaVersion;
    for (var i=0; i<formulario.length; i++) {          
        if(formulario[i].type ==='text') {
            if (formulario[i].value === null || formulario[i].value.length === 0 || /^\s*$/.test(formulario[i].value)){
                alert (formulario[i].name+ ' no puede estar vacío o contener sólo espacios en blanco.')    ;  
                todoCorrecto=false;
                formulario[i].name.focus();
            }
            
            if (formulario[i].name !== 'nombre' && formulario[i].name !== 'descripcion' && formulario[i].name !== 'fechaIni' && formulario[i].name !== 'fechaImplantacion'){
               if (isNaN(formulario[i].value) ) {
                 alert (formulario[i].name+ ' es un campo numérico, no acepta caracteres.') ;      
                 todoCorrecto=false;
                 formulario[i].name.focus() ;	
               }
            }              
             
            if (formulario[i].name === 'fechaIni' || formulario[i].name === 'fechaImplantacion'){
                var formatoFecha = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;                
                if (!(formulario[i].value.match(formatoFecha))) {
                      alert (formulario[i].name+ ' es un campo fecha, dd/mm/aaaa');       
                      todoCorrecto=false;
                      formulario[i].name.focus();
                }
                if  (!existeFecha(formulario[i].value)){
                     alert (formulario[i].name+ ' la fecha no es correcta')  ;     
                      todoCorrecto=false;
                      formulario[i].name.focus();
                }
            }
        }   
    }
    if (todoCorrecto === true) {formulario.submit();}
}
/*****************************************************************
 * Validar datos de entrada del formulario ModificarVersion.jsp
 *****************************************************************/
 function validarMV() {     
    var todoCorrecto = true;
    var formulario = document.ModificarVersion;
    for (var i=0; i<formulario.length; i++) {          
        if(formulario[i].type ==='text') {
            if (formulario[i].value === null || formulario[i].value.length === 0 || /^\s*$/.test(formulario[i].value)){
                alert (formulario[i].name+ ' no puede estar vacío o contener sólo espacios en blanco.')    ;  
                todoCorrecto=false;
                formulario[i].name.focus();
            }
            
            if (formulario[i].name != 'descripcion' && formulario[i].name != 'fechaIni' && formulario[i].name != 'fechaImplantacion'){
               if (isNaN(formulario[i].value) ) {
                 alert (formulario[i].name+ ' es un campo numérico, no acepta caracteres '+formulario[i].name) ;      
                 todoCorrecto=false;
                 formulario[i].name.focus() ;	
               }
            }              
             
            if (formulario[i].name === 'fechaIni' || formulario[i].name === 'fechaImplantacion'){
                var formatoFecha = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;                
                if (!(formulario[i].value.match(formatoFecha))) {
                      alert (formulario[i].name+ ' es un campo fecha, dd/mm/aaaa');       
                      todoCorrecto=false;
                      formulario[i].name.focus();
                }
                if  (!existeFecha(formulario[i].value)){
                     alert (formulario[i].name+ ' la fecha no es correcta')  ;     
                      todoCorrecto=false;
                      formulario[i].name.focus();
                }
            }
        }   
    }
    if (todoCorrecto === true) {formulario.submit();}
}

/*****************************************************************
 * Validar datos de entrada del formulario ModificarAplicacion.jsp
 *****************************************************************/
 function validarMA() {   
 var todoCorrecto = true;
    var formulario = document.editarFormulario;
    for (var i=0; i<formulario.length; i++) { 
        
        if(formulario[i].type ==='text') {
            if (formulario[i].value == null || formulario[i].value.length === 0 || /^\s*$/.test(formulario[i].value)){
                alert (formulario[i].name+ ' no puede estar vacío o contener sólo espacios en blanco.')    ;  
                todoCorrecto=false;
                formulario[i].name.focus();
            }
        }
        if (formulario[i].name === 'IPhost' || formulario[i].name === 'IPlog'){           
                var re = /^(([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]).){3}([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$/ ; //formato IP 
                var formatoCorrecto = re.exec(formulario[i].value);                 
                if (!formatoCorrecto)  {
                    alert (formulario[i].name+ ' no posee el formato IP correcto [nnnn.nnnn.nnnn.nnnn]')    ;  
                    todoCorrecto = false;
                    formulario[i].name.focus();
                }
        }             
    }
    if (todoCorrecto === true) {formulario.submit();}
}