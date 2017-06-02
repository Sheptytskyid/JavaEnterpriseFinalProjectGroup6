package net.greatstart.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "projects")
public class Project extends AbstractModel {

    @Embedded
    private ProjectDescription desc;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    private List<Investment> investments;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }

        Project project = (Project) obj;

        if (desc != null ? !desc.equals(project.desc) : project.desc != null) {
            return false;
        }
        if (!owner.equals(project.owner)) {
            return false;
        }
        return category != null ? category.equals(project.category) : project.category == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + owner.hashCode();
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
