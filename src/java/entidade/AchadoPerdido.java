/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

import java.time.LocalDateTime;

/**
 *
 * @author jonat
 */
public class AchadoPerdido {
    private int id;
    private LocalDateTime dataHora;
    private String descricaoItem;
    private String nomePessoaContato;
    private String contato;
    private int categoriaId;
    private String categoriaDescricao;

    public AchadoPerdido() {
    }

    public AchadoPerdido(int id, LocalDateTime dataHora, String descricaoItem, String nomePessoaContato, String contato, int categoriaId) {
        this.id = id;
        this.dataHora = dataHora;
        this.descricaoItem = descricaoItem;
        this.nomePessoaContato = nomePessoaContato;
        this.contato = contato;
        this.categoriaId = categoriaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public String getNomePessoaContato() {
        return nomePessoaContato;
    }

    public void setNomePessoaContato(String nomePessoaContato) {
        this.nomePessoaContato = nomePessoaContato;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaDescricao() {
        return categoriaDescricao;
    }

    public void setCategoriaDescricao(String categoriaDescricao) {
        this.categoriaDescricao = categoriaDescricao;
    }
}

