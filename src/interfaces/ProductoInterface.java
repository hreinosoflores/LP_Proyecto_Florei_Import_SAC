package interfaces;

import java.util.ArrayList;

import modelos.Producto;

public interface ProductoInterface {
	public ArrayList<Producto> listado();

	public int registrar(Producto p);

	public int eliminar(Producto p);

	public int actualizar(Producto p);
}
