package Excecoes;

public class NegocioException {
    public static void executar(Runnable acao) {
        try {
            acao.run();
        } catch (SaldoInsuficienteException e) {
            System.out.println("[BANCO] Atenção: " + e.getMessage());
        } catch (LimiteExcedidoException e ){
            System.out.println("[BANCO] Atenção: " + e.getMessage());
        } catch (Exception e){
            System.out.println("[SISTEMA] Erro interno. Contate o suporte ou tente novamente mais tarde.");
        }
    }

}

