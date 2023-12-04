//package com.movies.MoviesBackend.utility;
//
//import com.movies.MoviesBackend.entities.User;
//import com.movies.MoviesBackend.enums.Role;
//import com.movies.MoviesBackend.repository.RoleRepository;
//import com.movies.MoviesBackend.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Set;
//
//
//@Component
//public class Runner implements CommandLineRunner {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        if (roleRepository.findAll().isEmpty()) {
//
//            // USER ROLE
//            Role userRole = Role.USER;
//            //userRole.setName("USER");
//            roleRepository.save(userRole);
//
//            // ADMIN ROLE
//            Role adminRole = Role.ADMIN;
//            //adminRole.setName("ADMIN");
//            roleRepository.save(adminRole);
//
//            // super admin added
//            User admin = new User();
////            admin.setUsername("admin");
////            admin.setEnabled(true);
//            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
////            admin.setFirstName("Super");
////            admin.setLastName("Admin");
//            admin.setEmail("admin@dsinnovator.com");
//            //admin.setProfilePicPath("/images/profile/default.png");
//
//            Role role = roleRepository.findByName("ADMIN"); // admin role fetched
//            Set<Role> roles = new HashSet<>();
//            roles.add(role);
//            //admin.setRoles(roles);
//            userRepository.save(admin);
//
//        }
//    }
//}
