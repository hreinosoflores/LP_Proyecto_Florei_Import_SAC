package vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

import libreria.Hora;
import mantenimientos.GestionComprobantePago;
import modelos.ComprobantePago;

public class FrmComprobantePago extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtNumero;
	private JTable tblComprobantePago;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JComboBox<String> cboHora;
	private JComboBox<String> cboMin;
	private JComboBox<String> cboSeg;
	private JDateChooser txtFecha;
	private JComboBox cboTipo;
	private JComboBox cboLugar;
	private JTextField txtCliente;
	String aviso1 = "Ingrese el valor numerico del Numero. Ejm: COM0025 valor numerico->25";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmComprobantePago frame = new FrmComprobantePago();
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
	public FrmComprobantePago() {
		setFrameIcon(new ImageIcon(FrmComprobantePago.class.getResource("/iconos/comprobante.png")));
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Comprobante Pago");
		setBounds(100, 100, 640, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNmero.setBounds(10, 26, 71, 14);
		contentPane.add(lblNmero);

		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtNumero.setBounds(91, 20, 111, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(10, 68, 71, 14);
		contentPane.add(lblFecha);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipo.setBounds(10, 116, 71, 19);
		contentPane.add(lblTipo);

		cboTipo = new JComboBox();
		cboTipo.setModel(new DefaultComboBoxModel(new String[] { "Seleccione\t", "Boleta", "Factura" }));
		cboTipo.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboTipo.setBounds(91, 110, 111, 20);
		contentPane.add(cboTipo);

		JLabel lblHora = new JLabel("Hora");
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHora.setBounds(274, 68, 62, 14);
		contentPane.add(lblHora);

		JLabel lblLugar = new JLabel("Lugar");
		lblLugar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLugar.setBounds(274, 116, 46, 19);
		contentPane.add(lblLugar);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
				listar();
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrar.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/iconos/registrar.png")));
		btnRegistrar.setBounds(10, 188, 145, 45);
		contentPane.add(btnRegistrar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
				listar();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModificar.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/iconos/modificar.png")));
		btnModificar.setBounds(165, 188, 145, 45);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
				listar();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminar.setIcon(new ImageIcon(FrmComprobantePago.class.getResource("/iconos/eliminar.png")));
		btnEliminar.setBounds(320, 188, 145, 45);
		contentPane.add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 244, 606, 206);
		contentPane.add(scrollPane);

		tblComprobantePago = new JTable();
		tblComprobantePago.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rellenar();
			}
		});
		tblComprobantePago.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblComprobantePago);

		txtFecha = new JDateChooser();
		txtFecha.setBounds(91, 62, 111, 20);
		contentPane.add(txtFecha);

		cboHora = new JComboBox<String>();
		Hora.setHoras(cboHora);
		cboHora.setEditable(true);
		cboHora.setBounds(334, 62, 40, 23);
		contentPane.add(cboHora);

		cboMin = new JComboBox<String>();
		Hora.setMinutos(cboMin);
		cboMin.setEditable(true);
		cboMin.setBounds(384, 62, 40, 23);
		contentPane.add(cboMin);

		cboSeg = new JComboBox<String>();
		Hora.setSegundos(cboSeg);
		cboSeg.setEditable(true);
		cboSeg.setBounds(434, 62, 40, 23);
		contentPane.add(cboSeg);

		modelo = new DefaultTableModel();
		modelo.addColumn("Número Comprobante");
		modelo.addColumn("Fecha");
		modelo.addColumn("Hora");
		modelo.addColumn("Tipo");
		modelo.addColumn("Lugar");
		modelo.addColumn("Cliente");
		tblComprobantePago.setModel(modelo);

		ajustarAnchoColumnas();
		Hora.setHora(cboHora, cboMin, cboSeg);

		cboLugar = new JComboBox();
		cboLugar.setModel(new DefaultComboBoxModel(
				new String[] { "Nicolini", "Centro Ferretero", "La bellota", "SJL", "Paruro" }));
		cboLugar.setBounds(334, 115, 152, 20);
		contentPane.add(cboLugar);

		JButton btnLimpiar = new JButton("LIMPIAR DATOS");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setBounds(274, 10, 191, 39);
		contentPane.add(btnLimpiar);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCliente.setBounds(10, 163, 71, 14);
		contentPane.add(lblCliente);

		txtCliente = new JTextField();
		txtCliente.setBounds(92, 157, 46, 20);
		contentPane.add(txtCliente);
		txtCliente.setColumns(10);

		listar();
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	private void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblComprobantePago.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(14));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(8));
	}

	void limpiar() {
		Date hoy = new Date();
		txtNumero.setText("");
		txtFecha.setDate(hoy);
		cboTipo.setSelectedIndex(0);
		cboLugar.setSelectedIndex(0);
		txtCliente.setText("");
		txtNumero.requestFocus();
	}

	void listar() {
		GestionComprobantePago gcp = new GestionComprobantePago();
		ArrayList<ComprobantePago> lista = gcp.listado();
		modelo.setRowCount(0);
		for (ComprobantePago cp : lista) {
			Object[] fila = { cp.codigochar(), cp.getFec_comp(), cp.getHor_comp(), cp.getTip_comp(), cp.getLug_comp(),
					cp.getCod_cli()

			};
			modelo.addRow(fila);
		}
	}

	void rellenar() {
		int fila = tblComprobantePago.getSelectedRow();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = null;
		try {
			fecha = sdf.parse(tblComprobantePago.getValueAt(fila, 1).toString());
		} catch (ParseException e) {
			System.out.println("Error de conversion de fechas: " + e.getMessage());
		}
		txtNumero.setText(tblComprobantePago.getValueAt(fila, 0).toString());
		txtFecha.setDate(fecha);
		cboTipo.setSelectedIndex(0);
		cboLugar.setSelectedIndex(0);
		txtCliente.setText(tblComprobantePago.getValueAt(fila, 5).toString());
	}

	void registrar() {
		String fecha, hora, tipo_com, lugar;
		int cod_cli;
		fecha = leerFecha();
		hora = leerHora();
		tipo_com = leerTipo();
		lugar = leerLugar();
		cod_cli = leerCliente();

		ComprobantePago cp = new ComprobantePago();
		cp.setFec_comp(fecha);
		cp.setHor_comp(hora);
		cp.setTip_comp(tipo_com);
		cp.setLug_comp(lugar);
		cp.setCod_cli(cod_cli);

		GestionComprobantePago gcp = new GestionComprobantePago();
		int ok = gcp.registrar(cp);
		if (ok == 0) {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso4);
		} else {
			JOptionPane.showMessageDialog(null, FrmPrincipal.aviso1);
		}
	}

	void modificar() {
		int num_com, cod_cli;
		String fecha, hora, tipo_com, lugar;
		num_com = leerNumCom();
		fecha = leerFecha();
		hora = leerHora();
		tipo_com = leerTipo();
		lugar = leerLugar();
		cod_cli = leerCliente();

		ComprobantePago cp = new ComprobantePago();
		cp.setNum_comp(num_com);
		cp.setFec_comp(fecha);
		cp.setHor_comp(hora);
		cp.setTip_comp(tipo_com);
		cp.setLug_comp(lugar);
		cp.setCod_cli(cod_cli);

		if (num_com >= 1) {
			GestionComprobantePago gcp = new GestionComprobantePago();
			int ok = gcp.actualizar(cp);
			if (ok == 0) {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso5);
			} else {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso2);
			}

		} else {
			JOptionPane.showMessageDialog(null, aviso1);
		}
	}

	void eliminar() {
		int num_com;
		num_com = leerNumCom();
		ComprobantePago cp = new ComprobantePago();
		cp.setNum_comp(num_com);
		if (num_com >= 1) {
			GestionComprobantePago gcp = new GestionComprobantePago();
			int ok = gcp.eliminar(cp);
			if (ok == 0) {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso6);
			} else {
				JOptionPane.showMessageDialog(null, FrmPrincipal.aviso3);
			}

		} else {
			JOptionPane.showMessageDialog(null, aviso1);
		}

	}

	int leerNumCom() {
		int c = -1;
		try {
			c = Integer.parseInt(txtNumero.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "");
		}
		return c;
	}

	String leerFecha() {
		String c = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		c = sdf.format(txtFecha.getDate());
		return c;
	}

	String leerHora() {
		String c = null;
		String hora = cboHora.getSelectedItem().toString();
		String minuto = cboMin.getSelectedItem().toString();
		String segundo = cboSeg.getSelectedItem().toString();

		c = hora + ":" + minuto + ":" + segundo;
		return c;

	}

	String leerTipo() {
		int tp = 0;
		tp = cboTipo.getSelectedIndex();
		switch (tp) {
		case 2:
			return "F";
		default:
			return "B";
		}
	}

	String leerLugar() {
		String c = null;
		c = cboLugar.getSelectedItem().toString();
		return c;
	}

	int leerCliente() {
		int c = -1;
		try {
			c = Integer.parseInt(txtCliente.getText().trim());
			;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Ingrese el valor numerico del codigo Cliente. Ejm: CLI0025 valor numerico->25");
		}
		return c;
	}
}
