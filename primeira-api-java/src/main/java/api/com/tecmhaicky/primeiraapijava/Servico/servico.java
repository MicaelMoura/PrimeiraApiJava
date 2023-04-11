package api.com.tecmhaicky.primeiraapijava.Servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.com.tecmhaicky.primeiraapijava.Models.Mensage;
import api.com.tecmhaicky.primeiraapijava.Models.Pessoa;
import api.com.tecmhaicky.primeiraapijava.Repositorio.repositorio;

@Service
public class servico {
    
    @Autowired
    private Mensage mensage;

    @Autowired
    private repositorio acao;

    // [C]RUD
        // Cadastrar pessoa
        public ResponseEntity<?> cadastro(Pessoa obj) {

            if(obj.getNome().equals("")) {
                mensage.setMensage("Nome inválido.");
                return new ResponseEntity<>(mensage, HttpStatus.BAD_REQUEST);
            }
            else {
                if(obj.getIdade() <= 0 || obj.getIdade() > 150) {
                    mensage.setMensage("Idade inválida.");
                    return new ResponseEntity<>(mensage, HttpStatus.BAD_REQUEST);
                }
                else if(obj.getIdade() < 18){
                    mensage.setMensage("Apenas maiores de idade podem ser cadastrados.");
                    return new ResponseEntity<>(mensage, HttpStatus.BAD_REQUEST);
                }
                else {
                    return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
                }
            }

        }

    // C[R]UD
        // Selecionar todas as pessoas
        public ResponseEntity<?> listaPessoas() {
            return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
        }

        // Seleciona pessoa pelo id
        public ResponseEntity<?> selecionaPessoa(int id) {
            if(acao.countById(id) == 0) {
                mensage.setMensage("Código não encontrado");
                return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(acao.findById(id), HttpStatus.OK);
            }
        }

        // Seleciona pessoas pela idade
        public ResponseEntity<?> selecionaIdade(int idade) {
            if(idade <= 0 || idade > 150) {
                mensage.setMensage("Idade inválida.");
                return new ResponseEntity<>(mensage, HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(acao.findByIdade(idade), HttpStatus.OK);
            }
        }

        // Pessoas cadastradas
        public Long countPessoas() {
            return acao.count();
        }

        // Retorna pessoas de forma ordenada
        // c = código
        // i = idade
        // n = nome
        public ResponseEntity<?> selecionaPessoasOrdenadas(String ordenador, String order) {
            
            ordenador = ordenador.toLowerCase();
            
            if(!order.equals("n") && !order.equals("i") && !order.equals("c") ) {
                mensage.setMensage("order informado incorretamente");
                return new ResponseEntity<>(mensage, HttpStatus.BAD_REQUEST);
            }
            
            if(!ordenador.equals("asc") && !ordenador.equals("desc")) {
                ordenador = "asc";
            }
             
            if(order.equals("n")) {
                if(ordenador.equals("desc")) {
                    return new ResponseEntity<>(acao.findByOrderByNomeDesc(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(acao.findByOrderByNome(), HttpStatus.OK);
                }
            } else if(order.equals("i")) {
                if(ordenador.equals("desc")) {
                    return new ResponseEntity<>(acao.findByOrderByIdadeDesc(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(acao.findByOrderByIdade(), HttpStatus.OK);
                }
            } else {
                if(ordenador.equals("desc")) {
                    return new ResponseEntity<>(acao.findByOrderByIdDesc(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(acao.findByOrderById(), HttpStatus.OK);
                }
            }
        }

        // Busca pessoas pelo nome
        public ResponseEntity<?> buscaNome(String nome){
            if(nome.isEmpty()) {
                mensage.setMensage("Informe um nome.");
                return new ResponseEntity<>(mensage, HttpStatus.BAD_REQUEST);
            } else {
                if(acao.countByNome(nome) == 0) {
                    mensage.setMensage("Nenhuma pessoa encontrada.");
                    return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
                } else {
                    return new ResponseEntity<>(acao.findByNomeOrderByIdade(nome), HttpStatus.OK);
                }
            }
        }

        // Busca pessoas pela idade informada ou maior
        public ResponseEntity<?> pessoasIdadeMaiorIgual(int idade) {
            if(idade <= 0 || idade > 150) {
                mensage.setMensage("Idade inválida.");
                return new ResponseEntity<>(mensage, HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(acao.idadeMaiorIgual(idade), HttpStatus.OK);
            }
        }

        // Busca com containing
        public ResponseEntity<?> buscaContainig(String termo){
            if(acao.findByNomeContaining(termo).size() == 0) {
                mensage.setMensage("Nenhuma pessoa encontrada.");
                return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(acao.findByNomeContaining(termo), HttpStatus.OK);
            }
        }

        // Retorna a soma de todas as idades do banco
        public int somaIdadesTodos(){
            return acao.somaIdades();
        }
    
    // CR[U]D
        // Editar pessoa
        public ResponseEntity<?> editar(Pessoa pessoa) {
            if(acao.countById(pessoa.getId()) == 0) {
                mensage.setMensage("Código não encontrado");
                return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);            
            } else if(pessoa.getNome().equals("")) {
                mensage.setMensage("Nome inválido.");
                return new ResponseEntity<>(mensage, HttpStatus.BAD_REQUEST);
            }
            else {
                if(pessoa.getIdade() <= 0 || pessoa.getIdade() > 150) {
                    mensage.setMensage("Idade inválida.");
                    return new ResponseEntity<>(mensage, HttpStatus.BAD_REQUEST);
                }
                else if(pessoa.getIdade() < 18){
                    mensage.setMensage("Apenas maiores de idade podem ser cadastrados.");
                    return new ResponseEntity<>(mensage, HttpStatus.BAD_REQUEST);
                }
                else {
                    return new ResponseEntity<>(acao.save(pessoa), HttpStatus.OK);
                }
            }
        }

    // CRU[D]
        // Deletar pessoa
        public ResponseEntity<?> deletar(int id){
            if(acao.countById(id) == 0) {
                mensage.setMensage("Código não encontrado");
                return new ResponseEntity<>(mensage, HttpStatus.NOT_FOUND);            
            } else {
                acao.deleteById(id);

                mensage.setMensage("Código " + id + " deletado.");
                return new ResponseEntity<>(mensage, HttpStatus.OK);
            }
        }
}
