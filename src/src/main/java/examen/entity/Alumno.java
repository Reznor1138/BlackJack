package examen.entity;

import javax.persistence.*;

@Entity
@Table(name = "alumno")
public class Alumno {
	private Integer id;
	private String nombre;
	private String email;

	public Alumno() {

	}

	public Alumno(String nombre, String email) {
		this.nombre = nombre;
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}