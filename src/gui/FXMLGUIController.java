package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

public class FXMLGUIController implements Initializable{
	@FXML
	private TextField iniciali;
	@FXML
	private TextField inicialj;
	@FXML
	private ToggleGroup Origen;
	@FXML
	private TextField baseorigen1i;
	@FXML
	private TextField baseorigen1j;
	@FXML
	private TextField baseorigen2i;
	@FXML
	private TextField baseorigen2j;
	@FXML
	private ToggleGroup Destino;
	@FXML
	private TextField basedestino1i;
	@FXML
	private TextField basedestino1j;
	@FXML
	private TextField basedestino2i;
	@FXML
	private TextField basedestino2j;
	@FXML
	private TextField resultadoi;
	@FXML
	private TextField resultadoj;
	@FXML
	private Canvas grafico;
	@FXML
	private RadioButton canonor;
	@FXML
    private RadioButton canondes;
	private GraphicsContext gc;
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		gc = grafico.getGraphicsContext2D();
		clear();
	}

	@FXML
    void convertir(ActionEvent event) {
		double[] inicial = new double[2];
		try {
			inicial[0] = Double.parseDouble(iniciali.getText());
			inicial[1] = Double.parseDouble(inicialj.getText());
		} catch (Exception e) {
			System.err.println("Inserte números");
		}
		iniciali.clear();
		inicialj.clear();
		double[] origen1 = new double[2];
		double[] origen2 = new double[2];
		double[] destino1 = new double[2];
		double[] destino2 = new double[2];
		gc.setStroke(Color.BLACK);
		drawbase(origen1, origen2);
		gc.setStroke(Color.BLUE);
		drawbase(destino1, destino2);
		if (Origen.getSelectedToggle().equals(canonor)) {
			origen1[0] = 1;
			origen1[1] = 0;
			origen2[0] = 0;
			origen2[1] = 1;
			gc.setLineWidth(2);
			gc.setStroke(Color.RED);
			drawRect(0,0,inicial[0], inicial[1]);
		}else{
			try {
				origen1[0] = Double.parseDouble(baseorigen1i.getText());
				origen1[1] = Double.parseDouble(baseorigen1j.getText());
				origen2[0] = Double.parseDouble(baseorigen2i.getText());
				origen2[1] = Double.parseDouble(baseorigen2j.getText());
			} catch (Exception e) {
				origen1[0] = 0;
				origen1[1] = 0;
				origen2[0] = 0;
				origen2[1] = 0;
			}
			gc.setStroke(Color.RED);
			drawRect(0,0,(inicial[0]*origen1[0])+(inicial[1]*origen2[0]),(inicial[0]*origen1[1])+(inicial[1]*origen2[1]));
		}
		baseorigen1i.clear();
		baseorigen1j.clear();
		baseorigen2i.clear();
		baseorigen2j.clear();
		if(Destino.getSelectedToggle().equals(canondes)){
			destino1[0] = 1;
			destino1[1] = 0;
			destino2[0] = 0;
			destino2[1] = 1;
		}else{
			try {
				destino1[0] = Double.parseDouble(basedestino1i.getText());
				destino1[1] = Double.parseDouble(basedestino1j.getText());
				destino2[0] = Double.parseDouble(basedestino2i.getText());
				destino2[1] = Double.parseDouble(basedestino2j.getText());
			} catch (Exception e) {
				destino1[0] = 0;
				destino1[1] = 0;
				destino2[0] = 0;
				destino2[1] = 0;
			}
		}
		basedestino1i.clear();
		basedestino1j.clear();
		basedestino2i.clear();
		basedestino2j.clear();
		clear();
		double[][] matriz = mt(origen1, origen2, destino1, destino2);
		double[] resultado = mult(inicial, matriz);
		resultadoi.setText(String.valueOf(resultado[0]));
		resultadoj.setText(String.valueOf(resultado[1]));
	}
	double[] mult(double[] v, double[][] m){
		double[] mult = new double[2];
		mult[0] = (m[0][0]*v[0])+(m[0][1]*v[1]);
		mult[1] = (m[1][0]*v[0])+(m[1][1]*v[1]);
		return mult;
	}
	double[][] mt(double[] o1, double[] o2, double[] d1, double[] d2){
		double a = d1[0];
		double b = d2[0];
		double c = o1[0];
		double d = d1[1];
		double e = d2[1];
		double f = o1[1];
		double det = (a*e)-(b*d);
		double a11 = ((e*c)-(b*f))/det;
		double a21 = ((a*f)-(d*c))/det;
		c = o2[0];
		f = o2[1];
		double a12 = ((e*c)-(b*f))/det;
		double a22 = ((a*f)-(d*c))/det;
		System.out.println(a11+" "+a12);
		System.out.println(a21+" "+a22);
		double[][] mt = new double[2][2];
		mt[0][0] = a11;
		mt[0][1] = a12;
		mt[1][0] = a21;
		mt[1][1] = a22;
		return mt;
	}
	void drawbase(double[] base1, double[] base2){
		gc.setLineWidth(3);
		drawRect(0, 0, base1[0], base1[1]);
		drawRect(0, 0, base2[0], base2[1]);
		gc.setLineWidth(2);
		drawRect(-50*base1[0], -50*base1[1], 50*base1[0], 50*base1[1]);
		drawRect(-50*base2[0], -50*base2[1], 50*base2[0], 50*base2[1]);
		gc.setLineWidth(0.5);
		for (int i = 0; i < 50; i++) {
			drawRect((-50*base1[0])+(base2[0]*i), (-50*base1[1])+(base2[1]*i), (50*base1[0])+(base2[0]*i), (50*base1[1])+(base2[1]*i));
			drawRect((-50*base1[0])-(base2[0]*i), (-50*base1[1])-(base2[1]*i), (50*base1[0])-(base2[0]*i), (50*base1[1])-(base2[1]*i));
			drawRect((-50*base2[0])+(base1[0]*i), (-50*base2[1])+(base1[1]*i), (50*base2[0])+(base1[0]*i), (50*base2[1])+(base1[1]*i));
			drawRect((-50*base2[0])-(base1[0]*i), (-50*base2[1])-(base1[1]*i), (50*base2[0])-(base1[0]*i), (50*base2[1])-(base1[1]*i));
		}
	}
	private void drawRect(double xi, double yi, double xf, double yf){
		xi*=20;
		xf*=20;
		yi*=20;
		yf*=20;
		gc.strokeLine(xi+(grafico.getWidth()/2), (grafico.getWidth()/2)-yi, xf+grafico.getWidth()/2, (grafico.getWidth()/2)-yf);
	}
	void clear(){
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, grafico.getWidth(), grafico.getHeight());
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		//Arriba
		gc.strokeLine(0, 0, grafico.getWidth(), 0);
		//Derecha
		gc.strokeLine(grafico.getWidth(), 0, grafico.getWidth(), grafico.getHeight());
		//Abajo
		gc.strokeLine(0, grafico.getHeight(), grafico.getWidth(),grafico.getHeight());
		//Izquierda
		gc.strokeLine(0, 0, 0, grafico.getHeight());
	}
}
