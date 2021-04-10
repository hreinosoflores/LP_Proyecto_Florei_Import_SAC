package interfaces;

import modelos.Usuario;

public interface UsuarioInterface {
	public Usuario validaAcceso(String usuario, String clave);
}
