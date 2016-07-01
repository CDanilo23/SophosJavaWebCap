/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sophos.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author cristian.ordonez
 */
@Entity
@Table(name = "SOPHOSEMPLOYEESTUDENT")
@NamedQueries({
    @NamedQuery(name = "Sophosemployeestudent.findAll", query = "SELECT s FROM Sophosemployeestudent s"),
    @NamedQuery(name = "Sophosemployeestudent.findByEmpestId", query = "SELECT s FROM Sophosemployeestudent s WHERE s.empestId = :empestId"),
    @NamedQuery(name = "Sophosemployeestudent.findByEmpestEmployeeid", query = "SELECT s FROM Sophosemployeestudent s WHERE s.empestEmployeeid = :empestEmployeeid"),
    @NamedQuery(name = "Sophosemployeestudent.findByEmpestEmployeeshortname", query = "SELECT s FROM Sophosemployeestudent s WHERE s.empestEmployeeshortname LIKE :empestEmployeeshortname")})
public class Sophosemployeestudent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMPEST_ID")
    @GeneratedValue(generator = "ESTUDIANTES_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ESTUDIANTES_SEQ", sequenceName = "ESTUDIANTES_SEQ", initialValue = 1, allocationSize = 1)
    private Long empestId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMPEST_EMPLOYEEID")
    private String empestEmployeeid;
    @Size(max = 200)
    @Column(name = "EMPEST_EMPLOYEESHORTNAME")
    private String empestEmployeeshortname;
    
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    
    @Size(max = 20)
    @Column(name = "TELEFONO")
    private String telefono;
    
    @Size(max = 30)
    @Column(name = "CARGO")
    private String cargo;
    
    @ManyToMany(mappedBy = "sophosemployeestudentList",fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
    private List<Sophoscapacitations> sophoscapacitationsList;

    public Sophosemployeestudent() {
    }

    public Sophosemployeestudent(Long empestId) {
        this.empestId = empestId;
    }

    public Sophosemployeestudent(Long empestId, String empestEmployeeid) {
        this.empestId = empestId;
        this.empestEmployeeid = empestEmployeeid;
    }

    public Long getEmpestId() {
        return empestId;
    }

    public void setEmpestId(Long empestId) {
        this.empestId = empestId;
    }

    public String getEmpestEmployeeid() {
        return empestEmployeeid;
    }

    public void setEmpestEmployeeid(String empestEmployeeid) {
        this.empestEmployeeid = empestEmployeeid;
    }

    public String getEmpestEmployeeshortname() {
        return empestEmployeeshortname;
    }

    public void setEmpestEmployeeshortname(String empestEmployeeshortname) {
        this.empestEmployeeshortname = empestEmployeeshortname;
    }

    public List<Sophoscapacitations> getSophoscapacitationsList() {
        return sophoscapacitationsList;
    }

    public void setSophoscapacitationsList(List<Sophoscapacitations> sophoscapacitationsList) {
        this.sophoscapacitationsList = sophoscapacitationsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empestId != null ? empestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sophosemployeestudent)) {
            return false;
        }
        Sophosemployeestudent other = (Sophosemployeestudent) object;
        if ((this.empestId == null && other.empestId != null) || (this.empestId != null && !this.empestId.equals(other.empestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.sophos.entities.Sophosemployeestudent[ empestId=" + empestId + " ]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
}
