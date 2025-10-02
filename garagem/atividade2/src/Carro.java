public class Carro extends Veiculo {
    private Integer morotizacao_cv;
    private Integer qtde_marcha;
    private String opcionais;

    public Carro() {
    }
    public Carro(String nome, Integer ano, Integer modelo, String cor, String placa, Boolean unico_dono,
            Integer morotizacao_cv, Integer qtde_marcha, String opcionais , Garagem garagem, Categoria categoria) {
        super(nome, ano, modelo, cor, placa, unico_dono, garagem, categoria);
        this.morotizacao_cv = morotizacao_cv;
        this.qtde_marcha = qtde_marcha;
        this.opcionais = opcionais;
    }
    public Integer getMorotizacao_cv() {
        return morotizacao_cv;
    }
    public void setMorotizacao_cv(Integer morotizacao_cv) {
        this.morotizacao_cv = morotizacao_cv;
    }
    public Integer getQtde_marcha() {
        return qtde_marcha;
    }
    public void setQtde_marcha(Integer qtde_marcha) {
        this.qtde_marcha = qtde_marcha;
    }
    public String getOpcionais() {
        return opcionais;
    }
    public void setOpcionais(String opcionais) {
        this.opcionais = opcionais;
    }

    @Override
    public void salvar () {
        System.out.println("Carro salvo com sucesso!");
        super.salvar();
    }
    @Override
    public void alterar () {
        System.out.println("Carro alterado com sucesso!");
        super.alterar();
    }   
    @Override
    public void excluir () {
        System.out.println("Carro excluído com sucesso!");
        super.excluir();
    }
    @Override
    public void pesquisar () {
        System.out.println("Carro pesquisado com sucesso!");
        super.pesquisar();
    }
    

}
