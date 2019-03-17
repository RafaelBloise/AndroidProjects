package mobi.stos.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView saida = findViewById(R.id.Tela);

        Button btnRemove = findViewById(R.id.buttonRemove);
        Button btnDot = findViewById(R.id.buttonDot);
        Button btnDiv = findViewById(R.id.buttonDiv);
        Button btnPlus = findViewById(R.id.buttonPlus);
        Button btnMinus = findViewById(R.id.buttonMinus);
        Button btnMult = findViewById(R.id.buttonMult);
        Button btnClean = findViewById(R.id.buttonClean);
        Button btnResult = findViewById(R.id.botaoResult);
        Button btn0 = findViewById(R.id.button0);
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);
        Button btn5 = findViewById(R.id.button5);
        Button btn6 = findViewById(R.id.button6);
        Button btn7 = findViewById(R.id.button7);
        Button btn8 = findViewById(R.id.button8);
        Button btn9 = findViewById(R.id.button9);
        btnDot.setOnClickListener(btn -> saida.setText(addOutput(btnDot.getText().toString(), saida)));
        btn0.setOnClickListener(btn -> saida.setText(addOutput(btn0.getText().toString(), saida)));
        btn1.setOnClickListener(btn -> saida.setText(addOutput(btn1.getText().toString(), saida)));
        btn2.setOnClickListener(btn -> saida.setText(addOutput(btn2.getText().toString(), saida)));
        btn3.setOnClickListener(btn -> saida.setText(addOutput(btn3.getText().toString(), saida)));
        btn4.setOnClickListener(btn -> saida.setText(addOutput(btn4.getText().toString(), saida)));
        btn5.setOnClickListener(btn -> saida.setText(addOutput(btn5.getText().toString(), saida)));
        btn6.setOnClickListener(btn -> saida.setText(addOutput(btn6.getText().toString(), saida)));
        btn7.setOnClickListener(btn -> saida.setText(addOutput(btn7.getText().toString(), saida)));
        btn8.setOnClickListener(btn -> saida.setText(addOutput(btn8.getText().toString(), saida)));
        btn9.setOnClickListener(btn -> saida.setText(addOutput(btn9.getText().toString(), saida)));


        btnPlus.setOnClickListener(btn -> {
            if (Operacao(saida)) {
                saida.setText(showResult(saida));
            }
            saida.setText(addOutput(btnPlus.getText().toString(), saida));
        });

        btnMinus.setOnClickListener(btn -> {
            if (Operacao(saida)) {
                saida.setText(showResult(saida));
            }
            saida.setText(addOutput(btnMinus.getText().toString(), saida));
        });

        btnMult.setOnClickListener(btn -> {
            if (Operacao(saida)) {
                saida.setText(showResult(saida));
            }
            saida.setText(addOutput(btnMult.getText().toString(), saida));
        });

        btnDiv.setOnClickListener(btn -> {
            if (Operacao(saida)) {
                saida.setText(showResult(saida));
            }
            saida.setText(addOutput(btnDiv.getText().toString(), saida));
        });



        btnResult.setOnClickListener(btn -> {
            if (Operacao(saida)) {
                saida.setText(showResult(saida));
            } else {
                String msg = "OPERAÇÃO INVÁLIDA";
                saida.setText(msg);
            }
        });

        btnClean.setOnClickListener(btn -> cleanOutput(saida));

        btnRemove.setOnClickListener(btn -> {
            if (saida.getText().length() > 0) {
                limparUltimaEntrada(saida);
            }
        });

    }


    public String showResult(TextView saida) {
        String simbol = "";
        List<String> numbers;

        for (Character ch : saida.getText().toString().toCharArray()) {

            if (!Character.isDigit(ch) && !ch.toString().equals(".")) {
                simbol = String.valueOf(ch);
            }
        }

        switch (simbol) {
            case "+":
                numbers = Arrays.asList(saida.getText().toString().split("\\+"));
                return plus(new BigDecimal(numbers.get(0)), new BigDecimal(numbers.get(1)));
            case "-":
                boolean negativeNumber = saida.getText().toString().startsWith("-");
                if (negativeNumber) {
                    numbers = Arrays.asList(saida.getText().toString().substring(1).split("-"));
                    numbers.set(0, "-".concat(numbers.get(0)));
                } else {
                    numbers = Arrays.asList(saida.getText().toString().split("-"));
                }
                return subtract(new BigDecimal(numbers.get(0)), new BigDecimal(numbers.get(1)));
            case "/":
                numbers = Arrays.asList(saida.getText().toString().split("÷"));
                return divide(new BigDecimal(numbers.get(0)), new BigDecimal(numbers.get(1)));
            case "*":
                numbers = Arrays.asList(saida.getText().toString().split("\\*"));
                return mult(new BigDecimal(numbers.get(0)), new BigDecimal(numbers.get(1)));
        }
        return simbol;
    }

    public void cleanOutput(TextView output) {
        output.setText("");
    }

    public void limparUltimaEntrada(TextView output) {
        String Output = output.getText().subSequence(0, output.getText().length() - 1).toString();
        output.setText(Output);
    }

    public String addOutput(String txt, TextView output) {
        StringBuilder sb = new StringBuilder()
                .append(output.getText())
                .append(txt);
        return sb.toString();
    }

    public String plus(BigDecimal n1, BigDecimal n2) {
        BigDecimal result = n1.add(n2);
        return result.toString();
    }

    public String subtract(BigDecimal n1, BigDecimal n2) {
        BigDecimal result = n1.subtract(n2);
        return result.toString();
    }

    public String mult(BigDecimal n1, BigDecimal n2) {
        BigDecimal result = n1.multiply(n2);
        return result.toString();
    }

    public String divide(BigDecimal n1, BigDecimal n2) {
        BigDecimal result = n1.divide(n2);
        return result.toString();
    }


    public boolean Operacao(TextView saida) {

        String saidaNormal = saida.getText().toString().replaceAll("\\+", "")
                .replaceAll("-", "").replaceAll("\\*", "")
                .replaceAll("/", "").replaceAll(" ", "");
        if (saidaNormal.isEmpty()) {
            return false;
        }


        if (saida.getText().toString().startsWith("+") || saida.getText().toString().startsWith("*")
                || saida.getText().toString().startsWith("/")) {
            return false;
        }

        boolean negativeNumber = saida.getText().toString().startsWith("-");


        int count = 0;
        for (Character ch : saida.getText().toString().toCharArray()) {
            if (!Character.isDigit(ch) && !ch.toString().equals(".")) {
                count++;
            }
        }

        if (count == 1) {
            if (negativeNumber) {
                return false;
            }
            return true;
        } else if (count == 2 && negativeNumber) {
            return true;
        }
        return false;
    }


}



