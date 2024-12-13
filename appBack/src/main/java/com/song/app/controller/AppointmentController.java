package com.song.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.song.app.entity.Appointment;
import com.song.app.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody Appointment appointment) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        appointment.setUserId(Long.parseLong(auth.getName()));
        
        if (appointmentService.createAppointment(appointment)) {
            return ResponseEntity.ok("预约创建成功");
        }
        return ResponseEntity.badRequest().body("预��创建失败");
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam Integer status) {
        if (appointmentService.updateStatus(id, status)) {
            return ResponseEntity.ok("状态更新成功");
        }
        return ResponseEntity.badRequest().body("状态更新失败");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long id) {
        if (appointmentService.cancelAppointment(id)) {
            return ResponseEntity.ok("预约取消成功");
        }
        return ResponseEntity.badRequest().body("预约取消失败");
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyAppointments(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(auth.getName());
        IPage<Appointment> appointments = appointmentService.getUserAppointments(userId, page, size);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllAppointments(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        IPage<Appointment> appointments = appointmentService.getAllAppointments(page, size);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointment(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        if (appointment != null) {
            return ResponseEntity.ok(appointment);
        }
        return ResponseEntity.notFound().build();
    }
} 