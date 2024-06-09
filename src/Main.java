package model;

import java.io.IOException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        int opcao;
        do {
            System.out.println("Menu:");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.print("Escolha uma opção: ");
            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        incluir(scanner, repoFisica, repoJuridica);
                        break;
                    case 2:
                        alterar(scanner, repoFisica, repoJuridica);
                        break;
                    case 3:
                        excluir(scanner, repoFisica, repoJuridica);
                        break;
                    case 4:
                        obter(scanner, repoFisica, repoJuridica);
                        break;
                    case 5:
                        obterTodos(scanner, repoFisica, repoJuridica);
                        break;
                    case 6:
                        salvar(scanner, repoFisica, repoJuridica);
                        break;
                    case 7:
                        recuperar(scanner, repoFisica, repoJuridica);
                        break;
                    case 0:
                        System.out.println("Finalizando execução.");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } else {
                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                scanner.next();
                opcao = -1;
            }
        } while (opcao != 0);
    }

    private static void incluir(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String tipo = scanner.next().toUpperCase();
        scanner.nextLine();

        if (!tipo.equals("F") && !tipo.equals("J")) {
            System.out.println("Opção inválida!");
            return;
        }

        if (tipo.equals("F")) {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Idade: ");
            if (scanner.hasNextInt()) {
                int idade = scanner.nextInt();
                scanner.nextLine();
                PessoaFisica pessoa = new PessoaFisica(repoFisica.obterTodos().size() + 1, nome, cpf, idade);
                repoFisica.inserir(pessoa);
            } else {
                System.out.println("Por favor, insira um número válido para a idade.");
                scanner.nextLine();
            }
        } else if (tipo.equals("J")) {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();
            PessoaJuridica pessoa = new PessoaJuridica(repoJuridica.obterTodos().size() + 1, nome, cnpj);
            repoJuridica.inserir(pessoa);
        }
    }



    private static void alterar(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String tipo = scanner.next().toUpperCase();
        scanner.nextLine();

        System.out.print("ID da pessoa a ser alterada: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipo.equals("F")) {
            PessoaFisica pessoa = repoFisica.obter(id);
            if (pessoa != null) {
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Nova idade: ");
                if (scanner.hasNextInt()) {
                    int idade = scanner.nextInt();
                    scanner.nextLine();
                    pessoa.setNome(nome);
                    pessoa.setCpf(cpf);
                    pessoa.setIdade(idade);
                    repoFisica.alterar(pessoa);
                } else {
                    System.out.println("Por favor, insira um número válido para a idade.");
                    scanner.nextLine();
                }
            } else {
                System.out.println("Pessoa física não encontrada com o ID fornecido.");
            }
        } else if (tipo.equals("J")) {
            PessoaJuridica pessoa = repoJuridica.obter(id);
            if (pessoa != null) {
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo CNPJ: ");
                String cnpj = scanner.nextLine();
                pessoa.setNome(nome);
                pessoa.setCnpj(cnpj);
                repoJuridica.alterar(pessoa);
            } else {
                System.out.println("Pessoa jurídica não encontrada com o ID fornecido.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void excluir(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String tipo = scanner.next().toUpperCase();
        scanner.nextLine();

        System.out.print("ID da pessoa a ser excluída: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipo.equals("F")) {
            if (repoFisica.obter(id) != null) {
                repoFisica.excluir(id);
                System.out.println("Pessoa física excluída com sucesso.");
            } else {
                System.out.println("Pessoa física não encontrada com o ID fornecido.");
            }
        } else if (tipo.equals("J")) {
            if (repoJuridica.obter(id) != null) {
                repoJuridica.excluir(id);
                System.out.println("Pessoa jurídica excluída com sucesso.");
            } else {
                System.out.println("Pessoa jurídica não encontrada com o ID fornecido.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }


    private static void obter(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String tipo = scanner.next().toUpperCase();
        scanner.nextLine();

        System.out.print("Digite o ID da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipo.equals("F")) {
            PessoaFisica pessoa = repoFisica.obter(id);
            if (pessoa != null) {
                pessoa.exibir();
            } else {
                System.out.println("Pessoa física não encontrada com o ID fornecido.");
            }
        } else if (tipo.equals("J")) {
            PessoaJuridica pessoa = repoJuridica.obter(id);
            if (pessoa != null) {
                pessoa.exibir();
            } else {
                System.out.println("Pessoa jurídica não encontrada com o ID fornecido.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }


    private static void obterTodos(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String tipo = scanner.next().toUpperCase();
        scanner.nextLine();

        if (tipo.equals("F")) {
            for (PessoaFisica pessoa : repoFisica.obterTodos()) {
                pessoa.exibir();
            }
        } else if (tipo.equals("J")) {
            for (PessoaJuridica pessoa : repoJuridica.obterTodos()) {
                pessoa.exibir();
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }


    private static void salvar(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Digite o prefixo para os arquivos: ");
        String prefixo = scanner.next();
        try {
            repoFisica.persistir(prefixo + ".fisica.bin");
            repoJuridica.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private static void recuperar(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Digite o prefixo dos arquivos a serem recuperados: ");
        String prefixo = scanner.next();
        try {
            repoFisica.recuperar(prefixo + ".fisica.bin");
            repoJuridica.recuperar(prefixo + ".juridica.bin");
            System.out.println("Dados recuperados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar os dados: " + e.getMessage());
        }
    }
}
