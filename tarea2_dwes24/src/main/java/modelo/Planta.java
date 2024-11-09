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
    return  String.format("%-10s %-20s %-30s", codigo, nombrecomun, nombrecientifico);
}











}
