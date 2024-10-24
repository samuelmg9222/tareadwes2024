package modelo;

import java.util.Objects;

public class Planta {
private String codigo;
private String nombrecomun;
private String nombrecientifico;




public Planta(String codigo, String nombrecomun, String nombrecientifico) {
	super();
	this.codigo = codigo;
	this.nombrecomun = nombrecomun;
	this.nombrecientifico = nombrecientifico;
}


public Planta() {
	super();
}


public String getCodigo() {
	return codigo;
}
public void setCodigo(String codigo) {
	this.codigo = codigo;
}
public String getNombrecomun() {
	return nombrecomun;
}
public void setNombrecomun(String nombrecomun) {
	this.nombrecomun = nombrecomun;
}
public String getNombrecientifico() {
	return nombrecientifico;
}
public void setNombrecientifico(String nombrecientifico) {
	this.nombrecientifico = nombrecientifico;
}


@Override
public String toString() {
	return "Planta [codigo=" + codigo + ", nombrecomun=" + nombrecomun + ", nombrecientifico=" + nombrecientifico + "]";
}


@Override
public int hashCode() {
	return Objects.hash(codigo, nombrecientifico, nombrecomun);
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Planta other = (Planta) obj;
	return Objects.equals(codigo, other.codigo) && Objects.equals(nombrecientifico, other.nombrecientifico)
			&& Objects.equals(nombrecomun, other.nombrecomun);
}









}
