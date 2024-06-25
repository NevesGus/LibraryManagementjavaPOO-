import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVUtils {
    private static final String LIVROS_CSV = "livros.csv";
    private static final String CLIENTES_CSV = "clientes.csv";
    private static final String EMPRESTIMOS_CSV = "emprestimos.csv";

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    // Métodos para salvar, ler e excluir livros

    public static void salvarLivro(Livro livro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LIVROS_CSV, true))) {
            writer.write(livro.getTitulo() + "," + livro.getAutor() + "," + livro.getIsbn() + "," + livro.getCategoria() + "," + livro.isDisponivel());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Livro> lerLivros() {
        List<Livro> livros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LIVROS_CSV))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                Livro livro = new Livro(dados[0], dados[1], dados[2], dados[3]);
                livro.setDisponivel(Boolean.parseBoolean(dados[4]));
                livros.add(livro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public static void excluirLivro(String isbn) {
        List<Livro> livros = lerLivros();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LIVROS_CSV))) {
            for (Livro livro : livros) {
                if (!livro.getIsbn().equals(isbn)) {
                    writer.write(livro.getTitulo() + "," + livro.getAutor() + "," + livro.getIsbn() + "," + livro.getCategoria() + "," + livro.isDisponivel());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Métodos para salvar, ler e excluir clientes

    public static void salvarCliente(Cliente cliente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENTES_CSV, true))) {
            writer.write(cliente.getNome() + "," + cliente.getEndereco() + "," + cliente.getContato());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Cliente> lerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CLIENTES_CSV))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                Cliente cliente = new Cliente(dados[0], dados[1], dados[2]);
                clientes.add(cliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public static void excluirCliente(String nome) {
        List<Cliente> clientes = lerClientes();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENTES_CSV))) {
            for (Cliente cliente : clientes) {
                if (!cliente.getNome().equals(nome)) {
                    writer.write(cliente.getNome() + "," + cliente.getEndereco() + "," + cliente.getContato());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Métodos para salvar, ler e excluir empréstimos

    public static void salvarEmprestimo(Emprestimo emprestimo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPRESTIMOS_CSV, true))) {
            writer.write(emprestimo.getLivro().getIsbn() + "," + emprestimo.getCliente().getNome() + "," + DATE_FORMAT.format(emprestimo.getDataEmprestimo()) + "," + (emprestimo.getDataDevolucao() != null ? DATE_FORMAT.format(emprestimo.getDataDevolucao()) : ""));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Emprestimo> lerEmprestimos(List<Livro> livros, List<Cliente> clientes) {
        List<Emprestimo> emprestimos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPRESTIMOS_CSV))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                Livro livro = livros.stream().filter(l -> l.getIsbn().equals(dados[0])).findFirst().orElse(null);
                Cliente cliente = clientes.stream().filter(c -> c.getNome().equals(dados[1])).findFirst().orElse(null);
                Date dataEmprestimo = DATE_FORMAT.parse(dados[2]);
                Date dataDevolucao = dados.length > 3 && !dados[3].isEmpty() ? DATE_FORMAT.parse(dados[3]) : null;
                Emprestimo emprestimo = new Emprestimo(livro, cliente, dataEmprestimo);
                emprestimo.setDataDevolucao(dataDevolucao);
                emprestimos.add(emprestimo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emprestimos;
    }

    public static void excluirEmprestimo(String isbn, String nomeCliente) {
        List<Emprestimo> emprestimos = lerEmprestimos(lerLivros(), lerClientes());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPRESTIMOS_CSV))) {
            for (Emprestimo emprestimo : emprestimos) {
                if (!emprestimo.getLivro().getIsbn().equals(isbn) || !emprestimo.getCliente().getNome().equals(nomeCliente)) {
                    writer.write(emprestimo.getLivro().getIsbn() + "," + emprestimo.getCliente().getNome() + "," + DATE_FORMAT.format(emprestimo.getDataEmprestimo()) + "," + (emprestimo.getDataDevolucao() != null ? DATE_FORMAT.format(emprestimo.getDataDevolucao()) : ""));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

