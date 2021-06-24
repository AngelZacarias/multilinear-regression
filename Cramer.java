package examples.mlr;
import java.util.ArrayList;
import java.util.*;
import java.lang.*;

public class Cramer implements MLRProcedure {
  Operations operations;
  Map<String, Double> values = new HashMap<String, Double>();

  Cramer() {
    this.operations = new Operations();
  }

  @Override
  public Element calculate(ArrayList<Element> data) {
    double[][] MForSystemDeterminant, MForDeterminantX,
    MForDeterminantX2, MForDeterminantY = new double[3][5];
    double x1, x2, y, systemDeterminant;
    this.fillvalues(data);

    MForSystemDeterminant = this.getMForSystemDeterminant();
    MForDeterminantX = this.getMForDeterminantX();
    MForDeterminantX2 = this.getMForDeterminantX2();
    MForDeterminantY = this.getMForDeterminantY();

    systemDeterminant = operations.solveDeterminant(MForSystemDeterminant);
    x1 = operations.solveDeterminant(MForDeterminantX);
    x1 = x1/systemDeterminant;
    x2 = operations.solveDeterminant(MForDeterminantX2);
    x2 = x2/systemDeterminant;
    y = operations.solveDeterminant(MForDeterminantY);
    y = y/systemDeterminant;

    Element dt = new Element(x1, x2, y); //Solutions
    return dt;
  }

  public double[][] getMForDeterminantY() {
    double[][] M = new double[3][5];
    M[0][0] = values.get("n");
    M[0][1] = values.get("sx1");
    M[0][2] = values.get("sy");
    M[0][3] = values.get("n");
    M[0][4] = values.get("sx1");

    M[1][0] = values.get("sx1");
    M[1][1] = values.get("sx1sq");
    M[1][2] = values.get("sx1Y");
    M[1][3] = values.get("sx1");
    M[1][4] = values.get("sx1sq");

    M[2][0] = values.get("sx2");
    M[2][1] = values.get("sx1X2");
    M[2][2] = values.get("sx2Y");
    M[2][3] = values.get("sx2");
    M[2][4] = values.get("sx1X2");
    return M;
  }

  public double[][] getMForDeterminantX2() {
    double[][] M = new double[3][5];
    M[0][0] = values.get("n");
    M[0][1] = values.get("sy");
    M[0][2] = values.get("sx2");
    M[0][3] = values.get("n");
    M[0][4] = values.get("sy");

    M[1][0] = values.get("sx1");
    M[1][1] = values.get("sx1Y");
    M[1][2] = values.get("sx1X2");
    M[1][3] = values.get("sx1");
    M[1][4] = values.get("sx1Y");

    M[2][0] = values.get("sx2");
    M[2][1] = values.get("sx2Y");
    M[2][2] = values.get("sx2sq");
    M[2][3] = values.get("sx2");
    M[2][4] = values.get("sx2Y");
    return M;
  }

  public double[][] getMForDeterminantX() {
    double[][] M = new double[3][5];
    M[0][0] = values.get("sy");
    M[0][1] = values.get("sx1");
    M[0][2] = values.get("sx2");
    M[0][3] = values.get("sy");
    M[0][4] = values.get("sx1");

    M[1][0] = values.get("sx1Y");
    M[1][1] = values.get("sx1sq");
    M[1][2] = values.get("sx1X2");
    M[1][3] = values.get("sx1Y");
    M[1][4] = values.get("sx1sq");

    M[2][0] = values.get("sx2Y");
    M[2][1] = values.get("sx1X2");
    M[2][2] = values.get("sx2sq");
    M[2][3] = values.get("sx2Y");
    M[2][4] = values.get("sx1X2");
    return M;
  }

  public double[][] getMForSystemDeterminant() {
    double[][] M = new double[3][5];
    M[0][0] = values.get("n");
    M[0][1] = values.get("sx1");
    M[0][2] = values.get("sx2");
    M[0][3] = values.get("n");
    M[0][4] = values.get("sx1");

    M[1][0] = values.get("sx1");
    M[1][1] = values.get("sx1sq");
    M[1][2] = values.get("sx1X2");
    M[1][3] = values.get("sx1");
    M[1][4] = values.get("sx1sq");

    M[2][0] = values.get("sx2");
    M[2][1] = values.get("sx1X2");
    M[2][2] = values.get("sx2sq");
    M[2][3] = values.get("sx2");
    M[2][4] = values.get("sx1X2");
    return M;
  }

  public void fillvalues(ArrayList<Element> data) {
    double n=17, sx1, sx2, sy, sx2sq, 
    sx1sq, sx1X2, sx1Y, sx2Y;
    
    sx1 = this.operate(data, "sumx1");
    sx2 = this.operate(data, "sumx2");
    sy = this.operate(data, "sumy");

    sx1sq = this.operate(data, "sumx1sq");
    sx2sq = this.operate(data, "sumx2sq");

    sx1X2 = this.operate(data, "sumx1x2");
    sx1Y = this.operate(data, "sumx1y");
    sx2Y = this.operate(data, "sumx2y");

    

    //At the end make the assignments
    values.put("n", n);
    values.put("sx1", sx1);
    values.put("sx2", sx2);
    values.put("sy", sy);
    values.put("sx1sq", sx1sq);
    values.put("sx2sq", sx2sq);
    values.put("sx1X2", sx1X2);
    values.put("sx1Y", sx1Y);
    values.put("sx2Y", sx2Y);

    System.out.println("summationX1X2" + ": " + String.valueOf(sx1));
  }

  public double operate(ArrayList<Element> data, String operation) {
    double result = 0.0;
    for (Element elm : data) {
      switch(operation){
        case "sumx1":
            result = sumx1(result, elm.getX1());
            break;
        case "sumx2":
            result = sumx2(result, elm.getX2());
            break;
        case "sumy":
            result = sumy(result, elm.getY());
            break;
        case "sumx1sq":
            result = sumx1sq(result, elm.getX1());
            break;
        case "sumx2sq":
            result = sumx2sq(result, elm.getX2());
            break;
        case "sumx1x2":
            result = sumx1x2(result, elm.getX1(), elm.getX2());
            break;
        case "sumx1y":
            result = sumx1y(result, elm.getX1(), elm.getY());
            break;
        case "sumx2y":
            result = sumx2y(result, elm.getX2(), elm.getY());
            break;
        default:
            result = 0;
            break;
      }
    }
    return result;
  }

  public double sumx1(double sum, double value) {
    return sum + value;
  }

  public double sumx2(double sum, double value) {
    return sum  + value;
  }

  public double sumy(double sum, double value) {
    return sum  + value;
  }

  public double sumx1sq(double sum, double value) {
    return sum + (value * value);
  }

  public double sumx2sq(double sum, double value) {
    return sum  + (value * value);
  }

  public double sumx1x2(double sum, double value1, double value2) {
    return sum  + (value1 * value2);
  }

  public double sumx1y(double sum, double value1, double value2) {
    return sum  + (value1 * value2);
  }

  public double sumx2y(double sum, double value1, double value2) {
    return sum + (value1 * value2);
  }
}