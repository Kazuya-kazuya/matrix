package matrix;

import java.util.Random;
import java.util.function.Function;

public class Main {
	public static void main(String[] args) {
		//ランダム要素の行列生成
		Matrix mat1 = Matrix.getRandFillMatrix(4, 2);
		Matrix mat2 = Matrix.getRandFillMatrix(2, 3);
		System.out.println(mat1.toString());
		//行列の内積
		mat1.dot(mat2);
		System.out.println(mat1);
		//転置行列
		Matrix matT = mat1.transpose();
		System.out.println(matT.toString());
		//ラムダ式つかってみた
		Function<Float, Float> func = (i) -> {return (float)Math.exp(i);};
		mat1.useFunc(func);
		System.out.println(mat1);
	}
}

class Matrix{
	float[][] matrix;

	public Matrix(float[][] matrix) {
		this.matrix = matrix;
	}

	//0.0埋めの行列
	public static Matrix getZeroFillMatrix(int x, int y) {
		float[][] mat = new float[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				mat[i][j] = 0.0f;
			}
		}
		Matrix m = new Matrix(mat);
		return m;
	}

	//1.0埋めの行列
	public static Matrix getOneFillMatrix(int x, int y) {
		float[][] mat = new float[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				mat[i][j] = 1.0f;
			}
		}
		Matrix m = new Matrix(mat);
		return m;
	}

	//ランダム埋めの行列
	public static Matrix getRandFillMatrix(int x, int y) {
		Random rand = new Random();
		float[][] mat = new float[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				mat[i][j] = rand.nextFloat();
			}
		}
		Matrix m = new Matrix(mat);
		return m;
	}

	//スカラー和
	public void add(float num) {
		for(int i = 0; i < this.getXLength(); i++) {
			for(int j = 0; j < this.getYLength(); j++) {
				this.matrix[i][j] += num;
			}
		}
	}

	//行列和
	public void add(Matrix matrix) {
		for(int i = 0; i < this.getXLength(); i++) {
			for(int j = 0; j < this.getYLength(); j++) {
				float[][] mat = matrix.getMatrix();
				this.matrix[i][j] += mat[i][j];
			}
		}
	}

	//スカラー倍
	public void multiply(float num) {
		for(int i = 0; i < this.getXLength(); i++) {
			for(int j = 0; j < this.getYLength(); j++) {
				this.matrix[i][j] *= num;
			}
		}
	}

	//内積
	public void dot(Matrix matrix) {
		float[][] result = getZeroFillMatrix(this.getXLength(), matrix.getYLength()).getMatrix();
		float[][] mat = matrix.getMatrix();
		for(int i = 0; i < this.getXLength(); i++) {
			for(int j = 0; j < matrix.getYLength(); j++) {
				for(int k = 0; k < this.getYLength(); k++) {
					result[i][j] += this.matrix[i][k] * mat[k][j];
				}
			}
		}
		setMatrix(result);
	}

	//転置行列
	public Matrix transpose() {
		float[][] mat = new float[this.getYLength()][this.getXLength()];
		for(int i = 0; i < this.getXLength(); i++) {
			for(int j = 0; j < this.getYLength(); j++) {
				mat[j][i] = this.matrix[i][j];
			}
		}
		Matrix result = new Matrix(mat);
		return result;
	}

	//関数処理
	public void useFunc(Function<Float, Float> func) {
		for(int i = 0; i < this.getXLength(); i++) {
			for(int j = 0; j < this.getYLength(); j++) {
				this.matrix[i][j] = func.apply(this.matrix[i][j]);
			}
		}
	}

	public float[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(float[][] matrix) {
		this.matrix = matrix;
	}

	//行数の取得
	public int getXLength() {
		return this.matrix.length;
	}

	//列数の取得
	public int getYLength() {
		return this.matrix[0].length;
	}

	public String toString() {
		String str = "";
		str += "[\n";
		for(int i = 0; i < this.getXLength(); i++) {
			str += "[";
			for(int j = 0; j < this.getYLength(); j++) {
				str += this.matrix[i][j];
				if(j != this.getYLength() - 1) {
					str += ", ";
				}
			}
			str += "]";
			if(i != this.getXLength() - 1) {
				str += ",";
			}
			str += "\n";
		}
		str += "]";
		return str;
	}
}