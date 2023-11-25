    package com.zvaryyka.simpletaskmanager.config;

    import com.zvaryyka.simpletaskmanager.services.PersonDetailsService;
    import jdk.jshell.spi.ExecutionControl;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.NoOpPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class SecurityConfig {
        private final PersonDetailsService personDetailsService;

        @Autowired
        public SecurityConfig(PersonDetailsService personDetailsService) {
            this.personDetailsService = personDetailsService;
        }
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            //TODO настройка конфигурации безопасности
            http
                    .authorizeHttpRequests((requests) -> requests
                            .requestMatchers( "/index","/registration").permitAll()
                            .anyRequest().authenticated()
                    )
                    .formLogin((form) -> form
                            .loginPage("/login").loginProcessingUrl("/process_login").defaultSuccessUrl("/task", true)
                            .permitAll()
                    )
                    .logout((logout) -> logout
                            .logoutUrl("/logout") // Указание URL для выхода из системы
                            .logoutSuccessUrl("/login") // Указание URL для перенаправления после успешного выхода
                            .permitAll()
                    );
            //TODO logout не работает, надо решить проблему

            return http.build();
        }

        // Настраиваем аутентификацию

        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(personDetailsService)
                    .passwordEncoder(getPasswordEncoder());
        }
        @Bean
        public PasswordEncoder getPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

    }
