package interfaces;

import java.util.ArrayList;

import modelos.Cliente;

public interface ClienteInterface {
	public int registrar(Cliente c);

	public ArrayList<Cliente> listado();

	public int eliminar(Cliente c);

	public int actualizar(Cliente c);
}
