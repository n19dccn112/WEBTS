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
    public void configure(WebSecurity web) throws Exception {
        // định dạng mẫu URL k bị phân quyền
        // assets - trang hình ảnh
        web.ignoring()
                .antMatchers(HttpMethod.GET,
                        "/api/auth/**",
                        "/api/v1/public**",
                        "/swagger-ui**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/api/categories**",
                        "/api/categories/**",
                        "/api/Mon**",
                        "/api/Mon/**");
        web.ignoring()
                .antMatchers(HttpMethod.POST,
                        "/api/auth/**",
                        "/api/v1/public**",
                        "/swagger-ui**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**");
    }

    @Override
    // cấu hình phân quyền
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,
                        "/api/auth/**",
                        "/api/v1/public**",
                        "/swagger-ui**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/api/Mon**",
                        "/api/Mon/**",
                        "/api/NguyenLieu**",
                        "/api/NguyenLieu/**",
                        "/api/NguoiDung**",
                        "/api/NguoiDung/**",
                        "/api/DonDatHang**",
                        "/api/DonDatHang/**",
                        "/api/TonKho**",
                        "/api/TonKho/**",
                        "/api/DoiTacVanChuyen/**",
                        "/api/DoiTacVanChuyen**",
                        "/api/ChiTietDonDatHang/**",
                        "/api/ChiTietDonDatHang**")
                .hasRole("NHANVIEN")
                .antMatchers(HttpMethod.GET,
                        "/api/auth/**",
                        "/api/v1/public**",
                        "/swagger-ui**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/api/Mon**",
                        "/api/Mon/**",
                        "/api/NguyenLieu**",
                        "/api/NguyenLieu/**",
                        "/api/NguoiDung**",
                        "/api/NguoiDung/**",
                        "/api/DonDatHang**",
                        "/api/DonDatHang/**",
                        "/api/TonKho**",
                        "/api/TonKho/**",
                        "/api/DoiTacVanChuyen/**",
                        "/api/DoiTacVanChuyen**",
                        "/api/ChiTietDonDatHang/**",
                        "/api/ChiTietDonDatHang**")
                .hasRole("NHANVIEN")
                .antMatchers(HttpMethod.DELETE,
                        "/api/v1/admin**",
                        "/api/Mon**",
                        "/api/Mon/**",
                        "/api/NguyenLieu**",
                        "/api/NguyenLieu/**",
                        "/api/NguoiDung**",
                        "/api/NguoiDung/**",
                        "/api/DonDatHang**",
                        "/api/DonDatHang/**",
                        "/api/TonKho**",
                        "/api/TonKho/**",
                        "/api/DoiTacVanChuyen/**",
                        "/api/DoiTacVanChuyen**",
                        "/api/ChiTietDonDatHang/**",
                        "/api/ChiTietDonDatHang**")
                .hasRole("NHANVIEN")
                .antMatchers(HttpMethod.PUT,
                        "/api/v1/admin**",
                        "/api/Mon**",
                        "/api/Mon/**",
                        "/api/NguyenLieu**",
                        "/api/NguyenLieu/**",
                        "/api/NguoiDung**",
                        "/api/NguoiDung/**",
                        "/api/DonDatHang**",
                        "/api/DonDatHang/**",
                        "/api/TonKho**",
                        "/api/TonKho/**",
                        "/api/DoiTacVanChuyen/**",
                        "/api/DoiTacVanChuyen**",
                        "/api/ChiTietDonDatHang/**",
                        "/api/ChiTietDonDatHang**")
                .hasRole("NHANVIEN")
                .antMatchers(HttpMethod.GET,
                        "api/mon**",
                        "/api/mon/**",
                        "/api/DonDatHang**",
                        "/api/DonDatHang/**",
                        "/api/ChiTietDonDatHang/**",
                        "/api/ChiTietDonDatHang**")
                .hasRole("KHACHHANG")
                .antMatchers(HttpMethod.POST,
                        "/api/orders**",
                        "/api/orders/**",
                        "/api/orderDetails/**",
                        "/api/orderDetails**")
                .hasRole("KHACHHANG")
                .antMatchers(HttpMethod.DELETE,
                        "/api/orders**",
                        "/api/orders/**",
                        "/api/orderDetails/**",
                        "/api/orderDetails**")
                .hasRole("KHACHHANG")
                .antMatchers(HttpMethod.PUT,
                        "/api/orders**",
                        "/api/orders/**",
                        "/api/orderDetails/**",
                        "/api/orderDetails**")
                .hasRole("KHACHHANG")
                .antMatchers(
                        "/api/v1/**")
                .hasAnyRole("KHACHHANG", "NHANVIEN")
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
