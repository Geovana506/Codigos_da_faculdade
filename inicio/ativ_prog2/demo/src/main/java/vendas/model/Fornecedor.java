package vendas.model;

public class Fornecedor {
    private int id;
    private String razaoSocial;
    private String cnpj;
    private String nomeFantasia;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getNomeFantasia() { return nomeFantasia; }
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }
}