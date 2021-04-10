package modelos;

public class Transportista {
	private int cod_trans, usu_creador_trans;
	private String nom_trans, ape_trans, direc_trans, telf_trans, ruc_trans, num_lic;

	public int getCod_trans() {
		return cod_trans;
	}

	public void setCod_trans(int cod_trans) {
		this.cod_trans = cod_trans;
	}

	public int getUsu_creador_trans() {
		return usu_creador_trans;
	}

	public void setUsu_creador_trans(int usu_creador_trans) {
		this.usu_creador_trans = usu_creador_trans;
	}

	public String getNom_trans() {
		return nom_trans;
	}

	public void setNom_trans(String nom_trans) {
		this.nom_trans = nom_trans;
	}

	public String getApe_trans() {
		return ape_trans;
	}

	public void setApe_trans(String ape_trans) {
		this.ape_trans = ape_trans;
	}

	public String getDirec_trans() {
		return direc_trans;
	}

	public void setDirec_trans(String direc_trans) {
		this.direc_trans = direc_trans;
	}

	public String getTelf_trans() {
		return telf_trans;
	}

	public void setTelf_trans(String telf_trans) {
		this.telf_trans = telf_trans;
	}

	public String getRuc_trans() {
		return ruc_trans;
	}

	public void setRuc_trans(String ruc_trans) {
		this.ruc_trans = ruc_trans;
	}

	public String getNum_lic() {
		return num_lic;
	}

	public void setNum_lic(String num_lic) {
		this.num_lic = num_lic;
	}

	public String codigochar() {
		int codigo = cod_trans;
		if (codigo < 10)
			return "TR000" + codigo;
		else if (codigo < 100)
			return "TR00" + codigo;
		else if (codigo < 1000)
			return "TR0" + codigo;
		else
			return "TR" + codigo;
	}

	public Transportista(int cod_trans, int usu_creador_trans, String nom_trans, String ape_trans, String direc_trans,
			String telf_trans, String ruc_trans, String num_lic) {
		this.cod_trans = cod_trans;
		this.usu_creador_trans = usu_creador_trans;
		this.nom_trans = nom_trans;
		this.ape_trans = ape_trans;
		this.direc_trans = direc_trans;
		this.telf_trans = telf_trans;
		this.ruc_trans = ruc_trans;
		this.num_lic = num_lic;
	}

	public Transportista() {
	}

}
