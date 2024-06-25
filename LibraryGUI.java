import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LibraryGUI extends JFrame {
    private JTextField tituloField, autorField, isbnField, categoriaField;
    private JTextField nomeField, enderecoField, contatoField;
    private JTextField isbnEmprestimoField, nomeClienteEmprestimoField, dataEmprestimoField;
    private JTextArea displayArea;
    private UserAuth userAuth;

    public LibraryGUI() {
        userAuth = new UserAuth();
        exibirTelaLogin();
    }

    private void exibirTelaLogin() {
        JTextField usuarioField = new JTextField();
        JPasswordField senhaField = new JPasswordField();
        Object[] message = {
            "Usuário:", usuarioField,
            "Senha:", senhaField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String usuario = usuarioField.getText();
            String senha = new String(senhaField.getPassword());
            if (userAuth.autenticar(usuario, senha)) {
                inicializarInterface();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.", "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
                exibirTelaLogin();
            }
        } else {
            System.exit(0);
        }
    }

    private void inicializarInterface() {
        setTitle("Biblioteca");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Painel de gerenciamento de livros
        JPanel livrosPanel = new JPanel(new GridLayout(6, 2));
        livrosPanel.add(new JLabel("Título:"));
        tituloField = new JTextField();
        livrosPanel.add(tituloField);

        livrosPanel.add(new JLabel("Autor:"));
        autorField = new JTextField();
        livrosPanel.add(autorField);

        livrosPanel.add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        livrosPanel.add(isbnField);

        livrosPanel.add(new JLabel("Categoria:"));
        categoriaField = new JTextField();
        livrosPanel.add(categoriaField);

        JButton adicionarLivroBtn = new JButton("Adicionar Livro");
        adicionarLivroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarLivro();
            }
        });
        livrosPanel.add(adicionarLivroBtn);

        JButton excluirLivroBtn = new JButton("Excluir Livro");
        excluirLivroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirLivro();
            }
        });
        livrosPanel.add(excluirLivroBtn);

        JButton pesquisarLivroBtn = new JButton("Pesquisar Livro");
        pesquisarLivroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarLivro();
            }
        });
        livrosPanel.add(pesquisarLivroBtn);

        // Painel de gerenciamento de clientes
        JPanel clientesPanel = new JPanel(new GridLayout(4, 2));
        clientesPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        clientesPanel.add(nomeField);

        clientesPanel.add(new JLabel("Endereço:"));
        enderecoField = new JTextField();
        clientesPanel.add(enderecoField);

        clientesPanel.add(new JLabel("Contato:"));
        contatoField = new JTextField();
        clientesPanel.add(contatoField);

        JButton adicionarClienteBtn = new JButton("Adicionar Cliente");
        adicionarClienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCliente();
            }
        });
        clientesPanel.add(adicionarClienteBtn);

        JButton excluirClienteBtn = new JButton("Excluir Cliente");
        excluirClienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCliente();
            }
        });
        clientesPanel.add(excluirClienteBtn);

        JButton pesquisarClienteBtn = new JButton("Pesquisar Cliente");
        pesquisarClienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarCliente();
            }
        });
        clientesPanel.add(pesquisarClienteBtn);

        // Painel de gerenciamento de empréstimos
        JPanel emprestimosPanel = new JPanel(new GridLayout(4, 2));
        emprestimosPanel.add(new JLabel("ISBN do Livro:"));
        isbnEmprestimoField = new JTextField();
        emprestimosPanel.add(isbnEmprestimoField);

        emprestimosPanel.add(new JLabel("Nome do Cliente:"));
        nomeClienteEmprestimoField = new JTextField();
        emprestimosPanel.add(nomeClienteEmprestimoField);

        emprestimosPanel.add(new JLabel("Data de Empréstimo:"));
        dataEmprestimoField = new JTextField();
        emprestimosPanel.add(dataEmprestimoField);

        JButton registrarEmprestimoBtn = new JButton("Registrar Empréstimo");
        registrarEmprestimoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEmprestimo();
            }
        });
        emprestimosPanel.add(registrarEmprestimoBtn);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        add(tabbedPane, BorderLayout.CENTER);
        add(new JScrollPane(displayArea), BorderLayout.SOUTH);

        tabbedPane.addTab("Livros", livrosPanel);
        tabbedPane.addTab("Clientes", clientesPanel);
        tabbedPane.addTab("Empréstimos", emprestimosPanel);
    }

    private void adicionarLivro() {
        String titulo = tituloField.getText();
        String autor = autorField.getText();
        String isbn = isbnField.getText();
        String categoria = categoriaField.getText();

        Livro livro = new Livro(titulo, autor, isbn, categoria);
        CSVUtils.salvarLivro(livro);
        displayArea.setText("Livro adicionado com sucesso!");
    }

    private void excluirLivro() {
        String isbn = isbnField.getText();
        CSVUtils.excluirLivro(isbn);
        displayArea.setText("Livro excluído com sucesso!");
    }

    private void pesquisarLivro() {
        String isbn = isbnField.getText();
        List<Livro> livros = CSVUtils.lerLivros();
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                displayArea.setText("Livro encontrado:\nTítulo: " + livro.getTitulo() + "\nAutor: " + livro.getAutor() + "\nCategoria: " + livro.getCategoria() + "\nDisponível: " + livro.isDisponivel());
                return;
            }
        }
        displayArea.setText("Livro não encontrado.");
    }

    private void adicionarCliente() {
        String nome = nomeField.getText();
        String endereco = enderecoField.getText();
        String contato = contatoField.getText();

        Cliente cliente = new Cliente(nome, endereco, contato);
        CSVUtils.salvarCliente(cliente);
        displayArea.setText("Cliente adicionado com sucesso!");
    }

    private void excluirCliente() {
        String nome = nomeField.getText();
        CSVUtils.excluirCliente(nome);
        displayArea.setText("Cliente excluído com sucesso!");
    }

    private void pesquisarCliente() {
        String nome = nomeField.getText();
        List<Cliente> clientes = CSVUtils.lerClientes();
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equals(nome)) {
                displayArea.setText("Cliente encontrado:\nEndereço: " + cliente.getEndereco() + "\nContato: " + cliente.getContato());
                return;
            }
        }
        displayArea.setText("Cliente não encontrado.");
    }

    private void registrarEmprestimo() {
        String isbn = isbnEmprestimoField.getText();
        String nomeCliente = nomeClienteEmprestimoField.getText();
        String dataEmprestimoStr = dataEmprestimoField.getText();

        Livro livro = CSVUtils.buscarLivroPorISBN(isbn); // Buscar livro por ISBN
        Cliente cliente = CSVUtils.buscarClientePorNome(nomeCliente); // Buscar cliente por nome
        if (livro != null && cliente != null) {
            try {
                Date dataEmprestimo = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmprestimoStr);
                Emprestimo emprestimo = new Emprestimo(livro, cliente, dataEmprestimo);
                CSVUtils.salvarEmprestimo(emprestimo);
                displayArea.setText("Empréstimo registrado com sucesso!");
            } catch (ParseException e) {
                displayArea.setText("Formato de data inválido. Use dd/MM/yyyy.");
            }
        } else {
            displayArea.setText("Livro ou Cliente não encontrado.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryGUI().setVisible(true);
            }
        });
    }
}

