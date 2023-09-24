package models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class BlogReaderspk implements Serializable {
	
	private Long idParte1;
    private Long idParte2;
    
    public BlogReaderspk() {
    }

    public BlogReaderspk(Long idParte1, Long idParte2) {
        this.idParte1 = idParte1;
        this.idParte2 = idParte2;
    }

	/**
	 * @return the idParte1
	 */
	public Long getIdParte1() {
		return idParte1;
	}

	/**
	 * @param idParte1 the idParte1 to set
	 */
	public void setIdParte1(Long idParte1) {
		this.idParte1 = idParte1;
	}

	/**
	 * @return the idParte2
	 */
	public Long getIdParte2() {
		return idParte2;
	}

	/**
	 * @param idParte2 the idParte2 to set
	 */
	public void setIdParte2(Long idParte2) {
		this.idParte2 = idParte2;
	}

    
    
}
