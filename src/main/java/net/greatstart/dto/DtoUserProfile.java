package net.greatstart.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class DtoUserProfile {
    private Long id;
    @NotNull
    @Size(min = 2, max = 35, message = "{name.size}")
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String lastName;
    private byte[] photo;
    private List<DtoInvestment> dtoInvestments;

    @JsonSerialize
    public String initial() {
        StringBuilder initials = new StringBuilder();
        if (lastName != null && !lastName.isEmpty()) {
            initials.append(name.substring(0, 1).toUpperCase())
                    .append(".")
                    .append(lastName.substring(0, 1).toUpperCase())
                    .append(".");
        } else {
            initials.append(name.substring(0, 1).toUpperCase());
        }
        return initials.toString();
    }

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

        DtoUserProfile that = (DtoUserProfile) obj;

        if (!id.equals(that.id)) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (address != null ? !address.equals(that.address) : that.address != null) {
            return false;
        }
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) {
            return false;
        }
        if (!email.equals(that.email)) {
            return false;
        }
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + email.hashCode();
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
