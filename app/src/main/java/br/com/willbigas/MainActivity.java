package br.com.willbigas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.willbigas.controller.CalculadoraController;
import br.com.willbigas.model.Calculadora;
import br.com.willbigas.util.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private CalculadoraController calculadoraController;

    private Calculadora calculadora;

    private EditText edtNumero1;
    private EditText edtNumero2;

    private Button btnSomar;
    private Button btnSubtrair;
    private Button btnMultiplicar;
    private Button btnDividir;

    private LinearLayout layoutResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarControllers();
        inicializarCalculadora();
        inicializarComponentes();


    }

    private void inicializarControllers() {
        calculadoraController = new CalculadoraController();
    }

    private void inicializarCalculadora() {
        calculadora = new Calculadora();
    }

    private void inicializarComponentes() {
        edtNumero1 = findViewById(R.id.edtNumero1);
        edtNumero2 = findViewById(R.id.edtNumero2);

        btnSomar = findViewById(R.id.btnSomar);
        btnSubtrair = findViewById(R.id.btnSubtrair);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnDividir = findViewById(R.id.btnDividir);

        layoutResultado = findViewById(R.id.layoutResultado);
    }

    public void somar(View view) {
        if (validarDadosDaView()) {
            receberDadosDaView();
            calculadora = calculadoraController.somar(calculadora);
            exibirDadosNaView();
            limparDados();
        }
    }

    public void subtrair(View view) {
        if (validarDadosDaView()) {
            receberDadosDaView();
            calculadora = calculadoraController.subtrair(calculadora);
            exibirDadosNaView();
            limparDados();
        }
    }

    public void multiplicar(View view) {
        if (validarDadosDaView()) {
            receberDadosDaView();
            calculadora = calculadoraController.multiplicar(calculadora);
            exibirDadosNaView();
            limparDados();
        }
    }

    public void dividir(View view) {
        if (validarDadosDaView()) {
            receberDadosDaView();
            calculadora = calculadoraController.dividir(calculadora);
            exibirDadosNaView();
            limparDados();
        }
    }

    public void limparDados() {
        edtNumero1.setText("");
        edtNumero2.setText("");
        calculadora = new Calculadora(calculadora.getHistorico());
    }

    private boolean validarDadosDaView() {
        boolean foiValidado = true;

        if (edtNumero1.getText().toString() == null || edtNumero1.getText().toString().equals("")) {
            edtNumero1.setError(getString(R.string.required_numero1));
            foiValidado = false;
        }

        if (edtNumero2.getText().toString() == null || edtNumero2.getText().toString().equals("")) {
            edtNumero2.setError(getString(R.string.required_numero2));
            foiValidado = false;
        }
        return foiValidado;
    }

    private void receberDadosDaView() {
        calculadora = new Calculadora(Double.valueOf(edtNumero1.getText().toString()) , Double.valueOf(edtNumero2.getText().toString()));
    }

    private void exibirDadosNaView() {
        for (int i = 0; i < calculadora.getHistorico().size(); i++) {
            TextView tvResultado = new TextView(this);
            tvResultado.setTextSize(17);
            tvResultado.setGravity(Gravity.CENTER);
            tvResultado.setText(DecimalFormat.deDecimalParaString(calculadora.getHistorico().get(i).getNumero1()) + " " +calculadora.getHistorico().get(i).getTipoOperacao().getDescricao()  + " " + DecimalFormat.deDecimalParaString(calculadora.getHistorico().get(i).getNumero2()) + " = " + DecimalFormat.deDecimalParaString(calculadora.getHistorico().get(i).getResultado()));
            layoutResultado.addView(tvResultado);
        }
    }


}