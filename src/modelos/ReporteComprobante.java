package modelos;

import java.util.Date;

public class ReporteComprobante {

	private int codigo;
	private String tipo;
	private Date feccomp;
	private String fecharegistro;
	private String creador;
	private String cliente;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFeccomp() {
		return feccomp;
	}

	public void setFeccomp(Date feccomp) {
		this.feccomp = feccomp;
	}

	public String getFecharegistro() {
		return fecharegistro;
	}

	public void setFecharegistro(String fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public String getCreador() {
		return creador;
	}

	public void setCreador(String creador) {
		this.creador = creador;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

}
