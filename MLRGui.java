package examples.mlr;
import jade.core.AID;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MLRGUI extends JFrame {	
	private MLRAgent mlrAgent;
    private JTextField valueX1;
    private JTextField valueX2;

  MLRGUI(MLRAgent a) {
		super(a.getLocalName());
		mlrAgent = a;

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		p.add(new JLabel("x1:"));
		valueX1 = new JTextField(15);
		p.add(valueX1);
		p.add(new JLabel("x2:"));
    	valueX2 = new JTextField(15);
		p.add(valueX2);
		getContentPane().add(p, BorderLayout.CENTER);
		JButton addButton = new JButton("Calcular valor");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String value1ToPredict = valueX1.getText().trim();
					String value2ToPredict = valueX2.getText().trim();
					mlrAgent.makePrediction(Double.parseDouble(value1ToPredict), Double.parseDouble(value2ToPredict));
					valueX1.setText("");
					valueX2.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(MLRGUI.this, "valores erróneos. "+e.getMessage(), "Error: ", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				mlrAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}	
}