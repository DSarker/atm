import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui extends JFrame implements KeyListener {

	// Main display
	private JTextArea mainDisplay;

	// Input display
	private JTextField inputDisplay;

	// Buttons for the keypad
	private JButton one, two, three, four, five, six, seven, eight, nine, zero,
			decimal, enter;

	// Content panel holds content
	private JPanel contentPanel;

	// Flags for decimal, input status and password protection
	private boolean decFlag = false, pwProtect = false, inputStatus = false;

	// Holds the password from input
	private static StringBuffer password;

	/**
	 * Initializing the GUI
	 */
	public Gui() {

		super("ATM");

		setDisplay();
		setButtons();
		setButtonAction();
		setContent();

		this.setSize(600, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		this.setContentPane(contentPanel);
	}

	/**
	 * Sets up the specifications for the main display and input display
	 */
	private void setDisplay() {

		// Initialize the main display
		mainDisplay = new JTextArea(29, 38);
		mainDisplay.setPreferredSize(new Dimension(10, 35));
		mainDisplay.setBackground(Color.DARK_GRAY);
		mainDisplay.setForeground(Color.WHITE);
		mainDisplay.setFont(new Font("Verdana", Font.PLAIN, 15));
		mainDisplay.setLineWrap(true);
		mainDisplay.setWrapStyleWord(true);
		mainDisplay.setVisible(true);
		mainDisplay.setEditable(false);
		mainDisplay.addKeyListener(this);

		// Initialize the input display
		inputDisplay = new JTextField(38);
		inputDisplay.setPreferredSize(new Dimension(10, 35));
		inputDisplay.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		inputDisplay.setBackground(Color.LIGHT_GRAY);
		inputDisplay.setForeground(Color.BLACK);
		inputDisplay.setFont(new Font("Verdana", Font.PLAIN, 15));
		inputDisplay.setVisible(true);
		inputDisplay.setEditable(false);
		inputDisplay.addKeyListener(this);
	}

	/**
	 * Sets the specifications for the buttons
	 */
	private void setButtons() {

		// Initialize the buttons
		one = new JButton("1");
		two = new JButton("2");
		three = new JButton("3");
		four = new JButton("4");
		five = new JButton("5");
		six = new JButton("6");
		seven = new JButton("7");
		eight = new JButton("8");
		nine = new JButton("9");
		zero = new JButton("0");
		decimal = new JButton(".");
		enter = new JButton("Enter");

		// Dimensions for buttons
		Dimension dim = new Dimension(187, 55);

		one.setPreferredSize(dim);
		one.setFont(new Font("Arial", Font.BOLD, 40));
		two.setPreferredSize(dim);
		two.setFont(new Font("Arial", Font.BOLD, 40));
		three.setPreferredSize(dim);
		three.setFont(new Font("Arial", Font.BOLD, 40));
		four.setPreferredSize(dim);
		four.setFont(new Font("Arial", Font.BOLD, 40));
		five.setPreferredSize(dim);
		five.setFont(new Font("Arial", Font.BOLD, 40));
		six.setPreferredSize(dim);
		six.setFont(new Font("Arial", Font.BOLD, 40));
		seven.setPreferredSize(dim);
		seven.setFont(new Font("Arial", Font.BOLD, 40));
		eight.setPreferredSize(dim);
		eight.setFont(new Font("Arial", Font.BOLD, 40));
		nine.setPreferredSize(dim);
		nine.setFont(new Font("Arial", Font.BOLD, 40));
		zero.setPreferredSize(dim);
		zero.setFont(new Font("Arial", Font.BOLD, 40));
		decimal.setPreferredSize(dim);
		decimal.setFont(new Font("Arial", Font.BOLD, 40));

		// enter.setPreferredSize(new Dimension(379, 55));

		enter.setPreferredSize(dim);
		enter.setFont(new Font("Arial", Font.BOLD, 40));
	}

	/**
	 * Sets the content for the JPanel
	 */
	private void setContent() {

		// Initialize the content panel
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setLayout(new FlowLayout());

		// Add display to panel
		contentPanel.add(mainDisplay);
		contentPanel.add(inputDisplay);

		// Add buttons to panel
		contentPanel.add(seven);
		contentPanel.add(eight);
		contentPanel.add(nine);
		contentPanel.add(four);
		contentPanel.add(five);
		contentPanel.add(six);
		contentPanel.add(one);
		contentPanel.add(two);
		contentPanel.add(three);
		contentPanel.add(zero);
		contentPanel.add(decimal);
		contentPanel.add(enter);
	}

	/**
	 * Sets the actions for the buttons
	 */
	public void setButtonAction() {

		// Set the action of the buttons
		one.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pwProtect) {
					password.append("1");
					inputDisplay.setText(inputDisplay.getText() + "*");
				} else {
					inputDisplay.setText(inputDisplay.getText() + "1");
				}
				inputDisplay.requestFocus();
			}
		});

		two.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pwProtect) {
					password.append("2");
					inputDisplay.setText(inputDisplay.getText() + "*");
				} else {
					inputDisplay.setText(inputDisplay.getText() + "2");
				}
				inputDisplay.requestFocus();
			}
		});

		three.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pwProtect) {
					password.append("3");
					inputDisplay.setText(inputDisplay.getText() + "*");
				} else {
					inputDisplay.setText(inputDisplay.getText() + "3");
				}
				inputDisplay.requestFocus();
			}
		});

		four.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pwProtect) {
					password.append("4");
					inputDisplay.setText(inputDisplay.getText() + "*");
				} else {
					inputDisplay.setText(inputDisplay.getText() + "4");
				}
				inputDisplay.requestFocus();
			}
		});

		five.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pwProtect) {
					password.append("5");
					inputDisplay.setText(inputDisplay.getText() + "*");
				} else {
					inputDisplay.setText(inputDisplay.getText() + "5");
				}
				inputDisplay.requestFocus();
			}
		});

		six.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pwProtect) {
					password.append("6");
					inputDisplay.setText(inputDisplay.getText() + "*");
				} else {
					inputDisplay.setText(inputDisplay.getText() + "6");
				}
				inputDisplay.requestFocus();
			}
		});

		seven.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pwProtect) {
					password.append("7");
					inputDisplay.setText(inputDisplay.getText() + "*");
				} else {
					inputDisplay.setText(inputDisplay.getText() + "7");
				}
				inputDisplay.requestFocus();
			}
		});

		eight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pwProtect) {
					password.append("8");
					inputDisplay.setText(inputDisplay.getText() + "*");
				} else {
					inputDisplay.setText(inputDisplay.getText() + "8");
				}
				inputDisplay.requestFocus();
			}
		});

		nine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pwProtect) {
					password.append("9");
					inputDisplay.setText(inputDisplay.getText() + "*");
				} else {
					inputDisplay.setText(inputDisplay.getText() + "9");
				}
				inputDisplay.requestFocus();
			}
		});

		zero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pwProtect) {
					password.append("0");
					inputDisplay.setText(inputDisplay.getText() + "*");
				} else {
					inputDisplay.setText(inputDisplay.getText() + "0");
				}
				inputDisplay.requestFocus();
			}
		});

		decimal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (decFlag) {
					inputDisplay.setText(inputDisplay.getText() + ".");
				}
				inputDisplay.requestFocus();
			}
		});

		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (inputDisplay.getText().length() > 0) {
					inputStatus = true;
				} else if (pwProtect) {

				}
				inputDisplay.requestFocus();
			}
		});

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Added for optional input with a keyboard
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		int id = e.getKeyCode();

		if (pwProtect) {

			if (id == KeyEvent.VK_1) {
				password.append("1");
				inputDisplay.setText(inputDisplay.getText() + "*");

			} else if (id == KeyEvent.VK_2) {
				password.append("2");
				inputDisplay.setText(inputDisplay.getText() + "*");

			} else if (id == KeyEvent.VK_3) {
				password.append("3");
				inputDisplay.setText(inputDisplay.getText() + "*");

			} else if (id == KeyEvent.VK_4) {
				password.append("4");
				inputDisplay.setText(inputDisplay.getText() + "*");

			} else if (id == KeyEvent.VK_5) {
				password.append("5");
				inputDisplay.setText(inputDisplay.getText() + "*");

			} else if (id == KeyEvent.VK_6) {
				password.append("6");
				inputDisplay.setText(inputDisplay.getText() + "*");

			} else if (id == KeyEvent.VK_7) {
				password.append("7");
				inputDisplay.setText(inputDisplay.getText() + "*");

			} else if (id == KeyEvent.VK_8) {
				password.append("8");
				inputDisplay.setText(inputDisplay.getText() + "*");

			} else if (id == KeyEvent.VK_9) {
				password.append("9");
				inputDisplay.setText(inputDisplay.getText() + "*");

			} else if (id == KeyEvent.VK_0) {
				password.append("0");
				inputDisplay.setText(inputDisplay.getText() + "*");

			} else if (id == KeyEvent.VK_ENTER) {
				if (pwProtect && inputDisplay.getText().length() > 0) {
					this.getPassword();
					inputStatus = true;

				} else if (inputDisplay.getText().length() > 0) {
					inputStatus = true;
				}

			} else if (id == KeyEvent.VK_BACK_SPACE) {

				if (inputDisplay.getText().length() > 0) {
					inputDisplay.setText(inputDisplay.getText().substring(0,
							inputDisplay.getText().length() - 1));

					password = password.deleteCharAt(password.length() - 1);
				}

			} else if (id == KeyEvent.VK_ESCAPE) {
				inputDisplay.setText("");
			}

		} else {

			if (id == KeyEvent.VK_1) {
				inputDisplay.setText(inputDisplay.getText() + "1");

			} else if (id == KeyEvent.VK_2) {
				inputDisplay.setText(inputDisplay.getText() + "2");

			} else if (id == KeyEvent.VK_3) {
				inputDisplay.setText(inputDisplay.getText() + "3");

			} else if (id == KeyEvent.VK_4) {
				inputDisplay.setText(inputDisplay.getText() + "4");

			} else if (id == KeyEvent.VK_5) {
				inputDisplay.setText(inputDisplay.getText() + "5");

			} else if (id == KeyEvent.VK_6) {
				inputDisplay.setText(inputDisplay.getText() + "6");

			} else if (id == KeyEvent.VK_7) {
				inputDisplay.setText(inputDisplay.getText() + "7");

			} else if (id == KeyEvent.VK_8) {
				inputDisplay.setText(inputDisplay.getText() + "8");

			} else if (id == KeyEvent.VK_9) {
				inputDisplay.setText(inputDisplay.getText() + "9");

			} else if (id == KeyEvent.VK_0) {
				inputDisplay.setText(inputDisplay.getText() + "0");

			} else if (id == KeyEvent.VK_PERIOD) {
				if (decFlag) {
					for (int i = 0; i < inputDisplay.getText().length(); i++) {
						if (inputDisplay.getText().charAt(i) == '.') {
							decFlag = false;
						}
					}
					if (decFlag) {
						inputDisplay.setText(inputDisplay.getText() + ".");
					}
					decFlag = true;
				}

			} else if (id == KeyEvent.VK_ENTER) {
				if (inputDisplay.getText().length() > 0) {
					inputStatus = true;
				}

			} else if (id == KeyEvent.VK_ESCAPE) {
				inputDisplay.setText("");

			} else if (id == KeyEvent.VK_BACK_SPACE) {
				if (inputDisplay.getText().length() > 0) {

					inputDisplay.setText(inputDisplay.getText().substring(0,
							inputDisplay.getText().length() - 1));
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Set the text for the main display
	 * 
	 * @param text
	 *            Message to be displayed
	 */
	public void setMainDisplay(String text) {
		mainDisplay.setText(text);
	}

	/**
	 * Adds text to the main display
	 * 
	 * @param text
	 *            Message to be displayed
	 */
	public void appendMainDisplay(String text) {
		mainDisplay.append(text);
	}

	/**
	 * Enables or disables the decimal button
	 * 
	 * @param flag
	 */
	public void setDecimal(boolean flag) {
		decFlag = flag;
	}

	/**
	 * Sets the password masker on or off
	 * 
	 * @param pwMasker
	 */
	public void setPwMasker(boolean flag) {
		pwProtect = flag;
	}

	/**
	 * Return the password
	 * 
	 * @return Password
	 */
	public String getPassword() {
		return password.toString();
	}

	/**
	 * Wait until user enters information
	 * 
	 * @return Input form user
	 */
	public String getInput() {

		if (pwProtect) {
			password = new StringBuffer();
		}
		String text;

		while (!inputStatus) {
			Thread.yield();
		}

		inputStatus = false;
		text = inputDisplay.getText();
		mainDisplay.append(text);
		inputDisplay.setText("");

		if (pwProtect) {
			return getPassword();
		}

		return text;
	}
}