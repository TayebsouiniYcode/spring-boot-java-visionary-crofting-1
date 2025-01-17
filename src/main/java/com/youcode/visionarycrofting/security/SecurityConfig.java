    package com.youcode.visionarycrofting.security;

    import com.youcode.visionarycrofting.filter.CustomAuthenticationFilter;
    import com.youcode.visionarycrofting.filter.CustomAuthorizationFilter;
    import lombok.RequiredArgsConstructor;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    import static org.springframework.http.HttpMethod.GET;
    import static org.springframework.http.HttpMethod.POST;
    import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

    @Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
        private final UserDetailsService userDetailsService;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;

        @Override
        protected void configure ( AuthenticationManagerBuilder auth ) throws Exception {
            System.out.println ("this is configure method inside SecurityConfig with param auth" );
            auth.userDetailsService (  userDetailsService).passwordEncoder ( bCryptPasswordEncoder );
        }

        @Override
        protected void configure ( HttpSecurity http ) throws Exception {
            System.out.println ("this is configure in Security configure with http param" );
            CustomAuthenticationFilter customAuthenticationFilter =
                new CustomAuthenticationFilter(authenticationManagerBean ());

            customAuthenticationFilter.setFilterProcessesUrl ( "/api/login" );

            http.csrf ().disable ();
            http.sessionManagement ().sessionCreationPolicy ( STATELESS );
            http.authorizeRequests ().antMatchers ( "/api/v1/auth/login" ).permitAll ();
            http.authorizeRequests ().antMatchers ( "/api/login/**", "/").permitAll ();
            http.authorizeRequests ().antMatchers (GET, "/api/v1/user/**").hasAnyAuthority ( "ROLE_USER" );
            http.authorizeRequests ().antMatchers (POST, "/api/v1/user/save/**").hasAnyAuthority ( "ROLE_ADMIN" );
            http.authorizeRequests ().anyRequest ().authenticated ();
            http.addFilter ( customAuthenticationFilter  );
            http.addFilterBefore ( new CustomAuthorizationFilter (), UsernamePasswordAuthenticationFilter.class );
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean ();
        }
    }