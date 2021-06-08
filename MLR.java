package examples.mlr;
import java.util.ArrayList;


public class MLR {
    private double B0;
    private double B1;
    private double B2;
    MLRProcedure procedure;

    MLR(MLRProcedure procedure) {
        this.procedure = procedure;
    }

    public double getB0() {
        return this.B0;
    }

    public double getB2() {
        return this.B2;
    }
    
    public double getB1() {
        return this.B1;
    }

    public void setProcedure(MLRProcedure procedure) {
        this.procedure = procedure;
    }

    public void setData(ArrayList<Element> dataSet) {
        Element dr = this.procedure.calculate(dataSet);
        B0 = dr.getX1();
        B1 = dr.getX2();
        B2 = dr.getY();
    }

    public double getPrediction(double x1, double x2) {
        return this.B0 + this.B1 * x1 + this.B2 * x2;
    }
}