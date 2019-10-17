/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.Cidade;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class DaoCidade {
    
     public static boolean inserir(Cidade objeto) {
        String sql = "INSERT INTO cidade (nome, estado, habitantes, emancipacao, area, distancia_capital) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
           
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getEstado());
            ps.setInt(3, objeto.getHabitantes());
            ps.setDate(4, Date.valueOf(objeto.getEmancipacao()));
            ps.setDouble(5, objeto.getArea());
            ps.setInt(6, objeto.getDistancia_capital());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     
       public static boolean alterar(Cidade objeto) {
        String sql = "UPDATE cidade SET nome = ?, estado = ?, habitantes = ?, emancipacao = ?, area = ?, distancia_capital = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getEstado());
            ps.setInt(3, objeto.getHabitantes());
            ps.setDate(4, Date.valueOf(objeto.getEmancipacao()));
            ps.setDouble(5, objeto.getArea());
            ps.setInt(6, objeto.getDistancia_capital());
            ps.setInt(7, objeto.getCodigo());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     
      public static void main(String[] args) {
        Cidade objeto = new Cidade();
        objeto.setNome("Ibiruba");
        objeto.setEstado("RS");
        objeto.setHabitantes(10000);
        objeto.setEmancipacao(LocalDate.parse("11/01/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setArea(611.8);
        objeto.setDistancia_capital(296);
        
        
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
      
        public static boolean excluir(Cidade objeto) {
        String sql = "DELETE FROM cidade WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
        
        public static List<Cidade> consultar() {
        List<Cidade> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT nome, estado, habitantes, emancipacao, area, distancia_capital, codigo FROM cidade";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cidade objeto = new Cidade();
                //definir um set para cada atributo da entidade, cuidado com o tipo
               
                
                objeto.setNome(rs.getString("nome"));
                objeto.setEstado(rs.getString("estado"));
                objeto.setHabitantes(rs.getInt("habitantes"));
                objeto.setEmancipacao(rs.getDate("emancipacao").toLocalDate());
                objeto.setArea(rs.getDouble("area"));
                objeto.setDistancia_capital(rs.getInt("distancia_capital"));
                objeto.setCodigo(rs.getInt("codigo"));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
            }
        }
    
         public static Cidade consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT nome, estado, habitantes, emancipacao, area, distancia_capital, codigo FROM cidade WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cidade objeto = new Cidade();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                
                objeto.setNome(rs.getString("nome"));
                objeto.setEstado(rs.getString("estado"));
                objeto.setHabitantes(rs.getInt("habitantes"));
                objeto.setEmancipacao(rs.getDate("emancipacao").toLocalDate());
                objeto.setArea(rs.getDouble("area"));
                objeto.setDistancia_capital(rs.getInt("distancia_capital"));
                objeto.setCodigo(rs.getInt("codigo"));
                
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
