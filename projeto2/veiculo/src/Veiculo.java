public class Veiculo {
    private int id;
    private String placa;
    private String nome;
    private int ano;
    private String modelo;
    private Fabricante fabricante;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public Fabricante getFabricante() {
        return fabricante;
    }
    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public void ImprimirVeiculo(){
        System.out.println("------ DADOS DO VEÍCULO ------");
        System.out.println("ID: " +getId());
        System.out.println("Placa: " +getPlaca());
        System.out.println("Nome: " +getNome());
        System.out.println("Ano: " +getAno());
        System.out.println("Modelo: " +getModelo());
    }
}