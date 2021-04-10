package modelos;

public class Empresa {
	private int cod_emp, cod_cli;
	private String raz_soc_emp, direc_emp, ruc_emp, email_emp, telefono;

	public int getCod_emp() {
		return cod_emp;
	}

	public void setCod_emp(int cod_emp) {
		this.cod_emp = cod_emp;
	}

	public int getCod_cli() {
		return cod_cli;
	}

	public void setCod_cli(int cod_cli) {
		this.cod_cli = cod_cli;
	}

	public String getRaz_soc_emp() {
		return raz_soc_emp;
	}

	public void setRaz_soc_emp(String raz_soc_emp) {
		this.raz_soc_emp = raz_soc_emp;
	}

	public String getDirec_emp() {
		return direc_emp;
	}

	public void setDirec_emp(String direc_emp) {
		this.direc_emp = direc_emp;
	}

	public String getRuc_emp() {
		return ruc_emp;
	}

	public void setRuc_emp(String ruc_emp) {
		this.ruc_emp = ruc_emp;
	}

	public String getEmail_emp() {
		return email_emp;
	}

	public void setEmail_emp(String email_emp) {
		this.email_emp = email_emp;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String codigochar() {
		int codigo = cod_emp;
		if (codigo < 10)
			return "EMP000" + codigo;
		else if (codigo < 100)
			return "EMP00" + codigo;
		else if (codigo < 1000)
			return "EMP0" + codigo;
		else
			return "EMP" + codigo;
	}

}
