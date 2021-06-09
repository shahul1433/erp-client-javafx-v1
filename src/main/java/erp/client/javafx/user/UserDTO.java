package erp.client.javafx.user;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class UserDTO {

    private Long userId;
    private String name;
    private String designation;
    private String email;
    private String phone;
    private String username;
    private Set<UserRoleDTO> roles;
    private LocalDateTime addedDate;
    private LocalDateTime modifiedDate;
    private Boolean archive;

    public UserDTO() {
    }

    public UserDTO(Long userId, String name, String designation, String email, String phone, String username, Set<UserRoleDTO> roles, LocalDateTime addedDate, LocalDateTime modifiedDate, Boolean archive) {
        this.userId = userId;
        this.name = name;
        this.designation = designation;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.roles = roles;
        this.addedDate = addedDate;
        this.modifiedDate = modifiedDate;
        this.archive = archive;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<UserRoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoleDTO> roles) {
        this.roles = roles;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                ", addedDate=" + addedDate +
                ", modifiedDate=" + modifiedDate +
                ", archive=" + archive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return getUserId().equals(userDTO.getUserId()) && getName().equals(userDTO.getName()) && Objects.equals(getDesignation(), userDTO.getDesignation()) && Objects.equals(getEmail(), userDTO.getEmail()) && Objects.equals(getPhone(), userDTO.getPhone()) && getUsername().equals(userDTO.getUsername()) && getArchive().equals(userDTO.getArchive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getName(), getDesignation(), getEmail(), getPhone(), getUsername(), getArchive());
    }
}
