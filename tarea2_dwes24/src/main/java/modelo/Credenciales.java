package modelo;

import java.util.Objects;

public class Credenciales {
    private Long id;
    private String usuario;
    private String password;
    private Long idpersona;
    public Credenciales(Long id, String usuario, String password, Long idpersona) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.idpersona = idpersona;
    }
    

    public Credenciales() {
		super();
	}


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


	
	public Long getIdpersona() {
		return idpersona;
	}

	public void setIdpersona(Long idpersona) {
		this.idpersona = idpersona;
	}

	@Override
	public String toString() {
		return "Credenciales [id=" + id + ", usuario=" + usuario + ", password=" + password + "]";
	}

}
