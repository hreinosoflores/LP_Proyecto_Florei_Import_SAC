package vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import mantenimientos.GestionCliente;
import mantenimientos.GestionEmpresa;
import mantenimientos.GestionGuia;
import mantenimientos.GestionTransportista;
import modelos.Cliente;
import modelos.Empresa;

import modelos.Guia;
import modelos.Transportista;

public class FrmGuia extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumero;
	private JTextField txtPartida;
	private JTextField txtLlegada;
	private JLabel lblCodigoEmpresa;
	private JLabel lblFechaTrasla;
	private JLabel lblNumero;
	private JLabel lblDireccionPartida;
	private JLabel lblDireccionLlegada;
	private JLabel lblHoraTrasla;
	private JLabel lblMotivo;
	private JLabel lblCliente;
	private JLabel lblCodigoTransportista;
	private JScrollPane scrollPane;
	private DefaultTableModel modelo;
	private JTable tblGuia;
	private JDateChooser txtFechaTrasla;
	private JComboBox<String> cboMotivo;
	private JComboBox<String> cboHoras;
	private JComboBox<String> cboMinutos;
	private JComboBox<String> cboCliente;
	private JComboBox<String> cboTransportista;
	private JComboBox<String> cboEmpresa;
	private JButton btnLimpiar;
	private JButton btnRegistrar;
	private JButton btnModificar;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmGuia frame = new FrmGuia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmGuia() {
		setFrameIcon(new ImageIcon(FrmGuia.class.getResource("/iconos/comprobante.png")));
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Guia");
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNumero = new JLabel("N\u00FAmero");
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNumero.setBounds(10, 16, 105, 14);
		contentPane.add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setEditable(false);
		txtNumero.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtNumero.setBounds(165, 10, 101, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);

		lblDireccionPartida = new JLabel("Direcci\u00F3n partida");
		lblDireccionPartida.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDireccionPartida.setBounds(10, 78, 129, 14);
		contentPane.add(lblDireccionPartida);

		txtPartida = new JTextField();
		txtPartida.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtPartida.setBounds(165, 72, 565, 20);
		contentPane.add(txtPartida);
		txtPartida.setColumns(10);

		lblMotivo = new JLabel("Motivo traslado");
		lblMotivo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMotivo.setBounds(389, 181, 129, 14);
		contentPane.add(lblMotivo);

		lblCliente = new JLabel("Cliente destinatario");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCliente.setBounds(389, 149, 145, 14);
		contentPane.add(lblCliente);

		lblCodigoTransportista = new JLabel("Transportista");
		lblCodigoTransportista.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigoTransportista.setBounds(10, 149, 159, 14);
		contentPane.add(lblCodigoTransportista);

		lblCodigoEmpresa = new JLabel("Empresa remitente");
		lblCodigoEmpresa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigoEmpresa.setBounds(10, 181, 159, 14);
		contentPane.add(lblCodigoEmpresa);

		lblFechaTrasla = new JLabel("Fecha traslado");
		lblFechaTrasla.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaTrasla.setBounds(10, 41, 113, 14);
		contentPane.add(lblFechaTrasla);

		lblDireccionLlegada = new JLabel("Direccion llegada");
		lblDireccionLlegada.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDireccionLlegada.setBounds(10, 109, 119, 14);
		contentPane.add(lblDireccionLlegada);

		txtLlegada = new JTextField();
		txtLlegada.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtLlegada.setBounds(165, 103, 565, 20);
		contentPane.add(txtLlegada);
		txtLlegada.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 296, 964, 235);
		contentPane.add(scrollPane);

		tblGuia = new JTable();
		tblGuia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rellenar();
			}
		});
		scrollPane.setViewportView(tblGuia);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
				limpiar();
				listar_guias();
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrar.setIcon(new ImageIcon(FrmGuia.class.getResource("/iconos/registrar.png")));
		btnRegistrar.setBounds(10, 247, 145, 38);
		contentPane.add(btnRegistrar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
				limpiar();
				listar_guias();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModificar.setIcon(new ImageIcon(FrmGuia.class.getResource("/iconos/modificar.png")));
		btnModificar.setBounds(165, 247, 145, 38);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminardatos();
				limpiar();
				listar_guias();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminar.setIcon(new ImageIcon(FrmGuia.class.getResource("/iconos/eliminar.png")));
		btnEliminar.setBounds(320, 247, 145, 38);
		contentPane.add(btnEliminar);

		modelo = new DefaultTableModel();
		modelo.addColumn("Número");
		modelo.addColumn("Fecha Guía");
		modelo.addColumn("Fecha Traslado");
		modelo.addColumn("Dirección Partida");
		modelo.addColumn("Dirección Llegada");
		modelo.addColumn("Motivo Traslado");
		modelo.addColumn("Cliente");
		modelo.addColumn("Transportista");
		modelo.addColumn("Empresa");
		modelo.addColumn("Creador Guía");
		tblGuia.setModel(modelo);

		txtFechaTrasla = new JDateChooser();
		txtFechaTrasla.setBounds(165, 41, 181, 20);
		txtFechaTrasla.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(txtFechaTrasla);

		cboMotivo = new JComboBox<String>();
		cboMotivo.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Seleccione", "Venta al por mayor", "Venta al por menor" }));
		cboMotivo.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboMotivo.setBounds(539, 180, 191, 20);
		contentPane.add(cboMotivo);

		lblHoraTrasla = new JLabel("Hora traslado");
		lblHoraTrasla.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHoraTrasla.setBounds(389, 41, 129, 14);
		contentPane.add(lblHoraTrasla);

		cboHoras = new JComboBox<String>();
		cboHoras.setModel(
				new DefaultComboBoxModel<String>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08",
						"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
		cboHoras.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboHoras.setBounds(501, 40, 93, 20);
		contentPane.add(cboHoras);

		cboMinutos = new JComboBox<String>();
		cboMinutos.setModel(new DefaultComboBoxModel<String>(
				new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
						"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
						"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
						"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
		cboMinutos.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboMinutos.setBounds(637, 40, 93, 20);
		contentPane.add(cboMinutos);

		cboCliente = new JComboBox<String>();
		cboCliente.setModel(new DefaultComboBoxModel<String>(listar_clientes().toArray(new String[0])));
		cboCliente.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboCliente.setBounds(539, 148, 191, 20);
		contentPane.add(cboCliente);

		cboTransportista = new JComboBox<String>();
		cboTransportista.setModel(new DefaultComboBoxModel<String>(listar_transportistas().toArray(new String[0])));
		cboTransportista.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboTransportista.setBounds(165, 147, 181, 20);
		contentPane.add(cboTransportista);

		cboEmpresa = new JComboBox<String>();
		cboEmpresa.setModel(new DefaultComboBoxModel<String>(listar_empresas().toArray(new String[0])));
		cboEmpresa.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboEmpresa.setBounds(165, 179, 181, 20);
		contentPane.add(cboEmpresa);

		btnLimpiar = new JButton("LIMPIAR DATOS");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setBounds(539, 248, 191, 39);
		contentPane.add(btnLimpiar);

		ajustarAnchoColumnas();
		listar_guias();
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	private void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblGuia.getColumnModel();
		tcm.getColumn(1).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(8));
	}

	List<String> listar_clientes() {
		List<String> cboItems = new ArrayList<String>();
		cboItems.add("Seleccione");
		GestionCliente gc = new GestionCliente();
		List<Cliente> lista = gc.listado();
		for (Cliente cliente : lista) {
			cboItems.add(cliente.getNom_cli() + " " + cliente.getApe_cli());
		}
		return cboItems;
	}

	List<String> listar_transportistas() {
		List<String> cboItems = new ArrayList<String>();
		cboItems.add("Seleccione");
		GestionTransportista gt = new GestionTransportista();
		List<Transportista> lista = gt.listado();
		for (Transportista transportista : lista) {
			cboItems.add(transportista.getNom_trans() + " " + transportista.getApe_trans());
		}
		return cboItems;
	}

	List<String> listar_empresas() {
		List<String> cboItems = new ArrayList<String>();
		cboItems.add("Seleccione");
		GestionEmpresa ge = new GestionEmpresa();
		List<Empresa> lista = ge.listado();
		for (Empresa empresa : lista) {
			cboItems.add(empresa.getRaz_soc_emp());
		}
		return cboItems;
	}

	void listar_guias() {
		GestionGuia gg = new GestionGuia();
		ArrayList<Guia> lista = gg.listado();
		modelo.setRowCount(0);
		for (Guia g : lista) {
			Object[] fila = { g.getNum_gui(), g.getFec_guia(), g.getFec_trasl(), g.getDirec_part(), g.getDirec_lleg(),
					motivo_formateado(g.getMotiv_trasl()), g.getCod_cli(), g.getCod_trans(), g.getCod_emp(),
					g.getUsu_creador_gui()
			};
			modelo.addRow(fila);
		}

	}

	String motivo_formateado(String mot) {
		if (mot.equals("vmayor"))
			return "Venta al por mayor";
		else
			return "Venta al por menor";
	}

	void registrar() {
		int cod_cli, cod_trans, cod_emp, usu_creador_gui;
		String fec_trasl, direc_part, direc_lleg, motiv_trasl;

		fec_trasl = leerFecTrasl();
		direc_part = leerDireccionPartida();
		direc_lleg = leerDireccionLlegada();
		motiv_trasl = leerModo();
		cod_cli = leerCodigoCli();
		cod_trans = leerCodigoTra();
		cod_emp = leerCodigoEmp();
		usu_creador_gui = 1;// obtener de la sesion tras el logueo

		Guia g = new Guia();
		g.setFec_trasl(Timestamp.valueOf(fec_trasl));
		g.setDirec_part(direc_part);
		g.setDirec_lleg(direc_lleg);
		g.setMotiv_trasl(motiv_trasl);
		g.setCod_cli(cod_cli);
		g.setCod_trans(cod_trans);
		g.setCod_emp(cod_emp);
		g.setUsu_creador_gui(usu_creador_gui);

		GestionGuia gu = new GestionGuia();
		int ok = gu.registrar(g);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso4);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso1);
		}
	}

	void modificar() {
		int num_gui, cod_cli, cod_trans, cod_emp;
		String fec_trasl, direc_part, direc_lleg, motiv_trasl;

		num_gui = leerNumGuia();
		fec_trasl = leerFecTrasl();
		direc_part = leerDireccionPartida();
		direc_lleg = leerDireccionLlegada();
		motiv_trasl = leerModo();
		cod_cli = leerCodigoCli();
		cod_trans = leerCodigoTra();
		cod_emp = leerCodigoEmp();

		Guia g = new Guia();
		g.setNum_gui(num_gui);
		g.setFec_trasl(Timestamp.valueOf(fec_trasl));
		g.setDirec_part(direc_part);
		g.setDirec_lleg(direc_lleg);
		g.setMotiv_trasl(motiv_trasl);
		g.setCod_cli(cod_cli);
		g.setCod_trans(cod_trans);
		g.setCod_emp(cod_emp);

		GestionGuia gg = new GestionGuia();
		int ok = gg.actualizar(g);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso5);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso2);
		}

	}

	void eliminardatos() {
		int num_gui = leerNumGuia();
		Guia g = new Guia();
		g.setNum_gui(num_gui);
		GestionGuia gu = new GestionGuia();
		int ok = gu.eliminar(g);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso6);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso3);
		}

	}

	int leerNumGuia() {
		int cod = 0;
		try {
			cod = Integer.parseInt(txtNumero.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "");
		}
		return cod;

	}

	String leerFecTrasl() {
		String fecTrasl = null;
		try {
			String fec, hora, minuto = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			fec = sdf.format(txtFechaTrasla.getDate());
			hora = cboHoras.getSelectedItem().toString();
			minuto = cboMinutos.getSelectedItem().toString();
			fecTrasl = String.format("%s %s:%s:00", fec, hora, minuto);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingrese la fecha de traslado");
		}
		return fecTrasl;
	}

	String leerDireccionPartida() {
		String p = null;
		p = txtPartida.getText();
		return p;
	}

	String leerDireccionLlegada() {
		String l = null;
		l = txtLlegada.getText();
		return l;
	}

	String leerModo() {
		int tp = 0;
		tp = cboMotivo.getSelectedIndex();
		switch (tp) {
		case 1:
			return "Vmenor";
		case 2:
			return "Vmayor";
		default:
			return "";
		}
	}

	int leerCodigoCli() {
		int c = 0;
		c = cboCliente.getSelectedIndex();
		return c;
	}

	int leerCodigoTra() {
		int c = 0;
		c = cboTransportista.getSelectedIndex();
		return c;
	}

	int leerCodigoEmp() {
		int c = 0;
		c = cboEmpresa.getSelectedIndex();
		return c;
	}

	void rellenar() {
		try {

			int fila = tblGuia.getSelectedRow();
			Date fechaTrasla = new SimpleDateFormat("yyyy-MM-dd")
					.parse(tblGuia.getValueAt(fila, 2).toString().substring(0, 10));
			txtNumero.setText(tblGuia.getValueAt(fila, 0).toString());
			txtFechaTrasla.setDate(fechaTrasla);
			txtPartida.setText(tblGuia.getValueAt(fila, 3).toString());
			txtLlegada.setText(tblGuia.getValueAt(fila, 4).toString());
			cboHoras.setSelectedItem(tblGuia.getValueAt(fila, 2).toString().substring(11, 13));
			cboMinutos.setSelectedItem(tblGuia.getValueAt(fila, 2).toString().substring(14, 16));
			cboMotivo.setSelectedItem(tblGuia.getValueAt(fila, 5).toString());
			cboCliente.setSelectedIndex(Integer.parseInt(tblGuia.getValueAt(fila, 6).toString()));
			cboTransportista.setSelectedIndex(Integer.parseInt(tblGuia.getValueAt(fila, 7).toString()));
			cboEmpresa.setSelectedIndex(Integer.parseInt(tblGuia.getValueAt(fila, 8).toString()));
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Error al setear: " + e.getMessage());
			e.printStackTrace();
		}

	}

	void limpiar() {
		Date hoy = new Date();
		txtNumero.setText("");
		txtFechaTrasla.setDate(hoy);
		cboHoras.setSelectedIndex(0);
		cboMinutos.setSelectedIndex(0);
		cboMotivo.setSelectedIndex(0);
		txtPartida.setText("");
		txtLlegada.setText("");
		cboCliente.setSelectedIndex(0);
		cboTransportista.setSelectedIndex(0);
		cboEmpresa.setSelectedIndex(0);
		txtNumero.requestFocus();
	}
}
