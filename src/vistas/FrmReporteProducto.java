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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import mantenimientos.GestionReporteProducto;
import modelos.Producto;

public class FrmReporteProducto extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtStk1;
	private JTextField txtStk2;
	private JTextField txtDescCont;
	private JTable tblResultado;
	private DefaultTableModel modelo;
	private JComboBox<Object> cboTipoUni;
	private JButton btnImprimirPdf;
	private JButton btnMostrar;
	private JButton btnLimpiar;
	private JScrollPane scrollPane;
	private JLabel lblTipoUni;
	private JLabel lblStk1;
	private JLabel lblStk2;
	private JLabel lblDescCont;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteProducto frame = new FrmReporteProducto();
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
	public FrmReporteProducto() {
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setFrameIcon(new ImageIcon(FrmReporteProducto.class.getResource("/iconos/producto.png")));
		setTitle("Reporte de Producto en Stock");
		setBounds(100, 100, 640, 500);
		getContentPane().setLayout(null);

		txtStk1 = new JTextField();
		txtStk1.setBounds(174, 77, 138, 20);
		getContentPane().add(txtStk1);
		txtStk1.setColumns(10);

		txtStk2 = new JTextField();
		txtStk2.setBounds(174, 111, 138, 20);
		getContentPane().add(txtStk2);
		txtStk2.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 213, 582, 246);
		getContentPane().add(scrollPane);

		tblResultado = new JTable();
		scrollPane.setViewportView(tblResultado);
		modelo = new DefaultTableModel();
		tblResultado.setModel(modelo);

		modelo.addColumn("Código");
		modelo.addColumn("Marca");
		modelo.addColumn("Descripción");
		modelo.addColumn("Unidad Medida");
		modelo.addColumn("Stock");
		modelo.addColumn("Precio");
		modelo.addColumn("Peso");
		modelo.addColumn("Creado por");

		cboTipoUni = new JComboBox<Object>();
		cboTipoUni.setFont(new Font("Tahoma", Font.BOLD, 10));
		cboTipoUni.setModel(new DefaultComboBoxModel<Object>(new String[] { "Seleccione", "Unidad", "Caja" }));
		cboTipoUni.setBounds(174, 35, 138, 20);
		getContentPane().add(cboTipoUni);

		btnMostrar = new JButton("MOSTRAR ");
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listar();
			}
		});
		btnMostrar.setIcon(new ImageIcon(FrmReporteProducto.class.getResource("/iconos/reporte.png")));
		btnMostrar.setBounds(426, 35, 176, 35);
		getContentPane().add(btnMostrar);

		btnImprimirPdf = new JButton("CREAR PDF");
		btnImprimirPdf.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnImprimirPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImprimirPDF();
				limpiar();
				listar();
			}
		});
		btnImprimirPdf.setIcon(new ImageIcon(FrmReporteProducto.class.getResource("/iconos/imprimir.png")));
		btnImprimirPdf.setBounds(426, 98, 176, 35);
		getContentPane().add(btnImprimirPdf);

		btnLimpiar = new JButton("LIMPIAR DATOS");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
				listar();
			}
		});
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLimpiar.setBounds(426, 165, 176, 37);
		getContentPane().add(btnLimpiar);

		lblTipoUni = new JLabel("Unidad de Medida");
		lblTipoUni.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoUni.setBounds(20, 35, 145, 20);
		getContentPane().add(lblTipoUni);

		lblStk1 = new JLabel("Cantidad M\u00EDnima");
		lblStk1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStk1.setBounds(20, 75, 145, 20);
		getContentPane().add(lblStk1);

		lblStk2 = new JLabel("Cantidad M\u00E1xima");
		lblStk2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStk2.setBounds(20, 113, 145, 20);
		getContentPane().add(lblStk2);

		txtDescCont = new JTextField();
		txtDescCont.setColumns(10);
		txtDescCont.setBounds(20, 182, 292, 20);
		getContentPane().add(txtDescCont);

		lblDescCont = new JLabel("Descripci\u00F3n Prod Contiene");
		lblDescCont.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescCont.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescCont.setBounds(20, 152, 292, 20);
		getContentPane().add(lblDescCont);

	}

	void listar() {
		String tipo = leerTipo();
		int stk1 = leerStk1();
		int stk2 = leerStk2();
		String desc = leerDescCont();

		GestionReporteProducto gr = new GestionReporteProducto();
		ArrayList<Producto> lista = gr.listado(tipo, stk1, stk2, desc);
		modelo.setRowCount(0);
		for (Producto p : lista) {
			Object[] datos = { p.getCod_prod(), p.getMarca(), p.getDesc_prod(), p.getUni_med_prod(), p.getStk_prod(),
					"S/." + p.getPre_unit(), p.getPes_unit() + " kg." ,p.getUsu_creador_prod() };
			modelo.addRow(datos);
		}
	}

	void limpiar() {
		cboTipoUni.setSelectedIndex(0);
		txtStk1.setText("");
		txtStk2.setText("");
		txtDescCont.setText("");
	}

	String leerTipo() {
		int tp = cboTipoUni.getSelectedIndex();
		switch (tp) {
		case 1:
			return "u";
		case 2:
			return "c";
		default:
			return "";
		}
	}

	int leerStk1() {
		int s = 0;
		if (!txtStk1.getText().trim().equals("")) {
			try {
				s = Integer.parseInt(txtStk1.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ingrese un valor entero válido en el rango de inicio");
			}
		}
		return s;
	}

	int leerStk2() {
		int s = 0;
		if (!txtStk2.getText().trim().equals("")) {
			try {
				s = Integer.parseInt(txtStk2.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ingrese un valor entero válido en el rango final");
			}
		}
		return s;
	}

	String leerDescCont() {
		return txtDescCont.getText().trim();
	}

	void ImprimirPDF() {
		SimpleDateFormat sdf_fecha_larga = new SimpleDateFormat("'Lima,' dd 'de' MMMM 'del' y, HH:mm:ss");
		String tipo = leerTipo();
		int stk1 = leerStk1();
		int stk2 = leerStk2();
		String desc = leerDescCont();
		GestionReporteProducto gr = new GestionReporteProducto();
		ArrayList<Producto> lista = gr.listado(tipo, stk1, stk2, desc);
		if (lista.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay datos para imprimir");

		} else {
			try {
				Document doc = new Document();
				FileOutputStream fos = new FileOutputStream("ReporteProducto.pdf");
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
				doc.add(new Paragraph("Reporte de Productos"));
				doc.add(new Paragraph(" "));

				PdfPTable tabla = new PdfPTable(8);
				tabla.addCell("Código");
				tabla.addCell("Marca");
				tabla.addCell("Descripción");
				tabla.addCell("Unidad Medida");
				tabla.addCell("Stock");
				tabla.addCell("Precio");
				tabla.addCell("Peso");
				tabla.addCell("Creado por");

				for (Producto p : lista) {
					tabla.addCell(p.getCod_prod() + "");
					tabla.addCell(p.getMarca());
					tabla.addCell(p.getDesc_prod());
					tabla.addCell(p.getUni_med_prod());
					tabla.addCell(p.getStk_prod() + "");
					tabla.addCell(p.getPre_unit() + "");
					tabla.addCell(p.getPes_unit() + "");
					tabla.addCell(p.getUsu_creador_prod() + "");
				}

				doc.add(tabla);
				Date hoy = new Date();
				doc.add(new Paragraph(sdf_fecha_larga.format(hoy)));

				/* fin del docuemento */
				doc.close();
				Desktop.getDesktop().open(new File("ReporteProducto.pdf"));

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al crear pdf: " + e.getMessage());
			}

		}

	}
}
