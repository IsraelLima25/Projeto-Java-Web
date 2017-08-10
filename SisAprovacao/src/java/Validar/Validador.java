package Validar;

public class Validador {

    public static boolean validarNome(String nome) {

        boolean retorno = nome.matches("(?i)[a-z]{4,20}\\s{1}[a-z]{4,20}\\s{1}[a-z]{4,20}");
        return retorno;
    }

    public static boolean validarSenha(String senha) {

        boolean retorno = senha.matches("\\w{6}");
        return retorno;
    }

    public static boolean validarEmail(String email) {

        boolean retorno = email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        return retorno;
    }

    public static boolean validarCentro(String centro) {
        boolean retorno = centro.matches("(?i)[a-z]{10,45}");
        return retorno;
    }
}
