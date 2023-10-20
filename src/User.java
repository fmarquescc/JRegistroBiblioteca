public record User(String name, String email, String morada, String telefone, String nif, String login, String password) {
    public String mostra() {
        return "Nome: " + this.name + "\nEmail: " + this.email
                + "\nMorada: " + this.morada + "\nTelefone: " + this.telefone
                + "\nNIF: " + this.nif + "\nLogin: " + this.login + "\nPassword: " + this.password;
    }
}
