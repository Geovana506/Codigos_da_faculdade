public class Motos extends Veiculo {
    private Integer cilindrada; 

    public Motos() {
    }
    public Motos(String nome, Integer ano, Integer modelo, String cor, String placa, Boolean unico_dono,
            Integer cilindrada, Garagem garagem, Categoria categoria) {
        super(nome, ano, modelo, cor, placa, unico_dono, garagem, categoria);
        this.cilindrada = cilindrada;
    }
    public Integer getCilindrada() {
        return cilindrada;
    }
    public void setCilindrada(Integer cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public void salvar () {
        System.out.println("Moto salvo com sucesso!");
        super.salvar();
    }
    @Override
    public void alterar () {
        System.out.println("Moto alterado com sucesso!");
        super.alterar();
    }   
    @Override
    public void excluir () {
        System.out.println("Moto excluído com sucesso!");
        super.excluir();
    }
    @Override
    public void pesquisar () {
        System.out.println("Moto pesquisado com sucesso!");
        super.pesquisar();
    }
}
