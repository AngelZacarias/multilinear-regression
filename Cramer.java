package examples.mlr;
import java.util.ArrayList;
import java.util.*;
import java.lang.*;

public class Cramer implements MLRProcedure {
  Operations operations;
  Map<String, Double> valuesMap = new HashMap<String, Double>();

  Cramer() {
    this.operations = new Operations();
  }

  @Override
  public DataRegister calculate(ArrayList<DataRegister> data) {
    double[][] matrixForSystemDeterminant, matrixForDeterminantX,
      matrixForDeterminantX2, matrixForDeterminantY = new double[3][5];
    double x1, x2, y, systemDeterminant;
    this.fillValuesMap(data);

    matrixForSystemDeterminant = this.getMatrixForSystemDeterminant();
    matrixForDeterminantX = this.getMatrixForDeterminantX();
    matrixForDeterminantX2 = this.getMatrixForDeterminantX2();
    matrixForDeterminantY = this.getMatrixForDeterminantY();

    System.out.println("Matrices:");
    System.out.println("matrixForSystemDeterminant: " + Arrays.deepToString(matrixForSystemDeterminant));
    System.out.println("matrixForDeterminantX: " + Arrays.deepToString(matrixForDeterminantX));
    System.out.println("matrixForDeterminantX2: " + Arrays.deepToString(matrixForDeterminantX2));
    System.out.println("matrixForDeterminantY: " + Arrays.deepToString(matrixForDeterminantY));
    System.out.println("");

    systemDeterminant = operations.solveDeterminant(matrixForSystemDeterminant);
    x1 = operations.solveDeterminant(matrixForDeterminantX);
    x1 = x1/systemDeterminant;
    x2 = operations.solveDeterminant(matrixForDeterminantX2);
    x2 = x2/systemDeterminant;
    y = operations.solveDeterminant(matrixForDeterminantY);
    y = y/systemDeterminant;

    DataRegister dt = new DataRegister(x1, x2, y); //Solutions
    return dt;
  }

  public double[][] getMatrixForDeterminantY() {
    double[][] matrix = new double[3][5];
    matrix[0][0] = valuesMap.get("n");
    matrix[0][1] = valuesMap.get("summationX1");
    matrix[0][2] = valuesMap.get("summationY");
    matrix[0][3] = valuesMap.get("n");
    matrix[0][4] = valuesMap.get("summationX1");

    matrix[1][0] = valuesMap.get("summationX1");
    matrix[1][1] = valuesMap.get("summationX1Square");
    matrix[1][2] = valuesMap.get("summationX1Y");
    matrix[1][3] = valuesMap.get("summationX1");
    matrix[1][4] = valuesMap.get("summationX1Square");

    matrix[2][0] = valuesMap.get("summationX2");
    matrix[2][1] = valuesMap.get("summationX1X2");
    matrix[2][2] = valuesMap.get("summationX2Y");
    matrix[2][3] = valuesMap.get("summationX2");
    matrix[2][4] = valuesMap.get("summationX1X2");
    return matrix;
  }

  public double[][] getMatrixForDeterminantX2() {
    double[][] matrix = new double[3][5];
    matrix[0][0] = valuesMap.get("n");
    matrix[0][1] = valuesMap.get("summationY");
    matrix[0][2] = valuesMap.get("summationX2");
    matrix[0][3] = valuesMap.get("n");
    matrix[0][4] = valuesMap.get("summationY");

    matrix[1][0] = valuesMap.get("summationX1");
    matrix[1][1] = valuesMap.get("summationX1Y");
    matrix[1][2] = valuesMap.get("summationX1X2");
    matrix[1][3] = valuesMap.get("summationX1");
    matrix[1][4] = valuesMap.get("summationX1Y");

    matrix[2][0] = valuesMap.get("summationX2");
    matrix[2][1] = valuesMap.get("summationX2Y");
    matrix[2][2] = valuesMap.get("summationX2Square");
    matrix[2][3] = valuesMap.get("summationX2");
    matrix[2][4] = valuesMap.get("summationX2Y");
    return matrix;
  }

  public double[][] getMatrixForDeterminantX() {
    double[][] matrix = new double[3][5];
    matrix[0][0] = valuesMap.get("summationY");
    matrix[0][1] = valuesMap.get("summationX1");
    matrix[0][2] = valuesMap.get("summationX2");
    matrix[0][3] = valuesMap.get("summationY");
    matrix[0][4] = valuesMap.get("summationX1");

    matrix[1][0] = valuesMap.get("summationX1Y");
    matrix[1][1] = valuesMap.get("summationX1Square");
    matrix[1][2] = valuesMap.get("summationX1X2");
    matrix[1][3] = valuesMap.get("summationX1Y");
    matrix[1][4] = valuesMap.get("summationX1Square");

    matrix[2][0] = valuesMap.get("summationX2Y");
    matrix[2][1] = valuesMap.get("summationX1X2");
    matrix[2][2] = valuesMap.get("summationX2Square");
    matrix[2][3] = valuesMap.get("summationX2Y");
    matrix[2][4] = valuesMap.get("summationX1X2");
    return matrix;
  }

