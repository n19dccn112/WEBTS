package com.nhom10.webts.security;

import com.nhom10.webts.security.jwt.JwtAuthEntryPoint;
import com.nhom10.webts.security.jwt.JwtAuthTokenFilter;
import com.nhom10.webts.security.jwt.JwtUtils;
import com.nhom10.webts.security.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtAuthEntryPoint unauthorizedHandler;

    private final JwtUtils jwtUtils;

    public WebSecurityConfig(
            UserDetailsServiceImpl userDetailsService,
            JwtAuthEntryPoint unauthorizedHandler,
            JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtUtils = jwtUtils;
    }
//2-2login
    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter(jwtUtils, userDetailsService);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {
        // TODO
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    // cấu hình phân quyền
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(
                        "/api/auth/**",
                        "/api/v1/public**",
                        "/swagger-ui**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**")
                .permitAll()
                .antMatchers(HttpMethod.GET,
                        "/api/DonDatHang**",
                        "/api/DonDatHang/**",
                        "/api/categories**",
                        "/api/categories/**",
                        "/api/Mon**",
                        "/api/Mon/**")
                .permitAll()
//                .antMatchers(HttpMethod.PUT,
//                        "/api/Mon/**")
//                .permitAll()
                .antMatchers(HttpMethod.POST,
                        "/api/Mon**",
                        "/api/NguyenLieu**",
                        "/api/NguoiDung**",
                        "/api/DonDatHang**",
                        "/api/TonKho**",
                        "/api/DoiTacVanChuyen**",
                        "/api/ChiTietDonDatHang**")
                .hasRole("NHANVIEN")
                .antMatchers(HttpMethod.GET,
                        "/api/NguyenLieu**",
                        "/api/NguyenLieu/**",
                        "/api/NguoiDung**",
                        "/api/NguoiDung/**",
                        "/api/TonKho**",
                        "/api/TonKho/**",
                        "/api/DoiTacVanChuyen/**",
                        "/api/DoiTacVanChuyen**",
                        "/api/ChiTietDonDatHang/**",
                        "/api/ChiTietDonDatHang**")
                .hasRole("NHANVIEN")
                .antMatchers(HttpMethod.DELETE,
                        "/api/Mon/**",
                        "/api/NguyenLieu/**",
                        "/api/NguoiDung/**",
                        "/api/DonDatHang/**",
                        "/api/TonKho/**",
                        "/api/DoiTacVanChuyen/**",
                        "/api/ChiTietDonDatHang/**")
                .hasRole("NHANVIEN")
                .antMatchers(HttpMethod.PUT,
                        "/api/Mon/**",
                        "/api/NguyenLieu/**",
                        "/api/NguoiDung/**",
                        "/api/DonDatHang/**",
                        "/api/TonKho/**",
                        "/api/DoiTacVanChuyen/**",
                        "/api/ChiTietDonDatHang/**")
                .hasRole("NHANVIEN")
                .antMatchers(HttpMethod.GET,
                        "/api/ChiTietDonDatHang/**",
                        "/api/ChiTietDonDatHang**")
                .hasRole("KHACHHANG")
                .antMatchers(HttpMethod.POST,
                        "/api/orders**",
                        "/api/orderDetails**")
                .hasRole("KHACHHANG")
                .antMatchers(HttpMethod.DELETE,
                        "/api/orders**",
                        "/api/orderDetails**")
                .hasRole("KHACHHANG")
                .antMatchers(HttpMethod.PUT,
                        "/api/orders**",
                        "/api/orderDetails**")
                .hasRole("KHACHHANG")
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
