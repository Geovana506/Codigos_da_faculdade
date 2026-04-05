package primeiro_projeto_p2;

import controller.ProdutorController;
import util.Conexao;

public class Main {
    public static void main(String[] args) {

        try {
            System.out.println("Conexão estabelecida com sucesso!");

            ProdutorController controller = new ProdutorController();
            controller.listarProdutores();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao();
        }
    }
}