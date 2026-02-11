package teccr.justdoitcloud.repository;

import org.springframework.stereotype.Repository;
import teccr.justdoitcloud.data.User;

import java.util.Optional;

/**
 * Simple in-memory repository implementation for initial testing.
 * This is a temporary implementation until a persistence layer is added.
 */
@Repository
public class UserRepositoryInMemoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            return Optional.empty();
        }
        String trimmedUserName = userName.trim();
        User user = new User(trimmedUserName, toDisplayName(trimmedUserName), toEmail(trimmedUserName), User.Type.REGULAR);
        return Optional.of(user);
    }

    private String toDisplayName(String userName) {
        // Simple logic to convert username to display name (e.g., "christine_mcvie" -> "Christine Mcvie")
        String[] parts = userName.split("_");
        StringBuilder displayName = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                displayName.append(Character.toUpperCase(part.charAt(0)))
                        .append(part.substring(1))
                        .append(" ");
            }
        }
        return displayName.toString().trim();
    }

    private String toEmail(String userName) {
        // Simple logic to convert username to email (e.g., "christine_mcvie" -> "christine_mcvie@gmail.com")
        return userName + "@gmail.com";
    }
}
