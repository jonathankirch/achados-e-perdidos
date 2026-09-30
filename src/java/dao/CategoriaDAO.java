/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import apoio.ConexaoBD;
import entidade.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jonat
 */
public class CategoriaDAO {

    public String salvar(Categoria c) {
        try {
            String sql = "INSERT INTO Categoria (DESCRICAO) VALUES (?)";
            PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, c.getDescricao());
            ps.executeUpdate();
            return null; // Sucesso
        } catch (Exception e) {
            System.out.println("Erro ao salvar categoria: " + e.getMessage());
            return e.getMessage();
        }
    }

    public String atualizar(Categoria c) {
        try {
            String sql = "UPDATE Categoria SET DESCRICAO = ? WHERE ID = ?";
            PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, c.getDescricao());
            ps.setInt(2, c.getId());
            ps.executeUpdate();
            return null; // Sucesso
        } catch (Exception e) {
            System.out.println("Erro ao atualizar categoria: " + e.getMessage());
            return e.getMessage();
        }
    }

    public String excluir(int id) {
        try {
            String sql = "DELETE FROM Categoria WHERE ID = ?";
            PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return null; // Sucesso
        } catch (Exception e) {
            System.out.println("Erro ao excluir categoria: " + e.getMessage());
            return e.getMessage();
        }
    }

    public ArrayList<Categoria> consultarTodos() {
        ArrayList<Categoria> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Categoria ORDER BY ID";
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setDescricao(rs.getString("descricao"));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar categorias: " + e.getMessage());
        }
        return lista;
    }

    public Categoria consultarPorId(int id) {
        try {
            String sql = "SELECT * FROM Categoria WHERE ID = ?";
            PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setDescricao(rs.getString("descricao"));
                return c;
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar categoria por ID: " + e.getMessage());
        }
        return null;
    }

    public Categoria consultarPorDescricao(String descricao) {
        try {
            String sql = "SELECT * FROM Categoria WHERE LOWER(DESCRICAO) = LOWER(?)";
            PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, descricao);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setDescricao(rs.getString("descricao"));
                return c;
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar categoria por descricao: " + e.getMessage());
        }
        return null;
    }
}

