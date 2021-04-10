package vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

import mantenimientos.GestionGuia;
import modelos.Guia;

public class FrmGuia extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumero;
	private JTextField txtPartida;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField txtCliente;
	private JLabel lblCdigoTransportista;
	private JTextField txtTransportista;
	private JLabel lblCdigoEmpresa;
	private JTextField txtEmpresa;
	private JLabel lblNewLabel_3;
	private JTextField txtLlegada;
	private JTextField txtPeso;
	private JScrollPane scrollPane;
	private DefaultTableModel modelo;
	private JTable tblGuia;
	private JDateChooser txtFechaGuia;
	private JDateChooser txtFechaTrasla;
	String aviso1 = "Ingrese el valor numerico del codigo. Ejm: N0025 valor numerico->25";
	private JComboBox cboMotivo;

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
		setBounds(100, 100, 804, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("N\u00FAmero");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 16, 105, 14);
		contentPane.add(lblNewLabel);

		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtNumero.setBounds(165, 10, 101, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblFechaGuia = new JLabel("Fecha guia");
		lblFechaGuia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaGuia.setBounds(10, 47, 95, 14);
		contentPane.add(lblFechaGuia);

		JLabel lblDireccinPartida = new JLabel("Direcci\u00F3n partida");
		lblDireccinPartida.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDireccinPartida.setBounds(10, 78, 129, 14);
		contentPane.add(lblDireccinPartida);

		txtPartida = new JTextField();
		txtPartida.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtPartida.setBounds(165, 72, 145, 20);
		contentPane.add(txtPartida);
		txtPartida.setColumns(10);

		lblNewLabel_1 = new JLabel("Motivo traslado");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 109, 129, 14);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("C\u00F3digo cliente");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 140, 129, 14);
		contentPane.add(lblNewLabel_2);

		txtCliente = new JTextField();
		txtCliente.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCliente.setColumns(10);
		txtCliente.setBounds(165, 134, 101, 20);
		contentPane.add(txtCliente);

		lblCdigoTransportista = new JLabel("C\u00F3digo transportista");
		lblCdigoTransportista.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCdigoTransportista.setBounds(10, 177, 159, 14);
		contentPane.add(lblCdigoTransportista);

		txtTransportista = new JTextField();
		txtTransportista.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTransportista.setColumns(10);
		txtTransportista.setBounds(165, 171, 101, 20);
		contentPane.add(txtTransportista);

		lblCdigoEmpresa = new JLabel("C\u00F3digo empresa");
		lblCdigoEmpresa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCdigoEmpresa.setBounds(10, 208, 129, 14);
		contentPane.add(lblCdigoEmpresa);

		txtEmpresa = new JTextField();
		txtEmpresa.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtEmpresa.setColumns(10);
		txtEmpresa.setBounds(165, 202, 101, 20);
		contentPane.add(txtEmpresa);

		lblNewLabel_3 = new JLabel("Fecha traslado");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(349, 47, 113, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Direccion llegada");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(349, 78, 119, 14);
		contentPane.add(lblNewLabel_4);

		txtLlegada = new JTextField();
		txtLlegada.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtLlegada.setBounds(475, 72, 139, 20);
		contentPane.add(txtLlegada);
		txtLlegada.setColumns(10);

		JLabel lblPesoTotal = new JLabel("Peso total");
		lblPesoTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPesoTotal.setBounds(349, 109, 73, 14);
		contentPane.add(lblPesoTotal);

		txtPeso = new JTextField();
		txtPeso.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtPeso.setBounds(475, 103, 110, 20);
		contentPane.add(txtPeso);
		txtPeso.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 296, 768, 235);
		contentPane.add(scrollPane);

		tblGuia = new JTable();
		tblGuia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rellenarformulario();
			}
		});
		scrollPane.setViewportView(tblGuia);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
				listadoClientes();
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrar.setIcon(new ImageIcon(FrmGuia.class.getResource("/iconos/registrar.png")));
		btnRegistrar.setBounds(10, 247, 145, 38);
		contentPane.add(btnRegistrar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
				listadoClientes();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModificar.setIcon(new ImageIcon(FrmGuia.class.getResource("/iconos/modificar.png")));
		btnModificar.setBounds(165, 247, 145, 38);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminardatos();
				listadoClientes();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminar.setIcon(new ImageIcon(FrmGuia.class.getResource("/iconos/eliminar.png")));
		btnEliminar.setBounds(320, 247, 145, 38);
		contentPane.add(btnEliminar);

		txtFechaGuia = new JDateChooser();
		txtFechaGuia.setBounds(165, 41, 95, 20);
		contentPane.add(txtFechaGuia);

		modelo = new DefaultTableModel();
		modelo.addColumn("Número");
		modelo.addColumn("Fecha Guia");
		modelo.addColumn("Fecha Traslado");
		modelo.addColumn("Dir Partida");
		modelo.addColumn("Dir Llegada");
		modelo.addColumn("Motivo Traslado");
		modelo.addColumn("Peso Total");
		modelo.addColumn("Cliente");
		modelo.addColumn("Transportista");
		modelo.addColumn("Empresa");
		tblGuia.setModel(modelo);

		txtFechaTrasla = new JDateChooser();
		txtFechaTrasla.setBounds(472, 41, 95, 20);
		contentPane.add(txtFechaTrasla);

		JButton btnInfo = new JButton("");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Indique si la venta es al por mayor o al por menor",
						"MOTIVO DE TRASLADO", 1);
			}
		});
		btnInfo.setIcon(new ImageIcon(FrmGuia.class.getResource("/iconos/info.png")));
		btnInfo.setBounds(288, 103, 35, 20);
		contentPane.add(btnInfo);

		cboMotivo = new JComboBox();
		cboMotivo.setModel(new DefaultComboBoxModel(new String[] { "Seleccione", "Vmayor", "Vmenor" }));
		cboMotivo.setBounds(165, 103, 101, 20);
		contentPane.add(cboMotivo);

		ajustarAnchoColumnas();
		listadoClientes();
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

	void listadoClientes() {
		GestionGuia gg = new GestionGuia();
		ArrayList<Guia> lista = gg.listado();
		modelo.setRowCount(0);
		for (Guia g : lista) {
			Object[] fila = { g.codigochar(), g.getFec_guia(), g.getFec_trasl(), g.getDirec_part(), g.getDirec_lleg(),
					g.getMotiv_trasl(), g.getPes_tot(), g.getCod_cli(), g.getCod_trans(), g.getCod_emp()

			};
			modelo.addRow(fila);
		}

	}

	void registrar() {
		int cod_cli, cod_trans, cod_emp;
		String fec_guia, fec_trasl, direc_part, direc_lleg, motiv_trasl;
		double pes_tot;

		fec_guia = leerFecGuia();
		fec_trasl = leerFecTrasl();
		direc_part = leerDireccionPartida();
		direc_lleg = leerDireccionLlegada();
		motiv_trasl = leerModo();
		pes_tot = leerPeso();
		cod_cli = leerCodigoCli();
		cod_trans = leerCodigoTra();
		cod_emp = leerCodigoEmp();

		Guia g = new Guia();
		g.setFec_guia(fec_guia);
		g.setFec_trasl(fec_trasl);
		g.setDirec_part(direc_part);
		g.setDirec_lleg(direc_lleg);
		g.setMotiv_trasl(motiv_trasl);
		g.setPes_tot(pes_tot);
		g.setCod_cli(cod_cli);
		g.setCod_trans(cod_trans);
		g.setCod_emp(cod_emp);

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
		String fec_guia, fec_trasl, direc_part, direc_lleg, motiv_trasl;
		double pes_tot;
		num_gui = leerNumGuia();
		fec_guia = leerFecGuia();
		fec_trasl = leerFecTrasl();
		direc_part = leerDireccionPartida();
		direc_lleg = leerDireccionLlegada();
		motiv_trasl = leerModo();
		pes_tot = leerPeso();
		cod_cli = leerCodigoCli();
		cod_trans = leerCodigoTra();
		cod_emp = leerCodigoEmp();

		Guia g = new Guia();
		g.setNum_gui(num_gui);
		g.setFec_guia(fec_guia);
		g.setFec_trasl(fec_trasl);
		g.setDirec_part(direc_part);
		g.setDirec_lleg(direc_lleg);
		g.setMotiv_trasl(motiv_trasl);
		g.setPes_tot(pes_tot);
		g.setCod_cli(cod_cli);
		g.setCod_trans(cod_trans);
		g.setCod_emp(cod_emp);

		if (num_gui >= 1) {
			GestionGuia gg = new GestionGuia();
			int ok = gg.actualizar(g);
			if (ok == 0) {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso5);
			} else {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso2);
			}

		} else {
			JOptionPane.showMessageDialog(null, aviso1);
		}
	}

	void eliminardatos() {
		int num_gui = leerNumGuia();
		Guia g = new Guia();
		g.setNum_gui(num_gui);
		if (num_gui >= 1) {
			GestionGuia gu = new GestionGuia();
			int ok = gu.eliminar(g);
			if (ok == 0) {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso6);
			} else {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso3);
			}

		} else {
			JOptionPane.showMessageDialog(null, "Ingrese un codigo");
		}

	}

	int leerNumGuia() {
		int cod = -1;
		try {
			cod = Integer.parseInt(txtNumero.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, aviso1);
		}
		return cod;

	}

	String leerFecGuia() {
		try {
			String f = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			f = sdf.format(txtFechaGuia.getDate());
			if (f != null)
				return f;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingrese la fecha de registro de la guía");
		}
		return null;
	}

	String leerFecTrasl() {
		try {
			String t = null;

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			t = sdf.format(txtFechaTrasla.getDate());
			if (t != null)
				return t;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingrese la fecha de traslado");
		}
		return null;
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
		default:
			return "Vmayor";
		}
	}

	double leerPeso() {
		double p = -1;
		try {
			p = Double.parseDouble(txtPeso.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ingrese un valor entero o decimal positivo");
		}
		return p;

	}

	int leerCodigoCli() {
		int c = -1;
		try {
			c = Integer.parseInt(txtCliente.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,
					"Ingrese el valor numerico del codigo Cliente. Ejm: CLI0025 valor numerico->25");
		}
		return c;
	}

	int leerCodigoTra() {
		int t = -1;
		try {
			t = Integer.parseInt(txtTransportista.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,
					"Ingrese el valor numerico del codigo Transportista. Ejm: TR0025 valor numerico->25");
		}
		return t;

	}

	int leerCodigoEmp() {
		int e = -1;
		try {
			e = Integer.parseInt(txtEmpresa.getText().trim());
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(null,
					"Ingrese el valor numerico del codigo Empresa. Ejm: EMP0025 valor numerico->25");
		}
		return e;
	}

	void rellenarformulario() {

		int fila = tblGuia.getSelectedRow();
		txtNumero.setText(tblGuia.getValueAt(fila, 0).toString());
		txtFechaGuia.setToolTipText(tblGuia.getValueAt(fila, 1).toString());
		txtFechaTrasla.setToolTipText(tblGuia.getValueAt(fila, 2).toString());
		txtPartida.setText(tblGuia.getValueAt(fila, 3).toString());
		txtLlegada.setText(tblGuia.getValueAt(fila, 4).toString());
		cboMotivo.setSelectedIndex(0);
		txtPeso.setText(tblGuia.getValueAt(fila, 6).toString());
		txtCliente.setText(tblGuia.getValueAt(fila, 7).toString());
		txtTransportista.setText(tblGuia.getValueAt(fila, 8).toString());
		txtEmpresa.setText(tblGuia.getValueAt(fila, 9).toString());

	}
}
