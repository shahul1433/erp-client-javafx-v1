package erp.client.javafx.user;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class UserPersistDTO extends UserDTO{

    private String password;

    public UserPersistDTO() {
    }

    public  UserPersistDTO(UserDTO userDTO) {
        super(userDTO.getUserId(), userDTO.getName(), userDTO.getDesignation(), userDTO.getEmail(), userDTO.getPhone(),
                userDTO.getUsername(), userDTO.getRoles(), userDTO.getAddedDate(), userDTO.getModifiedDate(), userDTO.getArchive());
        this.password = null;
    }

    public UserPersistDTO(Long userId, String name, String designation, String email, String phone, String username, Set<UserRoleDTO> roles, LocalDateTime addedDate, LocalDateTime modifiedDate, Boolean archive, String password) {
        super(userId, name, designation, email, phone, username, roles, addedDate, modifiedDate, archive);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserPersistDTO{" +
                "password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPersistDTO)) return false;
        if (!super.equals(o)) return false;
        UserPersistDTO that = (UserPersistDTO) o;
        return getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPassword());
    }
}
