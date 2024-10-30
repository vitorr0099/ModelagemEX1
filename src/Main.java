import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Instanciar DAOs
            AutorDAO autorDAO = new AutorDAO();
            LivroDAO livroDAO = new LivroDAO();

            // Criar e Inserir um novo Autor
            Autor autor = new Autor();
            autor.setNome("George Orwell");
            autor.setNacionalidade("Britânico");
            autorDAO.inserirAutor(autor);
            System.out.println("Autor inserido com sucesso!");

            // Listar todos os Autores
            List<Autor> autores = autorDAO.listarAutores();
            System.out.println("Lista de Autores:");
            for (Autor a : autores) {
                System.out.println("ID: " + a.getIdAutor() + ", Nome: " + a.getNome() + ", Nacionalidade: " + a.getNacionalidade());
            }

            // Criar e Inserir um novo Livro
            Livro livro = new Livro();
            livro.setTitulo("1984");
            livro.setAnoPublicacao(1949);
            livro.setIdAutor(autor.getIdAutor()); // Associar o livro ao autor
            livroDAO.inserirLivro(livro);
            System.out.println("Livro inserido com sucesso!");

            // Listar todos os Livros
            List<Livro> livros = livroDAO.listarLivros();
            System.out.println("Lista de Livros:");
            for (Livro l : livros) {
                System.out.println("ID: " + l.getIdLivro() + ", Título: " + l.getTitulo() + ", Ano de Publicação: " + l.getAnoPublicacao() + ", ID do Autor: " + l.getIdAutor());
            }

            // Listar todos os livros de um autor específico
            System.out.println("Livros do autor com ID: " + autor.getIdAutor());
            List<Livro> livrosPorAutor = livroDAO.listarLivrosPorAutor(autor.getIdAutor());
            for (Livro l : livrosPorAutor) {
                System.out.println("ID: " + l.getIdLivro() + ", Título: " + l.getTitulo() + ", Ano de Publicação: " + l.getAnoPublicacao());
            }

            // Atualizar informações do Autor
            autor.setNome("George Orwell - Atualizado");
            autorDAO.atualizarAutor(autor);
            System.out.println("Autor atualizado com sucesso!");

            // Atualizar informações do Livro
            livro.setTitulo("1984 - Edição Atualizada");
            livroDAO.atualizarLivro(livro);
            System.out.println("Livro atualizado com sucesso!");

            // Excluir um Autor e seu(s) Livro(s)
            autorDAO.excluirAutor(autor.getIdAutor());
            System.out.println("Autor e seus livros excluídos com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ou manipular o banco de dados: " + e.getMessage());
        }
    }
}
