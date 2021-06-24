package examples.mlr;
import java.util.ArrayList;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.DFService;

public class MLRAgent extends Agent {
  	MLR mlr;
  	MLRGUI myGui;
  	protected void setup() {
    	System.out.println("Agent " + getLocalName() + " has started.");
    	mlr = new MLR(new Cramer());
    	mlr.setData(this.getDataset());
		myGui = new MLRGUI(this);
    	myGui.showGui();
	}

	public void makePrediction(double x1, double x2) {
		addBehaviour(new OneShotBehaviour(){
		  @Override
		  public void action() {
			double yPredicted = mlr.getPrediction(x1, x2);
			double b0 = mlr.getB0();
			double b1 = mlr.getB1();
			double b2 = mlr.getB2();
			System.out.println("Multiple Linear Regression Calculator");
			System.out.println("y = x1b1 + x2b2 + b0");
			System.out.println(String.valueOf(yPredicted) + " = " + String.valueOf(b0) + 
				" + " + String.valueOf(b1) + "(" + String.valueOf(x1) + ")" + " + " + 
				String.valueOf(b2) + "(" + String.valueOf(x2) + ")");
		  }
		});
	  }

	private ArrayList<Element> getDataset() {
		//Hardcoded array for testing
		ArrayList<Element> data = new ArrayList<Element>();
		data.add(new Element(41.9, 29.1, 251.3));
		data.add(new Element(43.4, 29.3, 251.3));
		data.add(new Element(43.9, 29.5, 248.3));
		data.add(new Element(44.5, 29.7, 267.5));
		data.add(new Element(47.3, 29.9, 273));
		data.add(new Element(47.5, 30.3, 276.5));
		data.add(new Element(47.9, 30.5, 270.3));
		data.add(new Element(50.2, 30.7, 274.9));
		data.add(new Element(52.8, 30.8, 285));
		data.add(new Element(53.2, 30.9, 290));
		data.add( new Element(56.7, 31.5, 297));
		data.add( new Element(57.0, 31.7, 302.5));
		data.add( new Element(63.5, 31.9, 304.5));
		data.add( new Element(65.3, 32, 309.3));
		data.add( new Element(71.1, 32.1, 321.7));
		data.add( new Element(77, 32.5, 330.7));
		data.add( new Element(77.8, 32.9, 349));
		return data;
	}
}