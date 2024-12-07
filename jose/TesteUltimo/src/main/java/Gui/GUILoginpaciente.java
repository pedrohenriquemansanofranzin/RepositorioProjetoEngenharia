package Gui;

import DAOs.DAOpaciente;
//import Gui.GUIpaciente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUILoginpaciente extends JFrame {
    JTextField tfCRM = new JTextField(15);
    JButton btLogin = new JButton("Login");
    JButton btCancelar = new JButton("Cancelar");

    public GUILoginpaciente() {
        setTitle("Tela de Login- paciente");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("CPF:"));
        add(tfCRM);
        add(btLogin);
        add(btCancelar);

        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpi = tfCRM.getText().trim();
                if (cpi.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira o CPF do paciente.");
                    return;
                }

                // Verificar no banco de dados
                if (pacienteExiste(cpi)) {
                    JOptionPane.showMessageDialog(null, "Bem-vindo!");
                    // Abrir o menu principal ou tela inicial
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado. Redirecionando...");
                    // Redirecionar para a tela de CRUD
                    new GUIMedico();
                }
            }
        });

        btCancelar.addActionListener(e -> System.exit(0));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para verificar se a paciente existe
public boolean pacienteExiste(String cip) {
    return new DAOpaciente().pacienteExiste(cip);
}
    public static void main(String[] args) {
        new GUILoginpaciente();
    }
}

