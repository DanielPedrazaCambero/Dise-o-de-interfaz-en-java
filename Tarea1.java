import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Tarea1 extends JFrame {

	private static final long serialVersionUID = 1L;

	// Patrón simple para validar formato de email
	private static final Pattern EMAIL_PATTERN =
			Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

	// Colores reutilizables
	private static final Color COLOR_FONDO = new Color(235, 224, 213);
	private static final Color COLOR_PANEL = new Color(250, 245, 239);
	private static final Color COLOR_TEXTO = new Color(70, 55, 45);
	private static final Color COLOR_BOTON = new Color(196, 154, 108);
	private static final Font FUENTE_LABEL = new Font("Tahoma", Font.PLAIN, 13);
	private static final Font FUENTE_TITULO = new Font("Tahoma", Font.BOLD, 22);

	// Componentes principales
	private JPanel contentPane;
	private JTextField textField;       // Nombre
	private JTextField textField_1;     // Apellidos
	private JTextField textotro;        // Texto para opción "Otro"
	private JTextField textField_3;     // Muestra la Edad (Bloqueado)
	private JTextField textField_4;     // Email

	private JTextArea textArea;         // Histórico
	private JComboBox<String> comboBox; // Ciudad
	private ButtonGroup grupoSexo;

	// RadioButtons para Sexo
	private JRadioButton Boton;                 // Hombre
	private JRadioButton rdbtnNewRadioButton_1; // Mujer
	private JRadioButton rdbtnNewRadioButton_2; // Otro

	// Spinners para Fecha
	private JSpinner spinner;   // Día
	private JSpinner spinner_1; // Mes
	private JSpinner spinner_2; // Año

	/**
	 * Método para calcular la edad automáticamente según la fecha elegida
	 */
	private void actualizarEdad() {
		try {
			int dia = (int) spinner.getValue();
			int mes = (int) spinner_1.getValue();
			int anio = (int) spinner_2.getValue();

			LocalDate fechaNac = LocalDate.of(anio, mes, dia);
			LocalDate fechaHoy = LocalDate.now();

			if (!fechaNac.isAfter(fechaHoy)) {
				int edad = Period.between(fechaNac, fechaHoy).getYears();
				textField_3.setText(edad + " años");
			} else {
				textField_3.setText("Inválida");
			}
		} catch (Exception ex) {
			textField_3.setText("Error");
		}
	}

	/**
	 * Método principal de ejecución
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tarea1 frame = new Tarea1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor del Frame
	 */
	public Tarea1() {
		// Configuración básica de la ventana
		setBackground(COLOR_FONDO);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 864, 668);
		setLocationRelativeTo(null); // Centra la ventana en la pantalla

		contentPane = new JPanel();
		contentPane.setBackground(COLOR_FONDO);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// -------------------------------------------------------------
		// 1. ETIQUETAS (LABELS)
		// -------------------------------------------------------------
		JLabel lblTitle = new JLabel("Registro Cafetería");
		lblTitle.setForeground(COLOR_TEXTO);
		lblTitle.setFont(FUENTE_TITULO);
		lblTitle.setBounds(31, 10, 242, 35);
		contentPane.add(lblTitle);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(COLOR_TEXTO);
		lblNombre.setFont(FUENTE_LABEL);
		lblNombre.setBounds(31, 55, 85, 21);
		contentPane.add(lblNombre);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setForeground(COLOR_TEXTO);
		lblApellidos.setFont(FUENTE_LABEL);
		lblApellidos.setBounds(31, 83, 79, 21);
		contentPane.add(lblApellidos);

		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setForeground(COLOR_TEXTO);
		lblCiudad.setFont(FUENTE_LABEL);
		lblCiudad.setBounds(31, 111, 95, 21);
		contentPane.add(lblCiudad);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setForeground(COLOR_TEXTO);
		lblSexo.setFont(FUENTE_LABEL);
		lblSexo.setBounds(31, 140, 49, 21);
		contentPane.add(lblSexo);

		JLabel lblFecha = new JLabel("Fecha nacimiento:");
		lblFecha.setForeground(COLOR_TEXTO);
		lblFecha.setFont(FUENTE_LABEL);
		lblFecha.setBounds(31, 172, 130, 21);
		contentPane.add(lblFecha);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(COLOR_TEXTO);
		lblEmail.setFont(FUENTE_LABEL);
		lblEmail.setBounds(31, 200, 63, 21);
		contentPane.add(lblEmail);

		JLabel lblHistorico = new JLabel("Histórico:");
		lblHistorico.setForeground(COLOR_TEXTO);
		lblHistorico.setFont(FUENTE_LABEL);
		lblHistorico.setBounds(31, 233, 79, 21);
		contentPane.add(lblHistorico);

		// -------------------------------------------------------------
		// 2. CAMPOS DE TEXTO BÁSICOS Y COMBOBOX
		// -------------------------------------------------------------
		textField = new JTextField();
		textField.setBounds(120, 54, 130, 22);
		textField.setColumns(10);
		textField.setBackground(COLOR_PANEL);
		textField.setBorder(new LineBorder(COLOR_BOTON, 1));
		contentPane.add(textField);

		textField_1 = new JTextField();
		textField_1.setBounds(120, 82, 130, 22);
		textField_1.setColumns(10);
		textField_1.setBackground(COLOR_PANEL);
		textField_1.setBorder(new LineBorder(COLOR_BOTON, 1));
		contentPane.add(textField_1);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(120, 109, 100, 22);
		comboBox.setBackground(COLOR_PANEL);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Cáceres", "Badajoz", "Merida"}));
		contentPane.add(comboBox);

		textArea = new JTextArea();
		textArea.setBounds(120, 232, 338, 132);
		textArea.setBackground(COLOR_PANEL);
		textArea.setLineWrap(true);
		textArea.setBorder(new LineBorder(COLOR_BOTON, 1));
		contentPane.add(textArea);

		// -------------------------------------------------------------
		// 3. SECCIÓN SEXO (RADIOBUTTONS Y CAMPO textotro)
		// -------------------------------------------------------------
		Boton = new JRadioButton("Hombre");
		Boton.setMnemonic('1');
		Boton.setForeground(COLOR_TEXTO);
		Boton.setFont(FUENTE_LABEL);
		Boton.setBackground(COLOR_FONDO);
		Boton.setBounds(90, 139, 85, 22);
		contentPane.add(Boton);

		rdbtnNewRadioButton_1 = new JRadioButton("Mujer");
		rdbtnNewRadioButton_1.setMnemonic('2');
		rdbtnNewRadioButton_1.setForeground(COLOR_TEXTO);
		rdbtnNewRadioButton_1.setFont(FUENTE_LABEL);
		rdbtnNewRadioButton_1.setBackground(COLOR_FONDO);
		rdbtnNewRadioButton_1.setBounds(170, 139, 57, 22);
		contentPane.add(rdbtnNewRadioButton_1);

		rdbtnNewRadioButton_2 = new JRadioButton("Otro");
		rdbtnNewRadioButton_2.setMnemonic('3');
		rdbtnNewRadioButton_2.setForeground(COLOR_TEXTO);
		rdbtnNewRadioButton_2.setFont(FUENTE_LABEL);
		rdbtnNewRadioButton_2.setBackground(COLOR_FONDO);
		rdbtnNewRadioButton_2.setBounds(229, 139, 57, 22);
		contentPane.add(rdbtnNewRadioButton_2);
		
		// agrupar los campo
		grupoSexo = new ButtonGroup();
		grupoSexo.add(Boton);
		grupoSexo.add(rdbtnNewRadioButton_1);
		grupoSexo.add(rdbtnNewRadioButton_2);


		textotro = new JTextField();
		textotro.setBounds(292, 142, 70, 20);
		textotro.setColumns(10);
		textotro.setEnabled(false);
		textotro.setEditable(true);
		textotro.setBackground(COLOR_PANEL);
		textotro.setBorder(new LineBorder(COLOR_BOTON, 1));
		contentPane.add(textotro);

		// Listener para habilitar textotro únicamente cuando se pulse "Otro"
		ActionListener listenerSexo = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNewRadioButton_2.isSelected()) {
					textotro.setEnabled(true);  // Permite escribir
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							textotro.requestFocusInWindow();
						}
					});
				} else {
					textotro.setEnabled(false); // Deshabilita para Hombre o Mujer
					textotro.setText("");       // Limpia el contenido
				}
			}
		};

		Boton.addActionListener(listenerSexo);
		rdbtnNewRadioButton_1.addActionListener(listenerSexo);
		rdbtnNewRadioButton_2.addActionListener(listenerSexo);

		// -------------------------------------------------------------
		// 4. SPINNERS Y MUESTRA DE EDAD (textField_3)
		// -------------------------------------------------------------
		int anioActual = LocalDate.now().getYear();

		spinner = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
		spinner.setBounds(170, 171, 41, 22);
		contentPane.add(spinner);

		spinner_1 = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
		spinner_1.setBounds(214, 171, 41, 22);
		contentPane.add(spinner_1);

		spinner_2 = new JSpinner(new SpinnerNumberModel(2000, 1900, anioActual, 1));
		spinner_2.setBounds(260, 171, 57, 22);
		contentPane.add(spinner_2);

		// textField_3: Muestra la edad calculada y NO permite edición
		textField_3 = new JTextField();
		textField_3.setBounds(325, 171, 70, 22);
		textField_3.setColumns(10);
		textField_3.setEditable(false);
		textField_3.setBackground(new Color(220, 235, 220));
		textField_3.setHorizontalAlignment(JTextField.CENTER);
		textField_3.setBorder(new LineBorder(COLOR_BOTON, 1));
		contentPane.add(textField_3);

		// calcular la edad al modificar los Spinners
		ChangeListener listenerFecha = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				actualizarEdad();
			}
		};

		spinner.addChangeListener(listenerFecha);
		spinner_1.addChangeListener(listenerFecha);
		spinner_2.addChangeListener(listenerFecha);

		// Cálculo inicial
		actualizarEdad();

		// -------------------------------------------------------------
		// 5. EMAIL Y BOTONES (LIMPIAR Y ENVIAR)
		// -------------------------------------------------------------
		textField_4 = new JTextField();
		textField_4.setBounds(77, 199, 150, 22);
		textField_4.setColumns(10);
		textField_4.setBackground(COLOR_PANEL);
		textField_4.setBorder(new LineBorder(COLOR_BOTON, 1));
		contentPane.add(textField_4);

		// BOTÓN LIMPIAR (VACÍA TODO EL FORMULARIO)
		JButton btnNewButton = new JButton("Limpiar");
		btnNewButton.setFont(FUENTE_LABEL);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBackground(new Color(225, 210, 195));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Vaciar todos los cuadros de texto
				textField.setText("");       // Nombre
				textField_1.setText("");     // Apellidos
				textotro.setText("");        // Texto de "Otro"
				textField_3.setText("");     // Edad
				textField_4.setText("");     // Email
				textArea.setText("");        // Histórico
				textArea.setEditable(true);  // Vuelve a permitir edición para el próximo registro

				// Deshabilitar el cuadro de texto para "Otro"
				textotro.setEnabled(false);

				// Restablecer la selección de Ciudad al primer elemento
				if (comboBox.getItemCount() > 0) {
					comboBox.setSelectedIndex(0);
				}

				// Deseleccionar el grupo de botones de Sexo
				grupoSexo.clearSelection();

				// Restablecer los Spinners de Fecha
				spinner.setValue(1);
				spinner_1.setValue(1);
				spinner_2.setValue(2000);

				// Recalcular/Limpiar el resultado de la edad
				actualizarEdad();
			}
		});
		btnNewButton.setBounds(240, 199, 84, 22);
		contentPane.add(btnNewButton);

		// BOTÓN ENVIAR
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setFont(FUENTE_LABEL);
		btnEnviar.setFocusPainted(false);
		btnEnviar.setBackground(COLOR_BOTON);
		btnEnviar.setForeground(Color.WHITE);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombre = textField.getText().trim();
				String apellidos = textField_1.getText().trim();
				String email = textField_4.getText().trim();

				// Validación: Nombre obligatorio
				if (nombre.isEmpty()) {
					JOptionPane.showMessageDialog(Tarea1.this,
						"Por favor, introduce tu nombre.",
						"Campo Requerido",
						JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Validación: Apellidos obligatorios
				if (apellidos.isEmpty()) {
					JOptionPane.showMessageDialog(Tarea1.this,
						"Por favor, introduce tus apellidos.",
						"Campo Requerido",
						JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Validación: Sexo seleccionado
				if (!Boton.isSelected() && !rdbtnNewRadioButton_1.isSelected()
						&& !rdbtnNewRadioButton_2.isSelected()) {
					JOptionPane.showMessageDialog(Tarea1.this,
						"Por favor, selecciona una opción de sexo.",
						"Campo Requerido",
						JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Validación: si es "Otro", el texto no puede estar vacío
				if (rdbtnNewRadioButton_2.isSelected() && textotro.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(Tarea1.this,
						"Por favor, especifica el sexo en el campo 'Otro'.",
						"Campo Requerido",
						JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Validación: Email obligatorio y con formato válido
				if (email.isEmpty()) {
					JOptionPane.showMessageDialog(Tarea1.this,
						"Por favor, introduce una dirección de correo.",
						"Campo Requerido",
						JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (!EMAIL_PATTERN.matcher(email).matches()) {
					JOptionPane.showMessageDialog(Tarea1.this,
						"El formato del correo electrónico no es válido.",
						"Email Inválido",
						JOptionPane.WARNING_MESSAGE);
					return;
				}

				actualizarEdad();

				String sexo = "No especificado";
				if (Boton.isSelected()) {
					sexo = "Hombre";
				} else if (rdbtnNewRadioButton_1.isSelected()) {
					sexo = "Mujer";
				} else if (rdbtnNewRadioButton_2.isSelected()) {
					sexo = "Otro (" + textotro.getText().trim() + ")";
				}

				// Resumen simple en una sola línea, separado por comas
				String resumen = nombre + " " + apellidos + ", "
						+ comboBox.getSelectedItem() + ", "
						+ sexo + ", "
						+ spinner.getValue() + "/" + spinner_1.getValue() + "/" + spinner_2.getValue() + ", "
						+ textField_3.getText() + ", "
						+ email;

				// Escribe el resumen en el Histórico (una sola línea) y lo bloquea
				textArea.setText(resumen);
				textArea.setEditable(false);

				JOptionPane.showMessageDialog(Tarea1.this,
					"Registro guardado en el Histórico. Se enviará a " + email,
					"Envío Confirmado",
					JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnEnviar.setBounds(330, 199, 65, 22);
		contentPane.add(btnEnviar);
	}
}