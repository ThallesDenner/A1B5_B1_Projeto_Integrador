package com.empresa.clinicaodontologica.entidade;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

// import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Paciente")
@Data  // atalho para @ToString, @EqualsAndHashCode, @Getter, @Setter e @RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // veja a observação 1
public class PacienteEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaciente;

    @NotNull
    private String nome;

    private String sobrenome;

    // @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")  // veja a observação 2
    private LocalDate dataNascimento;
    
    @NaturalId
    @NotNull
    @Size(min = 11, max = 11)
    private String cpf;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // descreve a relação entre Paciente e Endereco
    @JoinColumn(name = "idEndereco" , referencedColumnName = "idEndereco")  // essa anotação especifica a coluna que será tratada como chave estrangeira no relacionamento entre Paciente e Endereco.
    // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // veja a observação 1
    private EnderecoEntidade endereco;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // descreve a relação entre Paciente e Usuario
    @JoinColumn(name = "idUsuario" , referencedColumnName = "idUsuario")  // essa anotação especifica a coluna que será tratada como chave estrangeira no relacionamento entre Paciente e Usuario.
    // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // veja a observação 1
    private UsuarioEntidade usuario;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // mappedBy = "paciente" -> a chave estrangeira estará na tabela Consulta
    @JsonIgnore  // veja a observação 3
    private final Set<ConsultaEntidade> consultas = new HashSet<ConsultaEntidade>();

    public PacienteEntidade(String nome, String sobrenome, LocalDate dataNascimento, String cpf,
            EnderecoEntidade endereco, UsuarioEntidade usuario) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.usuario = usuario;
    }

    // public PacienteEntidade() {
    // }

    // @Override
    // public String toString() {
    //     return String.format(
    //         "Paciente {idPaciente: %d, nome: %s, sobrenome: %s, dataNascimento: %s, cpf: %s, endereco: %s, usuario: %s}", 
    //         idPaciente, nome, sobrenome, dataNascimento, cpf, endereco, usuario
    //     );
    // }

    // public Integer getIdPaciente() {
    //     return idPaciente;
    // }

    // public void setIdPaciente(Integer idPaciente) {
    //     this.idPaciente = idPaciente;
    // }

    // public String getNome() {
    //     return nome;
    // }

    // public void setNome(String nome) {
    //     this.nome = nome;
    // }

    // public String getSobrenome() {
    //     return sobrenome;
    // }

    // public void setSobrenome(String sobrenome) {
    //     this.sobrenome = sobrenome;
    // }

    // public LocalDate getDataNascimento() {
    //     return dataNascimento;
    // }

    // public void setDataNascimento(LocalDate dataNascimento) {
    //     this.dataNascimento = dataNascimento;
    // }

    // public String getCpf() {
    //     return cpf;
    // }

    // public void setCpf(String cpf) {
    //     this.cpf = cpf;
    // }

    // public EnderecoEntidade getEndereco() {
    //     return endereco;
    // }

    // public void setEndereco(EnderecoEntidade endereco) {
    //     this.endereco = endereco;
    // }

    // public UsuarioEntidade getUsuario() {
    //     return usuario;
    // }

    // public void setUsuario(UsuarioEntidade usuario) {
    //     this.usuario = usuario;
    // }

    // public Set<ConsultaEntidade> getConsultas() {
    //     return consultas;
    // }

    // public void setConsultas(Set<ConsultaEntidade> consultas) {
    //     this.consultas = consultas;
    // }
}

/*
 * Observação 1:
 * Para lidar com o problema relacionado à serialização do modelo usando a API Jackson quando os atributos do modelo 
 * têm um carregamento lento definido (FetchType.LAZY), temos que dizer ao serializador para ignorar a cadeia ou lixo 
 * útil que o Hibernate adiciona às classes, para que ele possa gerenciar o carregamento lento de dados declarando a 
 * anotação: @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}).
 * No caso, essa anotação foi colocada acima das classes Endereco e Usuario.
 * 
 * Links:
 * https://dzone.com/articles/key-java-annotations-to-build-full-spring-boot-res
 * 
 * 
 * 
 * Observação 2:
 * @JsonFormat é uma anotação de Jackson que usamos para especificar como formatar campos e/ou propriedades para saída JSON.
 * Além de especificar o formato de data, também podemos especificar a localidade para serialização.
 * Não especificar esse parâmetro resulta na execução da serialização com a localidade padrão:
 * @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ", locale = "en_GB")
 * 
 * Links:
 * https://www.baeldung.com/jackson-jsonformat
 * 
 * 
 * 
 * Observação 3:
 * A anotação @JsonIgnore é usado no nível do campo para marcar uma propriedade ou lista de propriedades a serem ignoradas
 * durante a serialização/deserialização.
 * 
 * Links:
 * https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsonignore.htm
 * https://www.baeldung.com/jackson-annotations
 */