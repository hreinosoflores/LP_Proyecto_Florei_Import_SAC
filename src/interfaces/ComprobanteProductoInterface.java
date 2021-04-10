package interfaces;

import java.util.ArrayList;

import modelos.ComprobanteProducto;

public interface ComprobanteProductoInterface {
	public int registrar(ComprobanteProducto cpr);

	public ArrayList<ComprobanteProducto> listado();

	public int eliminar(ComprobanteProducto cpr);

	public int actualizar(ComprobanteProducto cpr);

}
