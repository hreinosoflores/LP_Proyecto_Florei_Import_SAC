package interfaces;

import java.util.ArrayList;

import modelos.ComprobantePago;

public interface ComprobantePagoInterface {

	public int registrar(ComprobantePago cp);

	public ArrayList<ComprobantePago> listado();

	public int eliminar(ComprobantePago cp);

	public int actualizar(ComprobantePago cp);

	/*
	 * public ArrayList<ComprobantePago> reportecomp(String tipo, String
	 * fec_ini, String fec_fin);
	 */

}
