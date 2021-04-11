package modelos;

public class Usuario {
	private int codus;
	private String nom_usua, ape_usua, usua, clav;

	public int getCodus() {
		return codus;
	}

	public void setCodus(int codus) {
		this.codus = codus;
	}

	public String getNom_usua() {
		return nom_usua;
	}

	public void setNom_usua(String nom_usua) {
		this.nom_usua = nom_usua;
	}

	public String getApe_usua() {
		return ape_usua;
	}

	public void setApe_usua(String ape_usua) {
		this.ape_usua = ape_usua;
	}

	public String getUsua() {
		return usua;
	}

	public void setUsua(String usua) {
		this.usua = usua;
	}

	public String getClav() {
		return clav;
	}

	public void setClav(String clav) {
		this.clav = clav;
	}

	public Usuario(int codus, String nom_usua, String ape_usua, String usua, String clav) {
		this.codus = codus;
		this.nom_usua = nom_usua;
		this.ape_usua = ape_usua;
		this.usua = usua;
		this.clav = clav;
	}

	public Usuario() {
	}

}
