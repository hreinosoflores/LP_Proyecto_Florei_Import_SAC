package vistas;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import mantenimientos.GestionReporteComprobante;
import modelos.ReporteComprobante;

public class FrmReporteComprobante extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> cboTipo;
	private JDateChooser txtInicio;
	private JDateChooser txtFin;
	private JTable tblReporte;
	private DefaultTableModel modelo;
	private JLabel lblTipo;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private JButton btnPdf;
	private JButton btnLimpiar;
	private JButton btnMostrar;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteComprobante frame = new FrmReporteComprobante();
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
	public FrmReporteComprobante() {
		setFrameIcon(new ImageIcon(FrmReporteComprobante.class.getResource("/iconos/reporte.png")));
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de comprobantes por fecha");
		setBounds(100, 100, 640, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipo.setBounds(27, 39, 101, 20);
		contentPane.add(lblTipo);

		cboTipo = new JComboBox<String>();
		cboTipo.setModel(new DefaultComboBoxModel<String>(new String[] { "Seleccione", "Boleta", "Factura" }));
		cboTipo.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboTipo.setBounds(138, 39, 180, 20);
		contentPane.add(cboTipo);

		lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaInicio.setBounds(27, 110, 101, 20);
		contentPane.add(lblFechaInicio);

		lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaFin.setBounds(27, 183, 101, 20);
		contentPane.add(lblFechaFin);

		txtInicio = new JDateChooser();
		txtInicio.setBounds(138, 110, 180, 20);
		contentPane.add(txtInicio);

		txtFin = new JDateChooser();
		txtFin.setBounds(138, 183, 180, 20);
		contentPane.add(txtFin);

		btnPdf = new JButton("CREAR PDF");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerarPDF();
				limpiar();
				listar();
			}
		});
		btnPdf.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPdf.setIcon(new ImageIcon(FrmReporteComprobante.class.getResource("/iconos/imprimir.png")));
		btnPdf.setBounds(420, 101, 176, 35);
		contentPane.add(btnPdf);

		btnLimpiar = new JButton("LIMPIAR DATOS");
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
				listar();
			}
		});
		btnLimpiar.setBounds(420, 173, 176, 37);
		contentPane.add(btnLimpiar);

		btnMostrar = new JButton("MOSTRAR ");
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listar();
			}
		});
		btnMostrar.setIcon(new ImageIcon(FrmReporteComprobante.class.getResource("/iconos/reporte.png")));
		btnMostrar.setBounds(420, 24, 176, 35);
		contentPane.add(btnMostrar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 220, 569, 239);
		contentPane.add(scrollPane);

		tblReporte = new JTable();
		scrollPane.setViewportView(tblReporte);

		modelo = new DefaultTableModel();
		modelo.addColumn("Nro. Comp");
		modelo.addColumn("Fecha Registro");
		modelo.addColumn("Creador");
		modelo.addColumn("Cliente");
		tblReporte.setModel(modelo);

	}

	void listar() {
		String tipo = leertipo();
		String fec_ini = leerfec_ini();
		String fec_fin = leerfec_fin();

		GestionReporteComprobante gr = new GestionReporteComprobante();
		ArrayList<ReporteComprobante> lista = gr.listado(tipo, fec_ini, fec_fin);
		modelo.setRowCount(0);
		for (ReporteComprobante r : lista) {
			Object[] fila = { r.getCodigo(), r.getFecharegistro(), r.getCreador(), r.getCliente() };
			modelo.addRow(fila);
		}
	}

	void limpiar() {
		Date hoy = new Date();
		cboTipo.setSelectedIndex(0);
		txtInicio.setDate(hoy);
		txtFin.setDate(hoy);
	}

	void GenerarPDF() {
		SimpleDateFormat sdf_fecha_corta = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf_fecha_larga = new SimpleDateFormat("'Lima,' dd 'de' MMMM 'del' y, HH:mm:ss");	
		String tipo_formateado = cboTipo.getSelectedItem().toString();
		String fec_ini_formateado = txtInicio.getDate()==null?"":sdf_fecha_corta.format(txtInicio.getDate());
		String fec_fin_formateado = txtFin.getDate()==null?"":sdf_fecha_corta.format(txtFin.getDate());
		
		
		String tipo = leertipo();
		String fec_ini = leerfec_ini();
		String fec_fin = leerfec_fin();
		GestionReporteComprobante gr = new GestionReporteComprobante();
		ArrayList<ReporteComprobante> lista = gr.listado(tipo, fec_ini, fec_fin);
		if (lista.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay datos para imprimir");

		} else {
			try {
				Document doc = new Document();
				FileOutputStream fos = new FileOutputStream("ReporteComprobante.pdf");
				PdfWriter escritor = PdfWriter.getInstance(doc, fos);
				escritor.setInitialLeading(50);
				doc.open();
				/* contenido del documento */
				doc.add(new Paragraph(" "));
				doc.add(new Paragraph(" "));
				doc.add(new Paragraph(" "));
				Image logo = Image.getInstance("src/iconos/FloreiLogo.png");
				logo.scaleToFit(40, 40);
				logo.setAlignment(Chunk.ALIGN_LEFT);
				doc.add(logo);
				doc.add(new Paragraph("Florei Import S.A.C.",
						FontFactory.getFont("arial", 30, Font.ITALIC, BaseColor.GREEN)));
				doc.add(new Paragraph(" "));
				doc.add(new Paragraph("Reporte Comprobantes"));
				doc.add(new Paragraph(" "));
				if(fec_ini.equals("") && fec_fin.equals("")) {
					doc.add(new Paragraph("Tipo de comprobante: " + tipo_formateado));
				}else if(fec_ini.equals("")) {
					doc.add(new Paragraph("Tipo de comprobante: " + tipo_formateado + ", hasta " + fec_fin_formateado));
				}else if(fec_fin.equals("")) {
					doc.add(new Paragraph("Tipo de comprobante: " + tipo_formateado + ", desde " + fec_ini_formateado));
				}else {
					doc.add(new Paragraph("Tipo de comprobante: " + tipo_formateado + ", desde " + fec_ini_formateado + " hasta " + fec_fin_formateado));
				}
				doc.add(new Paragraph(" "));
				PdfPTable tabla = new PdfPTable(4);

				tabla.addCell("Nro. Comp");
				tabla.addCell("Fecha Registro");
				tabla.addCell("Creador");
				tabla.addCell("Cliente");

				for (ReporteComprobante r : lista) {
					tabla.addCell(r.getCodigo() + "");
					tabla.addCell(r.getFecharegistro());
					tabla.addCell(r.getCreador());
					tabla.addCell(r.getCliente());
				}
				doc.add(tabla);
				Date hoy = new Date();

				doc.add(new Paragraph(sdf_fecha_larga.format(hoy)));

				/* fin del docuemento */
				doc.close();
				Desktop.getDesktop().open(new File("ReporteComprobante.pdf"));

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al crear pdf: " + e.getMessage());
			}

		}

	}

	String leertipo() {
		int tp = cboTipo.getSelectedIndex();
		switch (tp) {
		case 1:
			return "b";
		case 2:
			return "f";
		default:
			return "";
		}
	}

	String leerfec_ini() {
		String f = "";
		if (txtInicio.getDate() != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				f = sdf.format(txtInicio.getDate());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error al formatear la fecha: " + e.getMessage());
			}
		}
		return f;
	}

	String leerfec_fin() {
		String f = "";
		if (txtFin.getDate() != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				f = sdf.format(txtFin.getDate());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error al formatear la fecha: " + e.getMessage());
			}

		}
		return f;
	}
}
