/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.DaoCidade;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Cidade;
import tela.manutencao.ManutencaoCidade;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class ControladorCidade {

    public static void inserir(ManutencaoCidade man){
        Cidade objeto = new Cidade();
     
        objeto.setNome(man.jtfNome.getText());
        objeto.setEstado(man.jtfEstado.getText());
        objeto.setHabitantes(Integer.parseInt(man.jtfHabitantes.getText()));
        objeto.setEmancipacao(LocalDate.parse(man.jtfEmancipacao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setArea(Double.parseDouble(man.jtfArea.getText()));
        objeto.setDistancia_capital(Integer.parseInt(man.jtfDistancia_capital.getText()));
        
        
        boolean resultado = DaoCidade.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            
            if (man.listagem != null) {
            atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
            
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

    public static void alterar(ManutencaoCidade man){
        Cidade objeto = new Cidade();
        //definir todos os atributos
        objeto.setNome(man.jtfNome.getText());
        objeto.setEstado(man.jtfEstado.getText());
        objeto.setHabitantes(Integer.parseInt(man.jtfHabitantes.getText()));
        objeto.setEmancipacao(LocalDate.parse(man.jtfEmancipacao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setArea(Double.parseDouble(man.jtfArea.getText()));
        objeto.setDistancia_capital(Integer.parseInt(man.jtfDistancia_capital.getText()));
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        
        
        boolean resultado = DaoCidade.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            if (man.listagem != null) {
            atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

    public static void excluir(ManutencaoCidade man){
        Cidade objeto = new Cidade();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoCidade.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            if (man.listagem != null) {
            atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    
    public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Nome");
        modelo.addColumn("Estado");
        modelo.addColumn("Habitantes");
        modelo.addColumn("Emancipacao");
        modelo.addColumn("Area");
        modelo.addColumn("Distancia da Capital");
        modelo.addColumn("Codigo");
        
        List<Cidade> resultados = DaoCidade.consultar();
        for (Cidade objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela

            linha.add(objeto.getNome());
            linha.add(objeto.getEstado());
            linha.add(objeto.getHabitantes());
            linha.add(objeto.getEmancipacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getArea());
            linha.add(objeto.getDistancia_capital());
            linha.add(objeto.getCodigo());
            
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
    
    public static void atualizaCampos(ManutencaoCidade man, int pk){ 
        Cidade objeto = DaoCidade.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        
        
        man.jtfNome.setText(objeto.getNome());
        man.jtfEstado.setText(objeto.getEstado());
        man.jtfHabitantes.setText(objeto.getHabitantes().toString());
        man.jtfEmancipacao.setText(objeto.getEmancipacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jtfArea.setText(objeto.getArea().toString());
        man.jtfDistancia_capital.setText(objeto.getDistancia_capital().toString());
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
}
