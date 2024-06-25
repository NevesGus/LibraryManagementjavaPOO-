import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LibraryGUI extends JFrame {
    private JTextField tituloField, autorField, isbnField, categoriaField;
    private JTextField nomeField, enderecoField, contatoField;
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

        JButton adicionarLivroButton = new JButton("Adicionar Livro");
        adicionarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarLivro();
            }
        });
        livrosPanel.add(adicionarLivroButton);

        JButton excluirLivroButton = new JButton("Excluir Livro");
        excluirLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirLivro();
            }
        });
        livrosPanel.add(excluirLivroButton);

        JButton pesquisarLivroButton = new JButton("Pesquisar Livro");
        pesquisarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarLivro();
            }
        });
        livrosPanel.add(pesquisarLivroButton);

        tabbedPane.addTab("Livros", livrosPanel);

        // Painel de gerenciamento de clientes
        JPanel clientesPanel = new JPanel(new GridLayout(5, 2));
        clientesPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        clientesPanel.add(nomeField);

        clientesPanel.add(new JLabel("Endereço:"));
        enderecoField = new JTextField();
        clientesPanel.add(enderecoField);

        clientesPanel.add(new JLabel("Contato:"));
        contatoField = new JTextField();
        clientesPanel.add(contatoField);

        JButton adicionarClienteButton = new JButton("Adicionar Cliente");
        adicionarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCliente();
            }
        });
        clientesPanel.add(adicionarClienteButton);

        JButton excluirClienteButton = new JButton("Excluir Cliente");
        excluirClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCliente();
            }
        });
        clientesPanel.add(excluirClienteButton);

        JButton pesquisarClienteButton = new JButton("Pesquisar Cliente");
        pesquisarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarCliente();
            }
        });
        clientesPanel.add(pesquisarClienteButton);

        tabbedPane.addTab("Clientes", clientesPanel);

        // Painel de exibição
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        add(tabbedPane, BorderLayout.NORTH);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryGUI().setVisible(true);
            }
        });
    }
}

