public class App {
    public static void main(String[] args) throws Exception {
        Veiculo veiculo1 = new Veiculo();
        veiculo1.setId(1);
        veiculo1.setPlaca("ONH-8409");
        veiculo1.setNome("Corolla");
        veiculo1.setAno(2016);
        veiculo1.setModelo("Sedan");

        Fabricante fabricante1 = new Fabricante();
        fabricante1.setId(1);   
        fabricante1.setNome("Toyota");
        fabricante1.setCidade("São Paulo");
        fabricante1.setCnpj("12.345.678/0001-90");

        veiculo1.ImprimirVeiculo();
        fabricante1.ImprimirFrabricante();
    }
}