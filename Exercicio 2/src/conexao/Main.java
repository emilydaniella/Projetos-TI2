import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Inserir usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Buscar usuário por ID");
            System.out.println("4 - Atualizar usuário");
            System.out.println("5 - Excluir usuário");
            System.out.println("6 - Sair");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    Usuario usuario = new Usuario(0, nome, email, senha);
                    usuarioDAO.inserir(usuario);
                    break;

                case 2:
                    List<Usuario> usuarios = usuarioDAO.listarTodos();
                    for (Usuario u : usuarios) {
                        System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome() + ", Email: " + u.getEmail());
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do usuário: ");
                    int idBusca = scanner.nextInt();
                    scanner.nextLine();
                    Usuario usuarioEncontrado = usuarioDAO.buscarPorId(idBusca);
                    if (usuarioEncontrado != null) {
                        System.out.println("ID: " + usuarioEncontrado.getId());
                        System.out.println("Nome: " + usuarioEncontrado.getNome());
                        System.out.println("Email: " + usuarioEncontrado.getEmail());
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o ID do usuário a ser atualizado: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo email: ");
                    String novoEmail = scanner.nextLine();
                    System.out.print("Nova senha: ");
                    String novaSenha = scanner.nextLine();
                    
                    Usuario usuarioAtualizado = new Usuario(idAtualizar, novoNome, novoEmail, novaSenha);
                    usuarioDAO.atualizar(usuarioAtualizado);
                    break;

                case 5:
                    System.out.print("Digite o ID do usuário a ser excluído: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine();
                    usuarioDAO.excluir(idExcluir);
                    break;

                case 6:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
