package com.backend.Odontologo.dto.entrada;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OdontologoEntradaDto {

    @NotNull(message = "La matrícula no puede ser nula")
    private Integer matricula;

    @Size(min = 2, max = 50, message = "El nombre del odontólogo debe tener entre 2 y 50 caracteres")
    @NotBlank(message = "Debe especificarse el nombre del odontólogo")
    private String nombre;

    @Size(min = 2, max = 50, message = "El apellido del odontólogo debe tener entre 2 y 50 caracteres")
    @NotBlank(message = "Debe especificarse el apellido del odontólogo")
    private String apellido;

        public OdontologoEntradaDto() {
        }

        public OdontologoEntradaDto(Integer matricula, String nombre, String apellido) {
            this.matricula = matricula;
            this.nombre = nombre;
            this.apellido = apellido;
        }

        public Integer getMatricula() {
            return matricula;
        }

        public void setMatricula(Integer matricula) {
            this.matricula = matricula;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }
    }
