<%-- 
    Document   : tabela
    Created on : Sep 25, 2026, 7:22:22 PM
    Author     : jonat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.AchadoPerdidoDAO"%>
<%@page import="entidade.AchadoPerdido"%>
<%@page import="dao.CategoriaDAO"%>
<%@page import="entidade.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.format.DateTimeFormatter"%>

<%-- icone da tabela --%>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<%-- estilo da tabela --%>
<style>
    .table-wrapper {
        background: #fff;
        padding: 20px 25px;
        margin: 30px 0;
        border-radius: 8px;
        box-shadow: 0 2px 5px rgba(0,0,0,.1);
    }
    .table-title {
        padding-bottom: 15px;
        background: #435d7d;
        color: #fff;
        padding: 16px 30px;
        margin: -20px -25px 10px;
        border-radius: 8px 8px 0 0;
    }
    .table-title h2 {
        margin: 5px 0 0;
        font-size: 24px;
    }
    .table-title .btn {
        color: #fff;
        float: right;
        font-size: 13px;
        border: none;
        min-width: 50px;
        border-radius: 4px;
        outline: none !important;
        margin-left: 10px;
    }
    .table-title .btn i {
        float: left;
        font-size: 21px;
        margin-right: 5px;
    }
    .table-title .btn span {
        float: left;
        margin-top: 2px;
    }
    table.table tr th, table.table tr td {
        border-color: #e9e9e9;
        padding: 12px 15px;
        vertical-align: middle;
    }
    table.table-striped tbody tr:nth-of-type(odd) {
        background-color: #fcfcfc;
    }
    table.table-striped.table-hover tbody tr:hover {
        background: #f5f5f5;
    }
    table.table td a {
        font-weight: bold;
        color: #566787;
        display: inline-block;
        text-decoration: none;
        outline: none !important;
    }
    table.table td a.edit {
        color: #FFC107;
    }
    table.table td a.delete {
        color: #F44336;
    }
    table.table td i {
        font-size: 20px;
    }
    .modal .modal-dialog {
        max-width: 450px;
    }
    .modal .modal-header, .modal .modal-body, .modal .modal-footer {
        padding: 20px 30px;
    }
    .modal .modal-content {
        border-radius: 6px;
    }
    .modal .modal-footer {
        background: #ecf0f1;
        border-radius: 0 0 6px 6px;
    }
</style>

<%
    String termoBusca = request.getParameter("busca");
    AchadoPerdidoDAO apDao = new AchadoPerdidoDAO();
    ArrayList<AchadoPerdido> lista;

    if (termoBusca != null && !termoBusca.trim().isEmpty()) {
        lista = apDao.buscar(termoBusca.trim());
    } else {
        lista = apDao.consultarTodos();
    }
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
%>

