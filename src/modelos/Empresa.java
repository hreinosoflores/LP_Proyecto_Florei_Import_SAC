package modelos;

public class Empresa {
	private int cod_emp, usu_creado_emp;
	private String raz_soc_emp, direc_emp, ruc_emp, email_emp, telefono;

	public int getCod_emp() {
		return cod_emp;
	}

	public void setCod_emp(int cod_emp) {
		this.cod_emp = cod_emp;
	}

	public int getUsu_creado_emp() {
		return usu_creado_emp;
	}

	public void setUsu_creado_emp(int usu_creado_emp) {
		this.usu_creado_emp = usu_creado_emp;
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

	public Empresa(int cod_emp, int usu_creado_emp, String raz_soc_emp, String direc_emp, String ruc_emp,
			String email_emp, String telefono) {
		this.cod_emp = cod_emp;
		this.usu_creado_emp = usu_creado_emp;
		this.raz_soc_emp = raz_soc_emp;
		this.direc_emp = direc_emp;
		this.ruc_emp = ruc_emp;
		this.email_emp = email_emp;
		this.telefono = telefono;
	}

	public Empresa() {
	}

}
