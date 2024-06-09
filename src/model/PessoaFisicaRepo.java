    package model;

    import java.io.*;
    import java.util.ArrayList;
    import java.util.List;

    public class PessoaFisicaRepo {
        private List<PessoaFisica> pessoasFisicas;

        public PessoaFisicaRepo() {
            this.pessoasFisicas = new ArrayList<>();
        }

        public void inserir(PessoaFisica pessoa) {
            pessoasFisicas.add(pessoa);
        }

        public void alterar(PessoaFisica pessoa) {
            for (int i = 0; i < pessoasFisicas.size(); i++) {
                if (pessoasFisicas.get(i).getId() == pessoa.getId()) {
                    pessoasFisicas.set(i, pessoa);
                    return;
                }
            }
        }

        public void excluir(int id) {
            pessoasFisicas.removeIf(p -> p.getId() == id);
        }

        public PessoaFisica obter(int id) {
            for (PessoaFisica p : pessoasFisicas) {
                if (p.getId() == id) {
                    return p;
                }
            }
            return null;
        }

        public List<PessoaFisica> obterTodos() {
            return new ArrayList<>(pessoasFisicas);
        }

        public void persistir(String arquivo) throws IOException {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
                oos.writeObject(pessoasFisicas);
            }
        }

        public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                pessoasFisicas = (List<PessoaFisica>) ois.readObject();
            }
        }
    }
