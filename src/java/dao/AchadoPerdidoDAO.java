/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import apoio.ConexaoBD;
import entidade.AchadoPerdido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author jonat
 */
public class AchadoPerdidoDAO {

    public String salvar(AchadoPerdido ap) {
        try {
            String sql = "INSERT INTO Achado_Perdido (DATA_HORA, DESCRICAO_ITEM, NOME_PESSOA_CONTATO, CONTATO, CATEGORIA_ID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql);

            LocalDateTime dh = (ap.getDataHora() != null) ? ap.getDataHora() : LocalDateTime.now();
            ps.setTimestamp(1, Timestamp.valueOf(dh));
            ps.setString(2, ap.getDescricaoItem());
            ps.setString(3, ap.getNomePessoaContato());
            ps.setString(4, ap.getContato());
            ps.setInt(5, ap.getCategoriaId());

            ps.executeUpdate();
            return null; // Sucesso
        } catch (Exception e) {
            System.out.println("Erro ao salvar AchadoPerdido: " + e.getMessage());
            return e.getMessage();
        }
    }

    public String atualizar(AchadoPerdido ap) {
        try {
            String sql = "UPDATE Achado_Perdido SET DATA_HORA = ?, DESCRICAO_ITEM = ?, NOME_PESSOA_CONTATO = ?, CONTATO = ?, CATEGORIA_ID = ? WHERE ID = ?";
            PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql);

            LocalDateTime dh = (ap.getDataHora() != null) ? ap.getDataHora() : LocalDateTime.now();
            ps.setTimestamp(1, Timestamp.valueOf(dh));
            ps.setString(2, ap.getDescricaoItem());
            ps.setString(3, ap.getNomePessoaContato());
            ps.setString(4, ap.getContato());
            ps.setInt(5, ap.getCategoriaId());
            ps.setInt(6, ap.getId());

            ps.executeUpdate();
            return null; // Sucesso
        } catch (Exception e) {
            System.out.println("Erro ao atualizar AchadoPerdido: " + e.getMessage());
            return e.getMessage();
        }
    }

    public String excluir(int id) {
        try {
            String sql = "DELETE FROM Achado_Perdido WHERE ID = ?";
            PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return null; // Sucesso
        } catch (Exception e) {
            System.out.println("Erro ao excluir AchadoPerdido: " + e.getMessage());
            return e.getMessage();
        }
    }

    public ArrayList<AchadoPerdido> consultarTodos() {
        ArrayList<AchadoPerdido> lista = new ArrayList<>();
        try {
            String sql = "SELECT ap.*, c.DESCRICAO as categoria_descricao "
                       + "FROM Achado_Perdido ap "
                       + "INNER JOIN Categoria c ON ap.CATEGORIA_ID = c.ID "
                       + "ORDER BY ap.ID DESC";
            PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AchadoPerdido ap = new AchadoPerdido();
                ap.setId(rs.getInt("id"));
                Timestamp ts = rs.getTimestamp("data_hora");
                if (ts != null) {
                    ap.setDataHora(ts.toLocalDateTime());
                }
                ap.setDescricaoItem(rs.getString("descricao_item"));
                ap.setNomePessoaContato(rs.getString("nome_pessoa_contato"));
                ap.setContato(rs.getString("contato"));
                ap.setCategoriaId(rs.getInt("categoria_id"));
                ap.setCategoriaDescricao(rs.getString("categoria_descricao"));

                lista.add(ap);
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar AchadosPerdidos: " + e.getMessage());
        }
        return lista;
    }

    public AchadoPerdido consultarPorId(int id) {
        try {
            String sql = "SELECT ap.*, c.DESCRICAO as categoria_descricao "
                       + "FROM Achado_Perdido ap "
                       + "INNER JOIN Categoria c ON ap.CATEGORIA_ID = c.ID "
                       + "WHERE ap.ID = ?";
            PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                AchadoPerdido ap = new AchadoPerdido();
                ap.setId(rs.getInt("id"));
                Timestamp ts = rs.getTimestamp("data_hora");
                if (ts != null) {
                    ap.setDataHora(ts.toLocalDateTime());
                }
                ap.setDescricaoItem(rs.getString("descricao_item"));
                ap.setNomePessoaContato(rs.getString("nome_pessoa_contato"));
                ap.setContato(rs.getString("contato"));
                ap.setCategoriaId(rs.getInt("categoria_id"));
                ap.setCategoriaDescricao(rs.getString("categoria_descricao"));

                return ap;
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar AchadoPerdido por ID: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<AchadoPerdido> buscar(String termo) {
        ArrayList<AchadoPerdido> lista = new ArrayList<>();
        try {
            String sql = "SELECT ap.*, c.DESCRICAO as categoria_descricao "
                       + "FROM Achado_Perdido ap "
                       + "INNER JOIN Categoria c ON ap.CATEGORIA_ID = c.ID "
                       + "WHERE LOWER(ap.DESCRICAO_ITEM) LIKE LOWER(?) "
                       + "ORDER BY ap.ID DESC";
            PreparedStatement ps = ConexaoBD.getInstance().getConnection().prepareStatement(sql);
            String busca = "%" + termo + "%";
            ps.setString(1, busca);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AchadoPerdido ap = new AchadoPerdido();
                ap.setId(rs.getInt("id"));
                Timestamp ts = rs.getTimestamp("data_hora");
                if (ts != null) {
                    ap.setDataHora(ts.toLocalDateTime());
                }
                ap.setDescricaoItem(rs.getString("descricao_item"));
                ap.setNomePessoaContato(rs.getString("nome_pessoa_contato"));
                ap.setContato(rs.getString("contato"));
                ap.setCategoriaId(rs.getInt("categoria_id"));
                ap.setCategoriaDescricao(rs.getString("categoria_descricao"));

                lista.add(ap);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar AchadosPerdidos: " + e.getMessage());
        }
        return lista;
    }
}

