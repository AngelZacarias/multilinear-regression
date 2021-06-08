package examples.mlr;
import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MLRGui extends JFrame {	
	private MLRAgent myAgent;
    private JTextField fieldValue1;
    private JTextField fieldValue2;

	MLRGui(MLRAgent a) {
		super(a.getLocalName());
		myAgent = a;

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		p.add(new JLabel("x1 Value:"));
		fieldValue1 = new JTextField(15);
		p.add(fieldValue1);
		p.add(new JLabel("x2 Value:"));
    	fieldValue2 = new JTextField(15);
		p.add(fieldValue2);
		getContentPane().add(p, BorderLayout.CENTER);
		JButton addButton = new JButton("Calculate Value");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String value1ToPredict = fieldValue1.getText().trim();
					String value2ToPredict = fieldValue2.getText().trim();
					myAgent.makePrediction(Double.parseDouble(value1ToPredict), Double.parseDouble(value2ToPredict));
					fieldValue1.setText("");
					fieldValue2.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(MLRGui.this, "Error: Invalid values. "+e.getMessage(), "Error: ", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
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