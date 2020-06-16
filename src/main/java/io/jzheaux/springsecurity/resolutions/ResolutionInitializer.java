package io.jzheaux.springsecurity.resolutions;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ResolutionInitializer implements SmartInitializingSingleton {
	private final ResolutionRepository resolutions;
	private final UserRepository users;
	private final PasswordEncoder passwordEncoder;

	public ResolutionInitializer(ResolutionRepository resolutions, UserRepository users, PasswordEncoder passwordEncoder) {
		this.resolutions = resolutions;
		this.users = users;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void afterSingletonsInstantiated() {
		this.resolutions.save(new Resolution("Read War and Peace", "user"));
		this.resolutions.save(new Resolution("Free Solo the Eiffel Tower", "user"));
		this.resolutions.save(new Resolution("Hang Christmas Lights", "user"));

		UUID userId= UUID.fromString("6c520944-af1c-11ea-b3de-0242ac130004");
		UUID readId=UUID.fromString("f2c82192-5ef8-41a8-9afe-704add279482");
		UUID writeId=UUID.fromString("2ef688b5-fca7-4491-a2fb-d798b68a5b85");

		User user = new User(userId, "user", passwordEncoder.encode("password"));
		user.grantAuthority("resolution:read");
		this.users.save(user);

		User hasread = new User(readId, "hasread", passwordEncoder.encode("password"));
		hasread.grantAuthority("resolution:read");
		this.users.save(hasread);

		User haswrite = new User(writeId, "haswrite", passwordEncoder.encode("password"));
		haswrite.grantAuthority("resolution:write");
		this.users.save(haswrite);
	}
}
