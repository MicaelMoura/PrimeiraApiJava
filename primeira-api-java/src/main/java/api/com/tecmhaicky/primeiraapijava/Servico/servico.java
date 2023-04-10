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

    // Cadastrar pessoa com validação
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

}
