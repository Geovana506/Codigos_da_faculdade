import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Venda {

    private LocalDate data;
    private String numeroVenda;
    private Produto produto;
    private Integer qtdeVendida;


    public void setData(LocalDate data) {
        this.data = data;
    }
    public LocalDate getData() {
        return data;
    }
    public String getNumeroVenda() {
        return numeroVenda;
    }
    public void setNumeroVenda(String numeroVenda) {
        this.numeroVenda = numeroVenda;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Integer getQtdeVendida() {
        return qtdeVendida;
    }
    public void setQtdeVendida(Integer qtdeVendida) {
        this.qtdeVendida = qtdeVendida;
    }

    public void ImprimirVenda(){
        System.out.println("------ DADOS DE VENDA ------");
        System.out.println("Número da venda: " +getNumeroVenda());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = getData().format(formatter);
        System.out.println("Data da venda: " + dataFormatada);

        System.out.println("------ DADOS DO PRODUTO ------");
        System.out.println("Produto Vendido: " +getProduto().getNome());
        System.out.println("Marca: " +getProduto().getMarca());
        System.out.println("Preço: " +getProduto().getPreco());
        System.out.println("Quantidade em estoque: " +getProduto().getQtde());
        System.out.println("-----------------------------");
    }
}