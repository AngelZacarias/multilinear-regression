package examples.mlr;
import java.util.ArrayList;

public class MatrixOperations implements MLRProcedure {
    Operations operations;

    MatrixOperations() {
        this.operations = new Operations();
    }

    @Override
    public Element calculate(ArrayList<Element> data) {
        Matrixs M = transformIndependentToMatrix(data);

        double[][] y = getYMatrix(data);
        double[][] firstPart = operations.invert(operations.multiply(M.xTransposed, M.x));
        double[][] secondPart = operations.multiply(M.xTransposed, y);
        double[][] thirdPart = operations.multiply(firstPart, secondPart);
        for (int i = 0; i < thirdPart.length; i++) {
            for (int j = 0; j < 1; j++) {
            }
        }
        Element dt = new Element(thirdPart[0][0], thirdPart[1][0], thirdPart[2][0]);
        return dt;
    }

    private Matrixs transformIndependentToMatrix(ArrayList<Element> data) {
        double[][] transposed = new double[3][data.size()];
        double[][] matrix = new double[data.size()][3];
        for (int j = 0; j < data.size(); j++) {
            matrix[j][0] = 1;
            matrix[j][1] = data.get(j).getX1();
            matrix[j][2] = data.get(j).getX2();

            transposed[0][j] = 1;
            transposed[1][j] = data.get(j).getX1();
            transposed[2][j] = data.get(j).getX2();
        }
        Matrixs result = new Matrixs();
        result.x = matrix;
        result.xTransposed = transposed;
        return result;
    }

    private double[][] getYMatrix(ArrayList<Element> data) {
        double[][] result = new double[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i).getY();
        }
        return result;
    }
}

class Matrixs {
    double x[][];
    double xTransposed[][];
}