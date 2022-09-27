package com.empresa.clinicaodontologica.entidade;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

// import com.fasterxml.jackson.annotation.JsonFormat;
// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;

// import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Consulta")
// @Data  // atalho para @ToString, @EqualsAndHashCode, @Getter, @Setter e @RequiredArgsConstructor (veja a observação 3)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConsulta;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)  // descreve a relação entre Consulta e Paciente
    @JoinColumn(name = "idPaciente")  // essa anotação especifica a coluna que será tratada como chave estrangeira no relacionamento entre Consulta e Paciente.
    // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // veja a observação 1
    private PacienteEntidade paciente;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)  // descreve a relação entre Consulta e Dentista
    @JoinColumn(name = "idDentista")  // essa anotação especifica a coluna que será tratada como chave estrangeira no relacionamento entre Consulta e Dentista.
    // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // veja a observação 1
    private DentistaEntidade dentista;

    @NotNull
    // @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataConsulta;

    @NotNull
    // @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horaConsulta;

    public ConsultaEntidade(PacienteEntidade paciente, DentistaEntidade dentista, LocalDate dataConsulta,
            LocalTime horaConsulta) {
        this.paciente = paciente;
        this.dentista = dentista;
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
    }

    // public ConsultaEntidade() {
    // }

    // @Override
    // public String toString() {
    //     return String.format("ConsultaModelo {idConsulta: %d, paciente: %s, dentista: %s, dataConsulta: %s, horaConsulta: %s}", 
    //     idConsulta, paciente, dentista, dataConsulta, horaConsulta);
    // }

    // public Integer getIdConsulta() {
    //     return idConsulta;
    // }

    // public void setIdConsulta(Integer idConsulta) {
    //     this.idConsulta = idConsulta;
    // }

    // public PacienteEntidade getPaciente() {
    //     return paciente;
    // }

    // public void setPaciente(PacienteEntidade paciente) {
    //     this.paciente = paciente;
    // }

    // public DentistaEntidade getDentista() {
    //     return dentista;
    // }

    // public void setDentista(DentistaEntidade dentista) {
    //     this.dentista = dentista;
    // }

    // public LocalDate getDataConsulta() {
    //     return dataConsulta;
    // }

    // public void setDataConsulta(LocalDate dataConsulta) {
    //     this.dataConsulta = dataConsulta;
    // }

    // public LocalTime getHoraConsulta() {
    //     return horaConsulta;
    // }

    // public void setHoraConsulta(LocalTime horaConsulta) {
    //     this.horaConsulta = horaConsulta;
    // }
}

/*
 * Observação 1:
 * Para lidar com o problema relacionado à serialização do modelo usando a API Jackson quando os atributos do modelo 
 * têm um carregamento lento definido (FetchType.LAZY), temos que dizer ao serializador para ignorar a cadeia ou lixo 
 * útil que o Hibernate adiciona às classes, para que ele possa gerenciar o carregamento lento de dados declarando a 
 * anotação: @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}).
 * No caso, essa anotação foi colocada acima das classes Paciente e Dentista.
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
 * @Data está comentado porque está gerando um erro ao excluir registros da tabela Paciente ou Dentista quando o id desses registros estão na tabela Consulta.
 * Porvavelmente, o problema está no @EqualsAndHashCode gerdo automaticamente pelo @Data.
 */
