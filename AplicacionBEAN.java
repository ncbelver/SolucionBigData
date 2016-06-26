 /* AplicacionBEAN.java: contiene los métodos get y set del objeto aplicacion

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

public class AplicacionBEAN {
	private int ID;
        private String codigo;
	private String nombre;
	private String descripcion;
        private String IPhost;
	private String IPlog;
        private int idTipo;   
        private int idIdioma;
        private int idUser;
        private int idDepto;
        private int idArea;
        private String idCiudad;
        private String idPais;
        private String fechaAlta;
	private String fechaBaja;        
	
	public int getID() {
		return ID;
	}
        public String getCodigo() {
		return codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
        public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	 public String getIPhost() {
        return IPhost;
        }

        public void setIPhost(String IPhost) {
            this.IPhost = IPhost;
        }

        public String getIPlog() {
            return IPlog;
        }

        public void setIPlog(String IPlog) {
            this.IPlog = IPlog;
        }

        public int getIdTipo() {
            return idTipo;
        }

        public void setIdTipo(int idTipo) {
            this.idTipo = idTipo;
        }

        public int getIdIdioma() {
            return idIdioma;
        }

        public void setIdIdioma(int idIdioma) {
            this.idIdioma = idIdioma;
        }

        public int getIdUser() {
            return idUser;
        }

        public void setIdUser(int idUser) {
            this.idUser = idUser;
        }

        public int getIdDepto() {
            return idDepto;
        }

        public void setIdDepto(int idDepto) {
            this.idDepto = idDepto;
        }

        public int getIdArea() {
            return idArea;
        }

        public void setIdArea(int idArea) {
            this.idArea = idArea;
        }

        public String getIdCiudad() {
            return idCiudad;
        }

        public void setIdCiudad(String idCiudad) {
            this.idCiudad = idCiudad;
        }

        public String getIdPais() {
            return idPais;
        }

        public void setIdPais(String idPais) {
            this.idPais = idPais;
        }

        public String getFechaAlta() {
            return fechaAlta;
        }

        public void setFechaAlta(String fechaAlta) {
            this.fechaAlta = fechaAlta;
        }

        public String getFechaBaja() {
            return fechaBaja;
        }

        public void setFechaBaja(String fechaBaja) {
            this.fechaBaja = fechaBaja;
        }
}