  public double[][] getMatrixForSystemDeterminant() {
    double[][] matrix = new double[3][5];
    matrix[0][0] = valuesMap.get("n");
    matrix[0][1] = valuesMap.get("summationX1");
    matrix[0][2] = valuesMap.get("summationX2");
    matrix[0][3] = valuesMap.get("n");
    matrix[0][4] = valuesMap.get("summationX1");

    matrix[1][0] = valuesMap.get("summationX1");
    matrix[1][1] = valuesMap.get("summationX1Square");
    matrix[1][2] = valuesMap.get("summationX1X2");
    matrix[1][3] = valuesMap.get("summationX1");
    matrix[1][4] = valuesMap.get("summationX1Square");

    matrix[2][0] = valuesMap.get("summationX2");
    matrix[2][1] = valuesMap.get("summationX1X2");
    matrix[2][2] = valuesMap.get("summationX2Square");
    matrix[2][3] = valuesMap.get("summationX2");
    matrix[2][4] = valuesMap.get("summationX1X2");
    return matrix;
  }

  public void fillValuesMap(ArrayList<DataRegister> data) {
    double n=17, summationX1, summationX2, summationY, summationX2Square, 
    summationX1Square, summationX1X2, summationX1Y, summationX2Y;
    
    summationX1 = this.getSummationX1(data);
    summationX2 = this.getSummationX2(data);
    summationY = this.getSummationY(data);

    summationX1Square = this.getSummationX1Square(data);
    summationX2Square = this.getSummationX2Square(data);

    summationX1X2 = this.getSummationX1X2(data);
    summationX1Y = this.getSummationX1Y(data);
    summationX2Y = this.getSummationX2Y(data);

    //At the end make the assignments
    valuesMap.put("n", n);
    valuesMap.put("summationX1", summationX1);
    valuesMap.put("summationX2", summationX2);
    valuesMap.put("summationY", summationY);
    valuesMap.put("summationX1Square", summationX1Square);
    valuesMap.put("summationX2Square", summationX2Square);
    valuesMap.put("summationX1X2", summationX1X2);
    valuesMap.put("summationX1Y", summationX1Y);
    valuesMap.put("summationX2Y", summationX2Y);

    System.out.println("Dictionary:");
    System.out.println("summationX1" + ": " + String.valueOf(valuesMap.get("summationX1")));
    System.out.println("summationX2" + ": " + String.valueOf(valuesMap.get("summationX2")));
    System.out.println("summationY" + ": " + String.valueOf(valuesMap.get("summationY")));
    System.out.println("summationX1Square" + ": " + String.valueOf(valuesMap.get("summationX1Square")));
    System.out.println("summationX2Square" + ": " + String.valueOf(valuesMap.get("summationX2Square")));
    System.out.println("summationX1X2" + ": " + String.valueOf(valuesMap.get("summationX1X2")));
    System.out.println("summationX1Y" + ": " + String.valueOf(valuesMap.get("summationX1Y")));
    System.out.println("summationX2Y" + ": " + String.valueOf(valuesMap.get("summationX2Y")));
  }

  public double getSummationX1(ArrayList<DataRegister> data) {
    double summationX1 = 0.0;
    for (DataRegister dataRegister : data) {
      summationX1 += dataRegister.getX1();
    }
    return summationX1;
  }

  public double getSummationX2(ArrayList<DataRegister> data) {
    double summationX2 = 0.0;
    for (DataRegister dataRegister : data) {
      summationX2 += dataRegister.getX2();
    }
    return summationX2;
  }

  public double getSummationY(ArrayList<DataRegister> data) {
    double summationY = 0.0;
    for (DataRegister dataRegister : data) {
      summationY += dataRegister.getY();
    }
    return summationY;
  }

  public double getSummationX1Square(ArrayList<DataRegister> data) {
    double summationX1Square = 0.0;
    for (DataRegister dataRegister : data) {
      summationX1Square += Math.pow(dataRegister.getX1(), 2);
    }
    return summationX1Square;
  }

  public double getSummationX2Square(ArrayList<DataRegister> data) {
    double summationX2Square = 0.0;
    for (DataRegister dataRegister : data) {
      summationX2Square += Math.pow(dataRegister.getX2(), 2);
    }
    return summationX2Square;
  }

  public double getSummationX1X2(ArrayList<DataRegister> data) {
    double summationX1X2 = 0.0;
    for (DataRegister dataRegister : data) {
      summationX1X2 += (dataRegister.getX1() * dataRegister.getX2());
    }
    return summationX1X2;
  }

  public double getSummationX1Y(ArrayList<DataRegister> data) {
    double summationX1Y = 0.0;
    for (DataRegister dataRegister : data) {
      summationX1Y += (dataRegister.getX1() * dataRegister.getY());
    }
    return summationX1Y;
  }

  public double getSummationX2Y(ArrayList<DataRegister> data) {
    double summationX2Y = 0.0;
    for (DataRegister dataRegister : data) {
      summationX2Y += (dataRegister.getX2() * dataRegister.getY());
    }
    return summationX2Y;
  }
}