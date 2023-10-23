package com.backend.minifabrica3d.application.ports.driver.rest.authorization;

import com.backend.minifabrica3d.domain.model.User;

public interface AuthRestPort {

    User login(User user );
    User register( User user );

}
