public class Fabricante {
    private int id;
    private String nome;
    private String cidade;
    private String cnpj;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    } 

    public void ImprimirFrabricante(){
        System.out.println("------ DADOS DO FABRICANTE ------");
        System.out.println("ID: " +getId());
        System.out.println("Nome: " +getNome());
        System.out.println("Cidade: " +getCidade());
        System.out.println("CNPJ: " +getCnpj());
    }
}