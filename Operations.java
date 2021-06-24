package examples.mlr;

public class Operations {
    public double[][] multiply(double[][] a, double[][] b) {
        double c[][] = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                c[i][j] = 0;
                for (int k = 0; k < a[0].length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    public double[][] invert(double a[][]) {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;
        gaussian(a, index);
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    public void gaussian(double a[][], int index[]) {
        int n = index.length;
        double c[] = new double[n];
        for (int i = 0; i < n; ++i)
            index[i] = i;
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1)
                    c1 = c0;
            }
            c[i] = c1;
        }
        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];
                a[index[i]][j] = pj;
                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }

    public double solveDeterminant(double[][] matrix) {
        double[] mainDiagonals = new double[3];
        double[] secondaryDiagonals = new double[3]; 
        double mainDiagonal=0, secondaryDiagonal=0;

        mainDiagonals[0] += (matrix[0][0] * matrix[1][1] * matrix[2][2]);
        mainDiagonals[1] += (matrix[0][1] * matrix[1][2] * matrix[2][3]);
        mainDiagonals[2] += (matrix[0][2] * matrix[1][3] * matrix[2][4]);

        secondaryDiagonals[0] += (matrix[2][0] * matrix[1][1] * matrix[0][2]);
        secondaryDiagonals[1] += (matrix[2][1] * matrix[1][2] * matrix[0][3]);
        secondaryDiagonals[2] += (matrix[2][2] * matrix[1][3] * matrix[0][4]);

        for (int i = 0; i < matrix.length; i++) {
            mainDiagonal += mainDiagonals[i];
            secondaryDiagonal += secondaryDiagonals[i];
        }
        return mainDiagonal - secondaryDiagonal;
    }
}