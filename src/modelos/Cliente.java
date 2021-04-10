package modelos;

public class Cliente {
	private int cod_cli, usu_creador_cli;
	private String nom_cli, ape_cli, tip_doc, num_doc, ruc_cli, direc_cli, telef_cli, email_cli;

	public int getCod_cli() {
		return cod_cli;
	}

	public void setCod_cli(int cod_cli) {
		this.cod_cli = cod_cli;
	}

	public int getUsu_creador_cli() {
		return usu_creador_cli;
	}

	public void setUsu_creador_cli(int usu_creador_cli) {
		this.usu_creador_cli = usu_creador_cli;
	}

	public String getNom_cli() {
		return nom_cli;
	}

	public void setNom_cli(String nom_cli) {
		this.nom_cli = nom_cli;
	}

	public String getApe_cli() {
		return ape_cli;
	}

	public void setApe_cli(String ape_cli) {
		this.ape_cli = ape_cli;
	}

	public String getTip_doc() {
		return tip_doc;
	}

	public void setTip_doc(String tip_doc) {
		this.tip_doc = tip_doc;
	}

	public String getNum_doc() {
		return num_doc;
	}

	public void setNum_doc(String num_doc) {
		this.num_doc = num_doc;
	}

	public String getRuc_cli() {
		return ruc_cli;
	}

	public void setRuc_cli(String ruc_cli) {
		this.ruc_cli = ruc_cli;
	}

	public String getDirec_cli() {
		return direc_cli;
	}

	public void setDirec_cli(String direc_cli) {
		this.direc_cli = direc_cli;
	}

	public String getTelef_cli() {
		return telef_cli;
	}

	public void setTelef_cli(String telef_cli) {
		this.telef_cli = telef_cli;
	}

	public String getEmail_cli() {
		return email_cli;
	}

	public void setEmail_cli(String email_cli) {
		this.email_cli = email_cli;
	}

	public String codigochar() {
		int codigo = cod_cli;
		if (codigo < 10)
			return "CLI000" + codigo;
		else if (codigo < 100)
			return "CLI00" + codigo;
		else if (codigo < 1000)
			return "CLI0" + codigo;
		else
			return "CLI" + codigo;
	}

	public Cliente(int cod_cli, int usu_creador_cli, String nom_cli, String ape_cli, String tip_doc, String num_doc,
			String ruc_cli, String direc_cli, String telef_cli, String email_cli) {
		this.cod_cli = cod_cli;
		this.usu_creador_cli = usu_creador_cli;
		this.nom_cli = nom_cli;
		this.ape_cli = ape_cli;
		this.tip_doc = tip_doc;
		this.num_doc = num_doc;
		this.ruc_cli = ruc_cli;
		this.direc_cli = direc_cli;
		this.telef_cli = telef_cli;
		this.email_cli = email_cli;
	}

	public Cliente() {
	}

}
