public class Maingar {
    public static void main(String[] args) throws Exception {
        Garagem garagem = new Garagem("Garagem da Geo", "Jataí");
        Categoria categoria1 = new Categoria("Esportivo");
        Motos moto1 = new Motos("Yamaha Fazer 250", 2021, 2021, "Vermelha", "ABC-1234", true, 250, garagem, categoria1);
        moto1.salvar();
        moto1.pesquisar();
        moto1.alterar();
        moto1.excluir();

        Categoria categoria2 = new Categoria("SUV");
        Carro carro1 = new Carro("Jeep Renegade", 2020, 2020, "Preto", "XYZ-5678", false, 4, 2020, "Automático", garagem, categoria2);
        carro1.salvar();
        carro1.pesquisar();
        carro1.alterar();
        carro1.excluir();

        
    }
}
