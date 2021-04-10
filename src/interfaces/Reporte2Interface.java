package interfaces;

import java.util.ArrayList;

import modelos.ReporteComprobante;

public interface Reporte2Interface {

	public ArrayList<ReporteComprobante> listado(String tipo);

	public ArrayList<ReporteComprobante> listado();

}