<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row align-items-center">
                <div class="col-md-4">
                    <h2>Gerenciar Achados e Perdidos</h2>
                </div>
                <div class="col-md-5">
                    <form action="index.jsp" method="GET" class="d-flex my-1">
                        <div class="input-group">
                            <input type="text" name="busca" class="form-control" placeholder="Nome do Item">
                            <button class="btn btn-primary" type="submit" style="float:none;">
                                Buscar
                            </button>
                        </div>
                    </form>
                </div>
                <div class="col-md-3 text-end">
                    <a href="#addModal" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addModal">
                        <i class="material-icons">&#xE147;</i> <span>Novo Item Perdido</span>
                    </a>
                </div>
            </div>
        </div>

        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Categoria</th>
                    <th>Descrição do Item</th>
                    <th>Nome Pessoa Contato</th>
                    <th>Contato</th>
                    <th>Data/Hora</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (lista.isEmpty()) {
                %>
                <tr>
                    <td colspan="7" class="text-center text-muted py-4">
                        <p class="mb-0">Nenhum registro de Achado/Perdido encontrado.</p>
                    </td>
                </tr>
                <%
                } else {
                    for (AchadoPerdido item : lista) {
                        String dataFormatada = item.getDataHora() != null ? item.getDataHora().format(fmt) : "";
                        String catDesc = item.getCategoriaDescricao() != null ? item.getCategoriaDescricao() : "Indefinida";
                        boolean isAchado = catDesc.equalsIgnoreCase("Achado");
                %>
                <tr>
                    <td><%= item.getId()%></td>
                    <td>
                        <span class="badge <%= isAchado ? "bg-success" : "bg-warning text-dark"%>">
                            <%= catDesc%>
                        </span>
                    </td>
                    <td><%= item.getDescricaoItem()%></td>
                    <td><%= item.getNomePessoaContato()%></td>
                    <td><%= item.getContato()%></td>
                    <td><%= dataFormatada%></td>
                    <td>
                        <a href="#editModal" class="edit" data-bs-toggle="modal" data-bs-target="#editModal"
                           onclick="preencherModalEditar('<%= item.getId()%>', '<%= item.getCategoriaId()%>', '<%= item.getDescricaoItem() != null ? item.getDescricaoItem().replace("'", "\\'") : ""%>', '<%= item.getNomePessoaContato() != null ? item.getNomePessoaContato().replace("'", "\\'") : ""%>', '<%= item.getContato() != null ? item.getContato().replace("'", "\\'") : ""%>')">
                            <i class="material-icons" data-bs-toggle="tooltip" title="Editar">&#xE254;</i>
                        </a>
                        <a href="#deleteModal" class="delete" data-bs-toggle="modal" data-bs-target="#deleteModal"
                           onclick="preencherModalExcluir('<%= item.getId()%>')">
                            <i class="material-icons" data-bs-toggle="tooltip" title="Excluir">&#xE872;</i>
                        </a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</div>

<%
    // Buscar categorias para popular os selects
    CategoriaDAO catDao = new CategoriaDAO();
    ArrayList<Categoria> categorias = catDao.consultarTodos();
%>

<!-- Modal Adicionar HTML -->
<div id="addModal" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="acao" method="POST">
                <input type="hidden" name="acao" value="salvar">
                <div class="modal-header">						
                    <h4 class="modal-title">Novo Achado/Perdido</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">					
                    <div class="mb-3">
                        <label class="form-label">Descrição do Item</label>
                        <input type="text" name="descricaoItem" class="form-control" placeholder="Ex: Chaveiro com 3 chaves" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Nome da Pessoa de Contato</label>
                        <input type="text" name="nomePessoaContato" class="form-control" placeholder="Ex: João da Silva" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Contato (Telefone/Celular)</label>
                        <input type="text" name="contato" class="form-control" placeholder="Ex: (54) 99999-8888" required>
                    </div>					
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-success">Salvar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal Editar HTML -->
<div id="editModal" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="acao" method="POST">
                <input type="hidden" name="acao" value="atualizar">
                <input type="hidden" name="id" id="edit_id">
                <div class="modal-header">						
                    <h4 class="modal-title">Editar Achado/Perdido</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">					
                    <div class="mb-3">
                        <label class="form-label">Categoria</label>
                        <select name="categoriaId" id="edit_categoriaId" class="form-select" required>
                            <option value="">Selecione uma categoria...</option>
                            <% for (Categoria cat : categorias) {%>
                            <option value="<%= cat.getId()%>"><%= cat.getDescricao()%></option>
                            <% }%>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Descrição do Item</label>
                        <input type="text" name="descricaoItem" id="edit_descricaoItem" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Nome da Pessoa de Contato</label>
                        <input type="text" name="nomePessoaContato" id="edit_nomePessoaContato" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Contato (Telefone / Celular)</label>
                        <input type="text" name="contato" id="edit_contato" class="form-control" required>
                    </div>					
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-info text-white">Salvar Alterações</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal Excluir HTML -->
<div id="deleteModal" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="acao" method="POST">
                <input type="hidden" name="acao" value="excluir">
                <input type="hidden" name="id" id="delete_id">
                <div class="modal-header">						
                    <h4 class="modal-title">Excluir Item</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">					
                    <p>Tem certeza de que deseja excluir este registro de Achado/Perdido?</p>
                    <p class="text-danger"><small>Esta ação não poderá ser desfeita.</small></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-danger">Excluir</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Scripts JS para preencher os dados nos modais -->
<script>
    function preencherModalEditar(id, categoriaId, descricaoItem, nomePessoaContato, contato) {
        document.getElementById('edit_id').value = id;
        document.getElementById('edit_categoriaId').value = categoriaId;
        document.getElementById('edit_descricaoItem').value = descricaoItem;
        document.getElementById('edit_nomePessoaContato').value = nomePessoaContato;
        document.getElementById('edit_contato').value = contato;
    }

    function preencherModalExcluir(id) {
        document.getElementById('delete_id').value = id;
    }

    $(document).ready(function () {
        $('[data-bs-toggle="tooltip"]').tooltip();
    });
</script>
