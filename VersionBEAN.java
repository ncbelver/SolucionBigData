 /* AplicacionBEAN.java: contiene los métodos get y set del objeto version

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
package DAO;

public class VersionBEAN {
        private int idAplic;
        private int idVersion;
        private String descripcion;  
        private int numIniReq;
        private int numFinReq;
        private int numUsu;
        private int idRol;

    public int getNumUsu() {
        return numUsu;
    }

    public void setNumUsu(int numUsu) {
        this.numUsu = numUsu;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
        private String fechaInicial;
        private String fechaImplantacion;
        private int defectosFuncionales;
        private int defectosDesarrollo;
        private int defectosArquitectura;

    public int getIdAplic() {
        return idAplic;
    }
     public String getDescripcion() {
        return descripcion;
    }

    public int getIdVersion() {
        return idVersion;
    }

    public int getNumIniReq() {
        return numIniReq;
    }

    public int getNumFinReq() {
        return numFinReq;
    }
 
    public String getFechaInicial() {
        return fechaInicial;
    }

    public String getFechaImplantacion() {
        return fechaImplantacion;
    }

    public int getDefectosFuncionales() {
        return defectosFuncionales;
    }

    public int getDefectosDesarrollo() {
        return defectosDesarrollo;
    }

    public int getDefectosArquitectura() {
        return defectosArquitectura;
    }

    public void setIdAplic(int idAplic) {
        this.idAplic = idAplic;
    }

    public void setIdVersion(int idVersion) {
        this.idVersion = idVersion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNumIniReq(int numIniReq) {
        this.numIniReq = numIniReq;
    }

    public void setNumFinReq(int numFinReq) {
        this.numFinReq = numFinReq;
    }

  public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public void setFechaImplantacion(String fechaImplantacion) {
        this.fechaImplantacion = fechaImplantacion;
    }

    public void setDefectosFuncionales(int defectosFuncionales) {
        this.defectosFuncionales = defectosFuncionales;
    }

    public void setDefectosDesarrollo(int defectosDesarrollo) {
        this.defectosDesarrollo = defectosDesarrollo;
    }

    public void setDefectosArquitectura(int defectosArquitectura) {
        this.defectosArquitectura = defectosArquitectura;
    }
 
           
}
