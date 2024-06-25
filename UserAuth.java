import java.util.HashMap;
import java.util.Map;

public class UserAuth {
    private Map<String, String> usuarios;

    public UserAuth() {
        usuarios = new HashMap<>();
        // Adicionar usuários padrão (librarian, admin)
        usuarios.put("librarian", "lib123");
        usuarios.put("admin", "admin123");
    }

    public boolean autenticar(String usuario, String senha) {
        return usuarios.containsKey(usuario) && usuarios.get(usuario).equals(senha);
    }
    
    public void adicionarUsuario(String usuario, String senha) {
        usuarios.put(usuario, senha);
    }

    public void removerUsuario(String usuario) {
        usuarios.remove(usuario);
    }

    public boolean existeUsuario(String usuario) {
        return usuarios.containsKey(usuario);
    }
}

