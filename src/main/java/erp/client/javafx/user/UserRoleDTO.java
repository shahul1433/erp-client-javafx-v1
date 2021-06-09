package erp.client.javafx.user;

import java.util.Objects;

public class UserRoleDTO {

    private Long userRoleId;
    private String role;

    public UserRoleDTO() {
    }

    public UserRoleDTO(Long userRoleId, String role) {
        this.userRoleId = userRoleId;
        this.role = role;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRoleDTO{" +
                "userRoleId=" + userRoleId +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleDTO)) return false;
        UserRoleDTO that = (UserRoleDTO) o;
        return getUserRoleId().equals(that.getUserRoleId()) && getRole().equals(that.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserRoleId(), getRole());
    }
}
