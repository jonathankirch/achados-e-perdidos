package servlet;

import dao.AchadoPerdidoDAO;
import entidade.AchadoPerdido;
import java.io.IOException;
import java.time.LocalDateTime;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author jonat
 */
public class acao extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String a = request.getParameter("parametro");
        if (a == null || a.isEmpty()) {
            a = request.getParameter("a");
        }
        if (a == null || a.isEmpty()) {
            a = request.getParameter("acao");
        }

        if (a != null && a.equalsIgnoreCase("excluir")) {
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                new AchadoPerdidoDAO().excluir(id);
            }
        }

        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String a = request.getParameter("parametro");
        if (a == null || a.isEmpty()) {
            a = request.getParameter("a");
        }
        if (a == null || a.isEmpty()) {
            a = request.getParameter("acao");
        }

        if (a != null) {
            AchadoPerdidoDAO dao = new AchadoPerdidoDAO();

            if (a.equalsIgnoreCase("salvar")) {
                String descricaoItem = request.getParameter("descricaoItem");
                String nomePessoaContato = request.getParameter("nomePessoaContato");
                String contato = request.getParameter("contato");

                String categoriaIdStr = request.getParameter("categoriaId");
                int categoriaId = 0;
                if (categoriaIdStr != null && !categoriaIdStr.trim().isEmpty()) {
                    categoriaId = Integer.parseInt(categoriaIdStr);
                } else {
                    entidade.Categoria catPerdido = new dao.CategoriaDAO().consultarPorDescricao("Perdido");
                    if (catPerdido != null) {
                        categoriaId = catPerdido.getId();
                    } else {
                        categoriaId = 2;
                    }
                }

                AchadoPerdido ap = new AchadoPerdido();
                ap.setDescricaoItem(descricaoItem);
                ap.setNomePessoaContato(nomePessoaContato);
                ap.setContato(contato);
                ap.setCategoriaId(categoriaId);
                ap.setDataHora(LocalDateTime.now());

                dao.salvar(ap);

            } else if (a.equalsIgnoreCase("atualizar") || a.equalsIgnoreCase("editar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String descricaoItem = request.getParameter("descricaoItem");
                String nomePessoaContato = request.getParameter("nomePessoaContato");
                String contato = request.getParameter("contato");
                int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));

                AchadoPerdido ap = dao.consultarPorId(id);
                if (ap == null) {
                    ap = new AchadoPerdido();
                    ap.setId(id);
                    ap.setDataHora(LocalDateTime.now());
                }
                ap.setDescricaoItem(descricaoItem);
                ap.setNomePessoaContato(nomePessoaContato);
                ap.setContato(contato);
                ap.setCategoriaId(categoriaId);

                dao.atualizar(ap);

            } else if (a.equalsIgnoreCase("excluir")) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.excluir(id);
            }
        }

        response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Servlet acao";
    }
}

