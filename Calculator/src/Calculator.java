// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];

    JButton addButton,subButton,mulButton,divButton;
    JButton decButton,equButton,delButton,clrButton,negButton;
    JPanel panel;
    Font myFont = new Font("Ink Free",Font.BOLD,30);
    double num1 = 0,num2 = 0, result = 0;
    char operator;





    Calculator(){
    frame = new JFrame("Calculator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(420,550);
    frame.setLayout(null);
    textField = new JTextField();
    textField.setBounds(50,25,300,50);
    textField.setFont(myFont);
    textField.setEditable(false);

    addButton = new JButton("+");
    subButton = new JButton("-");
    mulButton = new JButton("*");
    divButton = new JButton("/");
    decButton = new JButton(".");
    equButton = new JButton("=");
    delButton = new JButton("DEL");
    clrButton = new JButton("Clear");
    negButton = new JButton("(-)");

    functionButtons[0] = addButton;
    functionButtons[1] = subButton;
    functionButtons[2] = mulButton;
    functionButtons[3] = divButton;
    functionButtons[4] = decButton;
    functionButtons[5] = equButton;
    functionButtons[6] = delButton;
    functionButtons[7] = clrButton;
    functionButtons[8] = negButton;

    for (int i = 0;i < 9;i = i+1){
        functionButtons[i].addActionListener(this);
        functionButtons[i].setFont(myFont);
        functionButtons[i].setFocusable(false);
    }

    for (int i = 0;i < 10;i = i+1){
        numberButtons[i] = new JButton(String.valueOf(i));
        numberButtons[i].addActionListener(this);
        numberButtons[i].setFont(myFont);
        numberButtons[i].setFocusable(false);
    }

        negButton.setBounds(50,430,100,50);
        delButton.setBounds(150,430,100,50);
        clrButton.setBounds(250,430,100,50);

    panel = new JPanel();
    panel.setBounds(50,100,300,300);
    panel.setLayout(new GridLayout(4,4,10,10));


    panel.add(numberButtons[1]);
    panel.add(numberButtons[2]);
    panel.add(numberButtons[3]);
    panel.add(numberButtons[4]);
    panel.add(numberButtons[5]);
    panel.add(numberButtons[6]);
    panel.add(numberButtons[7]);
    panel.add(numberButtons[8]);
    panel.add(numberButtons[9]);
    panel.add(numberButtons[0]);

    panel.add(functionButtons[0]);
    panel.add(functionButtons[1]);
    panel.add(functionButtons[2]);
    panel.add(functionButtons[3]);

    panel.add(functionButtons[4]);
    panel.add(functionButtons[5]);
    panel.add(functionButtons[6]);
    panel.add(functionButtons[7]);
    panel.add(functionButtons[8]);

    frame.add(panel);
    frame.add(delButton);
    frame.add(clrButton);
    frame.add(textField);
    frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

    }

    public void cleartext(){
        textField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0;i<10;i = i +1){
            if(e.getSource()==numberButtons[i]){
                textField.setText(
                        textField.getText().concat(String.valueOf(i)));
            }
            }
        if (e.getSource()==decButton) {
            textField.setText(
                    textField.getText().concat("."));
        }
        if (e.getSource()==clrButton) {
            textField.setText("");
        }

        if (e.getSource()==addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            cleartext();
        }

        if (e.getSource()==subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            cleartext();
        }

        if (e.getSource()==mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            cleartext();
        }

        if (e.getSource()==divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            cleartext();
        }

        if (e.getSource()==negButton) {
            double temp = Double.parseDouble(textField.getText());
            temp = temp * -1;
            cleartext();
            textField.setText(String.valueOf(temp));
        }

        if (e.getSource()==equButton) {
            num2 = Double.parseDouble(textField.getText());
           switch(operator){
               case '+':
                   result = num2 + num1;

                   break;
               case '-':
                   result = num1 - num2;

                   break;
               case '*':
                   result = num1 * num2;

                   break;

               case '/':
                   try {
                       result = num1 / num2;

                   }
                   catch(Exception er) {
                       System.out.println("Dive by Zero");

                   }
                   break;
           }
           textField.setText(String.valueOf(result));
           num1 = result;
        }

        try {
            if (e.getSource() == delButton) {
                textField.setText("" + textField.getText().substring(0, textField.getText().length() - 1));
            }
        }
        catch(StringIndexOutOfBoundsException er) {
            System.err.println("StringIndexOutOfBoundsException caught!");
        }


    }
}