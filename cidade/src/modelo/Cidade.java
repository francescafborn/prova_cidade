/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author Administrador
 */
public class Cidade {
    
    private Integer codigo;
    private String nome;
    private String estado;
    private Integer habitantes;
    private LocalDate emancipacao;
    private Double area;
    private Integer distancia_capital;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(Integer habitantes) {
        this.habitantes = habitantes;
    }

    public LocalDate getEmancipacao() {
        return emancipacao;
    }

    public void setEmancipacao(LocalDate emancipacao) {
        this.emancipacao = emancipacao;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getDistancia_capital() {
        return distancia_capital;
    }

    public void setDistancia_capital(Integer distancia_capital) {
        this.distancia_capital = distancia_capital;
    }

    @Override
    public String toString() {
        return "Cidade{" + "nome=" + nome + '}';
    }
    
}
