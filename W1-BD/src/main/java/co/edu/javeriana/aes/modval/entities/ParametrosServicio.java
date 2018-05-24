package co.edu.javeriana.aes.modval.entities;

import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ParametrosServicio {
	
	String nombre;
	String ip;
	String puerto;
	String ruta;
	String accion;	
	String metodo;
	String idfactura;
	String valor;
	String tipo;
	
	/*
	@JsonCreator
	ParametrosServicio(@JsonProperty("nombre") String nombre, @JsonProperty("ip") String ip,@JsonProperty("puerto") String puerto,
			@JsonProperty("ruta") String ruta,@JsonProperty("accion") String accion,@JsonProperty("serviciopagar") String serviciopagar,
			@JsonProperty("metodo") String metodo,@JsonProperty("idfactura") String idfactura,@JsonProperty("idfactura") String valor,
			@JsonProperty("tipo") String tipo) {
        this.nombre = nombre;
        this.ip = ip;
        this.puerto = puerto ;
        this.ruta = ruta;
        this.accion = accion;	
        this.serviciopagar = serviciopagar;
        this.metodo = metodo ;
        this.idfactura = idfactura;
        this.valor = valor;
        this.tipo = tipo;
    }
	*/
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPuerto() {
		return puerto;
	}
	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public String getIdfactura() {
		return idfactura;
	}
	public void setIdfactura(String idfactura) {
		this.idfactura = idfactura;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	


}
