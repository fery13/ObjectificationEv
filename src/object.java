import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Jama.Matrix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;


class EvenOddRenderer implements TableCellRenderer {

	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		Color foreground, background;

		double val = Double.parseDouble(value.toString());
		val = 255 - 255 * ((double) val / (double) object.max);
		Color col = new Color((int) val, 0, 0);
		foreground = Color.WHITE;
		background = col;

		renderer.setForeground(foreground);
		renderer.setBackground(background);
		return renderer;
	}
}




public class object {

	public static int max = 0;
	
	public static void main (String [] args) throws FileNotFoundException {
		
		double [][] matt = readData("C:/Users/farshad.toosi/Documents/smaple.txt");
				
		int v = matt.length;
		
		GA try1 = new GA (500, 100, 10, 15, 5, v, matt); //int generation, int population, int tournoment, double crs, double matu, int v, double [][] sim
		MatObj lastRes = try1.GArun();
		
		max = 9;  // Find the maximum value in the similarity matrix. It is needed in order to encode the colors
		TableCreation(lastRes.getMtarix(), v, lastRes.getOrdering()); // send your data for heat map visualization.
		
		
		
	}
	
	
	
	
	
	public static void TableCreation(double[][] sim, int v, int[] ord) {

		final Object rowData[][] = new Object[v][v];
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				rowData[i][j] = sim[i][j];
			}
		}
		final String columnNames[] = new String[v];
		for (int i = 0; i < v; i++) {
			columnNames[i] = ord[i] + "";
		}
		final JTable table = new JTable(rowData, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setDefaultRenderer(Object.class, new EvenOddRenderer());
		JFrame frame = new JFrame("Heat Map");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(50*v, 50*v);
		frame.setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	
	
	public static double [][] getSimilarityMatrix(double [][] matrix) {
		double [][] SquareMatrix;
		Matrix A = new Matrix(matrix);
		Matrix B = A.transpose();
		Matrix C = A.times(B);
		SquareMatrix = C.getArray();
		return SquareMatrix;
	}
	
	public static double [][] readData(String addr) throws FileNotFoundException {
		
				File file = new File(addr);
				Scanner in = new Scanner (file);
				ArrayList <String> lines = new ArrayList<String> ();
				
				while(in.hasNextLine()) {
					lines.add(in.nextLine());
					
				}
				int f = lines.get(0).split(" ").length;
				int v = lines.size();
				
				double [][] mat = new double[lines.size()][f]; 
				
				for(int i=0;i<lines.size();i++) {
					String [] l = lines.get(i).split(" ");
					for(int j=0;j<f;j++) {
						mat[i][j] = Integer.parseInt(l[j]);
					}
				}
			 	
				return getSimilarityMatrix(mat);
		 
	}
	
	
}
