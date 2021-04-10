package modelos;

public class DetalleGuia {

	private int num_gui, cod_prod, cant;

	public int getNum_gui() {
		return num_gui;
	}

	public void setNum_gui(int num_gui) {
		this.num_gui = num_gui;
	}

	public int getCod_prod() {
		return cod_prod;
	}

	public void setCod_prod(int cod_prod) {
		this.cod_prod = cod_prod;
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

	public DetalleGuia(int num_gui, int cod_prod, int cant) {
		this.num_gui = num_gui;
		this.cod_prod = cod_prod;
		this.cant = cant;
	}

	public DetalleGuia() {
	}

}
