package com.empresa.clinicaodontologica.controlador;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.empresa.clinicaodontologica.entidade.UsuarioEntidade;
// import com.empresa.clinicaodontologica.servico.implementacao.UsuarioServicoImplementacao;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {   
    // private final UsuarioServicoImplementacao usuarioServico;
  
    // public UsuarioControlador(UsuarioServicoImplementacao usuarioServico) {
    //     this.usuarioServico = usuarioServico;
    // }

    // @PostMapping("/salvar")
    // public ResponseEntity<UsuarioEntidade> salvar(@RequestBody UsuarioEntidade usuario) {
    //     UsuarioEntidade usuarioEntidade = usuarioServico.salvar(usuario);
        
    //     if (usuarioEntidade == null) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    //     }
        
    //     return ResponseEntity.ok().body(usuarioEntidade);
    // }

    // @GetMapping("/buscar_tudo")
    // public ResponseEntity<List<UsuarioEntidade>> buscarTudo() {
    //     return ResponseEntity.ok().body(usuarioServico.buscarTudo());
    // }

    // @GetMapping("/buscar/{id}")
    // public ResponseEntity<UsuarioEntidade> buscarPorId(@PathVariable Integer id) {
    //     Optional<UsuarioEntidade> usuarioEntidade = usuarioServico.buscarPorId(id);

    //     if (usuarioEntidade.isPresent()) {
    //         return ResponseEntity.ok().body(usuarioEntidade.get());
    //     } else if (usuarioEntidade.isEmpty()) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    //     } 
        
    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    // }

    // @PutMapping("/atualizar")
    // public ResponseEntity<UsuarioEntidade> atualizar(@RequestBody UsuarioEntidade usuario) {
    //     UsuarioEntidade usuarioEntidade = usuarioServico.atualizar(usuario);
        
    //     if (usuarioEntidade == null) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    //     }

    //     return ResponseEntity.ok().body(usuarioEntidade);
    // }  

    // @DeleteMapping("/excluir/{id}") 
    // public ResponseEntity<String> excluir(@PathVariable Integer id) {
    //     if (usuarioServico.excluir(id)) {
    //         return ResponseEntity.ok().body(String.format("O registro foi excluído com sucesso.", id));
    //     } 

    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível excluir o registro.");
    // }

    @GetMapping("/tela_inicial")
    public String inicio() {
        return "<h1> Bem-vindo! </h1>";
    }

    @GetMapping("/paciente")
    public String paciente() {
        return "<h1> Bem-vindo paciente </h1>";
    }

    @GetMapping("/dentista")
    public String dentista() {
        return "<h1> Bem-vindo dentista </h1>";
    }

    @GetMapping("/administrador")
    public String administrador() {
        return "<h1> Bem-vindo administrador </h1>";
    }
}
