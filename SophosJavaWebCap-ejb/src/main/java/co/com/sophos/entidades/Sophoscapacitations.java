/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sophos.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author cristian.ordonez
 */
@Entity
@Table(name = "SOPHOSCAPACITATIONS")
@NamedQueries({
    @NamedQuery(name = "Sophoscapacitations.findAll", query = "SELECT s FROM Sophoscapacitations s"),
    @NamedQuery(name = "Sophoscapacitations.findByCapId", query = "SELECT s FROM Sophoscapacitations s WHERE s.capId = :capId"),
    @NamedQuery(name = "Sophoscapacitations.findByCapName", query = "SELECT s FROM Sophoscapacitations s WHERE s.capName = :capName"),
    @NamedQuery(name = "Sophoscapacitations.findByCapInitDate", query = "SELECT s FROM Sophoscapacitations s WHERE s.capInitDate = :capInitDate"),
    @NamedQuery(name = "Sophoscapacitations.findByCapEndDate", query = "SELECT s FROM Sophoscapacitations s WHERE s.capEndDate = :capEndDate")})
public class Sophoscapacitations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAP_ID")
    @GeneratedValue(generator = "CAPACITACIONES_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "CAPACITACIONES_SEQ", sequenceName = "CAPACITACIONES_SEQ", initialValue = 1, allocationSize = 1)
    private Long capId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CAP_NAME")
    private String capName;
    @Column(name = "CAP_INIT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date capInitDate;
    @Column(name = "CAP_END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date capEndDate;
    @JoinTable(name = "SOPHOSCAPXEMP", joinColumns = {
        @JoinColumn(name = "CAP_ID", referencedColumnName = "CAP_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "EMPEST_ID", referencedColumnName = "EMPEST_ID")})
    @ManyToMany
    private List<Sophosemployeestudent> sophosemployeestudentList;
    @JoinColumn(name = "CAP_CATEGORY", referencedColumnName = "CAT_ID")
    @ManyToOne
    private Sophoscapcategories capCategory;

    public Sophoscapacitations() {
    }

    public Sophoscapacitations(Long capId) {
        this.capId = capId;
    }

    public Sophoscapacitations(Long capId, String capName) {
        this.capId = capId;
        this.capName = capName;
    }

    public Long getCapId() {
        return capId;
    }

    public void setCapId(Long capId) {
        this.capId = capId;
    }

    public String getCapName() {
        return capName;
    }

    public void setCapName(String capName) {
        this.capName = capName;
    }

    public Date getCapInitDate() {
        return capInitDate;
    }

    public void setCapInitDate(Date capInitDate) {
        this.capInitDate = capInitDate;
    }

    public Date getCapEndDate() {
        return capEndDate;
    }

    public void setCapEndDate(Date capEndDate) {
        this.capEndDate = capEndDate;
    }

    public List<Sophosemployeestudent> getSophosemployeestudentList() {
        return sophosemployeestudentList;
    }

    public void setSophosemployeestudentList(List<Sophosemployeestudent> sophosemployeestudentList) {
        this.sophosemployeestudentList = sophosemployeestudentList;
    }

    public Sophoscapcategories getCapCategory() {
        return capCategory;
    }

    public void setCapCategory(Sophoscapcategories capCategory) {
        this.capCategory = capCategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (capId != null ? capId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sophoscapacitations)) {
            return false;
        }
        Sophoscapacitations other = (Sophoscapacitations) object;
        if ((this.capId == null && other.capId != null) || (this.capId != null && !this.capId.equals(other.capId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.sophos.entities.Sophoscapacitations[ capId=" + capId + " ]";
    }

}
