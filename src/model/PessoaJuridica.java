    package model;

    import java.io.Serializable;

    public class PessoaJuridica extends Pessoa implements Serializable {
        private String cnpj;

        public PessoaJuridica() {}

        public PessoaJuridica(int id, String nome, String cnpj) {
            super(id, nome);
            this.cnpj = cnpj;
        }

        public String getCnpj() {
            return cnpj;
        }

        public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
        }

        @Override
        public void exibir() {
            super.exibir();
            System.out.println("CNPJ: " + cnpj);
            System.out.println(); // Linha em branco para separar as pessoas
        }
    }
