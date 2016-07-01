

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sophos.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author cristian.ordonez
 */
@Entity
@Table(name = "SOPHOSCAPCATEGORIES")
@NamedQueries({
    @NamedQuery(name = "Sophoscapcategories.findAll", query = "SELECT s FROM Sophoscapcategories s"),
    @NamedQuery(name = "Sophoscapcategories.findByCatId", query = "SELECT s FROM Sophoscapcategories s WHERE s.catid = :catid"),
    @NamedQuery(name = "Sophoscapcategories.findByCatName", query = "SELECT s FROM Sophoscapcategories s WHERE s.catname = :catname"),
    @NamedQuery(name = "Sophoscapcategories.findByCatDescription", query = "SELECT s FROM Sophoscapcategories s WHERE s.catdescription = :catdescription"),
    @NamedQuery(name = "Sophoscapcategories.findByCatKeyWords", query = "SELECT s FROM Sophoscapcategories s WHERE s.catkeywords = :catkeywords")})
public class Sophoscapcategories implements Serializable {

    @OneToMany(mappedBy = "capCategory")
    private List<Sophoscapacitations> sophoscapacitationsList;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAT_ID")
    @GeneratedValue(generator="CATEGORIAS_SEQ",strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name = "CATEGORIAS_SEQ", sequenceName = "CATEGORIAS_SEQ", initialValue = 1, allocationSize = 1)
    private BigDecimal catid;
    @Size(max = 30)
    @Column(name = "CAT_NAME")
    private String catname;
    @Size(max = 30)
    @Column(name = "CAT_DESCRIPTION")
    private String catdescription;
    @Size(max = 30)
    @Column(name = "CAT_KEY_WORDS")
    private String catkeywords;

    public Sophoscapcategories() {
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catid != null ? catid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sophoscapcategories)) {
            return false;
        }
        Sophoscapcategories other = (Sophoscapcategories) object;
        if ((this.catid == null && other.catid != null) || (this.catid != null && !this.catid.equals(other.catid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.sophos.entidades.Sophoscapcategories[ catId=" + catid + " ]";
    }

    public Sophoscapcategories(BigDecimal catid) {
        this.catid = catid;
    }

    public BigDecimal getCatid() {
        return catid;
    }

    public void setCatid(BigDecimal catid) {
        this.catid = catid;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getCatdescription() {
        return catdescription;
    }

    public void setCatdescription(String catdescription) {
        this.catdescription = catdescription;
    }

    public String getCatkeywords() {
        return catkeywords;
    }

    public void setCatkeywords(String catkeywords) {
        this.catkeywords = catkeywords;
    }

    public List<Sophoscapacitations> getSophoscapacitationsList() {
        return sophoscapacitationsList;
    }

    public void setSophoscapacitationsList(List<Sophoscapacitations> sophoscapacitationsList) {
        this.sophoscapacitationsList = sophoscapacitationsList;
    }

}
