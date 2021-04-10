package modelos;

public class Transportista {
	private int cod_trans;
	private String nom_trans, ape_trans, direc_trans, telf_trans, ruc_trans, num_lic;

	// Metodos Set y Get
	public int getCod_trans() {
		return cod_trans;
	}

	public void setCod_trans(int cod_trans) {
		this.cod_trans = cod_trans;
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

}
